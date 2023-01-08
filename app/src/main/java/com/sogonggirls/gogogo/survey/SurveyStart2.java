package com.sogonggirls.gogogo.survey;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.sogonggirls.gogogo.R;

public class SurveyStart2 extends AppCompatActivity {

    ImageView meal;
    ImageView dessert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_start2);

        // 이미지뷰 동그랗게
        meal = (ImageView) findViewById(R.id.meal);
        meal.setBackground(new ShapeDrawable(new OvalShape()));
        meal.setClipToOutline(true);

        dessert = (ImageView) findViewById(R.id.dessert);
        dessert.setBackground(new ShapeDrawable(new OvalShape()));
        dessert.setClipToOutline(true);
    }

    public void selectMeal(View view) {
        Intent intent = new Intent(getApplicationContext(), SurveyMeal.class);
        startActivity(intent);
    }

    public void selectDessert(View view) {
        Intent intent = new Intent(getApplicationContext(), SurveyDessert.class);
        startActivity(intent);
    }
}