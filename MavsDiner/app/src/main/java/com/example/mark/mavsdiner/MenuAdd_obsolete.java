package com.example.mark.mavsdiner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mark on 4/7/2016.
 */
public class MenuAdd_obsolete extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private EditText add_title, add_desc, add_price;
    private Button add_menu_foodItem, cancel_menu_foodItem;
    private String insertUrl = "http://192.168.8.103/menuAddItem.php";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_menu_add_item);

        add_title = (EditText) findViewById(R.id.add_title);
        add_desc = (EditText) findViewById(R.id.add_desc);
        add_price = (EditText) findViewById(R.id.add_price);
        add_menu_foodItem = (Button) findViewById(R.id.add_menu_foodItem);
        cancel_menu_foodItem = (Button) findViewById(R.id.cancel_menu_foodItem);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        add_menu_foodItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request= new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String,String> getParams() throws AuthFailureError{
                        Map<String,String> parameters =new HashMap<String, String>();
                        parameters.put("food_item_name",add_title.getText().toString());
                        parameters.put("food_item_price",add_price.getText().toString());
                        parameters.put("food_item_description",add_desc.getText().toString());

                        return parameters;
                    }
                };
                requestQueue.add(request);

            }

        });



    //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    //navigationView.setNavigationItemSelectedListener(this);
}

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent i_drawer=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i_drawer);
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Toast toastMessage= Toast.makeText(this, "Back to Home-Dashboard", Toast.LENGTH_LONG);
            toastMessage.show();
            Intent i_drawer=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i_drawer);
            finish();

            //return true;
        } else if (id == R.id.nav_orders) {
            Toast toastMessage= Toast.makeText(this, "Back to View Orders", Toast.LENGTH_LONG);
            toastMessage.show();
            Intent i_drawer=new Intent(getApplicationContext(),ViewOrders.class);
            startActivity(i_drawer);
            finish();

        } else if (id == R.id.nav_menu) {
            Toast toastMessage= Toast.makeText(this, "Already in View Menu", Toast.LENGTH_LONG);
            toastMessage.show();

        } else if (id == R.id.nav_status) {
            Intent i_drawer=new Intent(getApplicationContext(),ModifyRestaurantStatus.class);
            startActivity(i_drawer);
            finish();

        } else if (id == R.id.nav_manage) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
