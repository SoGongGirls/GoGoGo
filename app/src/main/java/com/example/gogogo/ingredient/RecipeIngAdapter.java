package com.example.gogogo.ingredient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class RecipeIngAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<RecipeIngItem> items = new ArrayList<>();

    public RecipeIngAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount(){return items.size();}

    public void addItem(RecipeIngItem item){items.add(item);}

    @Override
    public Object getItem(int position){return items.get(position);}

    @Override
    public long getItemId(int position){return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        RecipeINGItemView view = null;
        if(convertView == null){
            view = new RecipeINGItemView(mContext);

        }else{
            view = (RecipeINGItemView) convertView;
        }

        RecipeIngItem item = items.get(position);
        view.setINGname(item.getName());
        view.setINGQuantity(item.getQuantity());

        return view;
    }
}
