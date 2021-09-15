package com.example.gogogo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.gogogo.R;
import com.google.android.material.navigation.NavigationView;

public class Fragment1 extends Fragment {

    NavigationView navigationView;
    TextView toolbar_title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment1, container, false);

        /* 객체 선언 */
        NavigationView navigationView = view.findViewById(R.id.drawer);
        toolbar_title = (TextView) view.findViewById(R.id.toolbar_title);

        toolbar_title.setText("메뉴 추천");  // 제목 설정



        return view;

    }// OnCreateView


}
