package com.nguyenphuocthach12t4gmail.socnetdemo.db.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by asiantech on 23/04/2017.
 */

public class LoginManagerDao extends DataBaseHelper {
    private SQLiteDatabase myDatabase;
    private Context myConText;
    public LoginManagerDao(Context context) {
        super(context);
        myDatabase=getMyDataBase();
        this. myConText=context;
    }
    public boolean isValidLogin(String username, String password) {
        String sql = "SELECT * FROM user_profiles where username='" + username + "' and password='" + password +"'";
        Cursor cursor = myDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            return true;
        }
        return false;
    }
    public int getYourId(String username) {
        String sql = "SELECT * FROM user_profiles where username='" + username + "'";
        Cursor cursor = myDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex("user_id"));
        }
        return -1;
    }
    public int getYourType(String username) {
        String sql = "SELECT * FROM user_profiles where username='" + username + "'";
        Cursor cursor = myDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            // Toast.makeText(myConText,String.valueOf(cursor.getInt(cursor.getColumnIndex("type_id"))), Toast.LENGTH_SHORT).show();
            return cursor.getInt(cursor.getColumnIndex("type_id"));

        }
        return -1;
    }

}

