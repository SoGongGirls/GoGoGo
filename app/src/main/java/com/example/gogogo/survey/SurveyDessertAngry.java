package com.example.gogogo.survey;

import static com.example.gogogo.survey.SurveyMeal.MENU;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gogogo.R;
import com.example.gogogo.Roulette;

import java.util.ArrayList;

public class SurveyDessertAngry extends AppCompatActivity {

    // 질문
    TextView question3;

    // 답변 사진
    ImageView select10;
    ImageView select11;

    // 답변 내용
    TextView select10Tv;
    TextView select11Tv;

    int count10 = 0;
    int count11 = 0;


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
        count10 += 1;

        if (count10 == 2) {
            MENU = new ArrayList<>();
            MENU.add("아이스크림");
            MENU.add("크로플");
            MENU.add("밀크쉐이크");
            MENU.add("젤라또");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
        }
        else if (count10 == 1 && count11 == 1) {
            MENU = new ArrayList<>();
            MENU.add("에그타르트");
            MENU.add("패션후르츠타르트");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
        }
        else {
            question3.setText("Q. 맛 VS 칼로리");

            select10.setImageResource(R.drawable.fiona_person);
            select11.setImageResource(R.drawable.fiona_monster);

            select10Tv.setText("맛");
            select11Tv.setText("칼로리");
        }
    }

    public void selectEleven(View view) {
        count11 += 1;

        if (count10 == 1 && count11 == 1) {
            MENU = new ArrayList<>();
            MENU.add("녹차");
            MENU.add("헛개차");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
        }
        else if (count11 == 2) {
            MENU = new ArrayList<>();
            MENU.add("율무차");
            MENU.add("홍차");
            MENU.add("꿀차");
            MENU.add("코코아");
            MENU.add("유자차");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
        }
        else {
            question3.setText("Q. 맛 VS 칼로리");

            // 답변 사진
            select10.setImageResource(R.drawable.fiona_person);
            select11.setImageResource(R.drawable.fiona_monster);

            // 답변 내용
            select10Tv.setText("맛");
            select11Tv.setText("칼로리");
        }
    }
}