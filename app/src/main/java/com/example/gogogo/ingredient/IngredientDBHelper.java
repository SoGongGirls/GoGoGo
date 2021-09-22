package com.example.gogogo.ingredient;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.audiofx.DynamicsProcessing;
import android.util.Log;

public class IngredientDBHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "IngredientDB.db";
    public static int VERSION = 1;
    public static String TAG ="재료 DB Helper"; //  Log.cat 확인용

    public IngredientDBHelper(Context context){
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v(TAG, "onCreate 호출");
        String sql = "create table if not exists ingredients("
                + "_id integer PRIMARY KEY autoincrement, "
                + "name text not null, expirations date, image text)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(TAG, "onUpgrade"+oldVersion+" ->"+newVersion);
        if (newVersion >1){
            Log.v(TAG, "기존 DB는 제거합니다.");
            db.execSQL("DROP TABLE IF EXISTS ingredients");
        }
    }
}
