package com.firebase.nikhilmanali.coalitionproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.nikhilmanali.coalitionproject.POJO.Items;
import com.firebase.nikhilmanali.coalitionproject.Interface.adapterViewInterface;
import com.firebase.nikhilmanali.coalitionproject.Presenter.itemPresenter;
import com.firebase.nikhilmanali.coalitionproject.R;
import com.firebase.nikhilmanali.coalitionproject.Interface.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;


public class itemAdapter extends RecyclerView.Adapter<itemAdapter.ViewHolder> {

    itemPresenter presenter;
    Context context;
    private OnItemClickListener clickListener;

    public itemAdapter(Context context){
        this.context=context;
        presenter=new itemPresenter(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_view_item_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        presenter.onBindViewAtPosition(position,holder);
    }


    @Override
    public int getItemCount() {
        return presenter.getCount();
    }

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements adapterViewInterface,View.OnClickListener  {

        TextView title_name;
        TextView product_price;
        ImageView product_image;

        public ViewHolder(View itemView) {
            super(itemView);
            title_name=(TextView) itemView.findViewById(R.id.product_name);
            product_price=(TextView) itemView.findViewById(R.id.product_price);
            product_image=(ImageView) itemView.findViewById(R.id.product_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void setTitle(String title) {
            title_name.setText(title);
        }

        @Override
        public void setPrice(String price) {
            product_price.setText("$"+price);
        }

        @Override
        public void setImageUrl(String imageUrl) {
            Picasso.with(context)
                    .load(imageUrl)
                    .into(product_image);        }


        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getPosition(),presenter.list());
        }
    }
}
