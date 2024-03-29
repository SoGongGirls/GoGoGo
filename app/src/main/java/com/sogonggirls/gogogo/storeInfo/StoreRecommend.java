package com.sogonggirls.gogogo.storeInfo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.sogonggirls.gogogo.MainActivity;
import com.sogonggirls.gogogo.R;
import com.google.android.material.tabs.TabLayout;

public class StoreRecommend extends AppCompatActivity {

    DeliveryList deliveryList;
    StoreList storeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_recommend);

        storeList = new StoreList();
        deliveryList = new DeliveryList();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, storeList).commit();

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("매장"));
        tabs.addTab(tabs.newTab().setText("배달"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("StoreRecommend", "선택된 탭 : " + position);

                Fragment selected = null;
                if (position == 0) {
                    selected = storeList;
                } else if (position == 1) {
                    selected = deliveryList;
                }

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

    }

    // 뒤로가기 버튼 눌렀을때, 홈화면으로 이동하기
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(StoreRecommend.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
