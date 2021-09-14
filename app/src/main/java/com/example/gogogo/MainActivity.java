package com.example.gogogo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.gogogo.fragment.Fragment1;
import com.example.gogogo.fragment.Fragment2;
import com.example.gogogo.fragment.Fragment3;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    View drawer;

    Fragment fragment1;
    Fragment fragment2;
    Fragment fragment3;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_drawer, menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        /* 초기화면 설정 - fragment1으로 고정 */
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        /* fragment 설정 */
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener(){
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.bottom_navigation_1:
                                getSupportFragmentManager().beginTransaction().
                                        replace(R.id.container, fragment1).commit();
                                return true;
                            case R.id.bottom_navigation_2:
                                getSupportFragmentManager().beginTransaction().
                                        replace(R.id.container, fragment2).commit();
                                return true;
                            case R.id.bottom_navigation_3:
                                getSupportFragmentManager().beginTransaction().
                                        replace(R.id.container, fragment3).commit();
                                return true;
                        }
                        return false;
                    }
                });


        /*
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
        */
    }
}