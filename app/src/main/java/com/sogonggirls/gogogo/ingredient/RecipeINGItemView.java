package com.sogonggirls.gogogo.ingredient;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.sogonggirls.gogogo.R;

public class RecipeINGItemView extends LinearLayout {
    TextView tvINGname;
    TextView tvINgquantity;

    public RecipeINGItemView(Context context){
        super(context);
        init(context);
    }

    public RecipeINGItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.recipe_ing_item, this, true);

        tvINGname = findViewById(R.id.tvINGname);
        tvINgquantity = findViewById(R.id.tvINGquentity);
    }

    public void setINGname(String name){tvINGname.setText(name);}
    public void setINGQuantity(String quantity){tvINgquantity.setText(quantity);}
}
