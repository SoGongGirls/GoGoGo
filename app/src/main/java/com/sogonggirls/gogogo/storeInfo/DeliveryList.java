package com.sogonggirls.gogogo.storeInfo;

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

import com.sogonggirls.gogogo.DatabaseHelper;
import com.sogonggirls.gogogo.R;
import com.sogonggirls.gogogo.Roulette;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class DeliveryList extends Fragment {
    SQLiteDatabase database;
    ListView deliveryList;
    DeliveryListAdapter adapter;
    public static final String TAG ="TAG DeliveryList.java";

    String[] menuList = new String[5];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.delivery_list, container, false);
        // 초기화, 참조 및 생성
        deliveryList = (ListView) view.findViewById(android.R.id.list);
        openDB();

        // 리스트뷰 참조 및 Adapter 연결
        adapter = new DeliveryListAdapter(getActivity());
        adapter.removeItemAll();

        //맨처음 초기화 데이터 보여주기(select)
        if (database != null) {
            String tableName = "delivery_data";
            String query = "select name, degree, id, logo, latitude, longitude, menu from "+tableName;
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


                String menu = cursor.getString(6);

                menuList = menu.split(",");
                ArrayList<String> menuList2 = new ArrayList<>(Arrays.asList(menuList));

                // 검색결과 변환
                if (Arrays.asList(menuList).contains("분식")) {
                    menuList2.remove("분식");
                    menuList2.add("김밥");
                    menuList2.add("떡볶이");
                    menuList2.add("쫄면");
                    menuList2.add("만두");
                    menuList2.add("볶음밥");
                }
                if (Arrays.asList(menuList).contains("중식")) {
                    menuList2.remove("중식");
                    menuList2.add("짜장면");
                    menuList2.add("짬뽕");
                    menuList2.add("탕수육");
                    menuList2.add("볶음밥");
                }

                if (Arrays.asList(menuList).contains("꽈배기")) {
                    menuList2.remove("꽈배기");
                    menuList2.add("츄러스");
                }
                if (Arrays.asList(menuList).contains("빙수")) {
                    menuList2.remove("빙수");
                    menuList2.add("딸기빙수");
                    menuList2.add("인절미빙수");
                    menuList2.add("팥빙수");
                    menuList2.add("요거트빙수");
                    menuList2.add("망고빙수");
                }
                if (Arrays.asList(menuList).contains("와플")) {
                    menuList2.remove("와플");
                    menuList2.add("벨기에와플");
                    menuList2.add("아메리칸와플");
                    menuList2.add("크로플");
                }
                if (Arrays.asList(menuList).contains("차")) {
                    menuList2.remove("차");
                    menuList2.add("율무차");
                    menuList2.add("녹차");
                    menuList2.add("홍차");
                    menuList2.add("꿀차");
                    menuList2.add("코코아");
                    menuList2.add("유자차");
                    menuList2.add("헛개차");
                }
                if (Arrays.asList(menuList).contains("케익")) {
                    menuList2.remove("케익");
                    menuList2.add("치즈케이크");
                    menuList2.add("초코케이크");
                    menuList2.add("당근케이크");
                }
                if (Arrays.asList(menuList).contains("타르트")) {
                    menuList2.remove("타르트");
                    menuList2.add("에그타르트");
                    menuList2.add("패션후르츠타르트");
                }
                if (Arrays.asList(menuList).contains("프레즐")) {
                    menuList2.remove("프레즐");
                    menuList2.add("츄러스");
                }


                // 룰렛 결과에 해당하는 데이터만 보여주기
                if (menuList2.contains(Roulette.result3)) {
                    adapter.addItem(new DeliveryItem(name, degree, id, logo, latitude, longitude));
                }
            }
            cursor.close();
        } else {
            Log.e(TAG, "selectData() db없음.");
        }
        deliveryList.setAdapter(adapter);


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

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button5.setSelected(false);
                button4.setSelected(true);
                button3.setSelected(false);
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
                Intent intent = new Intent(getActivity(), DeliveryDetail.class);
                DeliveryItem item = (DeliveryItem) adapter.getItem(i);
                String id = String.valueOf(item.getDeliveryId());
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
