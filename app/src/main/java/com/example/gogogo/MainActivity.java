package com.example.gogogo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.gogogo.fragment.Fragment1;
import com.example.gogogo.fragment.Fragment2;
import com.example.gogogo.fragment.Fragment3;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawer_layout;
    View drawer;
    View my_page;

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

        /* 객체 초기화 */
        navigationView = findViewById(R.id.drawer);
        bottomNavigationView = findViewById(R.id.bottom);
        drawer_layout = findViewById(R.id.drawer_layout);
        my_page = findViewById(R.id.my_page);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

//        my_page.bringToFront();

        my_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.openDrawer(drawer);
            }
        });


        /* 초기화면 설정 - fragment1으로 고정 */
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()) {
                    case R.id.nav_note:
                        Intent i_bookmark = new Intent(MainActivity.this, my_note.class);
                        startActivity(i_bookmark);
                        break;
                    case R.id.nav_location:
                        Intent i_location = new Intent(MainActivity.this, my_location.class);
                        startActivity(i_location);
                        break;
                }
                drawer_layout.closeDrawer(navigationView);
                return false;
            }

        });


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

    }
}