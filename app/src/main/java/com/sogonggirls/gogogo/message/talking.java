package com.sogonggirls.gogogo.message;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sogonggirls.gogogo.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class talking extends AppCompatActivity {
    //출처: https://javapp.tistory.com/151 [Don't Quit ! DOIT 포기하지 말자]

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    private String chatRoomUid; //채팅방 하나 id
    private String myuid; //나의 id
    private String destUid; //상대방 uid

    private Button button;
    private EditText editText;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy.MM.dd HH:mm");
    private User destUser;

    private String TAG = "토킹";

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;

    private List<ChatModel> chatlist;
    private String nick = "nick1"; //테스트용

//    @Override
//    public void onBackPressed(){
//        Intent intentcc = new(talking.this, MessageMain.class);
//        intentcc.setFlags(Intentcc.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
//        | Intentcc.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intentcc);
//        overridePendingTransition(R.anim.in_left, R.anim.out_right);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talking);

        myuid = FirebaseAuth.getInstance().getCurrentUser().getUid(); // 채팅 보내는 사람
        destUid = getIntent().getStringExtra("destUid"); //채팅 상대

        mRecyclerView = (RecyclerView)findViewById(R.id.talking_list);
        button = (Button)findViewById(R.id.talking_submit);
        editText = (EditText)findViewById(R.id.talking_edt);

        if(editText.getText().toString() == null )button.setEnabled(false);
        else button.setEnabled(true);

        checkChatRoom();
        sendTalk();
    }//onCreate


    //chatRoomUid (채팅방 아이디, 첫 대화일때 chatRoomUid 은 NULL 이다.)
    //메시지 전송시 chatRoomUid 가 null 일때 채팅방 생성한다.
    private void sendTalk(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatModel chatModel = new ChatModel();
                chatModel.users.put(myuid, true);
                chatModel.users.put(destUid, true);

                if(chatRoomUid == null){
                    Toast.makeText(talking.this, "채팅방 생성", Toast.LENGTH_SHORT).show();
                    button.setEnabled(false);
                    firebaseDatabase.getReference().child("chatrooms").push()
                            .setValue(chatModel)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override public void onSuccess(Void aVoid) {
                                    checkChatRoom();
                                }
                            });

                }else{
                    sendMsgToDataBase();
                }

            }
        });
    }

    //채팅방들 중 자신이 있는 지 확인하고 자신이 있으면 채팅할 상대방 id가 포함돼 있을때 채팅방 key를 가져와 저장한다.
    private void checkChatRoom() {
        //자신 key == true 일때 chatModel 가져온다.

        firebaseDatabase.getReference().child("chatrooms")
                .orderByChild("users/" + myuid)
                .equalTo(true)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            ChatModel chatModel = dataSnapshot.getValue(ChatModel.class);
                            if (chatModel.users.containsKey(destUid)) {
                                //상대방 id 포함돼 있을때 채팅방 key 가져옴
                                chatRoomUid = dataSnapshot.getKey();
                                button.setEnabled(true); //동기화
                                mRecyclerView.setLayoutManager(new LinearLayoutManager(talking.this));
                                mRecyclerView.setAdapter(new ChatAdapter()); //메시지 보내기
                                sendMsgToDataBase();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
    }

    //작성한 메시지를 데이터베이스에 보낸다.
    private void sendMsgToDataBase() {
        if(!editText.getText().toString().equals("")) {
            ChatModel.Comment comment = new ChatModel.Comment();
            comment.uid = myuid;
            comment.message = editText.getText().toString();
            comment.timestamp = ServerValue.TIMESTAMP;
            firebaseDatabase.getReference().child("chatrooms").child(chatRoomUid).child("comments")
                    .push().setValue(comment)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            editText.setText("");
                        }
                    });
        }
    }



    public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
        private List<ChatModel.Comment> comments;


        public ChatAdapter(){
            comments = new ArrayList<>();
            getDestUid();
        }
        private void getDestUid(){
            FirebaseDatabase.getInstance().getReference().child("users").child(destUid).addListenerForSingleValueEvent(new ValueEventListener(){
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot){
                    destUser = snapshot.getValue(User.class);
                    getMessageList();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error){

                }
            });
        }
        //채팅 내용 읽어들임
        private void getMessageList() {
            FirebaseDatabase.getInstance().getReference().child("chatrooms").child(chatRoomUid).child("comments")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            comments.clear();
                            for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                comments.add(dataSnapshot.getValue(ChatModel.Comment.class));
                            }
                            notifyDataSetChanged();
                            mRecyclerView.scrollToPosition(comments.size()-1);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item_you,parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ViewHolder viewHolder = ((ViewHolder)holder);
            if(comments.get(position).uid.equals(myuid)) {//나의 uid 이면
                //나의 말풍선 오른쪽으로
                viewHolder.textViewMsg.setText(comments.get(position).message);
                viewHolder.textViewMsg.setBackgroundResource(R.drawable.mybubble);
//                viewHolder.linearLayoutDest.setVisibility(View.INVISIBLE);
                //상대방 레이아웃
                viewHolder.linearLayoutRoot.setGravity(Gravity.RIGHT);
                viewHolder.linearLayoutTime.setGravity(Gravity.RIGHT);
            }else{
                //상대방 말풍선 왼쪽
//                viewHolder.textViewName.setText(destUser.name);
                Log.e(TAG, destUser + "");
                viewHolder.textViewName.setText("2번친구");
//                viewHolder.linearLayoutDest.setVisibility(View.VISIBLE);
                viewHolder.textViewMsg.setBackgroundResource(R.drawable.yoububble);
                viewHolder.textViewMsg.setText(comments.get(position).message);
                viewHolder.linearLayoutRoot.setGravity(Gravity.LEFT);
                viewHolder.linearLayoutTime.setGravity(Gravity.LEFT);
            }
            long unixTime = (long) comments.get(position).timestamp;
            Date date = new Date(unixTime);
//            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            String time = simpleDateFormat.format(date);
            viewHolder.textViewTimeStamp.setText(time);
        }


        @Override public int getItemCount() {
            return comments.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder {
            public TextView textViewMsg; //메시지 내용
            public TextView textViewName;
            public TextView textViewTimeStamp;

            public LinearLayout linearLayoutDest;
            public LinearLayout linearLayoutRoot;
            public LinearLayout linearLayoutTime;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewMsg = (TextView)itemView.findViewById(R.id.item_messagebox_textview_msg);
                textViewName = (TextView)itemView.findViewById(R.id.item_messagebox_TextView_name);
                textViewTimeStamp = (TextView)itemView.findViewById(R.id.item_messagebox_textview_timestamp);

                linearLayoutRoot = (LinearLayout)itemView.findViewById(R.id.item_messagebox_root);
                linearLayoutTime = (LinearLayout)itemView.findViewById(R.id.item_messagebox_layout_timestamp);
            }
        }
    }

}
