package com.example.gogogo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.gogogo.R;
import com.example.gogogo.ingredient.IngredientAdapter;
import com.example.gogogo.ingredient.Ingredient_add;

import java.util.List;

public class Fragment2 extends Fragment {

    IngredientAdapter adapter;
    TextView toolbar_title;
    ListView ingredient_list;
    ImageView ingredient_add;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment2, container, false);

        /* 객체 초기화 */
        adapter = new IngredientAdapter();
        toolbar_title = (TextView) view.findViewById(R.id.toolbar_title);
        ingredient_list = (ListView) view.findViewById(R.id.ingredient_list);
        ingredient_add = (ImageView) view.findViewById(R.id.ingredient_add);

        toolbar_title.setText("나의 냉장고");   // 제목 설정
        ingredient_list.setAdapter(adapter);  // 리스트뷰 어댑터 설정

        ingredient_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Ingredient_add.class);
                startActivity(intent);
            }
        });


        /* 리스트뷰 출력 예시 - 테스트용으로 넣은 거라 나중에 지워도 됨 */
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_launcher_background),
                "아보카도", "2021년 9월 19일",
                ContextCompat.getDrawable(getActivity(), R.drawable.ic_baseline_delete_24));
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_launcher_background),
                "아보카도", "2021년 9월 19일",
                ContextCompat.getDrawable(getActivity(), R.drawable.ic_baseline_delete_24));
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_launcher_background),
                "아보카도", "2021년 9월 19일",
                ContextCompat.getDrawable(getActivity(), R.drawable.ic_baseline_delete_24));
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_launcher_background),
                "아보카도", "2021년 9월 19일",
                ContextCompat.getDrawable(getActivity(), R.drawable.ic_baseline_delete_24));
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_launcher_background),
                "아보카도", "2021년 9월 19일",
                ContextCompat.getDrawable(getActivity(), R.drawable.ic_baseline_delete_24));
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_launcher_background),
                "아보카도", "2021년 9월 19일",
                ContextCompat.getDrawable(getActivity(), R.drawable.ic_baseline_delete_24));
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_launcher_background),
                "아보카도", "2021년 9월 19일",
                ContextCompat.getDrawable(getActivity(), R.drawable.ic_baseline_delete_24));



        return view;

    }// OnCreateView

}
