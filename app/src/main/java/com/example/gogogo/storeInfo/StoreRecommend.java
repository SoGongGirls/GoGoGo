package com.example.gogogo.storeInfo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.gogogo.R;
import com.example.gogogo.storeInfo.DeliveryList;
import com.example.gogogo.storeInfo.StoreList;
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
}
