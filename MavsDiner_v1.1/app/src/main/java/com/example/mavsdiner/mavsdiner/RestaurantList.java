package com.example.mavsdiner.mavsdiner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

public class RestaurantList extends AppCompatActivity {

    UserLocalStore userLocalStore;
    private String url ="http://omega.uta.edu/~sxr5833/viewRestaurant.php";
    private ListView restaurantListView;
    private RestaurantAdapter customRestaurantListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        System.out.println("Inside restaurant list sending post request");
        JsonArrayRequest request= new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                ArrayList<HashMap<String,String>> restaurantList= new ArrayList<>();

                for(int i=0;i<response.length();i++){

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String restaurantName = jsonObject.getString("restaurant_name");
                        String rating = jsonObject.getString("rating");
                        String status = jsonObject.getString("status");


                        HashMap<String,String> data= new HashMap<>();
                        data.put("restaurant_name",restaurantName);
                        data.put("rating",rating);
                        data.put("status",status);

                        restaurantList.add(data);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                restaurantListView= (ListView)findViewById(R.id.listViewRestaurant);

                customRestaurantListViewAdapter = new RestaurantAdapter(RestaurantList.this, restaurantList);
                restaurantListView.setAdapter(customRestaurantListViewAdapter);

                restaurantListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        TextView restaurantName = (TextView)view.findViewById(R.id.txtRestaurantName);
                        //Toast.makeText(getApplicationContext(), restaurantName.getText().toString(), Toast.LENGTH_LONG).show();

                        // navigate to restaurant page
                        Intent nextIntent = new Intent(RestaurantList.this, RestaurantActivity.class);
                        nextIntent.putExtra("restaurantName",restaurantName.getText().toString());
                        startActivity(nextIntent);


                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("ViewRestaurantList",error.getMessage());
            }
        });
        AppController.getmInstance().addToRequestQueue(request);

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
                startActivity(new Intent(RestaurantList.this, MainActivity.class));
                break;
            case R.id.ViewCart:
                break;
            default:
                break;
        }

        return true;
    }
}
