package com.example.gogogo.ingredient;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gogogo.R;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Ingredient_add extends AppCompatActivity {

    TextView toolbar_title;
    ImageView close;
    TextView dateSet;
    Button datePick;
    private DatePickerDialog.OnDateSetListener callbackMethod;
    Button submit;
    EditText name;
    String TAG=" Ingredient_add.java";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_add);

        /* 객체 초기화 */
        toolbar_title = findViewById(R.id.toolbar_title);
        close = findViewById(R.id.close);
        datePick = findViewById(R.id.datePick);

        submit = findViewById(R.id.submit);
        name = findViewById(R.id.ingredientSet);

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



        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //DB에 데이터 등록하기.
                IngredientDBQuery IQ = new IngredientDBQuery(getApplicationContext());
                String name2 = name.getText().toString();
                //2020년 8월 14일 -> 2020-08-04
                String date2 = dateSet.getText().toString().replace("년 ", "-").replace("월 " , "-").replace("일", "");
                Log.v(TAG, date2);
//                SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
//                Date date3 = null;
//                try {
//                    date3 = transFormat.parse(date2);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }

                IQ.insertData(name2, date2);
                Toast.makeText(getApplicationContext(), name2 +"등록 성공", Toast.LENGTH_SHORT).show();

                //인텐트 종료
                finish();
            }
       });

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











