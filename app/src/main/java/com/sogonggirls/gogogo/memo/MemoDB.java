package com.sogonggirls.gogogo.memo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


//SQLiteOpenHelper 상속
public class MemoDB extends SQLiteOpenHelper {
    public MemoDB(@Nullable Context context) {
        super(context, "memo_test3.db", null, 1);
    }
    //테이블 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table memo(_id integer primary key autoincrement,title text,content text,wdate text)");
        db.execSQL("insert into memo(title, content,wdate) values('연어','연어 덮밥 만들기','2019/11/09 06:10:30')");
        db.execSQL("insert into memo(title, content,wdate) values('참치','참치 덮밥 만들기','2019/11/09 06:10:30')");
        db.execSQL("insert into memo(title, content,wdate) values('돈까스','돈까스 덮밥 만들기','2019/11/09 06:10:30')");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}