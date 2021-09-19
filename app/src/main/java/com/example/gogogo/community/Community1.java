package com.example.gogogo.community;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gogogo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Community1 extends Fragment {

    FloatingActionButton community_write;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.community1, container, false);

        /* 객체 초기화 */
        community_write = (FloatingActionButton) view.findViewById(R.id.community_write);

        /* 글 작성 버튼 클릭 */
        community_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Community_insert1.class);
                startActivity(intent);
            }
        });


        return view;

    }// OnCreateView

}