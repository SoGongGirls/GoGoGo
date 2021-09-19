package com.example.gogogo.storeInfo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.example.gogogo.R;

public class StoreList extends ListFragment {
    StoreListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        adapter = new StoreListAdapter();
        setListAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_launcher_background),
                "식당1", "4.1", "1.2km");

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_launcher_background),
                "식당2", "3.0", "583m") ;

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick (ListView l, View v, int position, long id) {
        StoreItem item = (StoreItem) l.getItemAtPosition(position) ;

        String nameStr = item.getStoreName();
        String gradeStr = item.getStoreGrade();
        String distanceStr = item.getStoreDistance();
        Drawable imageDrawable = item.getStoreImage();

    }

    public void addItem(Drawable image, String name, String grade, String distance) {
        adapter.addItem(image, name, grade, distance);
    }
}
