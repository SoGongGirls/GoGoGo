package com.example.gogogo.storeInfo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gogogo.R;

public class StoreDetail extends AppCompatActivity {
    String TAG = "store 상세정보";
    SQLiteDatabase db;
    StoreListAdapter apdater;
    String id;
    ListView lvProcess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);

        View return1 = findViewById(R.id.return1);   // 뒤로가기 버튼

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        apdater = new StoreListAdapter(getApplicationContext());

        TextView storeName = (TextView)findViewById(R.id.storeName);
        TextView storeGrade = (TextView)findViewById(R.id.storeGrade);
        TextView storeMenu = (TextView)findViewById(R.id.storeMenu);
        TextView storeTel = (TextView)findViewById(R.id.storeTel);
        TextView storeAddress = (TextView)findViewById(R.id.storeAddress);

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
            String table1 = "store_data";
            String sql = "select name, tel, menu, degree, address from "+table1+" where id = "+id;
            Cursor cursor = db.rawQuery(sql, null);
            Log.v(TAG, "조회된 데이터 수 : " + cursor.getCount());

            cursor.moveToNext();
            storeName.setText(cursor.getString(0));
            storeTel.setText(cursor.getString(1));
            storeMenu.setText(cursor.getString(2));
            storeGrade.setText(cursor.getString(3));
            storeAddress.setText(cursor.getString(4));

//            // Glide로 이미지 표시하기
//            String imageUrl = cursor.getString(9);
//            Log.v(TAG, imageUrl);
//            Glide.with(this).load(imageUrl).error(R.drawable.test_food).into(recipe_image);

            cursor.close();
        }
        else {
            Log.e(TAG, "selectData() db없음.");
        }

        // back 버튼 구현
        return1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
