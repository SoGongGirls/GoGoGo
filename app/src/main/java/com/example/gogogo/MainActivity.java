package com.example.gogogo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 매장 리스트 내 탭
        TabLayout tabLayout = (TabLayout) findViewById(R.id.layout_tab);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // tab의 상태가 선택 상태로 변경
                int pos = tab.getPosition();
                changeView(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // tab의 상태가 선택되지 않음으로 변경 - do nothing
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // 이미 선택된 tab이 다시 선택 - do nothing
            }
        });
    }

    private void changeView(int index) {
        ListView listView1 = (ListView) findViewById(R.id.store_list);
        ListView listView2 = (ListView) findViewById(R.id.delivery_list);

        switch (index) {
            case 0:
                listView1.setVisibility(View.VISIBLE);
                listView2.setVisibility(View.INVISIBLE);
                break;
            case 1:
                listView1.setVisibility(View.INVISIBLE);
                listView2.setVisibility(View.VISIBLE);
                break;
        }
    }
}