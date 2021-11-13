package com.example.gogogo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
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

    Button recipe_all;
    ImageView back2;
    TextView toolbar_title2;

    ImageView cook1;
    ImageView cook2;
    ImageView cook3;
    ImageView cook4;
    ImageView cook5;
    ImageView cook6;
    ImageView cook7;
    ImageView cook8;
    ImageView cook9;
    ImageView cook10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_yes);

        /* 객체 초기화 */
        recipe_all = (Button) findViewById(R.id.recipe_all);
        back2 = (ImageView) findViewById(R.id.back2);
        toolbar_title2 = (TextView) findViewById(R.id.toolbar_title2);

        toolbar_title2.setText("요리할래");

        cook1 = (ImageView) findViewById(R.id.cook1);
        cook2 = (ImageView) findViewById(R.id.cook2);
        cook3 = (ImageView) findViewById(R.id.cook3);
        cook4 = (ImageView) findViewById(R.id.cook4);
        cook5 = (ImageView) findViewById(R.id.cook5);
        cook6 = (ImageView) findViewById(R.id.cook6);
        cook7 = (ImageView) findViewById(R.id.cook7);
        cook8 = (ImageView) findViewById(R.id.cook8);
        cook9 = (ImageView) findViewById(R.id.cook9);
        cook10 = (ImageView) findViewById(R.id.cook10);


        cook1.setBackground(new ShapeDrawable(new OvalShape()));
        cook1.setClipToOutline(true);

        cook2.setBackground(new ShapeDrawable(new OvalShape()));
        cook2.setClipToOutline(true);

        cook3.setBackground(new ShapeDrawable(new OvalShape()));
        cook3.setClipToOutline(true);

        cook4.setBackground(new ShapeDrawable(new OvalShape()));
        cook4.setClipToOutline(true);

        cook5.setBackground(new ShapeDrawable(new OvalShape()));
        cook5.setClipToOutline(true);

        cook6.setBackground(new ShapeDrawable(new OvalShape()));
        cook6.setClipToOutline(true);

        cook7.setBackground(new ShapeDrawable(new OvalShape()));
        cook7.setClipToOutline(true);

        cook8.setBackground(new ShapeDrawable(new OvalShape()));
        cook8.setClipToOutline(true);

        cook9.setBackground(new ShapeDrawable(new OvalShape()));
        cook9.setClipToOutline(true);

        cook10.setBackground(new ShapeDrawable(new OvalShape()));
        cook10.setClipToOutline(true);


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


        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }
}