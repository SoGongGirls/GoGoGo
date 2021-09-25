package com.example.gogogo.survey;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gogogo.R;

public class Survey extends AppCompatActivity {

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

    int count1 = 0;   // 해산물 화면전환 조건
    int count2 = 0;   // 면 화면전환 조건
    int count3 = 0;   // 밥 화면전환 조건

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        count1 += 1;
        count3 += 1;
        if (count1 == 2) {
            question.setText("Q. 생선 VS 생선말고");

            select1.setImageResource(R.drawable.eel);
            select2.setImageResource(R.drawable.seafood);
            select3.setImageResource(R.drawable.white);
            select4.setImageResource(R.drawable.white);

            select1Tv.setText("생선");
            select2Tv.setText("생선말고");
            select3Tv.setText("");
            select4Tv.setText("");
        }
        else if (count3 == 3) {
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
        else {
            question.setText("Q. 돼지 VS 소 VS 닭");

            // 답변 사진
            select1.setImageResource(R.drawable.pork);
            select2.setImageResource(R.drawable.steak);
            select3.setImageResource(R.drawable.chicken);
            select4.setImageResource(R.drawable.lamb);

            // 답변 내용
            select1Tv.setText("돼지고기");
            select2Tv.setText("소고기");
            select3Tv.setText("닭고기");
            select4Tv.setText("다른거");
        }
    }

    public void selectTwo(View view) {
        count1 += 1;
        count2 += 1;
        if (count2 == 2) {
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
        else {
            question.setText("Q. 익힌 VS 안익힌");

            // 답변 사진
            select1.setImageResource(R.drawable.eel);
            select2.setImageResource(R.drawable.fish);
            select3.setImageResource(R.drawable.white);
            select4.setImageResource(R.drawable.white);

            // 답변 내용
            select1Tv.setText("익힌");
            select2Tv.setText("안익힌");
            select3Tv.setText("");
            select4Tv.setText("");
        }
    }

    public void selectThree(View view) {
        count3 += 1;
        if (count3 == 2) {
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
    }

    public void selectFour(View view) {
        count2 += 1;
        count3 += 1;
        question.setText("Q. 빵 VS 면 VS 밥 VS 떡·만두");

        // 답변 사진
        select1.setImageResource(R.drawable.hamburger);
        select2.setImageResource(R.drawable.ramen);
        select3.setImageResource(R.drawable.rice1);
        select4.setImageResource(R.drawable.tteokbokki);

        // 답변 내용
        select1Tv.setText("빵");
        select2Tv.setText("면");
        select3Tv.setText("밥");
        select4Tv.setText("떡 or 만두");
    }
}
