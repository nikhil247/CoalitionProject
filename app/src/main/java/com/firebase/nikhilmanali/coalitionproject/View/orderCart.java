package com.firebase.nikhilmanali.coalitionproject.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.nikhilmanali.coalitionproject.Adapter.orderListAdapter;
import com.firebase.nikhilmanali.coalitionproject.Interface.orderCartInterface;
import com.firebase.nikhilmanali.coalitionproject.R;

import java.util.ArrayList;
import java.util.List;

public class orderCart extends AppCompatActivity implements orderCartInterface {

    String product_name,product_price,product_description,product_imageUrl;
    RecyclerView recyclerView;
    orderListAdapter adapter;
    Button process_order_btn;
    TextView total_amount;
    SharedPreferences pref;
    int get_total_amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordercart);
        getSupportActionBar().setTitle("Your Order");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        init();
        recyclerView.setAdapter(adapter);
        get_total_amount=pref.getInt("amount",0);
        total_amount.setText(get_total_amount+"");
        process_order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(orderCart.this,deliveryDetails.class));
            }
        });
    }

    void init(){

        pref= getApplicationContext().getSharedPreferences("MyPref", 0);
        process_order_btn=(Button) findViewById(R.id.process_order_btn);
        total_amount=(TextView)findViewById(R.id.total_amount);

        Intent intent = getIntent();
        product_name=intent.getStringExtra("product_name");
        product_price=intent.getStringExtra("product_price");
        product_description=intent.getStringExtra("product_description");
        product_imageUrl=intent.getStringExtra("product_imageUrl");

        List<String> order_list=new ArrayList<>();
        order_list.add(product_description);
        order_list.add(product_price);
        order_list.add(product_name);
        order_list.add(product_imageUrl);

        recyclerView=(RecyclerView) findViewById(R.id.order_list_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new orderListAdapter(this);


    }
    @Override
    public void getPrice() {

    }

    @Override
    public void totalAmount(int amount) {
        get_total_amount=amount;
        total_amount.setText("$"+get_total_amount+"");
    }

    @Override
    public void refresh() {
        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                finish();
            default:
                break;
        }
        return true;
    }
}
