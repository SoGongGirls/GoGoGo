package com.sogonggirls.gogogo.community;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sogonggirls.gogogo.R;
import com.sogonggirls.gogogo.login.FirebaseID;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class Community_insert1 extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private EditText mTitle, mContents;
    private String nickname;
    private Button submit;

    TextView toolbar_title;
    ImageView close;
    String toolbartitle ="자유 게시판";
    String community_id = FirebaseID.post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_insert1);

        Intent i = getIntent();
        Integer num = i.getIntExtra("communitynumber", 1);


        /* 객체 초기화 */
        toolbar_title = findViewById(R.id.toolbar_title);
        close = findViewById(R.id.close);

        submit = findViewById(R.id.submit);
        mTitle = findViewById(R.id.insert_title);
        mContents = findViewById(R.id.insert_content);

        // 제목 설정
        if (num == 2){
            toolbartitle = "밥동무 찾기";
        }else if(num == 3){
            toolbartitle = "자취 꿀팁";
        }
        toolbar_title.setText(toolbartitle);
        //엑스버튼
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //게시판에 작성자 넣기위한 닉네임 가져오기
        if(mAuth.getCurrentUser() != null){
            mStore.collection(FirebaseID.user).document(mAuth.getCurrentUser().getUid())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.getResult() != null){
                                nickname = (String) task.getResult().getData().get(FirebaseID.nickname);
                            }
                        }
                    });
        }

        //제출버튼
        if (num == 2){
            community_id = FirebaseID.eatingmate;
        }else if(num == 3){
            community_id = FirebaseID.tips;
        }
        Log.e("커뮤니티 아이디 확인", community_id);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAuth.getCurrentUser() != null){
                    String postId = mStore.collection(community_id).document().getId();
                    Map<String, Object> data = new HashMap<>();
                    data.put(FirebaseID.documentId, mAuth.getCurrentUser().getUid());
                    data.put(FirebaseID.title,mTitle.getText().toString());
                    data.put(FirebaseID.nickname, nickname);
                    data.put(FirebaseID.contents, mContents.getText().toString());
                    data.put(FirebaseID.timestamp, FieldValue.serverTimestamp());
                    mStore.collection(community_id).document(postId).set(data, SetOptions.merge());
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "로그인 해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });



   }// onCreate
}