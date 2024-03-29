package com.sogonggirls.gogogo.ingredient;

import android.graphics.drawable.Drawable;

public class IngredientItem {
    Drawable ingredient_img;    // 재료 이미지
    String ingredient_name;     // 재료 이름
    String ingredient_date;     // 재료 유통기한
    boolean check = false;

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

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}


