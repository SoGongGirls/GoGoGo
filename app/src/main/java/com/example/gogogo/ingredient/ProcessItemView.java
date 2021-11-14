package com.example.gogogo.ingredient;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.gogogo.R;

import java.io.InputStream;
import java.net.URL;

public class ProcessItemView extends LinearLayout {
    TextView tvOrder;
    ImageView Process_IMG;
    TextView tvExplain;
    TextView tvTip;

    public ProcessItemView(Context context){
        super(context);
        init(context);
    }

    public ProcessItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_process_item, this, true);

        tvOrder = findViewById(R.id.tvOrder);
        Process_IMG = findViewById(R.id.Process_IMG);
        tvExplain = findViewById(R.id.tvExplain);
        tvTip = findViewById(R.id.tvTip);
    }

    public void setOrder(String num){tvOrder.setText(num);}
    public void setExplain(String explain){tvExplain.setText(explain);}
    public void setTip(String tip){
        if (tip == null) tvTip.setVisibility(View.GONE);
        else tvTip.setText(tip);
    }
    public void setImage(String url){
        Process_IMG.setBackground(new ShapeDrawable(new OvalShape()));
        Process_IMG.setClipToOutline(true);
        if (url == null) Process_IMG.setVisibility(View.GONE);
        else Glide.with(this).load(url).error(R.drawable.no_image).into(Process_IMG);

    }

    private Drawable loadImage(String url){
        try{
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "srcName");
            return d;
        }catch (Exception e){
            return null;
        }
    }

}
