package com.example.mavsdiner.mavsdiner;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import android.content.Context;
import android.widget.TextView;

/**
 * Created by Swaroop on 3/14/16.
 */
public class MenuAdapter extends BaseAdapter {

    ArrayList<SingleFoodItem> list;
    Context context;
    MenuAdapter(Context c)
    {
        context = c;
        list = new ArrayList<SingleFoodItem>();
        Resources res = c.getResources();
        String [] foodItems = res.getStringArray(R.array.foodItemName);
        String [] foodDescriptions = res.getStringArray(R.array.foodItemDescription);
        String [] foodPrices = res.getStringArray(R.array.foodItemPrice);
        for(int i=0; i<7;i++){
            list.add(new SingleFoodItem(foodItems[i],foodDescriptions[i],foodPrices[i]));
        }
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
        return 0;
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

        SingleFoodItem temp = list.get(position);
        foodItemName.setText(temp.foodItemName);
        foodItemDescription.setText(temp.foodItemDescription);
        foodItemPrice.setText(temp.foodItemPrice);

        return row;
    }
}
