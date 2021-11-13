package com.example.gogogo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

        /* <- 객체 초기화 */
        recipe_all = (Button) findViewById(R.id.recipe_all);
        back2 = (ImageView) findViewById(R.id.back2);
        toolbar_title2 = (TextView) findViewById(R.id.toolbar_title2);

        LinearLayout ly_simple = (LinearLayout) findViewById(R.id.ly_simple);
        LinearLayout ly_diet = (LinearLayout) findViewById(R.id.ly_diet);
        LinearLayout ly_guest = (LinearLayout) findViewById(R.id.ly_guest);
        LinearLayout ly_sub = (LinearLayout) findViewById(R.id.ly_sub);
        LinearLayout ly_alcole = (LinearLayout) findViewById(R.id.ly_alcole);

        LinearLayout ly_snack = (LinearLayout) findViewById(R.id.ly_snack);
        LinearLayout ly_picnic = (LinearLayout) findViewById(R.id.ly_picnic);
        LinearLayout ly_rice = (LinearLayout) findViewById(R.id.ly_rice);
        LinearLayout ly_noodle = (LinearLayout) findViewById(R.id.ly_noodle);
        LinearLayout ly_soop = (LinearLayout) findViewById(R.id.ly_soop);
        /* 객체 초기화 ->*/

        toolbar_title2.setText("요리할래");

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        recipe_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Recipe_all.class);
                intent.putExtra("category", "all");
                startActivity(intent);
            }
        });
        ly_simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Recipe_all.class);
                intent.putExtra("category", "초간단");
                startActivity(intent);
            }
        });
        ly_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Recipe_all.class);
                intent.putExtra("category", "다이어트");
                startActivity(intent);
            }
        });
        ly_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Recipe_all.class);
                intent.putExtra("category", "손님접대");
                startActivity(intent);
            }
        });
        ly_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Recipe_all.class);
                intent.putExtra("category", "반찬");
                startActivity(intent);
            }
        });
        ly_alcole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Recipe_all.class);
                intent.putExtra("category", "술안주");
                startActivity(intent);
            }
        });
        ly_snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Recipe_all.class);
                intent.putExtra("category", "간식");
                startActivity(intent);
            }
        });
        ly_picnic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Recipe_all.class);
                intent.putExtra("category", "나들이");
                startActivity(intent);
            }
        });
        ly_rice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Recipe_all.class);
                intent.putExtra("category", "밥요리");
                startActivity(intent);
            }
        });
        ly_noodle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Recipe_all.class);
                intent.putExtra("category", "면요리");
                startActivity(intent);
            }
        });
        ly_soop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Recipe_all.class);
                intent.putExtra("category", "찌개/국");
                startActivity(intent);
            }
        });





    }
}