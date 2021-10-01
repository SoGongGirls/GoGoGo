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

import androidx.fragment.app.ListFragment;

import com.example.gogogo.DatabaseHelper;
import com.example.gogogo.R;

import java.util.Collections;
import java.util.Comparator;

public class DeliveryList extends ListFragment {
    SQLiteDatabase database;
    ListView deliveryList;
    DeliveryListAdapter adapter;
    public static final String TAG ="TAG DeliveryList.java";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.delivery_list, container, false);
        // 초기화, 참조 및 생성
        deliveryList = (ListView) view.findViewById(android.R.id.list);
        openDB();

        // 리스트뷰 참조 및 Adapter 연결
        adapter = new DeliveryListAdapter(getActivity());

        //맨처음 초기화 데이터 보여주기(select)
        if (database != null) {
            String tableName = "delivery_data";
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

                adapter.addItem(new DeliveryItem(name, degree, id, logo, latitude, longitude));
            }
            cursor.close();
        } else {
            Log.e(TAG, "selectData() db없음.");
        }
        deliveryList.setAdapter(adapter);


        // 리스트 정렬 기능
        Button button4 = (Button) view.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                //Collections.sort(storeList, gradeDesc);
                adapter.notifyDataSetChanged();
            }
        });


        // 리스트 정렬 기능
        Button button5 = (Button) view.findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comparator<DeliveryItem> gradeDesc = new Comparator<DeliveryItem>() {
                    @Override
                    public int compare(DeliveryItem o1, DeliveryItem o2) {
                        int ret = 0;

                        if (o1.getDeliveryGrade().compareTo(o2.getDeliveryGrade()) < 0)
                            ret = 1;
                        else if (o1.getDeliveryGrade().compareTo(o2.getDeliveryGrade()) == 0)
                            ret = 0;
                        else
                            ret = -1;

                        return ret;
                    }
                };

                Collections.sort(DeliveryListAdapter.items, gradeDesc);
                adapter.notifyDataSetChanged();
            }
        });

        Button button6 = (Button) view.findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comparator<DeliveryItem> distanceAsc = new Comparator<DeliveryItem>() {
                    @Override
                    public int compare(DeliveryItem o1, DeliveryItem o2) {
                        int ret = 0;

                        if (o1.getDeliveryTime() < o2.getDeliveryTime())
                            ret = -1;
                        else if (o1.getDeliveryTime() == o2.getDeliveryTime())
                            ret = 0;
                        else
                            ret = 1;

                        return ret;
                    }
                };

                Collections.sort(DeliveryListAdapter.items, distanceAsc);
                adapter.notifyDataSetChanged();
            }
        });


        // 가게 정보 상세보기
        deliveryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        if (database != null) {
            Log.v(TAG, "DB 열기 성공!");
        } else {
            Log.e(TAG, "DB 열기 실패!");
        }
    }
}
