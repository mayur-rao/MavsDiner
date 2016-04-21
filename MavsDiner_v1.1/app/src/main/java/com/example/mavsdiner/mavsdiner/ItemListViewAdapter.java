package com.example.mavsdiner.mavsdiner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mark on 4/9/2016.
 */
public class ItemListViewAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<HashMap<String,String>> items;
    private static LayoutInflater inflater= null;

    public ItemListViewAdapter(Context context, ArrayList<HashMap<String, String>> data) {

        mContext= context;
        items= data;
        inflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view= convertView;

        if(convertView==null){
            view= inflater.inflate(R.layout.order_item_list_row, null);

            TextView food_item_name= (TextView)view.findViewById(R.id.food_item_name);
            TextView quantity= (TextView)view.findViewById(R.id.quantity);

            HashMap<String,String> hashFoodItems= new HashMap<>();
            hashFoodItems= items.get(position);


            food_item_name.setText(hashFoodItems.get("food_item_name"));
            quantity.setText(hashFoodItems.get("quantity"));

        }
        return view;
    }

}
