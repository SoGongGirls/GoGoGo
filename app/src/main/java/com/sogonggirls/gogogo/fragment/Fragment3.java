package com.sogonggirls.gogogo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.sogonggirls.gogogo.R;
import com.sogonggirls.gogogo.community.PagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class Fragment3 extends Fragment {

//    TextView toolbar_title;
    ViewPager viewPager;
    TabLayout tabLayout;
//    Button go_login;
//    Button go_message;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment3, container, false);

        /* 객체 초기화 */
//        toolbar_title = (TextView) view.findViewById(R.id.toolbar_title);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        //임시버튼 두개

//        go_login = (Button) view.findViewById(R.id.go_login);
//        go_message = (Button)view.findViewById(R.id.go_message);

//        go_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), LoginActivity.class));
//            }
//        });

//        go_message.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), ChatFragment.class));
//            }
//        });

//        go_login = (Button) view.findViewById(R.id.go_login);
//        go_message = (Button)view.findViewById(R.id.go_message);

//        go_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), LoginActivity.class));
//            }
//        });
//
//        go_message.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), MessageMain.class));
//            }
//        });

//        toolbar_title.setText("커뮤니티");  // 제목 설정

        tabLayout.addTab(tabLayout.newTab().setText("자유 게시판"));
        tabLayout.addTab(tabLayout.newTab().setText("밥동무 찾기"));
        tabLayout.addTab(tabLayout.newTab().setText("자취 꿀팁"));

        viewPager.setAdapter(new PagerAdapter(getChildFragmentManager()));
        viewPager.setSaveEnabled(false);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }

        });

        return view;

    }// OnCreateView

}
