package com.example.mavsdiner.mavsdiner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
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
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RestaurantMenu extends AppCompatActivity {

    UserLocalStore userLocalStore;
    private String url ="http://omega.uta.edu/~sxr5833/viewRestaurantMenu.php";
    private ListView menuListView;
    private MenuAdapter customMenuListViewAdapter;
    private String restaurantId;
    private String foodItemId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);

        Intent myIntent = getIntent(); // gets the previously created intent
        final String restaurantName = myIntent.getStringExtra("restaurantName");

        TextView txtViewRestaurantName  = (TextView)findViewById(R.id.textView2);
        txtViewRestaurantName.setText(restaurantName);


        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println(response.toString());
                final ArrayList<HashMap<String,String>> menuList= new ArrayList<>();
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
                customMenuListViewAdapter.setCustomButtonListener(new CustomButtonListener()
                {
                    @Override
                    public void onButtonClickListener(int position, EditText editText, int value)
                    {
                        int quantity = Integer.parseInt(editText.getText().toString());
                        quantity = quantity+1*value;
                        if(quantity<0)
                            quantity=0;
                        editText.setText(String.valueOf(quantity));
                    }
                });

                menuListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                        //TextView txtRestaurantName = (TextView)view.findViewById(R.id.txtRestaurantName);
                        TextView txtFoodItemName = (TextView) view.findViewById(R.id.foodItemName);
                        TextView txtFoodItemDescription = (TextView) view.findViewById(R.id.description);
                        TextView txtFoodItemPrice = (TextView) view.findViewById(R.id.basicPrice);
                        TextView txtFoodItemQuantity = (TextView) view.findViewById(R.id.quantity);

                        //String strRestaurantName = txtRestaurantName.getText().toString();
                        final String strFoodItemName = txtFoodItemName.getText().toString();
                        final String strFoodItemQuantity = txtFoodItemQuantity.getText().toString();
                        //final String all = restaurantName+strFoodItemName+strFoodItemDescription+strFoodItemPrice+strFoodItemQuantity;


                        //Get Customer ID
                        userLocalStore = new UserLocalStore(RestaurantMenu.this);
                        User logUser = new User();
                        logUser = userLocalStore.getLoggedInUser();
                        final int userID = logUser.customer_id;

                        //Add to cart
                        StringRequest strReq2 = new StringRequest(Request.Method.POST,
                                "http://omega.uta.edu/~sxr5833/addCart.php", new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.substring(response.indexOf('{'), response.lastIndexOf('}') + 1));
                                    //foodItemId = jsonObject.getString("food_item_id");

                                }
                                catch(Exception e)
                                {
                                    System.out.println("Caught exception cart");
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                VolleyLog.d("ErrorAddCart: " + error.getMessage());
                            }
                        })
                        {
                            @Override
                            protected Map<String,String> getParams() throws AuthFailureError {
                                Map<String,String> parameters =new HashMap<String, String>();
                                parameters.put("restaurant_name", restaurantName);
                                parameters.put("food_item_name", strFoodItemName);
                                parameters.put("customer_id", Integer.toString(userID));
                                parameters.put("quantity", strFoodItemQuantity);
                                return parameters;
                            }
                        };
                        // Adding request to request queue
                        AppController.getmInstance().addToRequestQueue(strReq2);

                        Toast.makeText(RestaurantMenu.this, " Food item added to cart successfully", Toast.LENGTH_LONG).show();
                        return true;
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
                startActivity(new Intent(RestaurantMenu.this, CartActivity.class));
                break;
            default:
                break;
        }

        return true;
    }
}
