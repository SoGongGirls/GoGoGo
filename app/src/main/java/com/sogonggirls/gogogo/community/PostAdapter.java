package com.sogonggirls.gogogo.community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sogonggirls.gogogo.R;

import java.text.SimpleDateFormat;
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
//        TextView like = (TextView) convertView.findViewById(R.id.content_like);
        TextView date = (TextView) convertView.findViewById(R.id.content_date);

        PostItem item = datas.get(position);

        nickname.setText(item.getNickname());
        title.setText(item.getTitle());
//        like.setText("000");

//        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date from = .
//        String to = transFormat.format(from);

        date.setText(getTimestampToDate(item.getDate()));

        return convertView;
    }

    public static String getTimestampToDate(String timestampStr){
        long timestamp = Long.parseLong(timestampStr);
        Date date = new java.util.Date(timestamp*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+9"));
        String formattedDate = sdf.format(date);
        return formattedDate;
        //출처: https://aljjabaegi.tistory.com/460 [알짜배기 프로그래머]
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
