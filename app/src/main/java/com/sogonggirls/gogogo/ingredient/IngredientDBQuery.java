package com.sogonggirls.gogogo.ingredient;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class IngredientDBQuery {

    Context mContext = null;
    String TABLE_NAME = "ingredients";
    IngredientDBHelper dbHelper;
    SQLiteDatabase DB;
    String TAG = "ing db 쿼리";

    public IngredientDBQuery(Context context) {
        this.mContext = context;
    }

    //DB생성 or 열기
    public void openDB() {
        try {
            dbHelper = new IngredientDBHelper(this.mContext);
            DB = dbHelper.getWritableDatabase();
        } catch (Exception e) {
            Log.e(TAG, "Ingredient DB 오픈 에러");
        }
    }

    //데이터 저장
    public void insertData(String name, String date) {
        openDB();
        String sql = "insert into " + TABLE_NAME + " (name, expirations, image) values (\""
                + name + "\", \"" + date + "\", null )";
        DB.execSQL(sql);
    }

    //데이터 삭제
    public void deleteData(int id) {
        openDB();
        String sql = "delete from " + TABLE_NAME + " where _id = "
                + id;

        DB.execSQL(sql);
    }

    //전체 데이터 수
    public int countDB(){
        openDB();
        String[] token = null;
        String sql = "select count(*) from "+TABLE_NAME;
        Cursor result = DB.rawQuery(sql, token);
        result.moveToFirst();
        int count = result.getInt(0);
        result.close();
        return count;
    }

    public ArrayList<IngredientItem>  AllData(){
        openDB();
        ArrayList<IngredientItem> resultData= new ArrayList<>();
        String[] token = null;
        String sql = "select * from " + TABLE_NAME;

        Cursor result = DB.rawQuery(sql, token);
        result.moveToFirst();

        while(! result.isAfterLast()){
            String name = result.getString(1);
            String date = result.getString(2);

            IngredientItem item = new IngredientItem();
            item.setIngredient_date(date);
            item.setIngredient_name(name);

            resultData.add(item);
            result.moveToNext();
        }
        result.close();
        return resultData;
    }
}
