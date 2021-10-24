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

public class SurveyMealElse extends AppCompatActivity {

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

    int count2 = 0;
    int count3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_meal_else);

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
        count2 += 1;
        count3 += 1;
        if (count2 == 2 && count3 != 3) {
            MENU = new ArrayList<>();
            MENU.add("라면");
            MENU.add("짬뽕");
            MENU.add("우동");
            MENU.add("라멘");
            MENU.add("물냉면");
            MENU.add("국수");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
        }
        else if (count3 == 2) {
            question.setText("Q. 뜨끈한국물과 VS 국물말고");

            // 답변 사진
            select1.setImageResource(R.drawable.jjigae);
            select2.setImageResource(R.drawable.rice2);
            select3.setImageResource(R.drawable.white);
            select4.setImageResource(R.drawable.white);

            // 답변 내용
            select1Tv.setText("뜨끈한국물과");
            select2Tv.setText("국물말고");
            select3Tv.setText("");
            select4Tv.setText("");
        }
        else if (count3 == 3) {
            MENU = new ArrayList<>();
            MENU.add("부대찌개");
            MENU.add("김치찌개");
            MENU.add("국밥");
            MENU.add("육개장/순두부찌개");
            MENU.add("감자탕");
            MENU.add("된장찌개");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
        }
        else{
            MENU = new ArrayList<>();
            MENU.add("샌드위치");
            MENU.add("햄버거");
            MENU.add("피자");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
        }
    }

    public void selectTwo(View view) {
        count2 += 1;
        count3 += 1;
        if (count2 == 2 && count3 != 3) {
            MENU = new ArrayList<>();
            MENU.add("쫄면");
            MENU.add("짜장면");
            MENU.add("파스타");
            MENU.add("비빔냉면");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
        }
        else if (count3 == 2) {
            MENU = new ArrayList<>();
            MENU.add("호박죽");
            MENU.add("팥죽");
            MENU.add("전복죽");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
        }
        else if (count3 == 3) {
            MENU = new ArrayList<>();
            MENU.add("컵밥/밥버거");
            MENU.add("덮밥");
            MENU.add("카레");
            MENU.add("김밥");
            MENU.add("볶음밥");
            MENU.add("백반");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
        }
        else {
            question.setText("Q. 국물 VS 안국물");

            // 답변 사진
            select1.setImageResource(R.drawable.ramen);
            select2.setImageResource(R.drawable.jajangmyeon);
            select3.setImageResource(R.drawable.white);
            select4.setImageResource(R.drawable.white);

            // 답변 내용
            select1Tv.setText("국물");
            select2Tv.setText("안국물");
            select3Tv.setText("");
            select4Tv.setText("");
        }
    }

    public void selectThree(View view) {
        count3 += 1;
        question.setText("Q. 밥 VS 죽");

        // 답변 사진
        select1.setImageResource(R.drawable.rice1);
        select2.setImageResource(R.drawable.juk);
        select3.setImageResource(R.drawable.white);
        select4.setImageResource(R.drawable.white);

        // 답변 내용
        select1Tv.setText("밥");
        select2Tv.setText("죽");
        select3Tv.setText("");
        select4Tv.setText("");
    }

    public void selectFour(View view) {
        MENU = new ArrayList<>();
        MENU.add("떡볶이");
        MENU.add("만두");
        MENU.add("튀김");
        MENU.add("순대");
        MENU.add("어묵");

        Intent intent = new Intent(getApplicationContext(), Roulette.class);
        startActivity(intent);
    }
}
