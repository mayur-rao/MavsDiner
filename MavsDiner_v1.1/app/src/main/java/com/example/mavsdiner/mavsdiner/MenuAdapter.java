package com.example.mavsdiner.mavsdiner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Swaroop on 3/14/16.
 */
public class MenuAdapter extends BaseAdapter {

    private ArrayList<HashMap<String,String>> list;
    Context context;
    private  int qty;
    private EditText foodItemQuantity;
    public ArrayList<Integer> quantity = new ArrayList<Integer>();
    CustomButtonListener customButtonListener;

    MenuAdapter(Context c, ArrayList<HashMap<String, String>> data)
    {
        context = c;
        list = data;
        for(int i=0; i<data.size(); i++)
            quantity.add(1);
    }

    public void setCustomButtonListener(CustomButtonListener customButtonListner)
    {
        this.customButtonListener = customButtonListner;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
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
        foodItemQuantity = (EditText) row.findViewById(R.id.quantity);
        //qty = Integer.parseInt(foodItemQuantity.getText().toString());


        try{

            foodItemQuantity.setText(String.valueOf(quantity.get(position)));

        }catch(Exception e){
            e.printStackTrace();
        }

        String strFoodItemName = foodItemName.getText().toString();
        String strFoodItemDescription = foodItemDescription.getText().toString();
        String strFoodItemPrice = foodItemPrice.getText().toString();
        final String all = strFoodItemName+strFoodItemDescription+strFoodItemPrice;


        Button buttonInc = (Button) row.findViewById(R.id.buttonInc);
        buttonInc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (customButtonListener != null) {
                    customButtonListener.onButtonClickListener(position, foodItemQuantity, 1);
                    quantity.set(position,quantity.get(position) + 1);
                }
                else
                {
                    System.out.println("inside else inc");
                }
                notifyDataSetChanged();
                //Toast.makeText(context, "Clicked on inc button", Toast.LENGTH_LONG).show();
            }
        });

        Button buttonDec = (Button) row.findViewById(R.id.buttonDec);
        buttonDec.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (customButtonListener != null) {
                    customButtonListener.onButtonClickListener(position,foodItemQuantity,-1);
                    if(quantity.get(position)>1)
                        quantity.set(position, quantity.get(position) - 1);
                }
                else
                {
                    System.out.println("inside else dec");
                }
                notifyDataSetChanged();
                //Toast.makeText(context, "Clicked on dec button", Toast.LENGTH_LONG).show();
            }
        });

        HashMap<String,String> hashMenuItems= new HashMap<>();
        hashMenuItems= list.get(position);

        foodItemName.setText(hashMenuItems.get("food_item_name"));
        foodItemDescription.setText(hashMenuItems.get("food_item_description"));
        foodItemPrice.setText(hashMenuItems.get("food_item_price"));
        //foodItemQuantity.setText(Integer.toString(quantity));

        return row;
    }
}
