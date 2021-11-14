package com.example.gogogo.message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gogogo.R;
import com.example.gogogo.login.FirebaseID;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MessageMain extends AppCompatActivity {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy.MM.dd HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_main);

        TextView toolbar_title2 = findViewById(R.id.toolbar_title2);
        ImageView back2 = findViewById(R.id.back2);

        RecyclerView recyclerView = findViewById(R.id.message_list);
        recyclerView.setAdapter(new ChatRecyclerViewAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        toolbar_title2.setText("채팅방 목록");

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    class ChatRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private List<ChatModel> chatModels = new ArrayList<>();
        private String uid;
        private ArrayList<String> destinationUsers = new ArrayList<>();
        public ChatRecyclerViewAdapter() {
            uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            FirebaseDatabase.getInstance().getReference().child("chatrooms").orderByChild("users/"+uid)
                    .equalTo(true)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            chatModels.clear();

                            for(DataSnapshot item : dataSnapshot.getChildren() ){
                                chatModels.add(item.getValue(ChatModel.class));
                            }
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_list_item,parent,false);

            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            CustomViewHolder customViewHolder = (CustomViewHolder) holder;
            String destUid = null;

            //챗방에 있는 유저를 ㅇ리일이 체크
            for(String user: chatModels.get(position).users.keySet()){
                if(!user.equals(uid)){
                    Log.e("헤이마마", user);
                    destUid = user;
                    destinationUsers.add(destUid);
                }
            }
            FirebaseDatabase.getInstance().getReference().child("users").child(destUid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    UserModel userModel = snapshot.getValue(UserModel.class);
                    Log.e("헤이마마", String.valueOf(snapshot.getValue()));
                    //customViewHolder.dest_name.setText(userModel.name);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            Map<String, ChatModel.Comment> commentMap = new TreeMap<>(Collections.reverseOrder());
            commentMap.putAll(chatModels.get(position).comments);
            String lastMkey = (String) commentMap.keySet().toArray()[0];
            customViewHolder.lastmsg.setText(chatModels.get(position).comments.get(lastMkey).message);
            String time = simpleDateFormat.format( chatModels.get(position).comments.get(lastMkey).timestamp);
            customViewHolder.lasttime.setText(time);

            customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), talking.class);
                    intent.putExtra("destUid", destinationUsers.get(position) );
//                    ActivityOptions activityOptions = ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.fromright);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return chatModels.size();
        }
    }

    private class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView dest_name;
        public TextView lastmsg;
        public TextView lasttime;
        public CustomViewHolder(View view) {
            super(view);

            dest_name = view.findViewById(R.id.item_chat_tv_title);
            lastmsg = view.findViewById(R.id.item_chat_tv_comment);
            lasttime = view.findViewById(R.id.item_chat_tv_timestamp);
        }
    }
}