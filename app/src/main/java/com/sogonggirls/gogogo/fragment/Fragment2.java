package com.sogonggirls.gogogo.fragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.sogonggirls.gogogo.R;
import com.sogonggirls.gogogo.ingredient.IngredientAdapter;
import com.sogonggirls.gogogo.ingredient.IngredientDBQuery;
import com.sogonggirls.gogogo.ingredient.IngredientItem;
import com.sogonggirls.gogogo.ingredient.Ingredient_add;
import com.sogonggirls.gogogo.ingredient.Recommend_recipe;

import java.util.ArrayList;

public class Fragment2 extends Fragment {

    IngredientAdapter adapter;
    ListView ingredient_list;
    ImageView ingredient_add;
    ImageView ingredient_refresh;
    String TAG = "프래그먼트2";
    SQLiteDatabase DB;
    Button recipe_search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);

        /* 객체 초기화 */
        adapter = new IngredientAdapter();
        ingredient_list = (ListView) view.findViewById(R.id.ingredient_list);
        ingredient_add = (ImageView) view.findViewById(R.id.ingredient_add);
        ingredient_refresh = (ImageView) view.findViewById(R.id.ingredient_refresh);
        recipe_search = (Button) view.findViewById(R.id.recipe_search);

        ingredient_list.setAdapter(adapter);  // 리스트뷰 어댑터 설정

        // 재료 추가버튼
        ingredient_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Ingredient_add.class);
                startActivity(intent);
            }
        });

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_launcher_background),
                "아보카도", "2021년 09월 19일");

        // 새로고침 버튼
        ingredient_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getActivity().getIntent();
                getActivity().finish();
                startActivity(intent);
            }
        });

        // 보여줄 데이터 가져오기
        IngredientDBQuery IQ = new IngredientDBQuery(getActivity());
        ArrayList<IngredientItem> items = IQ.AllData();
        Log.v(TAG, ""+IQ.countDB());
        if (IQ.countDB() >0){
            for(int i=0; i<IQ.countDB() ; i++){
                IngredientItem item = items.get(i);
                String date = item.getIngredient_date().replaceFirst("-", "년 ").replaceFirst("-", "월 ")+"일";
                adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_launcher_background),
                        item.getIngredient_name(),date);
            }

        }

        // 레시피 검색
        recipe_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //선택한 재료 이름 가져오기
                String inputING="";
                for(int i=0; i<adapter.getCount(); i++){
                    IngredientItem item = (IngredientItem) adapter.getItem(i);
                    if(item.isCheck()){
                        //true 일때,
                        inputING += item.getIngredient_name();
                        inputING += " ";
                    }
                }
                Log.v(TAG, inputING);

                if(inputING == ""){
                    Toast.makeText(getContext(), "재료를 1개 이상 선택하세요.", Toast.LENGTH_SHORT).show();
                } else{
                    Intent intent2 = new Intent(getActivity(), Recommend_recipe.class);
                    intent2.putExtra("inputING", inputING);
                    startActivity(intent2);
                }
            }
        });

        return view;

    }// OnCreateView
}
