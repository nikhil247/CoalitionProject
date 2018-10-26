package com.firebase.nikhilmanali.coalitionproject.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.nikhilmanali.coalitionproject.Adapter.itemAdapter;
import com.firebase.nikhilmanali.coalitionproject.Interface.OnItemClickListener;
import com.firebase.nikhilmanali.coalitionproject.POJO.Items;
import com.firebase.nikhilmanali.coalitionproject.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    RecyclerView recyclerView;
    itemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        recyclerView.setAdapter(adapter);
    }

    public void init(){
        recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new itemAdapter(this);
        adapter.setClickListener(this);
    }

    @Override
    public void onClick(View view, int position,List<Items> item_list) {

        Intent i = new Intent(this, yourOrder.class);
        i.putExtra("product_id",item_list.get(position).getId());
        i.putExtra("product_name",item_list.get(position).getProduct_name());
        i.putExtra("product_price",item_list.get(position).getPrice());
        i.putExtra("product_description",item_list.get(position).getDescription());
        i.putExtra("product_imageUrl",item_list.get(position).getImage());
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cart:
                startActivity(new Intent(this,orderCart.class));
                break;
            default:
                break;
        }
        return true;
    }
}
