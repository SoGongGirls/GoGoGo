package com.example.gogogo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gogogo.Cook_Yes;
import com.example.gogogo.R;
import com.example.gogogo.Recipe_all;
import com.google.android.material.navigation.NavigationView;

public class Fragment1 extends Fragment {

    NavigationView navigationView;
    TextView toolbar_title;
    Button cook_yes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment1, container, false);

        /* 객체 초기화 */
        NavigationView navigationView = view.findViewById(R.id.drawer);
        toolbar_title = (TextView) view.findViewById(R.id.toolbar_title);
        cook_yes = (Button) view.findViewById(R.id.cook_yes);

        toolbar_title.setText("메뉴 추천");  // 제목 설정

        cook_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Cook_Yes.class);
                startActivity(intent);
            }
        });

        return view;

    }// OnCreateView


}
