package com.example.sqliteloginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FNAME = "first_name";
    private static final String COLUMN_LNAME = "last_name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_PASSWORD = "password";

    private static final String TABLE_CREATE = "create table users (id integer primary key not null ,  " +
            "first_name text not null,  last_name text not null, email text not null, phone text not null, password text not null)";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "drop table if exists "+ TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
        db.close();
    }

    public void insertUser(User u){
        db = this.getWritableDatabase();
        ContentValues conVal = new ContentValues();

        Cursor cursor = db.rawQuery("select * from users", null);
        conVal.put(COLUMN_ID,cursor.getCount()+1);
        conVal.put(COLUMN_FNAME,u.getfName().toString());
        conVal.put(COLUMN_LNAME,u.getlName().toString());
        conVal.put(COLUMN_EMAIL,u.getEmail().toString());
        conVal.put(COLUMN_PHONE,u.getPhone().toString());
        conVal.put(COLUMN_PASSWORD,u.getPassword().toString());

        db.insert(TABLE_NAME, null, conVal);
    }

    public String searchUser(String email){
        db = this.getReadableDatabase();

        String query = "select email, password from users where email =" + "'" + email + "'";
        String password = "";

        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            password = cursor.getString(1);
        }
        return (password);
    }

    public ArrayList<String> checkUser(String email, String phone){
        db = this.getReadableDatabase();

        ArrayList<String> userCred = new ArrayList<String>();
        String id = "";
        String password = "";
        String query= "select id,email, phone, password from users where email = " + "'" + email + "'" + " and phone = " +
                "'" + phone + "'";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            id = cursor.getString(0);
            password = cursor.getString(3);
            userCred.add(id);
            userCred.add(password);
        }
        return userCred;
    }

    public void updatePassword(String id, String newPassword){
        db = this.getWritableDatabase();
        String query = "update users set password =" + "'" + newPassword +"'" + " where id =" + id ;
        db.execSQL(query);
        db.close();
    }

    public ArrayList<ArrayList<String>> getAllRecords(){

        ArrayList<ArrayList<String>> allRec = new ArrayList<ArrayList<String>>();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users", null);
        if(cursor.moveToFirst()){
            do{
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(cursor.getString(0));
                temp.add(cursor.getString(1));
                temp.add(cursor.getString(2));
                temp.add(cursor.getString(3));
                temp.add(cursor.getString(4));
                temp.add(cursor.getString(5));
                allRec.add(temp);
            }while(cursor.moveToNext());
        }
        return allRec;
    }
}
