package com.firebase.nikhilmanali.coalitionproject.Presenter;

import android.content.Context;

import com.firebase.nikhilmanali.coalitionproject.Interface.adapterViewInterface;
import com.firebase.nikhilmanali.coalitionproject.Model.DataBaseHandler;
import com.firebase.nikhilmanali.coalitionproject.Model.Database;
import com.firebase.nikhilmanali.coalitionproject.Model.rawDataNotation;
import com.firebase.nikhilmanali.coalitionproject.POJO.Items;
import java.util.List;

/**
 * Created by Nikhil Manali on 10/24/2018.
 */

public class itemPresenter  {

    List<Items> itemsList;
    private static DataBaseHandler db;


   public itemPresenter(Context context){
       db=new DataBaseHandler(context);
       insertItemList();
   }

   public void insertItemList(){
       if(db.getItemCount()==0) {

           Items item1=new Items(rawDataNotation.ITEM1_ID,rawDataNotation.ITEM1_DESC,rawDataNotation.ITEM1_PRICE,rawDataNotation.ITEM1_NAME,rawDataNotation.ITEM1_IMGURL);
           Items item2=new Items(rawDataNotation.ITEM2_ID,rawDataNotation.ITEM2_DESC,rawDataNotation.ITEM2_PRICE,rawDataNotation.ITEM2_NAME,rawDataNotation.ITEM2_IMGURL);
           Items item3=new Items(rawDataNotation.ITEM3_ID,rawDataNotation.ITEM3_DESC,rawDataNotation.ITEM3_PRICE,rawDataNotation.ITEM3_NAME,rawDataNotation.ITEM3_IMGURL);
           Items item4=new Items(rawDataNotation.ITEM4_ID,rawDataNotation.ITEM4_DESC,rawDataNotation.ITEM4_PRICE,rawDataNotation.ITEM4_NAME,rawDataNotation.ITEM4_IMGURL);


           db.addItem(item1, Database.TABLE_ITEM_LIST);
           db.addItem(item2,Database.TABLE_ITEM_LIST);
           db.addItem(item3,Database.TABLE_ITEM_LIST);
           db.addItem(item4,Database.TABLE_ITEM_LIST);
       }

       itemsList=db.getAllItems(Database.TABLE_ITEM_LIST);
   }

    public void onBindViewAtPosition(int position,adapterViewInterface view) {
        Items item = itemsList.get(position);
        view.setPrice(item.getPrice());
        view.setTitle(item.getProduct_name());
        view.setImageUrl(item.getImage());

    }

    public int getCount() {
        return itemsList.size();
    }

    public List<Items> list(){
        return itemsList;
    }

}
