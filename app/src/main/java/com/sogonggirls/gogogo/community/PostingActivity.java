package com.sogonggirls.gogogo.community;

import static com.sogonggirls.gogogo.community.PostAdapter.getTimestampToDate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sogonggirls.gogogo.R;
import com.sogonggirls.gogogo.login.FirebaseID;
import com.sogonggirls.gogogo.message.talking;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostingActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private List<ReplyItem> mDatas;
    private ReplyAdapter mAdapter;
    private ListView mReplyView;

    private String doc_id, ID_COLLECTION;
    private TextView title, nickname, date, contents;
    private Integer comu_id;

    private String TAG = "포스팅 상세페이지";

    private EditText edt_comment;
    private Button submit_comment;
    private Button go_chat;

    private String destId;

    String reply_nicks;
    TextView toolbar_title2;
    ImageView button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);

        title = findViewById(R.id.title_posting);
        nickname = findViewById(R.id.nickname_posting);
        date = findViewById(R.id.date_posting);
        contents = findViewById(R.id.contents_posting);

        edt_comment = findViewById(R.id.edt_comment);
        submit_comment = findViewById(R.id.submit_comment);
        mReplyView = findViewById(R.id.post_comment_list);

        toolbar_title2 = findViewById(R.id.toolbar_title2);
        toolbar_title2.setText("커뮤니티");

        button2 = findViewById(R.id.back2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });

        go_chat = findViewById(R.id.go_chat);
        mDatas = new ArrayList<>();

        Intent intent = getIntent();
        doc_id = intent.getStringExtra("doc_id");
        comu_id = intent.getIntExtra("comu_id", 1);
        Log.v(TAG, doc_id);

        if(mAuth.getCurrentUser() != null) {
            mStore.collection(FirebaseID.user).document(mAuth.getCurrentUser().getUid())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.getResult() != null) {
                                reply_nicks = (String) task.getResult().getData().get(FirebaseID.nickname);
                                Log.e("댓글용 작성자", reply_nicks);
                            }
                        }
                    });
        }

        //내용 가져오기
        if(comu_id == 2){
            ID_COLLECTION = FirebaseID.eatingmate;
        }else if(comu_id == 3){
            ID_COLLECTION = FirebaseID.tips;
        }else{
            ID_COLLECTION = FirebaseID.post;
        }

        mStore.collection(ID_COLLECTION).document(doc_id)
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

                        date_p = date_p.replace("Timestamp(seconds=", "").replace(" nanoseconds=", "").replace(")", "");
                        String[] array = date_p.split(",");
                        date_p = array[0] ;

                        String nickname_p = String.valueOf(shot.get(FirebaseID.nickname));
                        String title_p = String.valueOf(shot.get(FirebaseID.title));
                        String contents_p = String.valueOf(shot.get(FirebaseID.contents));
                        destId = String.valueOf(shot.get(FirebaseID.documentId));

                        title.setText(title_p);
                        nickname.setText(nickname_p);
                        date.setText(getTimestampToDate(date_p));
                        contents.setText(contents_p);

                    } else {
                        Log.d(TAG, "No such document");
                        contents.setText("일시적인 오류입니다.");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        //댓글작성
        submit_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //댓글 내용 가져오기
                String reply_contents = edt_comment.getText().toString();
                //게시판에 작성자 넣기위한 닉네임 가져오기

                if (reply_nicks == null & reply_contents == null) {
                    Toast.makeText(getApplicationContext(), "로그인 하시거나 내용을 작성해주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    String replyId = mStore.collection(ID_COLLECTION).document(doc_id).collection(FirebaseID.reply).document().getId();
                    Log.e("댓글", replyId);
                    Map<String, Object> data = new HashMap<>();
                    data.put(FirebaseID.documentId, mAuth.getCurrentUser().getUid()); //작성자 아이디
                    data.put(FirebaseID.reply_contents, reply_contents); //댓글내용
                    data.put(FirebaseID.nickname, reply_nicks);//작성자 닉네임
                    data.put(FirebaseID.timestamp, FieldValue.serverTimestamp()); // 시간
                    mStore.collection(ID_COLLECTION).document(doc_id).collection(FirebaseID.reply).document(replyId).set(data, SetOptions.merge());
                }

            }
        });

        go_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), talking.class);
                intent.putExtra("destUid", destId );
                Log.e(TAG, destId);
                startActivity(intent);
            }
        });
    }//onCreate

    @Override
    public void onStart() {
        super.onStart();
        mStore.collection(ID_COLLECTION).document(doc_id).collection(FirebaseID.reply)
                .orderBy(FirebaseID.timestamp, Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() { //실시간 정렬/단점 새로안불러오고 추가로 불러온다.
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                        if (queryDocumentSnapshots != null) {
                            mDatas.clear();//단점 해결용 초기화
                            for (DocumentSnapshot snap : queryDocumentSnapshots.getDocuments()) {
                                Map<String, Object> shot = snap.getData();
                                String replyId = snap.getId();
                                String documentId = String.valueOf(shot.get(FirebaseID.documentId));
                                String nickname = String.valueOf(shot.get(FirebaseID.nickname));
                                String contents = String.valueOf(shot.get(FirebaseID.reply_contents));
                                String reply_date = String.valueOf(shot.get(FirebaseID.timestamp));

                                reply_date = reply_date.replace("Timestamp(seconds=", "").replace(" nanoseconds=", "").replace(")", "");
                                String[] array = reply_date.split(",");
                                String tai = array[0] ;

                                ReplyItem data = new ReplyItem(documentId, contents, nickname, replyId, tai);
                                mDatas.add(data);
                            }
                            mAdapter = new ReplyAdapter(mDatas);
                            mReplyView.setAdapter((ListAdapter) mAdapter);
                        }

                    }
                });
    }

}