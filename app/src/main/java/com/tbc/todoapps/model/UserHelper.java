package com.tbc.todoapps.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserHelper extends SQLiteOpenHelper {
    public UserHelper(Context context) {
        super(context, "Login.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("CREATE Table users(username Text primary key, password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP Table if exists users");
    }

    // method use for registration user account
    public boolean insertDate(String username, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = DB.insert("users", null, contentValues);

        if (result ==-1){
            return false;
        }
        else {
            return true;
        }
    }

    // method use for Checking username
    public boolean checkusername(String username){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM USERS WHERE username =?", new  String[] {username});
        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

    // method use for Checking Username Password during Login
    public boolean checkusernamePassword(String username, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM USERS WHERE username =? and password =?", new  String[] {username, password});{
            if (cursor.getCount()>0){
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}
