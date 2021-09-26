package com.example.gogogo.survey;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gogogo.R;

public class SurveyDessertSad extends AppCompatActivity {

    // 질문
    TextView question5;

    // 답변 사진
    ImageView select14;
    ImageView select15;

    // 답변 내용
    TextView select14Tv;
    TextView select15Tv;

    int count14 = 0;   // 따뜻한거 조건


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_dessert_sad);

        question5 = (TextView) findViewById(R.id.question5);

        select14Tv = (TextView) findViewById(R.id.select14_tv);
        select15Tv = (TextView) findViewById(R.id.select15_tv);

        // 이미지뷰 동그랗게
        select14 = (ImageView) findViewById(R.id.select14);
        select14.setBackground(new ShapeDrawable(new OvalShape()));
        select14.setClipToOutline(true);

        select15 = (ImageView) findViewById(R.id.select15);
        select15.setBackground(new ShapeDrawable(new OvalShape()));
        select15.setClipToOutline(true);
    }

    public void selectFourteen(View view) {
        count14 += 1;

        if (count14 == 3) {
            question5.setText("Q. 타르트 VS 빵");

            // 답변 사진
            select14.setImageResource(R.drawable.tarte);
            select15.setImageResource(R.drawable.macaroon);

            // 답변 내용
            select14Tv.setText("타르트");
            select15Tv.setText("빵");
        }
        else {
            question5.setText("Q. 한입거리 VS 푸짐");

            // 답변 사진
            select14.setImageResource(R.drawable.piglet);
            select15.setImageResource(R.drawable.pooh);

            // 답변 내용
            select14Tv.setText("한입거리");
            select15Tv.setText("푸짐");
        }
    }

    public void selectFifteen(View view) {
        count14 += 1;

        if (count14 == 3) {
            question5.setText("Q. 케이크 VS 와플");

            // 답변 사진
            select14.setImageResource(R.drawable.cake);
            select15.setImageResource(R.drawable.waffle);

            // 답변 내용
            select14Tv.setText("케이크");
            select15Tv.setText("와플");
        }
        else {
            question5.setText("Q. 맛 VS 칼로리");

            // 답변 사진
            select14.setImageResource(R.drawable.fiona_person);
            select15.setImageResource(R.drawable.fiona_monster);

            // 답변 내용
            select14Tv.setText("맛");
            select15Tv.setText("칼로리");
        }
    }
}
