package com.firebase.nikhilmanali.coalitionproject.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.nikhilmanali.coalitionproject.Presenter.orderPresenter;
import com.firebase.nikhilmanali.coalitionproject.R;
import com.squareup.picasso.Picasso;

public class yourOrder extends AppCompatActivity {

    ImageView imageView;
    TextView price,description;
    Button order;
    orderPresenter presenter;

    String product_name,product_price,product_description,product_imageUrl;
    int product_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        init();
        getSupportActionBar().setTitle(product_name);
        price.setText("$"+product_price);
        description.setText(product_description);

        Picasso.with(this)
                .load(product_imageUrl)
                .into(imageView);

        if(presenter.isIdPresent(product_id)!=false) {
                order.setText("Added to cart");
                order.setEnabled(false);
            }
            order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (presenter.isIdPresent(product_id) == false) {
                        presenter.insertOrderListItem(product_id, product_description, product_price, product_name, product_imageUrl);
                        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                        SharedPreferences.Editor editor = pref.edit();

                        if (pref.getInt("amount", 0) == 0) {
                            editor.putInt("amount", Integer.parseInt(product_price));
                        } else {
                            int amount = pref.getInt("amount", 0);
                            editor.putInt("amount", Integer.parseInt(product_price) + amount);
                        }
                        editor.commit();
                        order.setText("Added to cart");
                        order.setEnabled(false);

                    }


                }
            });
        }


    void init(){

        presenter=new orderPresenter(this);

        imageView=(ImageView)findViewById(R.id.image);
        price=(TextView)findViewById(R.id.price);
        description=(TextView)findViewById(R.id.description);
        order=(Button) findViewById(R.id.process_order);

        Intent intent = getIntent();
        product_id=intent.getIntExtra("product_id",0);
        product_name=intent.getStringExtra("product_name");
        product_price=intent.getStringExtra("product_price");
        product_description=intent.getStringExtra("product_description");
        product_imageUrl=intent.getStringExtra("product_imageUrl");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.order_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cart:
                startActivity(new Intent(this,orderCart.class));
                break;
            case android.R.id.home :
                finish();
            default:
                break;
        }
        return true;
    }

}
