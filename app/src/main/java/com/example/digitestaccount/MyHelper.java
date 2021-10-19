package com.example.digitestaccount;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class MyHelper extends SQLiteOpenHelper {

    public static final String dbName = "myDb";
    public static int version = 1;
    public MyHelper (Context context){
        super(context,dbName,null,version);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS CREDENTIALS (AMOUNT INT,TYPE TEXT,DESCRIPTION TEXT,CURRENTDATE TEXT)";
        db.execSQL(sql);

    }

    public void insertData(int amount,String type, String description, String currentDate, SQLiteDatabase database)
    {

        ContentValues values = new ContentValues();
        values.put("AMOUNT",amount);
        values.put("TYPE",type);
        values.put("DESCRIPTION",description);
        values.put("CURRENTDATE",currentDate);

        database.insert("CREDENTIALS",null,values);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}

