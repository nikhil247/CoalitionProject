package com.firebase.nikhilmanali.coalitionproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.nikhilmanali.coalitionproject.Interface.orderCartInterface;
import com.firebase.nikhilmanali.coalitionproject.Presenter.orderPresenter;
import com.firebase.nikhilmanali.coalitionproject.Interface.orderViewInterface;
import com.firebase.nikhilmanali.coalitionproject.R;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * Created by Nikhil Manali on 10/25/2018.
 */

public class orderListAdapter extends RecyclerView.Adapter<orderListAdapter.ViewHolder> {

    orderPresenter presenter;
    orderCartInterface orderCart_Interface;
    Context context;
    int total_amount;
    SharedPreferences pref;

    public orderListAdapter(Context context){
        this.context=context;
        presenter=new orderPresenter(context);
        orderCart_Interface = (orderCartInterface)context;
        pref= context.getSharedPreferences("MyPref", 0);
        total_amount=pref.getInt("amount",0);
    }

    @Override
    public orderListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new orderListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_order_item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(orderListAdapter.ViewHolder holder, int position) {
        presenter.onBindViewAtPosition(position,holder);
    }


    @Override
    public int getItemCount() {
        return presenter.getCount();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements orderViewInterface {


        TextView title_name,product_price,qty;
        ImageView product_image,plus_btn,minus_btn;
        String get_price;
        int count=1;

        public ViewHolder(View itemView) {
            super(itemView);
            title_name=(TextView) itemView.findViewById(R.id.product_name);
            product_price=(TextView) itemView.findViewById(R.id.product_price);
            product_image=(ImageView) itemView.findViewById(R.id.product_image);
            qty=(TextView) itemView.findViewById(R.id.qty);
            plus_btn=(ImageView) itemView.findViewById(R.id.plus);
            minus_btn=(ImageView)itemView.findViewById(R.id.minus);


            plus_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count++;
                    qty.setText(count+"");
                    total_amount+=Integer.parseInt(get_price);
                    product_price.setText("$"+(Integer.parseInt(get_price)*count)+"");
                    orderCart_Interface.totalAmount(total_amount);
                }
            });

            minus_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count--;
                    if(count<1){
                        count=1;
                    }
                    qty.setText(count+"");
                    total_amount-=Integer.parseInt(get_price);
                    product_price.setText("$"+(Integer.parseInt(get_price)*count)+"");
                    orderCart_Interface.totalAmount(total_amount);
                }
            });

        }

        @Override
        public void setTitle(String title) {
          title_name.setText(title);

        }

        @Override
        public void setPrice(String price) {
            get_price=price;
            product_price.setText("$"+(Integer.parseInt(get_price)));
        }

        @Override
        public void setImageUrl(String imageUrl) {
            Picasso.with(context)
                    .load(imageUrl)
                    .into(product_image);
        }



    }
}

