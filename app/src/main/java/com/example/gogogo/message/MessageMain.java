package com.example.gogogo.message;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.gogogo.R;
import com.example.gogogo.login.FirebaseID;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MessageMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_main);

        //채팅 리스트가 있을 곳
        //채팅목록불러오기
        //채팅 클릭하면 그 채팅으로 이동

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(FirebaseID.message);

        myRef.setValue("Hello, World!");
    }
}