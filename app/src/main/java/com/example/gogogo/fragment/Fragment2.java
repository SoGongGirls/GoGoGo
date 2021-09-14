package com.example.gogogo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gogogo.R;

public class Fragment2 extends Fragment {

    TextView toolbar_title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment2, container, false);

        toolbar_title = (TextView) view.findViewById(R.id.toolbar_title);
        toolbar_title.setText("나의 냉장고");


        return view;

    }// OnCreateView

}
