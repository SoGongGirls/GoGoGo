package com.example.gogogo.ingredient;

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

import androidx.core.content.ContextCompat;

import com.example.gogogo.R;
import com.example.gogogo.storeInfo.StoreItem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        Button check_icon = (Button) convertView.findViewById(R.id.check_icon);
        TextView ingredient_date_over = (TextView) convertView.findViewById(R.id.ingredient_date_over);

        IngredientItem ingredientItem = ingredientsList.get(position);

        ingredient_img.setImageDrawable(ingredientItem.getIngredient_img());
        ingredient_name.setText(ingredientItem.getIngredient_name());
        String ing_date = ingredientItem.getIngredient_date(); // 2020년 09월 10일
        ingredient_date.setText(ing_date);

        //날짜 제대로 바꿔주기
        //2020년 08월 14일 -> 2020-08-04
        String dateNew = ing_date.replace("년 ", "-").replace("월 ", "-").replace("일", "");
        Log.v("ING 어댑터 => dateNew : ", dateNew);

        //시간경과 표시
        long now = System.currentTimeMillis();
        Date dateNow = new Date(now);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        long nResult = 0;
        try {
            Date dateCreated = dateFormat.parse(dateNew);
            long duration = dateNow.getTime() - dateCreated.getTime();
            nResult = duration / (60 * 60000 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (nResult >= 7) {
            //날짜를 구해서 보여주자
            ingredient_date_over.setText("구매한 지 " + nResult + "일이 지났습니다.");
        } else {
            //텍스트 뷰 안보이게
            ingredient_date_over.setVisibility(View.GONE);
        }


        //재료 추가 버튼을 눌렀을 경우
        check_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ingredientItem.isCheck()) { // true 추가된 상태 => 삭제한다.
                    ingredientItem.setCheck(false);
                    check_icon.setBackgroundResource(R.drawable.round2);
                    check_icon.setText("추가");
                    check_icon.setTextColor(Color.parseColor("#ffffff"));
                } else {//false 일때 => 추가한다.
                    ingredientItem.setCheck(true);
                    check_icon.setBackgroundResource(R.drawable.round3);
                    check_icon.setText("제거");
                    check_icon.setTextColor(Color.parseColor("#f8730a"));
                }
            }
        });

        return convertView;
    }

    public void addItem(Drawable img, String name, String date) {
        IngredientItem item = new IngredientItem();

        item.setIngredient_img(img);
        item.setIngredient_name(name);
        item.setIngredient_date(date);
        ingredientsList.add(item);
    }
}
