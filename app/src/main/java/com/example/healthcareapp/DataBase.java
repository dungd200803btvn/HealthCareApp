package com.example.healthcareapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "create table users(username text,email text,password text)";
        String query2 = "create table cart(username text,product text,price float,otype text)";
        String query3 = "create table order1(username text,fullname text,address text,contact text,pincode int,date text,time text,amount float,otype text)";
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void register(String username,String email,String password){
        ContentValues content = new ContentValues();
        content.put("username",username);
        content.put("email",email);
        content.put("password",password);
        SQLiteDatabase database = getWritableDatabase();
        database.insert("users",null,content);
        database.close();
    }

    public int login(String username,String password){
        int result =0;
        String[] str = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username=? and password=?",str);
            if(c.moveToFirst()){
                result=1;
            }
            return result;
    }

    public void addtoCart(String username,String product,Float price,String otype){
        ContentValues content = new ContentValues();
        content.put("username",username);
        content.put("product",product);
        content.put("price",price);
        content.put("otype",otype);
        SQLiteDatabase database = getWritableDatabase();
        database.insert("cart",null,content);
       database.close();
    }
    public int checkCart(String username,String product){
        int result =0;
        String[] str = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where username=? and product=?",str);
        if(c.moveToFirst()){
            result=1;
        }
        return result;
    }
    public void removeCart(String username,String otype){
        String[]  str= new String[2];
        str[0] = username;
        str[1] = otype;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart","username=? and otype=?",str);
       db.close();
    }
    public ArrayList getCart(String username,String otype){
        ArrayList<String> arr = new ArrayList<>();
        String[] str = new String[2];
        str[0] = username;
        str[1] =otype;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where username=? and otype=?",str);
        if(c.moveToFirst()){
            do{
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product+"$"+price);
            }while (c.moveToNext());
        }
        return arr;

    }

    public void addOrder(String username,String fullname,String address,String contact,int pincode,String date,String time,float price,String otype){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("fullname",fullname);
        cv.put("address",address);
        cv.put("contact",contact);
        cv.put("pincode",pincode);
        cv.put("date",date);
        cv.put("time",time);
        cv.put("amount",price);
        cv.put("otype",otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("order1",null,cv);
        db.close();

    }
    public ArrayList gerOrderData(String username){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] str = new String[1];
        str[0] = username;
        Cursor c = db.rawQuery("select * from order1 where username= ?",str);
        if(c.moveToFirst()){
            do{
                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+
                        c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8));
            }while (c.moveToNext());
        }
        db.close();
        return arr;
    }
    public int checkAppointmentExists(String username,String fullname,String address,String contact,String date,String time){
        int result =0;
        String[] str = new String[6];
        str[0] = username;
        str[1] = fullname;
        str[2] = address;
        str[3] = contact;
        str[4] =  date;
        str[5] = time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from order1 where username= ? and fullname = ? and address = ? and contact = ? and date = ? and time = ? ",str);
        if(c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }
    public void removeOrder(String fullname,String otype,String address){
        String[]  str= new String[3];
        str[0] = fullname;
        str[1] = otype;
        str[2] = address;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("order1","fullname=? and otype=? and address=?",str);
        db.close();
    }
}
