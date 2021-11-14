package com.example.gogogo.ingredient;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.gogogo.R;
import com.example.gogogo.bookmark.BookmarkQuery;

public class RecipeItemView extends LinearLayout {
    TextView tvName;
    TextView tvfoodtype;
    ImageView imageView;
    Button btnBookmark;

    public RecipeItemView(Context context){
        super(context);
        init(context);
    }

    public RecipeItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.recipe_item, this, true);

        tvName = findViewById(R.id.r_name);
        tvfoodtype = findViewById(R.id.r_type);
        imageView = findViewById(R.id.r_img);
        btnBookmark = (Button) findViewById(R.id.bookmark_icon);
    }

    public void setName(String name){
        tvName.setText(name);
    }

    public void setFoodType(String foodType){
        tvfoodtype.setText(foodType);
    }

    public void setImage(String url){
        imageView.setBackground(new ShapeDrawable(new OvalShape()));
        imageView.setClipToOutline(true);
        Glide.with(this).load(url).error(R.drawable.no_image).into(imageView);
    }
    public void setBook(int rcode){
        BookmarkQuery BQ = new BookmarkQuery(getContext());
        boolean check = BQ.checkBookmarkData(rcode);
        if (check){
            btnBookmark.setBackgroundResource(R.drawable.bookmark_selected);
        }else{
            btnBookmark.setBackgroundResource(R.drawable.bookmark_none);
        }

    }

}