package com.example.gogogo.message;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gogogo.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import org.w3c.dom.Comment;

import java.util.HashMap;
import java.util.Map;

public class talking extends AppCompatActivity {
    //출처: https://javapp.tistory.com/151 [Don't Quit ! DOIT 포기하지 말자]

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private String chatRoomUid; //채팅방 하나 id
    private String myuid; //나의 id
    private String destUid; //상대방 uid

    private Button button;
    private EditText editText;


    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy.MM.dd HH:mm");

    private String TAG = "토킹";
    private Button button;
    private EditText editText;

    private talkAdapter mAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talking);

        init();
        sendMsg();



        //토킹하는 곳
        //토킹 불러오기
        //톡 보내기
    }//onCreate

    private void init(){
        myuid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        destUid = getIntent().getStringExtra("destUid"); //채팅 상대

        recyclerView = (RecyclerView)findViewById(R.id.talking_list);
        editText = findViewById(R.id.talking_edt);
        button = findViewById(R.id.talking_submit);

        if(editText.getText().toString() == null) button.setEnabled(false);
        else button.setEnabled(true);

        checkChatRoom();
    }


    public class ChatModel {
        public Map<String,Boolean> users = new HashMap<>(); //채팅방 유저
        public Map<String, Comment> comments = new HashMap<>(); //채팅 메시지

        public static class Comment {
            public String uid;
            public String message;
            public Object timestamp;
        }
    }


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
                    button.Enabled(false);
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
        /* chatModel
        public Map<String,Boolean> users = new HashMap<>(); //채팅방 유저
        public Map<String, ChatModel.Comment> comments = new HashMap<>(); //채팅 메시지
        */
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
                                recyclerView.setLayoutManager(new LinearLayoutManager(MessageActivity.this));
                                recyclerView.setAdapter(new RecyclerViewAdapter()); //메시지 보내기
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
                        recyclerView.scrollToPosition(comments.size()-1);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }



}
