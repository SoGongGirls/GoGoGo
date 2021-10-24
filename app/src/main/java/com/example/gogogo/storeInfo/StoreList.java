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
import com.example.gogogo.Roulette;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StoreList extends Fragment {
    SQLiteDatabase database;
    ListView storeList;
    StoreListAdapter adapter;
    public static final String TAG ="TAG StoreList.java";

    String[] menuList = new String[5];


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.store_list, container, false);
        // 초기화, 참조 및 생성
        storeList = (ListView) view.findViewById(android.R.id.list);
        openDB();

        // 리스트뷰 참조 및 Adapter 연결
        adapter = new StoreListAdapter(getActivity());

        // 맨 처음 초기화 데이터 보여주기 (select)
        if (database != null) {
            String tableName = "store_data";
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
                if (Arrays.asList(menuList).contains("갈비찜")) {
                    menuList2.remove("갈비찜");
                    menuList2.add("갈비");
                }
                if (Arrays.asList(menuList).contains("갈비탕")) {
                    menuList2.remove("갈비탕");
                    menuList2.add("갈비");
                }
                if (Arrays.asList(menuList).contains("곰탕")) {
                    menuList2.remove("곰탕");
                    menuList2.add("국밥");
                }
                if (Arrays.asList(menuList).contains("김치전골")) {
                    menuList2.remove("김치전골");
                    menuList2.add("김치찌개");
                }
                if (Arrays.asList(menuList).contains("꼼장어")) {
                    menuList2.remove("꼼장어");
                    menuList2.add("장어");
                }
                if (Arrays.asList(menuList).contains("꽃게탕")) {
                    menuList2.remove("꽃게탕");
                    menuList2.add("해물탕");
                }
                if (Arrays.asList(menuList).contains("꼬치")) {
                    menuList2.remove("꼬치");
                    menuList2.add("튀김");
                }
                if (Arrays.asList(menuList).contains("낙지볶음")) {
                    menuList2.remove("낙지볶음");
                    menuList2.add("쭈꾸미/낙지");
                }
                if (Arrays.asList(menuList).contains("냉면")) {
                    menuList2.remove("냉면");
                    menuList2.add("물냉면");
                    menuList2.add("비빔냉면");
                }
                if (Arrays.asList(menuList).contains("돼지갈비")) {
                    menuList2.remove("돼지갈비");
                    menuList2.add("삼겹살");
                }
                if (Arrays.asList(menuList).contains("딱새우회")) {
                    menuList2.remove("딱새우회");
                    menuList2.add("회/물회");
                }
                if (Arrays.asList(menuList).contains("떡갈비")) {
                    menuList2.remove("떡갈비");
                    menuList2.add("갈비");
                }
                if (Arrays.asList(menuList).contains("메기탕")) {
                    menuList2.remove("메기탕");
                    menuList2.add("매운탕");
                }
                if (Arrays.asList(menuList).contains("물회")) {
                    menuList2.remove("물회");
                    menuList2.add("회/물회");
                }
                if (Arrays.asList(menuList).contains("밥버거")) {
                    menuList2.remove("밥버거");
                    menuList2.add("컵밥/밥버거");
                }
                if (Arrays.asList(menuList).contains("복어")) {
                    menuList2.remove("복어");
                    menuList2.add("장어/복어");
                }
                if (Arrays.asList(menuList).contains("분식")) {
                    menuList2.remove("분식");
                    menuList2.add("김밥");
                    menuList2.add("떡볶이");
                    menuList2.add("쫄면");
                    menuList2.add("만두");
                    menuList2.add("볶음밥");
                }
                if (Arrays.asList(menuList).contains("비빔국수")) {
                    menuList2.remove("비빔국수");
                    menuList2.add("국수");
                }
                if (Arrays.asList(menuList).contains("삼계탕")) {
                    menuList2.remove("삼계탕");
                    menuList2.add("닭백숙");
                }
                if (Arrays.asList(menuList).contains("설렁탕")) {
                    menuList2.remove("설렁탕");
                    menuList2.add("국밥");
                }
                if (Arrays.asList(menuList).contains("소고기")) {
                    menuList2.remove("소고기");
                    menuList2.add("소고기구이");
                }
                if (Arrays.asList(menuList).contains("수육")) {
                    menuList2.remove("수육");
                    menuList2.add("보쌈");
                }
                if (Arrays.asList(menuList).contains("순두부찌개")) {
                    menuList2.remove("순두부찌개");
                    menuList2.add("육개장/순두부찌개");
                }
                if (Arrays.asList(menuList).contains("생고기")) {
                    menuList2.remove("생고기");
                    menuList2.add("육회");
                }
                if (Arrays.asList(menuList).contains("생선조림")) {
                    menuList2.remove("생선조림");
                    menuList2.add("생선찜");
                }
                if (Arrays.asList(menuList).contains("아구찜")) {
                    menuList2.remove("아구찜");
                    menuList2.add("생선찜");
                }
                if (Arrays.asList(menuList).contains("양갈비")) {
                    menuList2.remove("양갈비");
                    menuList2.add("양고기");
                }
                if (Arrays.asList(menuList).contains("연포탕")) {
                    menuList2.remove("연포탕");
                    menuList2.add("해물탕");
                }
                if (Arrays.asList(menuList).contains("어묵탕")) {
                    menuList2.remove("어묵탕");
                    menuList2.add("어묵");
                }
                if (Arrays.asList(menuList).contains("오리")) {
                    menuList2.remove("오리");
                    menuList2.add("오리구이");
                    menuList2.add("오리탕");
                    menuList2.add("오리주물럭");
                }
                if (Arrays.asList(menuList).contains("오리로스")) {
                    menuList2.remove("오리로스");
                    menuList2.add("오리구이");
                }
                if (Arrays.asList(menuList).contains("오리전골")) {
                    menuList2.remove("오리전골");
                    menuList2.add("오리주물럭");
                }
                if (Arrays.asList(menuList).contains("오리훈제")) {
                    menuList2.remove("오리훈제");
                    menuList2.add("오리구이");
                }
                if (Arrays.asList(menuList).contains("장어")) {
                    menuList2.remove("장어");
                    menuList2.add("장어/복어");
                }
                if (Arrays.asList(menuList).contains("장어구이")) {
                    menuList2.remove("장어구이");
                    menuList2.add("장어/복어");
                }
                if (Arrays.asList(menuList).contains("전복구이")) {
                    menuList2.remove("전복구이");
                    menuList2.add("조개구이");
                }
                if (Arrays.asList(menuList).contains("제육볶음")) {
                    menuList2.remove("제육볶음");
                    menuList2.add("백반");
                }
                if (Arrays.asList(menuList).contains("조개전골")) {
                    menuList2.remove("조개전골");
                    menuList2.add("해물탕");
                }
                if (Arrays.asList(menuList).contains("조개탕")) {
                    menuList2.remove("조개탕");
                    menuList2.add("해물탕");
                }
                if (Arrays.asList(menuList).contains("죽")) {
                    menuList2.remove("죽");
                    menuList2.add("호박죽");
                    menuList2.add("전복죽");
                    menuList2.add("팥죽");
                }
                if (Arrays.asList(menuList).contains("중식")) {
                    menuList2.remove("중식");
                    menuList2.add("짜장면");
                    menuList2.add("짬뽕");
                    menuList2.add("탕수육");
                    menuList2.add("볶음밥");
                }
                if (Arrays.asList(menuList).contains("쭈꾸미")) {
                    menuList2.remove("쭈꾸미");
                    menuList2.add("쭈꾸미/낙지");
                }
                if (Arrays.asList(menuList).contains("청국장")) {
                    menuList2.remove("청국장");
                    menuList2.add("된장찌개");
                }
                if (Arrays.asList(menuList).contains("칼국수")) {
                    menuList2.remove("칼국수");
                    menuList2.add("국수");
                }
                if (Arrays.asList(menuList).contains("컵밥")) {
                    menuList2.remove("컵밥");
                    menuList2.add("컵밥/밥버거");
                }
                if (Arrays.asList(menuList).contains("콩물국수")) {
                    menuList2.remove("콩물국수");
                    menuList2.add("국수");
                }
                if (Arrays.asList(menuList).contains("해장국")) {
                    menuList2.remove("해장국");
                    menuList2.add("국밥");
                }
                if (Arrays.asList(menuList).contains("회")) {
                    menuList2.remove("회");
                    menuList2.add("회/물회");
                }


                // 룰렛 결과에 해당하는 데이터만 보여주기
                if (menuList2.contains(Roulette.result3)) {
                    adapter.addItem(new StoreItem(name, degree, id, logo, latitude, longitude));
                }
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