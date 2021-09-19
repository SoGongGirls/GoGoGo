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
    private ArrayList<StoreItem> listViewItemList =  new ArrayList<StoreItem>();

    public StoreListAdapter() { }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.store_item, parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.storeImage);
        TextView nameTextView = (TextView) convertView.findViewById(R.id.storeName);
        TextView gradeTextView = (TextView) convertView.findViewById(R.id.storeGrade);
        TextView distanceTextView = (TextView) convertView.findViewById(R.id.storeDistance);

        StoreItem listViewItem = listViewItemList.get(position);

        imageView.setImageDrawable(listViewItem.getStoreImage());
        nameTextView.setText(listViewItem.getStoreName());
        gradeTextView.setText(listViewItem.getStoreGrade());
        distanceTextView.setText(listViewItem.getStoreDistance());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    public void addItem(Drawable image, String name, String grade, String distance) {
        StoreItem item = new StoreItem();

        item.setStoreImage(image);
        item.setStoreName(name);
        item.setStoreGrade(grade);
        item.setStoreDistance(distance);

        listViewItemList.add(item);
    }
}
