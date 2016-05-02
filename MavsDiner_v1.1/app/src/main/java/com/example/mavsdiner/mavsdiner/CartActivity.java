package com.example.mavsdiner.mavsdiner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    CartAdapter sum;
    String json_string;
    ArrayList<HashMap<String, String>> restaurantList = new ArrayList<>();
    UserLocalStore userLocalStore;

    private String insertUrl = "http://omega.uta.edu/~sxr5833/insert.php";
    RequestQueue requestQueue;

    int sumtoti = 0,oidi = 0, ridi = 0;
    String sumtot;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        userLocalStore = new UserLocalStore(this);
        //sum= new CartAdapter(this);
        //final int finalSum= sum.getSum();

        //System.out.println("finalSum");
        //System.out.println(finalSum);


        //String[] foods = {"LPizza", "Coffee", "Burger"};
        //ListAdapter buckysAdapter = new CartAdapter(this, foods);
        //ListView buckysListView = (ListView) findViewById(R.id.buckysListView);
        //buckysListView.setAdapter(buckysAdapter);


        //buckysListView.setOnItemClickListener(
         //      new AdapterView.OnItemClickListener() {
        //            @Override
         //           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
         //               String food = String.valueOf(parent.getItemAtPosition(position));
          //              Toast.makeText(CartActivity.this, food, Toast.LENGTH_LONG).show();
         //           }
         //       }
        //);

        //strat background task automaticaly

        new BackgroundTask().execute();

        System.out.println("main active2");
        System.out.println(json_string);

        //new CartAdapter().execute();

        System.out.println("Here BACK AGAIN2");
        System.out.println(sumtoti);

        /////////////////////////////////

        //String[] foods = {"LPizza", "Coffee", "Burger"};
        //ListAdapter buckysAdapter = new CartAdapter(this, foods);
        //ListView buckysListView = (ListView) findViewById(R.id.buckysListView);
        //buckysListView.setAdapter(buckysAdapter);


        //Context context = getApplicationContext();
        //CharSequence text = "Order Placed";
        //int duration = Toast.LENGTH_LONG;

        //Toast toast = Toast.makeText(context, text, duration);
        //toast.show();


        Button button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //CartAdapter sum= new CartAdapter(CartActivity.this);
                //int finalSumi= sum.getSum();
                //System.out.println("finalSumi");
                //System.out.println(finalSumi);

                Toast.makeText(getApplicationContext(), "Order placed Successfully", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
                startActivity(intent);
                finish();

                System.out.println("restaurantList HERE");
                System.out.println(restaurantList);


                HashMap<String, String> restl = restaurantList.get(0);
                System.out.println(restl);
                //String rn = restl.get("restaurant_name");
                //String fin = restl.get("food_item_name");
                //String fip = restl.get("food_item_price");
                //String q = restl.get("quantity");
                final String order_id = restl.get("order_id");
                final String restaurant_id = restl.get("restaurant_id");
                oidi = Integer.parseInt(order_id);
                ridi = Integer.parseInt(restaurant_id);
                System.out.println(oidi);
                System.out.println(ridi);

                requestQueue = Volley.newRequestQueue(getApplicationContext());

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

                        parameters.put("order_id", order_id);
                        parameters.put("restaurant_id",restaurant_id);

                        return parameters;
                    }
                };
                requestQueue.add(request);

            }
        });
    }
    //
    class BackgroundTask extends AsyncTask<Void,Void,String> {


        String json_url;
        String JSON_STRING;
        String parm1;
        CartAdapter buckysAdapter ;



        @Override
        protected void onPreExecute() {
            Log.d("MyAsyncTask", "onPre");
            json_url = "http://omega.uta.edu/~sxr5833/json_fi.php";


        }

        @Override
        protected String doInBackground(Void... voids) {
            Log.d("MyAsyncTask", "onDo");
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null) {

                    stringBuilder.append(JSON_STRING + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onProgressUpdate (Void...values){
                super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String json_string){

                Log.d("MyAsyncTask", "onPostExecute");
                //super.onPostExecute(result);
                if(json_string == null){
                    Context context = getApplicationContext();
                    CharSequence text = "json strin null";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();


                }
                else{

                    System.out.println(json_string);
                    //parm1 = json_string[0] ;
                    System.out.println("post");

                    try {
                        JSONObject jsonObj = new JSONObject(json_string);

                        // Getting JSON Array node
                        JSONArray restaurants = jsonObj.getJSONArray("server response");

                        // looping through All Contacts
                        for (int i = 0; i < restaurants.length(); i++) {
                            JSONObject c = restaurants.getJSONObject(i);

                            //String order_id = c.getString("order_id");
                            String restaurant_name = c.getString("restaurant_name");
                            String food_item_name = c.getString("food_item_name");
                            String food_item_price = c.getString("food_item_price");
                            String quantity = c.getString("quantity");
                            String order_id = c.getString("order_id");
                            String restaurant_id = c.getString("restaurant_id");

                            // tmp hashmap for single contact
                            HashMap<String, String> restaurantl = new HashMap<String, String>();

                            // adding each child node to HashMap key => value
                            //restaurantl.put("order_id", order_id);
                            restaurantl.put("restaurant_name", restaurant_name);
                            restaurantl.put("food_item_name", food_item_name);
                            restaurantl.put("food_item_price", food_item_price);
                            restaurantl.put("quantity", quantity);
                            restaurantl.put("order_id", order_id);
                            restaurantl.put("restaurant_id", restaurant_id);

                            // adding contact to contact list
                            restaurantList.add(restaurantl);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    System.out.println("restaurantList");
                    System.out.println(restaurantList);


                    //String[] foods = {"XPizza", "Coffee", "Burger"};
                    //CartAdapter buckysAdapter = new CartAdapter(CartActivity.this, restaurantList);
                    buckysAdapter = new CartAdapter(CartActivity.this, restaurantList);
                    //ListAdapter buckysAdapter = new CartAdapter(CartActivity.this, foods,restaurantList);
                    ListView buckysListView = (ListView) findViewById(R.id.buckysListView);
                    buckysListView.setAdapter(buckysAdapter);



                    buckysAdapter.setCustomButtonListener(new CustomButtonListener()
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

                    //sumtoti = buckysAdapter.getSum();
                    //System.out.println("Here BACK AGAIN");
                    //System.out.println(sumtoti);

                    //System.out.println("Here BACK AGAIN");


                    //System.out.println(sumtoti);

                }

            System.out.println("Here BACK AGAIN11");
            sumtoti = buckysAdapter.getSum();
            System.out.println("Here BACK AGAIN");
            System.out.println(sumtoti);



        }
        //CartAdapter sum2= new CartAdapter(CartActivity.this);
        //int finalSum2= sum2.getSum();

        //System.out.println("Here BACK AGAIN12");
    }
    //System.out.println("Here BACK AGAIN11");

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cart_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID Logout was selected
            case R.id.LogoutCart:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(CartActivity.this, MainActivity.class));
                break;
            default:
                break;
        }

        return true;
    }

}