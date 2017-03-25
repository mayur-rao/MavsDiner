package com.example.mavsdiner.mavsdiner;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RestaurantActivity extends AppCompatActivity {

    UserLocalStore userLocalStore;
    private String url ="http://omega.uta.edu/~sxr5833/viewRestaurantDetails.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent myIntent = getIntent(); // gets the previously created intent
        final String restaurantName = myIntent.getStringExtra("restaurantName");

        TextView textRestaurantName = (TextView) findViewById(R.id.RestaurantName);
        textRestaurantName.setText(restaurantName);

        System.out.println("Inside restaurant activity");

        /*JsonArrayRequest request= new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                for(int i=0;i<response.length();i++) {
                    System.out.println("response received");
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        float rating = Float.parseFloat(jsonObject.getString("rating"));
                        String operatingHours = jsonObject.getString("operating_hours");
                        String streetNumber = jsonObject.getString("street_number");
                        String streetName = jsonObject.getString("street_name");
                        String city = jsonObject.getString("city");
                        String state = jsonObject.getString("state");
                        String country = jsonObject.getString("country");
                        String zipCode = jsonObject.getString("zip_code");
                        System.out.println("Setting rating");
                        RatingBar restaurantRating = (RatingBar) findViewById(R.id.RestaurantRating);
                        restaurantRating.setRating(rating);

                        System.out.println("Setting hours");
                        TextView textOperatingHours = (TextView) findViewById(R.id.textView4);
                        textOperatingHours.setText(operatingHours);

                        String address = streetNumber + " " + streetName + " " + city + " " + state + " " + zipCode;

                        System.out.println("address");
                        System.out.println(address);
                        TextView textAddress = (TextView) findViewById(R.id.textView3);
                        textAddress.setText(address);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(error.getMessage());
            }
        }
        )
        /*{
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("restaurant_name", restaurantName);
                System.out.println(restaurantName);
                return params;
            }

        }*/;
        //AppController.getmInstance().addToRequestQueue(request);


        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.substring(response.indexOf('{'), response.lastIndexOf('}') + 1));
                    float rating = Float.parseFloat(jsonObject.getString("rating"));
                    String operatingHours = jsonObject.getString("operating_hours");
                    String streetNumber = jsonObject.getString("street_number");
                    String streetName = jsonObject.getString("street_name");
                    String city = jsonObject.getString("city");
                    String state = jsonObject.getString("state");
                    String country = jsonObject.getString("country");
                    String zipCode = jsonObject.getString("zip_code");
                    System.out.println("Setting rating");
                    RatingBar restaurantRating = (RatingBar) findViewById(R.id.RestaurantRating);
                    restaurantRating.setRating(rating);

                    System.out.println("Setting hours");
                    TextView textOperatingHours = (TextView) findViewById(R.id.textView4);
                    textOperatingHours.setText(operatingHours);

                    String address = streetNumber + " " + streetName + " " + city + " " + state + " " + zipCode;

                    System.out.println("address");
                    System.out.println(address);
                    TextView textAddress = (TextView) findViewById(R.id.textView3);
                    textAddress.setText(address);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

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


        //Menu
        TextView textview = (TextView) findViewById(R.id.Menu);
        textview.setPaintFlags(textview.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(RestaurantActivity.this, RestaurantMenu.class);
                nextIntent.putExtra("restaurantName",restaurantName);
                startActivity(nextIntent);
            }
        });

        //Rating
        TextView tv = (TextView)findViewById(R.id.RateUs);
        tv.setPaintFlags(textview.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(RestaurantActivity.this, Rating.class);
                nextIntent.putExtra("restaurantName",restaurantName);
                startActivity(nextIntent);
                //startActivity(new Intent(RestaurantActivity.this, Rating.class));
            }
        });

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
                startActivity(new Intent(RestaurantActivity.this, MainActivity.class));
                break;
            case R.id.ViewCart:
                break;
            default:
                break;
        }

        return true;
    }
}
