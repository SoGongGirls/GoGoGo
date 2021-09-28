package com.example.gogogo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.gogogo.R;
import com.example.gogogo.ingredient.RecipeAdapter;
import com.example.gogogo.ingredient.RecipeItem;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class Recipe_all extends AppCompatActivity {

    public static final String TAG ="태그 Recipe_all.java" ;
    SQLiteDatabase database;
    ListView recipe_list;
    ImageView back;
    TextView toolbar_title;
    RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_all);

        /* 객체 초기화 */
        recipe_list = findViewById(R.id.recipe_list);
        back = findViewById(R.id.back);
        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("전체 레시피");


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        adapter = new RecipeAdapter(this);

        //맨처음 초기화 데이터 보여주기(select)
        if (database != null) {
            String tableName = "recipe_info";
            String query = "select name, foodtypename, rcode, image_url from "+tableName ;
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

    }
}