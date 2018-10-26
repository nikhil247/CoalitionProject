package com.firebase.nikhilmanali.coalitionproject.Presenter;

import android.content.Context;
import android.util.Log;

import com.firebase.nikhilmanali.coalitionproject.Interface.orderViewInterface;
import com.firebase.nikhilmanali.coalitionproject.Model.DataBaseHandler;
import com.firebase.nikhilmanali.coalitionproject.Model.Database;
import com.firebase.nikhilmanali.coalitionproject.POJO.Items;

import java.util.List;


public class orderPresenter {
    private static DataBaseHandler db;
    private static List<Items> orderList;

    public orderPresenter(Context context){
        db=new DataBaseHandler(context);
        getOrderList();

    }

    public void insertOrderListItem(int id,String description,String price,String name,String imageUrl){
        Items items=new Items(id,description,price,name,imageUrl);
        db.addItem(items, Database.TABLE_ORDER_LIST);

    }

    public void onBindViewAtPosition(int position,orderViewInterface view) {
        Items item = orderList.get(position);
        view.setPrice(item.getPrice());
        view.setTitle(item.getProduct_name());
        view.setImageUrl(item.getImage());


    }


    public int getCount() {
        return orderList.size();
    }

    public void getOrderList(){
        orderList=db.getAllItems(Database.TABLE_ORDER_LIST);
    }
    public List<Items> list(){
        return orderList;
    }

    public boolean isIdPresent(int id){
       return db.getId(id,Database.TABLE_ORDER_LIST);
    }




}
