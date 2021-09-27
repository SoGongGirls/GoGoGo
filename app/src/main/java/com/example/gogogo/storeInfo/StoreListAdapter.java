package com.example.gogogo.storeInfo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gogogo.R;

import java.util.ArrayList;

public class StoreListAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    public static ArrayList<StoreItem> items = new ArrayList<StoreItem>();


    public StoreListAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void addItem(StoreItem item) {
        items.add(item);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void removeItemAll(){
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StoreItemView view = null;
        if (convertView == null) {
            view = new StoreItemView(mContext);
        } else {
            view = (StoreItemView) convertView;
        }

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        StoreItem item = items.get(position);

        //데이터 값 표시하기
        view.setName(item.getStoreName());
        view.setGrade(item.getStoreGrade());
        view.setDistance(item.getStoreDistance());
        view.setImage(item.getStoreLogo());

        return view;
    }
}
