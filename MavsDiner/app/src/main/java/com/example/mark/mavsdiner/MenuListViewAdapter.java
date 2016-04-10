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
 * Created by Mark on 3/20/2016.
 */
public class MenuListViewAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<HashMap<String,String>> foodItems;
    private static LayoutInflater inflater= null;

    public MenuListViewAdapter(Context context, ArrayList<HashMap<String, String>> data) {

        mContext= context;
        foodItems= data;
        inflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return foodItems.size();
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
            view= inflater.inflate(R.layout.menu_list_row, null);

            TextView id = (TextView)view.findViewById(R.id.food_item_id);
            TextView title = (TextView)view.findViewById(R.id.title);
            //ToggleButton foodItemAvailability = (ToggleButton) view.findViewById(R.id.foodItemAvailability);
            //Boolean avail = foodItemAvailability.isChecked();
            TextView avail= (TextView)view.findViewById(R.id.avail);
            TextView description= (TextView)view.findViewById(R.id.description);
            TextView price= (TextView)view.findViewById(R.id.price);
            ImageView food_image= (ImageView)view.findViewById(R.id.food_image);

            HashMap<String,String> hashFoodItems= new HashMap<>();
            hashFoodItems= foodItems.get(position);

            id.setText(hashFoodItems.get("id"));
            title.setText(hashFoodItems.get("title"));
            //foodItemAvailability.setChecked(avail);
            avail.setText(hashFoodItems.get("avail"));
            description.setText(hashFoodItems.get("description"));
            price.setText(hashFoodItems.get("price"));
            food_image.setImageDrawable(mContext.getResources().getDrawable(R.drawable.mavsdinericon));
        }
        return view;
    }
}
