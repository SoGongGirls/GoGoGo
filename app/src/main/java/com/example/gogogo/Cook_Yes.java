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

    Button recipe_all;
    ImageView back2;
    TextView toolbar_title2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_yes);

        /* 객체 초기화 */
        recipe_all = (Button) findViewById(R.id.recipe_all);
        back2 = (ImageView) findViewById(R.id.back2);
        toolbar_title2 = (TextView) findViewById(R.id.toolbar_title2);

        toolbar_title2.setText("요리할래");


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