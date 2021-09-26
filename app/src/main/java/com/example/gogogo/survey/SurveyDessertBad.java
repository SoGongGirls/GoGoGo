package com.example.gogogo.survey;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gogogo.R;

public class SurveyDessertBad extends AppCompatActivity {

    // 답변 사진
    ImageView select7;
    ImageView select8;
    ImageView select9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_dessert_bad);

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
        Intent intent = new Intent(getApplicationContext(), SurveyDessertSad.class);
        startActivity(intent);
    }

    public void selectEight(View view) {
        Intent intent = new Intent(getApplicationContext(), SurveyDessertAngry.class);
        startActivity(intent);
    }

    public void selectNine(View view) {
        Intent intent = new Intent(getApplicationContext(), SurveyDessertDepressed.class);
        startActivity(intent);
    }
}
