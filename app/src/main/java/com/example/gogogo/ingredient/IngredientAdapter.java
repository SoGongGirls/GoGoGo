package com.example.gogogo.ingredient;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gogogo.R;
import com.example.gogogo.storeInfo.StoreItem;

import java.util.ArrayList;

public class IngredientAdapter extends BaseAdapter {

    /* 재료 데이터 저장을 위한 Array List */
    private ArrayList<IngredientItem> ingredientsList = new ArrayList<>();

    public IngredientAdapter() {

    }

    @Override
    public int getCount() {
         return ingredientsList.size();
    }

    @Override
    public Object getItem(int position) {
        return ingredientsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.ingredient_item, parent, false);
        }

        ImageView ingredient_img = (ImageView) convertView.findViewById(R.id.ingredient_img);
        TextView ingredient_name = (TextView) convertView.findViewById(R.id.ingredient_name);
        TextView ingredient_date = (TextView) convertView.findViewById(R.id.ingredient_date);
        ImageView ingredient_icon = (ImageView) convertView.findViewById(R.id.ingredient_icon);

        IngredientItem ingredientItem = ingredientsList.get(position);

        ingredient_img.setImageDrawable(ingredientItem.getIngredient_img());
        ingredient_name.setText(ingredientItem.getIngredient_name());
        ingredient_date.setText(ingredientItem.getIngredient_date());
        ingredient_icon.setImageDrawable(ingredientItem.getIngredient_icon());

        return convertView;
    }

    public void addItem(Drawable img, String name, String date, Drawable icon) {
        IngredientItem item = new IngredientItem();

        item.setIngredient_img(img);
        item.setIngredient_name(name);
        item.setIngredient_date(date);
        item.setIngredient_icon(icon);

        ingredientsList.add(item);
    }
}
