package com.example.gogogo.storeInfo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.gogogo.DatabaseHelper;
import com.example.gogogo.R;

public class DeliveryDetail extends AppCompatActivity {
    String TAG = "store 상세정보";
    SQLiteDatabase db;
    StoreListAdapter apdater;
    String id;
    ListView lvProcess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_detail);

//        Button button = findViewById(R.id.return1);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.putExtra("name", "mike");
//                setResult(RESULT_OK, intent);
//                finish();
//            }
//        });

        // View return1 = findViewById(R.id.return1);   // 뒤로가기 버튼

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        apdater = new StoreListAdapter(getApplicationContext());

        TextView storeName = (TextView)findViewById(R.id.storeName);
        TextView storeGrade = (TextView)findViewById(R.id.storeGrade);
        TextView storeMenu = (TextView)findViewById(R.id.storeMenu);
        TextView storeTel = (TextView)findViewById(R.id.storeTel);
        TextView storeAddress = (TextView)findViewById(R.id.storeAddress);
        ImageView storeImage = (ImageView)findViewById(R.id.imageView);

        RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar);

        //db 오픈
        if (db == null){
            DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
            db = helper.getWritableDatabase();
            if (db != null) {
                Log.v(TAG, "DB열기 성공!");
            } else {
                Log.e(TAG, "DB열기 실패!");
            }
        }

        //db 값 불러오기
        if (db != null) {
            String table1 = "delivery_data";
            String sql = "select name, tel, menu, degree, address, photo from "+table1+" where id = "+id;
            Cursor cursor = db.rawQuery(sql, null);
            Log.v(TAG, "조회된 데이터 수 : " + cursor.getCount());

            cursor.moveToNext();
            storeName.setText(cursor.getString(0));
            storeTel.setText(cursor.getString(1));
            storeMenu.setText(cursor.getString(2));
            storeGrade.setText(cursor.getString(3));
            storeAddress.setText(cursor.getString(4));


            // 평점에 따라 별 개수 표시
            ratingBar.setRating(Float.parseFloat(cursor.getString(3)));

            // Glide로 이미지 표시하기
            String imageUrl = cursor.getString(5);
            Log.v(TAG, imageUrl);
            Glide.with(this).load(imageUrl).error(R.drawable.no_image_2).into(storeImage);

            cursor.close();
        }
        else {
            Log.e(TAG, "selectData() db없음.");
        }

//        // back 버튼 구현
//        return1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
    }
}
