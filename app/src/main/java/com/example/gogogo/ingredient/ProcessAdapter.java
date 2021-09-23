package com.example.gogogo.ingredient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ProcessAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<ProcessItem> items = new ArrayList<>();

    public ProcessAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount(){return items.size();}

    public void addItem(ProcessItem item){items.add(item);}

    @Override
    public Object getItem(int position){return items.get(position);}

    @Override
    public long getItemId(int position){return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ProcessItemView view = null;
        if(convertView == null){
            view = new ProcessItemView(mContext);

        }else{
            view = (ProcessItemView) convertView;
        }
        ProcessItem item = items.get(position);
        view.setOrder(item.getOrder());
        view.setImage(item.getImg_url());
        view.setExplain(item.getExplain());
        view.setTip(item.getTip());

        return view;
    }
}