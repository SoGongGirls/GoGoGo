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

public class SurveyDessertDepressed2 extends AppCompatActivity {

    // 질문
    TextView question4;

    // 답변 사진
    ImageView select12;
    ImageView select13;

    // 답변 내용
    TextView select12Tv;
    TextView select13Tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_dessert_depressed_2);

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
        MENU = new ArrayList<>();
        MENU.add("그릭요거트");
        MENU.add("플레인요거트");
        MENU.add("딸기요거트");
        MENU.add("블루베리요거트");

        Intent intent = new Intent(getApplicationContext(), Roulette.class);
        startActivity(intent);
    }

    public void selectThirteen(View view) {
        MENU = new ArrayList<>();
        MENU.add("망고주스");
        MENU.add("오렌지주스");
        MENU.add("수박주스");

        Intent intent = new Intent(getApplicationContext(), Roulette.class);
        startActivity(intent);
    }
}
