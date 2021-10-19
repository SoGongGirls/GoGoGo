package com.example.gogogo.community;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.gogogo.R;
import com.example.gogogo.login.FirebaseID;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class PostingActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private String doc_id;
    private TextView title, nickname, date, contents;

    private String TAG = "포스팅 상세페이지";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);

        title = findViewById(R.id.title_posting);
        nickname = findViewById(R.id.nickname_posting);
        date = findViewById(R.id.date_posting);
        contents = findViewById(R.id.contents_posting);

        Intent intent = getIntent();
        doc_id = intent.getStringExtra("doc_id");
        Log.v(TAG, doc_id);

        mStore.collection("FirebaseID.post").document(doc_id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        Map<String, Object> shot = document.getData();
                        String date_p = String.valueOf(shot.get(FirebaseID.timestamp));
                        String nickname_p = String.valueOf(shot.get(FirebaseID.nickname));
                        String title_p = String.valueOf(shot.get(FirebaseID.title));
                        String contents_p = String.valueOf(shot.get(FirebaseID.contents));

                        title.setText(title_p);
                        nickname.setText(nickname_p);
                        date.setText(date_p);
                        contents.setText(contents_p);

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }//onCreate

}