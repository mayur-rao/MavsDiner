package com.example.mavsdiner.mavsdiner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VendorDashboard extends AppCompatActivity {

    UserLocalStore userLocalStore;
    RequestQueue requestQueue;
    private Button nav_view_orders;
    private Button nav_modify_menu;
    private Button nav_change_status;
    private TextView alphaText;

    private String url ="http://omega.uta.edu/~mxf6133/vendorDashboard.php?restaurant_id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userLocalStore = new UserLocalStore(this);
        //int vendorId= userLocalStore.getVendorId(); System.out.println(vendorId);
        String vendorId= Integer.toString(userLocalStore.getVendorId());

        url= url.concat(vendorId);

        Toast.makeText(getApplicationContext(), vendorId, Toast.LENGTH_LONG).show();


        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest request= new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String restaurant_name = jsonObject.getString("restaurant_name");
                        alphaText = (TextView)findViewById(R.id.alphaText);
                        alphaText.setText(restaurant_name);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("VendorDashboard",error.getMessage());
            }
        });
        AppController.getmInstance().addToRequestQueue(request);



        nav_view_orders = (Button)findViewById(R.id.nav_view_orders);
        nav_modify_menu = (Button)findViewById(R.id.nav_modify_menu);
        nav_change_status = (Button)findViewById(R.id.nav_change_status);

        nav_view_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),ViewOrders.class);
                startActivity(i);
            }
        });

        nav_modify_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),ModifyMenu.class);
                startActivity(i);
            }
        });

        nav_change_status.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),ModifyRestaurantStatus.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.vendor_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID Logout was selected
            case R.id.Logout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            default:
                break;
        }

        return true;
    }

}
