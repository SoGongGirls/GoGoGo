package com.example.gogogo.storeInfo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.example.gogogo.DatabaseHelper;
import com.example.gogogo.R;

import java.util.Collections;
import java.util.Comparator;

public class StoreList extends Fragment {
    SQLiteDatabase database;
    ListView storeList;
    StoreListAdapter adapter;
    public static final String TAG ="TAG StoreList.java";


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
            String query = "select name, degree, id, logo, latitude, longitude from "+tableName;
            Cursor cursor = database.rawQuery(query, null);
            Log.v(TAG, "조회된 데이터 수 : " + cursor.getCount());

            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                String name = cursor.getString(0);
                String degree = cursor.getString(1);
                int id = cursor.getInt(2);
                String logo = cursor.getString(3);
                double latitude = cursor.getDouble(4);
                double longitude = cursor.getDouble(5);

                adapter.addItem(new StoreItem(name, degree, id, logo, latitude, longitude));
            }
            cursor.close();
        } else {
            Log.e(TAG, "selectData() db없음.");
        }
        storeList.setAdapter(adapter);


        // 리스트 정렬 기능
        Button button3 = (Button) view.findViewById(R.id.button3);   // 추천순
        Button button4 = (Button) view.findViewById(R.id.button4);   // 거리순
        Button button5 = (Button) view.findViewById(R.id.button5);   // 기본순

        button5.setSelected(true);   // 기본순 버튼 눌린 상태로 유지

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button5.setSelected(false);
                button4.setSelected(false);
                button3.setSelected(true);
                Comparator<StoreItem> gradeDesc = new Comparator<StoreItem>() {
                    @Override
                    public int compare(StoreItem o1, StoreItem o2) {
                        int ret = 0;

                        if (o1.getStoreGrade().compareTo(o2.getStoreGrade()) < 0)
                            ret = 1;
                        else if (o1.getStoreGrade().compareTo(o2.getStoreGrade()) == 0)
                            ret = 0;
                        else
                            ret = -1;

                        return ret;
                    }
                };

                Collections.sort(StoreListAdapter.items, gradeDesc);
                adapter.notifyDataSetChanged();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button5.setSelected(false);
                button4.setSelected(true);
                button3.setSelected(false);
                Comparator<StoreItem> distanceAsc = new Comparator<StoreItem>() {
                    @Override
                    public int compare(StoreItem o1, StoreItem o2) {
                        int ret = 0;

                        if (o1.getStoreDistance() < o2.getStoreDistance())
                            ret = -1;
                        else if (o1.getStoreDistance() == o2.getStoreDistance())
                            ret = 0;
                        else
                            ret = 1;

                        return ret;
                    }
                };

                Collections.sort(StoreListAdapter.items, distanceAsc);
                adapter.notifyDataSetChanged();
            }
        });


        // 가게 정보 상세보기
        storeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), StoreDetail.class);
                StoreItem item = (StoreItem) adapter.getItem(i);
                String id = String.valueOf(item.getStoreId());
                Log.v(TAG, "id는" + id);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        return view;

    }

//    @Override
//    public void onListItemClick (ListView l, View v, int position, long id) {
//        StoreItem item = (StoreItem) l.getItemAtPosition(position) ;
//
//        String nameStr = item.getStoreName();
//        String gradeStr = item.getStoreGrade();
////        double distanceStr = item.getStoreDistance();
//        String logoStr = item.getStoreLogo();
//
//    }

//    public void addItem(Drawable image, String name, String grade, String distance) {
//        adapter.addItem(image, name, grade, distance);
//    }

    public void openDB() {
        Log.v(TAG, "openDB() 실행");
        DatabaseHelper helper = new DatabaseHelper(getContext());
        database = helper.getWritableDatabase();


        //Log.v(Double.toString(myLatitude), "위도");

        if (database != null) {
            Log.v(TAG, "DB 열기 성공!");
        } else {
            Log.e(TAG, "DB 열기 실패!");
        }
    }
}