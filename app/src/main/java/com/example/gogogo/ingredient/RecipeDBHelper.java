package com.example.gogogo.ingredient;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class RecipeDBHelper extends SQLiteOpenHelper {

    static String NAME = "recipe3.db";//"testdb.sqlite"; //to-do
    static SQLiteDatabase.CursorFactory FACTORY = null;
    static String PACKEGE = "com.example.gogogo"; //to-do
    static String DB = "recipe3.db"; //To-do
    static int VERSION  = 1;
    String TAG = "레시피db헬퍼";

    public RecipeDBHelper(Context context) {
        super(context, NAME, FACTORY, VERSION);
        // TODO Auto-generated constructor stub
        try {
            boolean bResult = isCheckDB(context);  // DB가 있는지?
            Log.i(TAG, "DB Check="+bResult);
            if(!bResult){   // DB가 없으면 복사
                copyDB(context);
            }else{
            }

        } catch (Exception e) {
        }

    }

    // DB가 있나 체크함수
    public boolean isCheckDB(Context mContext){

        String filePath = "/data/data/" + PACKEGE + "/databases/" + DB;
        File file = new File(filePath);

        if (file.exists()) {
            return true;
        }
        return false;
    }

    // DB를 복사하기
    // assets의 /db/xxxx.db 파일을 설치된 프로그램의 내부 DB공간으로 복사하기

    public void copyDB(Context mContext){

        Log.d(TAG, "copyDB실행");

        AssetManager manager = mContext.getAssets();
        String folderPath = "/data/data/" + PACKEGE + "/databases";
        String filePath = "/data/data/" + PACKEGE + "/databases/" +DB;
        File folder = new File(folderPath);
        File file = new File(filePath);

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            //InputStream is = manager.open("db/" + DB);
            InputStream is = manager.open(DB); //나는 애샛폴더에 바로 DB가 있으니까!
            Log.v(TAG, "1");
            BufferedInputStream bis = new BufferedInputStream(is);
            Log.v(TAG, "2");
            if (folder.exists()) {

            }else{
                folder.mkdirs();
                Log.v(TAG, "3");
            }


            if (file.exists()) {
                file.delete();
                Log.v(TAG, "4");
                file.createNewFile();
            }
            Log.v(TAG, "5");

            fos = new FileOutputStream(file);//오류 발생 추정
            Log.v(TAG, "9");
            bos = new BufferedOutputStream(fos);
            Log.v(TAG, "99");
            int read = -1;
            Log.v(TAG, "999");
            byte[] buffer = new byte[1024];
            Log.v(TAG, "9999");
            while ((read = bis.read(buffer, 0, 1024)) != -1) {
                bos.write(buffer, 0, read);
                Log.v(TAG, "9999999999");
            }
            Log.v(TAG, "10");
            bos.flush();
            bos.close();
            fos.close();
            bis.close();
            is.close();
            Log.v(TAG, "7");
        } catch (IOException e) {
            Log.e("ErrorMessage : ", e.getMessage()+"여긴가");
        }
    }

    /** Called when the activity is first created. */

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "onCreate 실행, 실행되면 안되는데?");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

        String QUERY = "DROP TABLE IF EXISTS word";
        db.execSQL(QUERY);
        onCreate(db);
    }

}