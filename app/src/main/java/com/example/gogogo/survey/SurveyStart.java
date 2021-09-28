package com.example.gogogo.survey;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gogogo.R;

public class SurveyStart extends AppCompatActivity {

    ImageView start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_start);

        // 이미지뷰 동그랗게
        start = (ImageView) findViewById(R.id.start);
        start.setBackground(new ShapeDrawable(new OvalShape()));
        start.setClipToOutline(true);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SurveyStart2.class);
                startActivity(intent);
            }
        });
    }
}
