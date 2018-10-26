package com.firebase.nikhilmanali.coalitionproject.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.firebase.nikhilmanali.coalitionproject.POJO.Items;
import java.util.ArrayList;
import java.util.List;



public class DataBaseHandler extends SQLiteOpenHelper  {

    private static final int DATABASE_VERSION = 31; // Database Version
    private static final String DATABASE_NAME = "items_database";   // Database Name

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creating Tables
        db.execSQL(Database.CREATE_TABLE_ITEM_LIST);
        db.execSQL(Database.CREATE_TABLE_ORDER_LIST);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Database.TABLE_ITEM_LIST);
        onCreate(db);
    }

    public void addItem(Items items,String table_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Database.ITEM_ID, items.getId());
        values.put(Database.ITEM_DESC, items.getDescription());
        values.put(Database.ITEM_PRICE, items.getPrice());
        values.put(Database.ITEM_NAME, items.getProduct_name());
        values.put(Database.ITEM_IMAGE_URL, items.getImage());
        db.insert(table_name, null, values);
        db.close();
    }



    public int getItemCount() {
        String countQuery = "SELECT id FROM "+Database.TABLE_ITEM_LIST;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public List<Items> getAllItems(String table_name) {
        List<Items> itemList = new ArrayList<>();

        String selectQuery = "SELECT * FROM "+table_name+" ORDER BY " +Database.ITEM_ID;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Items items=new Items();
                items.setId(cursor.getInt(cursor.getColumnIndex(Database.ITEM_ID)));
                items.setDescription(cursor.getString(cursor.getColumnIndex(Database.ITEM_DESC)));
                items.setPrice(cursor.getString(cursor.getColumnIndex(Database.ITEM_PRICE)));
                items.setProduct_name(cursor.getString(cursor.getColumnIndex(Database.ITEM_NAME)));
                items.setImage(cursor.getString(cursor.getColumnIndex(Database.ITEM_IMAGE_URL)));
                itemList.add(items);
            } while (cursor.moveToNext());
        }
        db.close();   // close inserting data from database
        return itemList;  // close inserting data from database
    }

    public boolean getId(int id,String table_name){
        String countQuery = "SELECT id FROM "+table_name+" WHERE id="+id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        if(count>0){
            return true;
        }else {
            return false;
        }

    }




}
