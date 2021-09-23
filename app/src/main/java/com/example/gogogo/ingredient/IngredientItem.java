package com.example.gogogo.ingredient;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class IngredientItem {
    Drawable ingredient_img;    // 재료 이미지
    String ingredient_name;     // 재료 이름
    String ingredient_date;     // 재료 유통기한
    Drawable ingredient_icon;   // 재료 선택 아이콘
    boolean check;


    public Drawable getIngredient_img() {
        return ingredient_img;
    }

    public void setIngredient_img(Drawable ingredient_img) {
        this.ingredient_img = ingredient_img;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) { this.ingredient_name = ingredient_name; }

    public String getIngredient_date() { return ingredient_date; }

    public void setIngredient_date(String ingredient_date) { this.ingredient_date = ingredient_date; }

    public Drawable getIngredient_icon() {
        return ingredient_icon;
    }

    public void setIngredient_icon(Drawable ingredient_icon) { this.ingredient_icon = ingredient_icon; }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}


