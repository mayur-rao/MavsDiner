package com.example.mavsdiner.mavsdiner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class Rating extends AppCompatActivity {

    private static RatingBar rateRestaurant;
    private String url ="http://omega.uta.edu/~sxr5833/updateRestaurantRating.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        Intent myIntent = getIntent(); // gets the previously created intent
        final String restaurantName = myIntent.getStringExtra("restaurantName");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=(int)Math.round(dm.widthPixels*0.75);
        int height = (int)Math.round(dm.heightPixels*0.4);

        getWindow().setLayout(width,height);

        listenerForRatingBar();

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                StringRequest strReq = new StringRequest(Request.Method.POST,
                        url, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {


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
                        parameters.put("rating",Float.toString(rateRestaurant.getRating()));
                        parameters.put("restaurant_name",restaurantName);
                        return parameters;
                    }
                };
                // Adding request to request queue
                AppController.getmInstance().addToRequestQueue(strReq);

                Toast.makeText(getApplicationContext(), "Thank you for your rating", Toast.LENGTH_LONG).show();
                Intent nextIntent = new Intent(Rating.this, RestaurantActivity.class);
                nextIntent.putExtra("restaurantName",restaurantName);
                startActivity(nextIntent);
            }
        });

        Button buttonCancel = (Button)findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextIntent = new Intent(Rating.this, RestaurantActivity.class);
                nextIntent.putExtra("restaurantName",restaurantName);
                startActivity(nextIntent);
            }
        });
    }

    public void listenerForRatingBar(){
        rateRestaurant = (RatingBar)findViewById(R.id.MyRating);

        rateRestaurant.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        System.out.println("");
                    }
                }
        );
    }
}
