package com.sogonggirls.gogogo.storeInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class DeliveryListAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    public static ArrayList<DeliveryItem> items = new ArrayList<DeliveryItem>();


    public DeliveryListAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void addItem(DeliveryItem item) {
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
        DeliveryItemView view = null;
        if (convertView == null) {
            view = new DeliveryItemView(mContext);
        } else {
            view = (DeliveryItemView) convertView;
        }

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        DeliveryItem item = items.get(position);

        //데이터 값 표시하기
        view.setName(item.getDeliveryName());
        view.setGrade(item.getDeliveryGrade());
        view.setTime(item.getDeliveryTime());
        view.setImage(item.getDeliveryLogo());

        return view;
    }
}
