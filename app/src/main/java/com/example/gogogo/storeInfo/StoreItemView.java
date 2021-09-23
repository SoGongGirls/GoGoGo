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

public class StoreItemView extends LinearLayout {
    ImageView imageView;   // 가게 사진
    TextView tvName;   // 가게 이름
    TextView tvGrade;   // 가게 등급
    TextView tvDistance;   // 거리

    public StoreItemView(Context context){
        super(context);
        init(context);
    }

    public StoreItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.store_item, this, true);

        imageView = findViewById(R.id.storeImage);
        tvName = findViewById(R.id.storeName);
        tvGrade = findViewById(R.id.storeGrade);
        tvDistance = findViewById(R.id.storeDistance);
    }

    public void setName(String name){
        tvName.setText(name);
    }

    public void setGrade(String grade){
        tvGrade.setText(grade);
    }

    public void setDistance(double distance){
        String distance2 = String.format("%.2f", distance);
        tvDistance.setText(distance2 + " km");
    }

    public void setImage(String url){
        Glide.with(this).load(url).into(imageView);
    }
}