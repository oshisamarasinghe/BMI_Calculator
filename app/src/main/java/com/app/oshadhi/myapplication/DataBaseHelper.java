package com.app.oshadhi.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DataBaseHelper  extends SQLiteOpenHelper{
    final static String DATABASE_NAME = "healthyMe.db";
    final static String USER_TABLE = "user";
    final static String USER_COL_ID = "ID";
    final static String USER_COL_NAME = "username";
    final static String USER_COL_HEI = "height";
    final static String USER_COL_WEI = "weight";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user (ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, height FLOAT, weight FLOAT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }
    public Long addUser (String username, float height, float weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("height",height);
        contentValues.put("weight",weight);

        Long res = db.insert("user", null, contentValues);
        db.close();
        return res;
    }
    public Cursor viewUser(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        String [] cols = {USER_COL_NAME,USER_COL_HEI,USER_COL_WEI};
        String selection = USER_COL_NAME + "=?" ;
        String[] selectionArgs = {username};
        Cursor cursor = db.query(USER_TABLE,cols,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();

        if (count > 0) {
            return cursor;
        }
        else {
            return null;
        }
    }

    public void UpdateUser (String username, float height, float weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String [] cols = {USER_COL_NAME,USER_COL_HEI,USER_COL_WEI};
        String selection = USER_COL_NAME + "=?" ;
        String[] selectionArgs = {username};
        contentValues.put("username", username);
        contentValues.put("height",height);
        contentValues.put("weight",weight);

        db.update(USER_TABLE,contentValues,selection,selectionArgs);
        db.close();
    }
}
