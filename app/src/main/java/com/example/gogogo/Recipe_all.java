package com.example.gogogo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.gogogo.R;
import com.example.gogogo.ingredient.Recipe;
import com.example.gogogo.ingredient.RecipeAdapter;
import com.example.gogogo.ingredient.RecipeDBHelper;
import com.example.gogogo.ingredient.RecipeItem;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class Recipe_all extends AppCompatActivity {

    public static final String TAG ="태그 Recipe_all.java" ;
    private SQLiteDatabase database;
    private ListView recipe_list;
    private ImageView back2;
    private TextView toolbar_title;
    private RecipeAdapter adapter;
    private String query = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_all);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        /* 객체 초기화 */
        recipe_list = findViewById(R.id.recipe_list);
        back2 = (ImageView) findViewById(R.id.back2);
        toolbar_title = findViewById(R.id.toolbar_title2);

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        String tableName = "recipe_info";

        switch (category){
            case "all": toolbar_title.setText("전체 레시피");
                        break;
            case "초간단": toolbar_title.setText("초간단");
                        break;
            case "다이어트": toolbar_title.setText("다이어트");
                        break;
            case "손님접대": toolbar_title.setText("손님접대");
                        break;
            case "반찬": toolbar_title.setText("반찬");
                        break;
            case "술안주": toolbar_title.setText("술안주");
                        break;
            case "간식": toolbar_title.setText("간식");
                        break;
            case "나들이": toolbar_title.setText("나들이");
                        break;
            case "밥요리": toolbar_title.setText("밥요리");
                        break;
            case "면요리": toolbar_title.setText("면요리");
                        break;
            case "찌개/국": toolbar_title.setText("찌개/국");
                        break;
            default: toolbar_title.setText("전체 레시피");
                        break;
        }

        adapter = new RecipeAdapter(this);

        RecipeDBHelper helper = new RecipeDBHelper(getApplicationContext());
        database = helper.getWritableDatabase();
        if (database != null) { Log.v(TAG, "DB열기 성공!");
        } else { Log.e(TAG, "DB열기 실패!"); }


        if (database != null) {
            if (category.equals("all") ){
                query = "select name, foodtypename, rcode, image_url from "+tableName ;
            }else{
                query = "select name, foodtypename, rcode, image_url from "+ tableName
                        + " where foodtypename = \'" + category+ "\'  ";
            }
            Log.e(TAG, query);
            Cursor cursor = database.rawQuery(query, null);
            Log.v(TAG, "조회된 데이터 수 : " + cursor.getCount());

            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                String name = cursor.getString(0);
                String foodtypename = cursor.getString(1);
                int rcode = cursor.getInt(2);
                String img_url = cursor.getString(3);

                adapter.addItem(new RecipeItem(name, foodtypename, rcode, img_url ));
            }
            cursor.close();
        } else {
            Log.e(TAG, "selectData() db없음.");
        }
        recipe_list.setAdapter(adapter);


        recipe_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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