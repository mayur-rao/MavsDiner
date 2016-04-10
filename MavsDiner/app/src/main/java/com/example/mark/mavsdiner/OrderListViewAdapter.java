package com.example.mark.mavsdiner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mark on 3/28/2016.
 */
public class OrderListViewAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<HashMap<String,String>> orderItems;
    private static LayoutInflater inflater= null;

    public OrderListViewAdapter(Context context, ArrayList<HashMap<String, String>> data) {

        mContext= context;
        orderItems= data;
        inflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return orderItems.size();
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
            view= inflater.inflate(R.layout.order_list_row, null);

            TextView order_id= (TextView)view.findViewById(R.id.order_id);
            TextView total_quantity= (TextView)view.findViewById(R.id.total_quantity);
            ImageView food_image= (ImageView)view.findViewById(R.id.food_image);

            HashMap<String,String> hashFoodItems= new HashMap<>();
            hashFoodItems= orderItems.get(position);


            order_id.setText(hashFoodItems.get("order_id"));
            total_quantity.setText(hashFoodItems.get("total_quantity"));
            food_image.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_menu_orders));
        }
        return view;
    }
}
