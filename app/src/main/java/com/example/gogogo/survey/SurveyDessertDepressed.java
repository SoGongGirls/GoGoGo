package com.example.gogogo.survey;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gogogo.R;

public class SurveyDessertDepressed extends AppCompatActivity {

    // 질문
    TextView question4;

    // 답변 사진
    ImageView select12;
    ImageView select13;

    // 답변 내용
    TextView select12Tv;
    TextView select13Tv;

    int count12 = 0;   // 찬거-맛 조건
    int count13 = 0;   // 찬거-칼로리 조건


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_dessert_depressed);

        question4 = (TextView) findViewById(R.id.question4);

        select12Tv = (TextView) findViewById(R.id.select12_tv);
        select13Tv = (TextView) findViewById(R.id.select13_tv);

        // 이미지뷰 동그랗게
        select12 = (ImageView) findViewById(R.id.select12);
        select12.setBackground(new ShapeDrawable(new OvalShape()));
        select12.setClipToOutline(true);

        select13 = (ImageView) findViewById(R.id.select13);
        select13.setBackground(new ShapeDrawable(new OvalShape()));
        select13.setClipToOutline(true);
    }

    public void selectTwelve(View view) {
        count12 += 1;
        count13 += 1;

        if (count12 == 2) {
            question4.setText("Q. 아이스크림 VS 빙수");

            // 답변 사진
            select12.setImageResource(R.drawable.icecream);
            select13.setImageResource(R.drawable.shavedice);

            // 답변 내용
            select12Tv.setText("아이스크림");
            select13Tv.setText("빙수");
        }
        else {
            question4.setText("Q. 맛 VS 칼로리");

            select12.setImageResource(R.drawable.fiona_person);
            select13.setImageResource(R.drawable.fiona_monster);

            select12Tv.setText("맛");
            select13Tv.setText("칼로리");
        }
    }

    public void selectThirteen(View view) {
        count13 += 1;

        if (count13 == 2) {
            question4.setText("Q. 요거트 VS 주스");

            // 답변 사진
            select12.setImageResource(R.drawable.yogurt);
            select13.setImageResource(R.drawable.juice);

            // 답변 내용
            select12Tv.setText("요거트");
            select13Tv.setText("주스");
        }
        else {
            question4.setText("Q. 맛 VS 칼로리");

            // 답변 사진
            select12.setImageResource(R.drawable.fiona_person);
            select13.setImageResource(R.drawable.fiona_monster);

            // 답변 내용
            select12Tv.setText("맛");
            select13Tv.setText("칼로리");
        }
    }
}
