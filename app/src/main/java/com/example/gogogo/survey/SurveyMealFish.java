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

public class SurveyMealFish extends AppCompatActivity {

    // 질문
    TextView question;

    // 답변 사진
    ImageView select1;
    ImageView select2;

    // 답변 내용
    TextView select1Tv;
    TextView select2Tv;

    int count1 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_meal_fish);

        question = (TextView) findViewById(R.id.question);

        select1Tv = (TextView) findViewById(R.id.select1_tv);
        select2Tv = (TextView) findViewById(R.id.select2_tv);

        // 이미지뷰 동그랗게
        select1 = (ImageView) findViewById(R.id.select1);
        select1.setBackground(new ShapeDrawable(new OvalShape()));
        select1.setClipToOutline(true);

        select2 = (ImageView) findViewById(R.id.select2);
        select2.setBackground(new ShapeDrawable(new OvalShape()));
        select2.setClipToOutline(true);
    }

    public void selectOne(View view) {
        count1 += 1;

        if (count1 == 2) {
            MENU = new ArrayList<>();
            MENU.add("생선구이");
            MENU.add("코다리");
            MENU.add("아구찜");
            MENU.add("매운탕");
            MENU.add("장어");
            MENU.add("추어탕");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
        }
        else {
            question.setText("Q. 생선 VS 생선말고");

            // 답변 사진
            select1.setImageResource(R.drawable.eel);
            select2.setImageResource(R.drawable.seafood);

            // 답변 내용
            select1Tv.setText("생선");
            select2Tv.setText("생선말고");
        }
    }

    public void selectTwo(View view) {
        count1 += 1;

        if (count1 == 2) {
            MENU = new ArrayList<>();
            MENU.add("쭈꾸미/낙지");
            MENU.add("해물탕");
            MENU.add("대하");
            MENU.add("조개구이");
            MENU.add("대게");
            MENU.add("해물찜");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
        }
        else {
            MENU = new ArrayList<>();
            MENU.add("초밥");
            MENU.add("게장");
            MENU.add("회/물회");
            MENU.add("연어");
            MENU.add("산낙지");
            MENU.add("꼬막");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
        }
    }
}
