package com.example.mavsdiner.mavsdiner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RestaurantMenu extends AppCompatActivity {

    UserLocalStore userLocalStore;
    private String url ="http://omega.uta.edu/~sxr5833/viewRestaurantMenu.php";
    private ListView menuListView;
    private MenuAdapter customMenuListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);

        Intent myIntent = getIntent(); // gets the previously created intent
        final String restaurantName = myIntent.getStringExtra("restaurantName");

        TextView txtViewRestaurantName  = (TextView)findViewById(R.id.textView2);
        txtViewRestaurantName.setText(restaurantName);

        Button addToCart = (Button)findViewById(R.id.button4);
        addToCart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Food item(s) added to the cart", Toast.LENGTH_LONG).show();
            }
        });

        /*System.out.println("Inside restaurant menu sending post request");
        JsonArrayRequest request= new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                ArrayList<HashMap<String,String>> menuList= new ArrayList<>();

                for(int i=0;i<response.length();i++){

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String foodItemName = jsonObject.getString("food_item_name");
                        String foodItemPrice = jsonObject.getString("food_item_price");
                        String foodItemDescription = jsonObject.getString("food_item_description");


                        HashMap<String,String> data= new HashMap<>();
                        data.put("food_item_name",foodItemName);
                        data.put("food_item_price",foodItemPrice);
                        data.put("food_item_description",foodItemDescription);

                        menuList.add(data);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                menuListView= (ListView)findViewById(R.id.menuListView);

                customMenuListViewAdapter = new MenuAdapter(RestaurantMenu.this, menuList);
                menuListView.setAdapter(customMenuListViewAdapter);

                menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        TextView food_item_name = (TextView)view.findViewById(R.id.foodItemName);
                        TextView food_item_description = (TextView)view.findViewById(R.id.description);
                        TextView food_item_price = (TextView)view.findViewById(R.id.basicPrice);


                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("ViewMenuList",error.getMessage());
            }
        });
        AppController.getmInstance().addToRequestQueue(request); */

        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println(response.toString());
                ArrayList<HashMap<String,String>> menuList= new ArrayList<>();
                try {
                JSONArray jsonArray = new JSONArray(response.toString());
                for(int i=0;i<jsonArray.length();i++){


                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String foodItemName = jsonObject.getString("food_item_name");
                        String foodItemPrice = jsonObject.getString("food_item_price");
                        String foodItemDescription = jsonObject.getString("food_item_description");


                        HashMap<String,String> data= new HashMap<>();
                        data.put("food_item_name",foodItemName);
                        data.put("food_item_price",foodItemPrice);
                        data.put("food_item_description",foodItemDescription);

                        menuList.add(data);

                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                menuListView= (ListView)findViewById(R.id.menuListView);

                customMenuListViewAdapter = new MenuAdapter(RestaurantMenu.this, menuList);
                menuListView.setAdapter(customMenuListViewAdapter);

                menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        TextView food_item_name = (TextView)view.findViewById(R.id.foodItemName);
                        TextView food_item_description = (TextView)view.findViewById(R.id.description);
                        TextView food_item_price = (TextView)view.findViewById(R.id.basicPrice);


                    }
                });
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: " + error.getMessage());
            }
        })
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> parameters =new HashMap<String, String>();
                parameters.put("restaurant_name",restaurantName);
                return parameters;
            }
        };
        // Adding request to request queue
        AppController.getmInstance().addToRequestQueue(strReq);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID Logout was selected
            case R.id.Logout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(RestaurantMenu.this, MainActivity.class));
                break;
            case R.id.ViewCart:
                break;
            default:
                break;
        }

        return true;
    }
}
