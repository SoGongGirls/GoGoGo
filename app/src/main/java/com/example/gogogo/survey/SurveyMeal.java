package com.example.gogogo.survey;

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

public class SurveyMeal extends AppCompatActivity {

    // 질문
    TextView question;

    // 답변 사진
    ImageView select1;
    ImageView select2;
    ImageView select3;
    ImageView select4;

    // 답변 내용
    TextView select1Tv;
    TextView select2Tv;
    TextView select3Tv;
    TextView select4Tv;

    public static ArrayList<String> MENU;   // 메뉴 룰렛 전달


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_meal);

        question = (TextView) findViewById(R.id.question);

        select1Tv = (TextView) findViewById(R.id.select1_tv);
        select2Tv = (TextView) findViewById(R.id.select2_tv);
        select3Tv = (TextView) findViewById(R.id.select3_tv);
        select4Tv = (TextView) findViewById(R.id.select4_tv);

        // 이미지뷰 동그랗게
        select1 = (ImageView) findViewById(R.id.select1);
        select1.setBackground(new ShapeDrawable(new OvalShape()));
        select1.setClipToOutline(true);

        select2 = (ImageView) findViewById(R.id.select2);
        select2.setBackground(new ShapeDrawable(new OvalShape()));
        select2.setClipToOutline(true);

        select3 = (ImageView) findViewById(R.id.select3);
        select3.setBackground(new ShapeDrawable(new OvalShape()));
        select3.setClipToOutline(true);

        select4 = (ImageView) findViewById(R.id.select4);
        select4.setBackground(new ShapeDrawable(new OvalShape()));
        select4.setClipToOutline(true);
    }

    public void selectOne(View view) {
        Intent intent = new Intent(getApplicationContext(), SurveyMealMeat.class);
        startActivity(intent);
    }

    public void selectTwo(View view) {
        Intent intent = new Intent(getApplicationContext(), SurveyMealFish.class);
        startActivity(intent);
    }

    public void selectThree(View view) {
        MENU = new ArrayList<>();
        MENU.add("훠궈");
        MENU.add("쌀국수");
        MENU.add("타코");
        MENU.add("마라탕");
        MENU.add("꿔바로우");

        Intent intent = new Intent(getApplicationContext(), Roulette.class);
        startActivity(intent);
    }

    public void selectFour(View view) {
        Intent intent = new Intent(getApplicationContext(), SurveyMealElse.class);
        startActivity(intent);
    }
}
