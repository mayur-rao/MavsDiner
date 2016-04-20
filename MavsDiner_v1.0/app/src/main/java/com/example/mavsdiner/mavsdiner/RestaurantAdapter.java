package com.example.mavsdiner.mavsdiner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Swaroop on 4/18/16.
 */
public class RestaurantAdapter extends BaseAdapter {

    private ArrayList<HashMap<String,String>> list;
    Context context;
    RestaurantAdapter(Context c, ArrayList<HashMap<String, String>> data)
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
        View row = inflater.inflate(R.layout.single_restaurant,parent,false);
        TextView restaurantName = (TextView) row.findViewById(R.id.txtRestaurantName);
        RatingBar restaurantRating = (RatingBar) row.findViewById(R.id.ratingBar);
        TextView restaurantStatus = (TextView) row.findViewById(R.id.txtRestaurantStatus);

        HashMap<String,String> hashMenuItems= new HashMap<>();
        hashMenuItems= list.get(position);

        restaurantName.setText(hashMenuItems.get("restaurant_name"));
        restaurantRating.setRating(Float.parseFloat(hashMenuItems.get("rating")));
        restaurantStatus.setText(hashMenuItems.get("status"));

        return row;
    }
}
