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

public class SurveyDessertSad2 extends AppCompatActivity {


    // 답변 사진
    ImageView select7;
    ImageView select8;
    ImageView select9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_dessert_sad_2);

        // 이미지뷰 동그랗게
        select7 = (ImageView) findViewById(R.id.select7);
        select7.setBackground(new ShapeDrawable(new OvalShape()));
        select7.setClipToOutline(true);

        select8 = (ImageView) findViewById(R.id.select8);
        select8.setBackground(new ShapeDrawable(new OvalShape()));
        select8.setClipToOutline(true);

        select9 = (ImageView) findViewById(R.id.select9);
        select9.setBackground(new ShapeDrawable(new OvalShape()));
        select9.setClipToOutline(true);
    }

    public void selectSeven(View view) {
        MENU = new ArrayList<>();
        MENU.add("치즈케이크");
        MENU.add("초코케이크");
        MENU.add("당근케이크");
        MENU.add("팬케이크");

        Intent intent = new Intent(getApplicationContext(), Roulette.class);
        startActivity(intent);
    }

    public void selectEight(View view) {
        MENU = new ArrayList<>();
        MENU.add("애플파이");
        MENU.add("호박파이");

        Intent intent = new Intent(getApplicationContext(), Roulette.class);
        startActivity(intent);
    }

    public void selectNine(View view) {
        MENU = new ArrayList<>();
        MENU.add("벨기에와플");
        MENU.add("아메리칸와플");

        Intent intent = new Intent(getApplicationContext(), Roulette.class);
        startActivity(intent);
    }
}
