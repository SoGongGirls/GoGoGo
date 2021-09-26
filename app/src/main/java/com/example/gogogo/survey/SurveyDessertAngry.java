package com.example.gogogo.survey;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gogogo.R;

public class SurveyDessertAngry extends AppCompatActivity {

    // 질문
    TextView question3;

    // 답변 사진
    ImageView select10;
    ImageView select11;

    // 답변 내용
    TextView select10Tv;
    TextView select11Tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_dessert_angry);

        question3 = (TextView) findViewById(R.id.question3);

        select10Tv = (TextView) findViewById(R.id.select10_tv);
        select11Tv = (TextView) findViewById(R.id.select11_tv);

        // 이미지뷰 동그랗게
        select10 = (ImageView) findViewById(R.id.select10);
        select10.setBackground(new ShapeDrawable(new OvalShape()));
        select10.setClipToOutline(true);

        select11 = (ImageView) findViewById(R.id.select11);
        select11.setBackground(new ShapeDrawable(new OvalShape()));
        select11.setClipToOutline(true);
    }

    public void selectTen(View view) {
        question3.setText("Q. 맛 VS 칼로리");

        select10.setImageResource(R.drawable.fiona_person);
        select11.setImageResource(R.drawable.fiona_monster);

        select10Tv.setText("맛");
        select11Tv.setText("칼로리");
    }

    public void selectEleven(View view) {
        question3.setText("Q. 맛 VS 칼로리");

        // 답변 사진
        select10.setImageResource(R.drawable.fiona_person);
        select11.setImageResource(R.drawable.fiona_monster);

        // 답변 내용
        select10Tv.setText("맛");
        select11Tv.setText("칼로리");
    }
}