package com.example.gogogo.ingredient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gogogo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recommend_recipe extends AppCompatActivity {

    SQLiteDatabase DB;
    String TAG="레시피 추천";
    ListView R_ListView;
    Intent intent;
    RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_recipe);

        intent = getIntent();
        String inputING = intent.getStringExtra("inputING");
        ArrayList<String> IngList = new ArrayList<String>(Arrays.asList(inputING.split(" ")));

        TextView bar_title = findViewById(R.id.toolbar_title2);
        bar_title.setText("재료 추천 레시피");

        R_ListView = (ListView) findViewById(R.id.R_ListView);
        adapter = new RecipeAdapter(getApplicationContext());

        //db실행
        RecipeDBHelper helper = new RecipeDBHelper(getApplicationContext());
        DB = helper.getWritableDatabase();
        if (DB != null) { Log.v(TAG, "DB열기 성공!");
        } else { Log.e(TAG, "DB열기 실패!"); }

        //추천 레시피 가져오기
        if (DB != null) {
            ArrayList<ArrayList> curList = new ArrayList<ArrayList>(); //[토마토rcode리스트, 계란rcode리스트]
            String IngredientTable= "recipe_ingredient";

            //재료별 rcode 저장
            for(int i=0; i<IngList.size(); i++){
                String query = "select rcode from "+IngredientTable+" where search_name=\""+IngList.get(i)+"\"";
                ArrayList<Integer> rcode = new ArrayList<Integer>();
                try {
                    Cursor cursor = DB.rawQuery(query, null);
                    Log.v(TAG, "조회된 데이터 수 : " + cursor.getCount());
                    cursor.moveToFirst();
                    int j = 0;
                    if (cursor.getCount() > 0) {
                        for (j = 0; j < cursor.getCount(); j++) {
                            rcode.add(cursor.getInt(0));
                            cursor.moveToNext();
                        }
                        curList.add(rcode);
                        cursor.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("SearchData", e.getMessage());
                }
            }

            //일치하는 rcode만 찾기
            Log.v(TAG, "일치하는 rcode 찾기");
            ArrayList<Integer> realRcode = new ArrayList<>();
            realRcode = curList.remove(0);
            realRcode = SearchRealRcode.searchVal(realRcode, curList);

            //real rcode 조회
            for(int k = 0; k < realRcode.size(); k++){
                //for(int k = 0; k < 14; k++){
                Log.v(TAG, String.valueOf(realRcode.size()));
                Log.v(TAG, "real rcode 조회");
                String q = "select name, foodtypename, rcode, image_url from recipe_info where rcode="+realRcode.get(k);
                //String q = "select name, foodtypename from recipe_info where rcode="+curList.get(0).get(k);
                Cursor cursor = DB.rawQuery(q, null);
                Log.v(TAG, "조회된 데이터 수 : " + cursor.getCount());
                cursor.moveToNext();
                String name = cursor.getString(0);
                String foodtypename = cursor.getString(1);
                int rcode = cursor.getInt(2);
                String img_url = cursor.getString(3);
                adapter.addItem(new RecipeItem(name, foodtypename, rcode, img_url ));
                cursor.close();
            }
            adapter.notifyDataSetChanged();
            R_ListView.setAdapter(adapter);
            Log.v(TAG, "gridview 적용");

        } else {
            Log.e(TAG, "추천레시피검색 오류 db없음.");
        }

        //상세페이지로 연결
        R_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent i_recipe = new Intent(getApplicationContext(), Recipe.class);
                RecipeItem item = (RecipeItem) adapter.getItem(i);
                String rcode = String.valueOf(item.getRcode());
                Log.v(TAG, "rcode는" + rcode);
                i_recipe.putExtra("rcode", rcode);
                startActivity(i_recipe);
            }
        });


    }
}