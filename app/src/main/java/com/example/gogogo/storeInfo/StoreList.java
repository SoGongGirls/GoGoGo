package com.example.gogogo.storeInfo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.example.gogogo.R;

public class StoreList extends ListFragment {
    SQLiteDatabase database;
    ListView storeList;
    StoreListAdapter adapter;
    public static final String TAG ="TAG StoreList.java" ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.store_list, container, false);
        // 초기화, 참조 및 생성
        storeList = (ListView) view.findViewById(android.R.id.list);
        openDB();

        // 리스트뷰 참조 및 Adapter 연결
        adapter = new StoreListAdapter(getActivity());

        //맨처음 초기화 데이터 보여주기(select)
        if (database != null) {
            String tableName = "store_data";
            String query = "select name, degree, id, logo from "+tableName;
            Cursor cursor = database.rawQuery(query, null);
            Log.v(TAG, "조회된 데이터 수 : " + cursor.getCount());

            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                String name = cursor.getString(0);
                String degree = cursor.getString(1);
                int id = cursor.getInt(2);
                String logo = cursor.getString(3);

                adapter.addItem(new StoreItem(name, degree, id, logo));
            }
            cursor.close();
        } else {
            Log.e(TAG, "selectData() db없음.");
        }
        storeList.setAdapter(adapter);

        return view;

    }

    @Override
    public void onListItemClick (ListView l, View v, int position, long id) {
        StoreItem item = (StoreItem) l.getItemAtPosition(position) ;

        String nameStr = item.getStoreName();
        String gradeStr = item.getStoreGrade();
        String distanceStr = item.getStoreDistance();
        String logoStr = item.getStoreLogo();

    }

    public void openDB() {
        Log.v(TAG, "openDB() 실행");
        DatabaseHelper helper = new DatabaseHelper(getContext());
        database = helper.getWritableDatabase();

        if (database != null) {
            Log.v(TAG, "DB 열기 성공!");
        } else {
            Log.e(TAG, "DB 열기 실패!");
        }
    }
}