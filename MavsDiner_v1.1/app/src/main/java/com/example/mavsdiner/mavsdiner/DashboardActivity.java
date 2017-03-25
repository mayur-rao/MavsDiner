package com.example.mavsdiner.mavsdiner;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DashboardActivity extends AppCompatActivity {

    UserLocalStore userLocalStore;
    private String url ="http://omega.uta.edu/~sxr5833/getUserName.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView listRestaurant = (TextView) findViewById(R.id.textViewListRestaurant);
        listRestaurant.setPaintFlags(listRestaurant.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        listRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, RestaurantList.class));
            }
        });

        TextView mapRestaurnt = (TextView) findViewById(R.id.textViewMapRestaurant);
        mapRestaurnt.setPaintFlags(mapRestaurnt.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

        userLocalStore = new UserLocalStore(this);

        User logUser = new User();
        logUser = userLocalStore.getLoggedInUser();
        final int userID = logUser.customer_id;

        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.substring(response.indexOf('{'), response.lastIndexOf('}') + 1));
                    String firstName = jsonObject.getString("first_name");

                    TextView txtViewName = (TextView)findViewById(R.id.textViewMsg);
                    txtViewName.setText("Welcome "+firstName);
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
                parameters.put("customer_id", Integer.toString(userID));
                return parameters;
            }
        };
        // Adding request to request queue
        AppController.getmInstance().addToRequestQueue(strReq);

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
                startActivity(new Intent(DashboardActivity.this, MainActivity.class));
                break;
            case R.id.ViewCart:
                break;
            default:
                break;
        }

        return true;
    }
}
