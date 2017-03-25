package com.example.mavsdiner.mavsdiner;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class MenuModifyItem extends Dialog {

    private EditText add_title;
    private EditText add_desc;
    private EditText add_price;
    private int available;
    private ToggleButton foodItemAvailability;
    private Button cancel_menu_foodItem;
    private Button add_menu_foodItem;
    RequestQueue requestQueue;
    //private String insertUrl = "http://192.168.8.103/getModifyMenu.php";
    private String insertUrl ="http://omega.uta.edu/~mxf6133/getModifyMenu.php";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public MenuModifyItem(final Context context, final String id, String title, String description, String price, final int avail) {
        super(context);

        /** 'Window.FEATURE_NO_TITLE' - Used to hide the title */
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        /** Design the dialog in main.xml file */
        setContentView(R.layout.content_menu_modify_item);

        add_title= (EditText)findViewById(R.id.add_title);
        add_desc= (EditText)findViewById(R.id.add_desc);
        add_price= (EditText)findViewById(R.id.add_price);
        foodItemAvailability= (ToggleButton)findViewById(R.id.foodItemAvailability);
        cancel_menu_foodItem = (Button) findViewById(R.id.cancel_menu_foodItem);
        add_menu_foodItem = (Button)findViewById(R.id.add_menu_foodItem);
        requestQueue = Volley.newRequestQueue(context);

        add_title.setText(title);
        add_desc.setText(description);
        add_price.setText(price);

        if(avail == 1){
            foodItemAvailability.setChecked(true);
        }else{
            foodItemAvailability.setChecked(false);
        }

        add_menu_foodItem.setOnClickListener(new View.OnClickListener(){
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
                    protected Map<String,String> getParams() throws AuthFailureError {
                        Map<String,String> parameters =new HashMap<String, String>();

                        if(foodItemAvailability.isChecked()){
                            available = 1;
                        } else {
                            available = 0;
                        }

                        parameters.put("food_item_id",id);
                        parameters.put("food_item_name",add_title.getText().toString());
                        parameters.put("food_item_price",add_price.getText().toString());
                        parameters.put("food_item_description",add_desc.getText().toString());
                        parameters.put("availability",Integer.toString(available));
                        return parameters;
                    }
                };
                requestQueue.add(request);

                Toast.makeText(context, "Menu Modified Successfully", Toast.LENGTH_LONG).show();
                dismiss();
            }
        });

        cancel_menu_foodItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}


