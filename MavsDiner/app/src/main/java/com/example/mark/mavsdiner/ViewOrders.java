package com.example.mark.mavsdiner;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
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

public class ViewOrders extends AppCompatActivity {

    private ListView orderListView;
    private OrderListViewAdapter customOrderListViewApapter;
    private ImageView deleteFoodItem;
    private AlertDialog.Builder deleteAlert;
    private Button completeOrderBtn;
    private String url ="http://192.168.8.103/viewOrders.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        completeOrderBtn= (Button)findViewById(R.id.completeOrderBtn);

        Toast.makeText(this, "Click to View Food Items Ordered", Toast.LENGTH_LONG).show();

        JsonArrayRequest request= new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                ArrayList<HashMap<String,String>> orderList= new ArrayList<>();

                for(int i=0;i<response.length();i++){

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String id = jsonObject.getString("order_id");
                        //String food_item_id = jsonObject.getString("food_item_id");
                        //String customer_id = jsonObject.getString("customer_id");
                        String total_quantity = jsonObject.getString("total_quantity");
                        //String restaurant_id = jsonObject.getString("restaurant_id");
                        Log.v("Order id is ", id);

                        HashMap<String,String> data= new HashMap<>();
                        data.put("order_id",id);
                        //data.put("food_item_id",food_item_id);
                        //data.put("customer_id",customer_id);
                        data.put("total_quantity",total_quantity);
                        //data.put("restaurant_id",restaurant_id);

                        orderList.add(data);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                orderListView= (ListView)findViewById(R.id.order_list);

                customOrderListViewApapter= new OrderListViewAdapter(getApplicationContext(),orderList);
                orderListView.setAdapter(customOrderListViewApapter);

                orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        TextView order_id = (TextView)view.findViewById(R.id.order_id);
                        TextView total_quantity= (TextView)view.findViewById(R.id.total_quantity);//total quantity is calculated

                        String o_id = order_id.getText().toString();
                        String t_q = total_quantity.getText().toString();

                       Toast.makeText(getApplicationContext(), "OID " + o_id, Toast.LENGTH_SHORT).show();
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
                VolleyLog.d("ModifyMenu",error.getMessage());
            }
        });
        AppController.getmInstance().addToRequestQueue(request);


/*
        final String[] order_id= new String[]
                {"1001","1002","1003","1004","1005","1006","1007","1008","1009","1010","1011"};
        final String[] total_quantity= new String[]
                {"4","3","3","7","2","5","4","4","7","9","3","2"};

        ArrayList<HashMap<String,String>> orderList= new ArrayList<>();

        for(int i=0;i<11;i++){
            HashMap<String,String> data= new HashMap<>();
            data.put("order_id",order_id[i]);
            data.put("total_quantity",total_quantity[i]);

            orderList.add(data);
        }
        orderListView= (ListView)findViewById(R.id.order_list);

        customOrderListViewApapter= new OrderListViewAdapter(getApplicationContext(),orderList);

        orderListView.setAdapter(customOrderListViewApapter);

        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int myPosition = position;
                String itemClickedId = orderListView.getItemAtPosition(myPosition).toString();
                Toast.makeText(getApplicationContext(), "ID clicked " + itemClickedId, Toast.LENGTH_SHORT).show();

                Toast.makeText(getApplicationContext(), "Completion Notification sent to the customer.", Toast.LENGTH_LONG).show();
            }
        });
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
