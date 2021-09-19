package com.example.gogogo.community;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gogogo.R;

public class Community_insert1 extends AppCompatActivity {

    TextView toolbar_title;
    ImageView close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_insert1);

        /* 객체 초기화 */
        toolbar_title = findViewById(R.id.toolbar_title);
        close = findViewById(R.id.close);

        toolbar_title.setText("자유 게시판");  // 제목 설정

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

   }// onCreate
}