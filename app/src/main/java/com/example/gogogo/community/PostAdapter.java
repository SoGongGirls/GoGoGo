package com.example.gogogo.community;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gogogo.R;
import com.example.gogogo.ingredient.IngredientItem;
import com.example.gogogo.ingredient.ProcessItem;
import com.example.gogogo.ingredient.ProcessItemView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostAdapter extends BaseAdapter {

    private List<PostItem> datas;
    public PostAdapter(List<PostItem> datas){
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.community_item, parent, false);
        }

        TextView nickname = (TextView) convertView.findViewById(R.id.content_nick);
        TextView title = (TextView) convertView.findViewById(R.id.content_title);
        TextView like = (TextView) convertView.findViewById(R.id.content_like);
        TextView date = (TextView) convertView.findViewById(R.id.content_date);

        PostItem item = datas.get(position);

        nickname.setText(item.getNickname());
        title.setText(item.getTitle());
        like.setText("000");

        Date from = new Date();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String to = transFormat.format(from);

        date.setText(to);

        return convertView;
    }

//    public void addItem(Drawable img, String name, String date) {
//        IngredientItem item = new IngredientItem();
//
//        item.setIngredient_img(img);
//        item.setIngredient_name(name);
//        item.setIngredient_date(date);
//        ingredientsList.add(item);
//    }
}
