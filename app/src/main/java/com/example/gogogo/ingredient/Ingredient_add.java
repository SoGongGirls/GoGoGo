package com.example.gogogo.ingredient;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gogogo.R;

import org.w3c.dom.Text;

import java.util.Calendar;

public class Ingredient_add extends AppCompatActivity {

    TextView toolbar_title;
    ImageView close;
    TextView dateSet;
    Button datePick;
    private DatePickerDialog.OnDateSetListener callbackMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_add);

        /* 객체 초기화 */
        toolbar_title = findViewById(R.id.toolbar_title);
        close = findViewById(R.id.close);
        datePick = findViewById(R.id.datePick);

        toolbar_title.setText("재료 추가하기");  // 제목 설정

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickHandler(datePick);
            }
        });
        InitializeView();
        InitializeListener();

    }

    public void InitializeView()
    {
        dateSet = findViewById(R.id.dateSet);
    }

    public void InitializeListener()
    {
        callbackMethod = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                dateSet.setText(String.format("%d년 %d월 %d일", year, month+1, day));
            }
        };
    }

    public void OnClickHandler(View view)
    {
        Calendar cal = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod,
                cal.get(Calendar.YEAR),  cal.get(Calendar.MONTH), cal.get(Calendar.DATE)

        );
        dialog.show();
    }




}











