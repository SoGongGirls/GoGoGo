package com.example.gogogo.survey;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gogogo.R;

public class SurveyDessert extends AppCompatActivity {

    ImageView good;
    ImageView bad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_dessert);


        // 이미지뷰 동그랗게
        good = (ImageView) findViewById(R.id.good);
        good.setBackground(new ShapeDrawable(new OvalShape()));
        good.setClipToOutline(true);

        bad = (ImageView) findViewById(R.id.bad);
        bad.setBackground(new ShapeDrawable(new OvalShape()));
        bad.setClipToOutline(true);
    }

    public void selectGood(View view) {
        Intent intent = new Intent(getApplicationContext(), SurveyDessertGood.class);
        startActivity(intent);
    }

    public void selectBad(View view) {
        Intent intent = new Intent(getApplicationContext(), SurveyDessertBad.class);
        startActivity(intent);
    }
}
