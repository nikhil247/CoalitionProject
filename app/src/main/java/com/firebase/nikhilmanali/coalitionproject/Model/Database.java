package com.firebase.nikhilmanali.coalitionproject.Model;



public class Database {

    public static final String TABLE_ITEM_LIST = "item_list_table";
    public static final String TABLE_ORDER_LIST = "order_list";


    public static final String ITEM_ID = "id";
    public static final String ITEM_DESC = "description";
    public static final String ITEM_PRICE = "price";
    public static final String ITEM_NAME = "product_name";
    public static final String ITEM_IMAGE_URL = "image";

    public Database() {
    }

    public static final String CREATE_TABLE_ITEM_LIST =
            "CREATE TABLE " + TABLE_ITEM_LIST + "("
                    + ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ITEM_DESC + " TEXT,"
                    + ITEM_PRICE + " TEXT,"
                    + ITEM_NAME + " TEXT,"
                    + ITEM_IMAGE_URL + " TEXT"
                    +")";

    public static final String CREATE_TABLE_ORDER_LIST =
            "CREATE TABLE " + TABLE_ORDER_LIST + "("
                    + ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ITEM_DESC + " TEXT,"
                    + ITEM_PRICE + " TEXT,"
                    + ITEM_NAME + " TEXT,"
                    + ITEM_IMAGE_URL + " TEXT"
                    +")";



}
