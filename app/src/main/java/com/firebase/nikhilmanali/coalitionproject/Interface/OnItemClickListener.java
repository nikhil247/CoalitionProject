package com.firebase.nikhilmanali.coalitionproject.Interface;


import android.view.View;
import com.firebase.nikhilmanali.coalitionproject.POJO.Items;
import java.util.List;

public interface OnItemClickListener {
     void onClick(View view, int position, List<Items> list);
}