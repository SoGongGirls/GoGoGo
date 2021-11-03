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

public class SurveyDessertGood extends AppCompatActivity {

    // 질문
    TextView question2;

    // 답변 사진
    ImageView select5;
    ImageView select6;

    // 답변 내용
    TextView select5Tv;
    TextView select6Tv;

    int count5 = 0;   // 기분좋아-찬거 조건
    int count6 = 0;   // 기분좋아-따뜻한거 조건

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_dessert_good);

        question2 = (TextView) findViewById(R.id.question2);

        select5Tv = (TextView) findViewById(R.id.select5_tv);
        select6Tv = (TextView) findViewById(R.id.select6_tv);

        // 이미지뷰 동그랗게
        select5 = (ImageView) findViewById(R.id.select5);
        select5.setBackground(new ShapeDrawable(new OvalShape()));
        select5.setClipToOutline(true);

        select6 = (ImageView) findViewById(R.id.select6);
        select6.setBackground(new ShapeDrawable(new OvalShape()));
        select6.setClipToOutline(true);
    }

    public void selectFive(View view) {
        count5 += 1;
        count6 += 1;
        if (count5 == 2) {
            question2.setText("Q. 스무디 VS 라떼");

            // 답변 사진
            select5.setImageResource(R.drawable.smoothie);
            select6.setImageResource(R.drawable.latte);

            // 답변 내용
            select5Tv.setText("스무디");
            select6Tv.setText("라떼");
        }
        else if (count6 == 3) {
            MENU = new ArrayList<>();
            MENU.add("토스트");
            MENU.add("샌드위치");
            MENU.add("샐러드");
            MENU.add("핫도그");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
        }
        else if (count6 == 2) {
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
        else if (count5 == 3) {
            MENU = new ArrayList<>();
            MENU.add("프라푸치노");
            MENU.add("스무디");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
        }
        else {
            question2.setText("Q. 맛 VS 칼로리");

            select5.setImageResource(R.drawable.fiona_person);
            select6.setImageResource(R.drawable.fiona_monster);

            select5Tv.setText("맛");
            select6Tv.setText("칼로리");
        }
    }

    public void selectSix(View view) {
        count6 += 1;
        if (count6 == 2 && count5 != 1) {
            question2.setText("Q. 토스트 VS 스프");

            // 답변 사진
            select5.setImageResource(R.drawable.toast);
            select6.setImageResource(R.drawable.soup);

            // 답변 내용
            select5Tv.setText("토스트");
            select6Tv.setText("스프");
        }
        else if (count5 == 1) {
            MENU = new ArrayList<>();
            MENU.add("콜드브루");
            MENU.add("아이스아메리카노");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
        }
        else if (count6 == 3 && count5 != 2) {
            MENU = new ArrayList<>();
            MENU.add("크림스프");
            MENU.add("감자스프");
            MENU.add("옥수수스프");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
        }
        else if (count6 == 3) {
            MENU = new ArrayList<>();
            MENU.add("오곡라떼");
            MENU.add("돌체라떼");
            MENU.add("스무디");
            MENU.add("밀크티");

            Intent intent = new Intent(getApplicationContext(), Roulette.class);
            startActivity(intent);
        }
        else {
            question2.setText("Q. 단 VS 짠");

            // 답변 사진
            select5.setImageResource(R.drawable.dounet);
            select6.setImageResource(R.drawable.toast);

            // 답변 내용
            select5Tv.setText("단거");
            select6Tv.setText("짠거");
        }
    }
}
