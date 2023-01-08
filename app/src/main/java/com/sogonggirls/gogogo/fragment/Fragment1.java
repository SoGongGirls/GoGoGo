package com.sogonggirls.gogogo.fragment;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sogonggirls.gogogo.Cook_Yes;
import com.sogonggirls.gogogo.R;
import com.sogonggirls.gogogo.survey.SurveyStart;

public class Fragment1 extends Fragment {

//    TextView toolbar_title;
    ImageView cook_yes;
    ImageView cook_no;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment1, container, false);

        /* 객체 초기화 */

//        toolbar_title = (TextView) view.findViewById(R.id.toolbar_title);
        cook_yes = (ImageView) view.findViewById(R.id.cook_yes);
        cook_yes.setBackground(new ShapeDrawable(new OvalShape()));
        cook_yes.setClipToOutline(true);

//        toolbar_title.setText("메뉴 추천");  // 제목 설정

        cook_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Cook_Yes.class);
                startActivity(intent);
            }
        });

        /* 요리안해 화면 전환 */
        cook_no = (ImageView) view.findViewById(R.id.cook_no);
        cook_no.setBackground(new ShapeDrawable(new OvalShape()));
        cook_no.setClipToOutline(true);

        cook_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SurveyStart.class);
                startActivity(intent);
            }
        });

        return view;

    }// OnCreateView


}
