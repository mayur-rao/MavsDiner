package com.example.mavsdiner.mavsdiner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
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

public class ModifyMenu extends AppCompatActivity {

    private ListView listView;
    private MenuListViewAdapter customListViewApapter;
    //private String url ="http://192.168.8.103/modifyMenu.php";
    private String url ="http://omega.uta.edu/~mxf6133/modifyMenu.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Toast.makeText(this, "Click to Modify Food Item in your Menu", Toast.LENGTH_LONG).show();

        JsonArrayRequest request= new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                ArrayList<HashMap<String,String>> foodList= new ArrayList<>();

                for(int i=0;i<response.length();i++){

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String id = jsonObject.getString("food_item_id");
                        String title = jsonObject.getString("food_item_name");
                        String avail = jsonObject.getString("availability");
                        String description = jsonObject.getString("food_item_description");
                        String price = jsonObject.getString("food_item_price");
                        Log.v("Title is ", title);

                        HashMap<String,String> data= new HashMap<>();
                        data.put("id",id);
                        data.put("title",title);

                        if (avail.equals("0")){
                            data.put("avail","Not Available");
                        } else{
                            data.put("avail","Available");
                        }

                        //data.put("avail",avail);
                        data.put("description",description);
                        data.put("price",price);

                        foodList.add(data);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                listView= (ListView)findViewById(R.id.list);
                customListViewApapter= new MenuListViewAdapter(getApplicationContext(),foodList);
                listView.setAdapter(customListViewApapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        TextView fid = (TextView)view.findViewById(R.id.food_item_id);
                        TextView title= (TextView)view.findViewById(R.id.title);
                        TextView description= (TextView)view.findViewById(R.id.description);
                        TextView price= (TextView)view.findViewById(R.id.price);
                        TextView avail= (TextView)view.findViewById(R.id.avail);

                        String s_fid = fid.getText().toString();
                        String s_title = title.getText().toString();
                        String s_description = description.getText().toString();
                        String s_price = price.getText().toString();
                        int s_avail;

                        if(avail.getText().toString().equals("Available")){
                            s_avail = 1;
                        }else{
                            s_avail = 0;
                        }

                        Toast.makeText(getApplicationContext(), "FID " + s_fid, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Title " + s_title, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Description " + s_description, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Price " + s_price, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Avail " + s_avail, Toast.LENGTH_SHORT).show();

                        MenuModifyItem menuModifyItem = new MenuModifyItem(listView.getContext(), s_fid, s_title, s_description, s_price, s_avail);
                        menuModifyItem.show();
                    }
                });
                Log.v("Data from the web ", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("ModifyMenu",error.getMessage());
            }
        });
        AppController.getmInstance().addToRequestQueue(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.modifymenu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Logout) {
            return true;
        }
        else if(id == R.id.action_add){
            Toast toastMessage= Toast.makeText(this, "Add Food Item to Menu", Toast.LENGTH_LONG);
            toastMessage.show();
            MenuAddItem menuAddItem = new MenuAddItem(this);
            menuAddItem.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
