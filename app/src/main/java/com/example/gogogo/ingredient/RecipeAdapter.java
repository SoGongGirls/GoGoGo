package com.example.gogogo.ingredient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class RecipeAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<RecipeItem> items = new ArrayList<>();

    public RecipeAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void addItem(RecipeItem item){
        items.add(item);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount(){return items.size();}

    @Override
    public Object getItem(int position){return items.get(position);}

    @Override
    public long getItemId(int position){return position;}

    public void removeItemAll(){
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecipeItemView view = null;
        if (convertView == null) {
            view = new RecipeItemView(mContext);
        } else {
            view = (RecipeItemView) convertView;
        }


        RecipeItem item = items.get(position);

        //데이터 값 표시하기
        view.setName(item.getName());
        view.setFoodType(item.getFoodtype());
        view.setImage(item.getImglink());

        return view;
    }
}
