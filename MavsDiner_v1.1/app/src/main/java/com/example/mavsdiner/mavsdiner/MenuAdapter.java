package com.example.mavsdiner.mavsdiner;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.widget.TextView;

/**
 * Created by Swaroop on 3/14/16.
 */
public class MenuAdapter extends BaseAdapter {

    private ArrayList<HashMap<String,String>> list;
    Context context;
    MenuAdapter(Context c, ArrayList<HashMap<String, String>> data)
    {
        context = c;
        list = data;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*
        1. get the root view
        2. use the root view to find other views
        3. set the values
         */
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.restaurant_menu,parent,false);
        TextView foodItemName = (TextView) row.findViewById(R.id.foodItemName);
        TextView foodItemDescription = (TextView) row.findViewById(R.id.description);
        TextView foodItemPrice = (TextView) row.findViewById(R.id.basicPrice);

        HashMap<String,String> hashMenuItems= new HashMap<>();
        hashMenuItems= list.get(position);

        foodItemName.setText(hashMenuItems.get("food_item_name"));
        foodItemDescription.setText(hashMenuItems.get("food_item_description"));
        foodItemPrice.setText(hashMenuItems.get("food_item_price"));

        return row;
    }
}
