package com.sogonggirls.gogogo.ingredient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.sogonggirls.gogogo.R;
import com.sogonggirls.gogogo.bookmark.BookmarkQuery;

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
        view.setBook(item.getRcode());

        //즐겨찾기 구현
        Button btnBookmark = (Button) view.findViewById(R.id.bookmark_icon);
        RecipeItem finalItem = item;
        btnBookmark.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                BookmarkQuery BQ = new BookmarkQuery(mContext);
                boolean check = BQ.checkBookmarkData(finalItem.getRcode());

                if (check == false){
                    //즐겨찾기를 실행
                    BQ.insertBookmarkRcode(finalItem.getRcode());
                    Toast.makeText(mContext, finalItem.getName()+"을/를 북마크에 추가했습니다.", Toast.LENGTH_LONG).show();
                    btnBookmark.setBackgroundResource(R.drawable.bookmark_selected);
                }else if (check== true){
                    //즐겨찾기 해제
                    BQ.deleteBookmarkRcode(finalItem.getRcode());
                    Toast.makeText(mContext, finalItem.getName()+"을/를 북마크에서 삭제했습니다.", Toast.LENGTH_LONG).show();
                    btnBookmark.setBackgroundResource(R.drawable.bookmark_none);
                }

            }
        });

        return view;
    }
}
