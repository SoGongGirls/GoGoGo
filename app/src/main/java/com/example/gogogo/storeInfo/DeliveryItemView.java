package com.example.gogogo.storeInfo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.gogogo.R;

public class DeliveryItemView extends LinearLayout {

    ImageView imageView;   // 가게 사진
    TextView tvName;   // 가게 이름
    TextView tvGrade;   // 가게 등급
    TextView tvTime;   // 배달 예상 시간


    public DeliveryItemView(Context context){
        super(context);
        init(context);
    }

    public DeliveryItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.delivery_item, this, true);

        imageView = findViewById(R.id.deliveryImage);
        tvName = findViewById(R.id.deliveryName);
        tvGrade = findViewById(R.id.deliveryGrade);
        tvTime = findViewById(R.id.deliveryTime);
    }

    public void setName(String name){
        tvName.setText(name);
    }

    public void setGrade(String grade){
        tvGrade.setText(grade);
    }

    public void setTime(double distance){
        String distance2 = String.format("%.2f", distance);
        tvTime.setText(distance2 + " km");
    }

    public void setImage(String url){
        Glide.with(this).load(url).into(imageView);
    }
}

