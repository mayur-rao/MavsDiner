package com.example.mark.mavsdiner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
public class MenuAdd extends AppCompatActivity {

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
