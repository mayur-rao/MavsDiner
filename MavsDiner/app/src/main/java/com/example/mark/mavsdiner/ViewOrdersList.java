package com.example.mark.mavsdiner;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewOrdersList extends Dialog {

    private TextView order_item;
    private TextView quantity;
    private ListView itemListView;
    private ItemListViewAdapter customItemListViewAdapter;
    //private String url ="http://192.168.8.103/viewOrdersList.php";
    private String url ="http://omega.uta.edu/~mxf6133/viewOrdersList.php";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public ViewOrdersList(final Context context, final String id) {
        super(context);

        /** 'Window.FEATURE_NO_TITLE' - Used to hide the title */
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        /** Design the dialog in main.xml file */
        setContentView(R.layout.activity_view_orders_list);

 /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); */


        /// MenuModifyItem code goes here????????????????? since we are sending the order_id from android to php
        JsonArrayRequest request= new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                ArrayList<HashMap<String,String>> orderList= new ArrayList<>();

                for(int i=0;i<response.length();i++){

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String id = jsonObject.getString("order_id");
                        String food_item_id = jsonObject.getString("food_item_id");
                        String food_item_name = jsonObject.getString("food_item_name");
                        String quantity = jsonObject.getString("quantity");
                        //String restaurant_id = jsonObject.getString("restaurant_id");
                        Log.v("Order id is ", id);

                        HashMap<String,String> data= new HashMap<>();
                        data.put("order_id",id);
                        data.put("food_item_id",food_item_id);
                        data.put("food_item_name",food_item_name);
                        data.put("quantity",quantity);
                        //data.put("restaurant_id",restaurant_id);

                        orderList.add(data);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                itemListView= (ListView)findViewById(R.id.order_list);

                customItemListViewAdapter = new ItemListViewAdapter(context,orderList);
                itemListView.setAdapter(customItemListViewAdapter);

                itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        TextView food_item_name = (TextView)view.findViewById(R.id.food_item_name);
                        TextView quantity= (TextView)view.findViewById(R.id.quantity);

                        String f_name = food_item_name.getText().toString();
                        String qty = quantity.getText().toString();

                        Toast.makeText(context, "FNAME " + f_name, Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, "QTY " + qty, Toast.LENGTH_SHORT).show();
                        /*
                        Toast.makeText(getApplicationContext(), "Avail " + s_avail, Toast.LENGTH_SHORT).show();
*/
                        // MenuModifyItem menuModifyItem = new MenuModifyItem(orderListView.getContext(), s_fid, s_title, s_description, s_price, s_avail);
                        // menuModifyItem.show();
                    }
                });
                Log.v("Data from the web ", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("ViewOrdersList",error.getMessage());
            }
        });
        AppController.getmInstance().addToRequestQueue(request);


    }

}
