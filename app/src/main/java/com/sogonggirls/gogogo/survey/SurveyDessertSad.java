package com.sogonggirls.gogogo.survey;

import static com.sogonggirls.gogogo.survey.SurveyMeal.MENU;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sogonggirls.gogogo.R;
import com.sogonggirls.gogogo.Roulette;

import java.util.ArrayList;

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
    int count15 = 0;


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

        if (count14 == 3 && count15 == 1) {
            MENU = new ArrayList<>();
            MENU.add("에그타르트");
            MENU.add("패션후르츠타르트");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
        }
        else if (count14 == 3) {
            MENU = new ArrayList<>();
            MENU.add("아이스크림");
            MENU.add("크로플");
            MENU.add("밀크쉐이크");
            MENU.add("젤라또");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
        }
        else if (count14 == 2 && count15 == 1) {
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
        count15 += 1;

        if (count14 == 1 && count15 == 2) {
            Intent intent = new Intent(getApplicationContext(), SurveyDessertSad2.class);
            startActivity(intent);
        }
        else if (count14 == 1 && count15 == 1) {
            MENU = new ArrayList<>();
            MENU.add("딸기빙수");
            MENU.add("인절미빙수");
            MENU.add("팥빙수");
            MENU.add("망고빙수");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
        }
        else if (count14 == 2 && count15 == 2) {
            MENU = new ArrayList<>();
            MENU.add("마카롱");
            MENU.add("츄러스");
            MENU.add("도넛");
            MENU.add("쿠키");
            MENU.add("호떡");
            MENU.add("빵");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
        }
        else if (count15 == 2) {
            MENU = new ArrayList<>();
            MENU.add("율무차");
            MENU.add("녹차");
            MENU.add("홍차");
            MENU.add("코코아");
            MENU.add("유자차");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
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
