package com.example.gogogo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gogogo.storeInfo.StoreDetail;
import com.example.gogogo.storeInfo.StoreList;
import com.example.gogogo.storeInfo.SurveyResult;

import org.w3c.dom.Text;

public class Cook_Yes extends AppCompatActivity {

    Button test1;
    Button test2;
    Button recipe_all;
    Button roulette;
    ImageView back;
    TextView toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_yes);

        /* 객체 초기화 */
        test1 = findViewById(R.id.test1);
        test2 = findViewById(R.id.test2);
        recipe_all = (Button) findViewById(R.id.recipe_all);
        roulette = (Button) findViewById(R.id.roulette);
        back = (ImageView) findViewById(R.id.back);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);

        toolbar_title.setText("요리할래");

        test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(view.getContext(), SurveyResult.class);
                startActivity(intent1);
            }
        });


//        test2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent2 = new Intent(view.getContext(), Store_detail_test.class);
//                startActivity(intent2);
//            }
//        });


        recipe_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Recipe_all.class);
                startActivity(intent);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        roulette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), Roulette.class);
                startActivity(i);
            }
        });

    }
}