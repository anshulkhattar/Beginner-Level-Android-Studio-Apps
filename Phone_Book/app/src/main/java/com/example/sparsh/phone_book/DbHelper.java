package com.example.sparsh.phone_book;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.IDNA;
import android.net.Uri;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper
{
    public static String dbname = "Phone_Book.db";
    public static String table="entries";
    public static int db_version = 1;
    public static String column1 = "name";
    public static String column2="phone" ;
    public static String column3 = "email" ;
    public static String column4 = "address";
    public static String column5 = "nickname";
    public static String column6 = "image";

    String query = "create table entries(_id integer primary key autoincrement ,name text ,phone text,email text, address text ,nickname text ,image text)";
    SQLiteDatabase db ;

    public DbHelper(Context context) {

        super(context, dbname, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
     sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void saveNewRecord(String s1,String s2,String s3,String s4,String s5)
    {
        db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(column1,s1);
        values.put(column2,s2);
        values.put(column3,s3);
        values.put(column4,s4);
        values.put(column5,s5);

        db.insert(table,null,values);

        db.close();
    }

    public Cursor searchallcontact()
    {
        db = getWritableDatabase();

        Cursor c = db.query(table,null,null,null,null,null,"name");


        return c;
    }


    public ArrayList<String>  onContactSearch (String uname)
    {
        db = getWritableDatabase();
        ArrayList<String> alist = new ArrayList<>();
        Cursor c = db.query(table,null,"name=?",new String[]{uname},null,null,null);
        c.moveToNext();
        String s1 = c.getString(1);
        String s2 = c.getString(2);
        String s3 = c.getString(3);
        String s4 = c.getString(4);
        String s5 = c.getString(5);

        alist.add(s1);
        alist.add(s2);
        alist.add(s3);
        alist.add(s4);
        alist.add(s5);

        c.close();
        db.close();
        return alist;
    }
    public void update(String s1,String s2,String s3,String s4,String s5)
    {
        db = getWritableDatabase();

        ContentValues value = new ContentValues();

        value.put(column1,s1);
        value.put(column2,s2);
        value.put(column3,s3);
        value.put(column4,s4);
        value.put(column5,s5);

        db.update(table,value,DbHelper.column1+"=?",new String[]{Details.nam});

        value.clear();
        db.close();
    }

    public int delete(String uname)
    {
        db = getWritableDatabase();
        int i = db.delete(table,"name=?",new String[]{uname});
        db.close();
        return i;
    }

    public Cursor singleContact(String uname)
    {
        db = getWritableDatabase();

        String q = "select * from entries WHERE name LIKE '%"+uname+"%'";
        Cursor c = db.rawQuery(q,null);
        return c;
    }

    public ArrayList<String> searchAllAuto()
    {
        db = getWritableDatabase();
        ArrayList <String> alist = new ArrayList<>();
        Cursor c = db.query(table,null,null,null,null,null,column1);

        while (c.moveToNext())
        {
         alist.add(c.getString(1));

        }

        c.close();
        db.close();
        return alist;

    }

    public void imageSave(String s , Uri uri)
    {
        db = getWritableDatabase();
        ContentValues vice = new ContentValues();
        vice.put(column6,uri.toString());
        db.update(table,vice,"name=?",new String[]{s});
        db.close();
    }



}
