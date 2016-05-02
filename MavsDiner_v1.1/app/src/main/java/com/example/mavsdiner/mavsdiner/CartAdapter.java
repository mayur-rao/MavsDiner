package com.example.mavsdiner.mavsdiner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

//import android.widget.ArrayAdapter;

//class CartAdapter extends ArrayAdapter<String> {
//class CartAdapter extends ArrayAdapter<HashMap<String, String>> {
public class CartAdapter extends BaseAdapter {


    private ArrayList<HashMap<String,String>> list;
    String PriceText2,finstr,quantity2,finstr2,totpricestr;
    String dollor = "$";
    Context context;
    int totpricei = 0;
    int quantityi = 0;
    int sumtotic = 0;
    public ArrayList<Integer> quantity = new ArrayList<Integer>();
    CustomButtonListener customButtonListener;
    private EditText foodItemQuantity;
    private HashMap<String,String> hashMenuItems= new HashMap<>();
    CartAdapter(Context context){
        return;
    }

    CartAdapter(Context c, ArrayList<HashMap<String, String>> data)
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
        View row = inflater.inflate(R.layout.cart_custom_row,parent,false);
        TextView buckysText  = (TextView) row.findViewById(R.id.buckysText );
        TextView restaurantName  = (TextView) row.findViewById(R.id.restaurantName);
        TextView PriceText  = (TextView) row.findViewById(R.id.PriceText );
        foodItemQuantity  = (EditText) row.findViewById(R.id.quantity );
        TextView TotPrice  = (TextView) row.findViewById(R.id.TotPrice );
        //TextView SumTotPrice  = (TextView) row.findViewById(R.id.SumTotPrice );

        //RatingBar restaurantRating = (RatingBar) row.findViewById(R.id.ratingBar);
        //TextView restaurantStatus = (TextView) row.findViewById(R.id.txtRestaurantStatus);
        //HashMap<String,String> hashMenuItems= new HashMap<>();
        hashMenuItems= list.get(position);

        try{

            foodItemQuantity.setText(String.valueOf(quantity.get(position)));

        }catch(Exception e){
            e.printStackTrace();
        }
        Button buttonInc = (Button) row.findViewById(R.id.buttonInc);
        buttonInc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (customButtonListener != null) {
                    customButtonListener.onButtonClickListener(position, foodItemQuantity, 1);
                    quantity.set(position,quantity.get(position) + 1);
                    /*PriceText2 = hashMenuItems.get("food_item_price");
                    totpricei = Integer.parseInt(PriceText2);
                    quantityi = quantity.get(position);
                    totpricei = totpricei * quantityi;
                    System.out.println(PriceText2);
                    System.out.println(quantityi);
                    System.out.println(totpricei);
                    System.out.println(position);
                    TotPrice.setText(Integer.toString(totpricei));*/
                    //Toast.makeText(context, "Clicked on inc button", Toast.LENGTH_LONG).show();
                }
                else
                {
                    System.out.println("inside else inc");
                }
                notifyDataSetChanged();

            }
        });

        Button buttonDec = (Button) row.findViewById(R.id.buttonDec);
        buttonDec.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (customButtonListener != null) {
                    customButtonListener.onButtonClickListener(position,foodItemQuantity,-1);
                    if(quantity.get(position)>1)
                        quantity.set(position, quantity.get(position) - 1);
                    //Toast.makeText(context, "Clicked on dec button", Toast.LENGTH_LONG).show();
                }
                else
                {
                    System.out.println("inside else dec");
                }
                notifyDataSetChanged();

            }
        });

        //HashMap<String,String> hashMenuItems= new HashMap<>();
        //hashMenuItems= list.get(position);

        buckysText.setText(hashMenuItems.get("food_item_name"));
        restaurantName.setText(hashMenuItems.get("restaurant_name"));
        PriceText2 = hashMenuItems.get("food_item_price");
        finstr = PriceText2 + dollor ;
        PriceText.setText(finstr);
        //PriceText.setText(hashMenuItems.get("food_item_price"));
        //foodItemQuantity.setText(hashMenuItems.get("quantity"));
        quantity2 = hashMenuItems.get("quantity");
        //restaurantRating.setRating(Float.parseFloat(hashMenuItems.get("rating")));
        //restaurantStatus.setText(hashMenuItems.get("status"));
        //totpricei = Integer.parseInt(PriceText2);
        //totpricei = 0;


        //quantityi = Integer.parseInt(quantity2);
        //totpricei = totpricei * quantityi;
        //totpricestr = Integer.toString(totpricei);
        //finstr2  = totpricestr + dollor ;
        TotPrice.setText(finstr);
        //sumtotic = sumtotic + totpricei;
        //System.out.println("sumtotic is");
        //System.out.println(sumtotic);
        //SumTotPrice.setText(finstr2);

        return row;
    }

    int getSum() {
        return sumtotic;
    }


}