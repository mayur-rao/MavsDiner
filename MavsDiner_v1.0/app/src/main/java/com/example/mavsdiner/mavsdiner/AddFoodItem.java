package com.example.mavsdiner.mavsdiner;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mayur on 4/20/2016.
 */
public class AddFoodItem extends AppCompatActivity {

    private ProgressDialog pDialog;

    // URL to get contacts JSON
    private static String url = "http://www.json-generator.com/api/json/get/bOeJKcOztu?indent=2";

    // JSON Node names
    private static final String TAG_FOODITEMS = "fooditems";
    private static final String TAG_ITEMNAME= "itemname";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_PRICE = "price";
    private static final String TAG_CART = "cart";
    String cart = null;
    ListAdapter adapter;
    String data;


    // contacts JSONArray
    JSONArray fooditems = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> foodList = new ArrayList<>();

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);
        new GetRestaurants().execute();
       /* ListViewLayout adapter = new
                ListViewLayout(ListViewRest.this, restaurant, image_id, status);
        list=(ListView)findViewById(R.id.rest_list);
        list.setAdapter(adapter);*/
        /*list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            }
        });*/

    }


    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetRestaurants extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(AddFoodItem.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            RestaurantHandler sh = new RestaurantHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, RestaurantHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    fooditems = jsonObj.getJSONArray(TAG_FOODITEMS);

                    // looping through All Contacts
                    for (int i = 0; i < fooditems.length(); i++) {
                        JSONObject c = fooditems.getJSONObject(i);

                        String food = c.getString(TAG_ITEMNAME);
                        String desc = c.getString(TAG_DESCRIPTION);
                        String price = c.getString(TAG_PRICE);
                        cart = c.getString(TAG_CART);

                        // tmp hashmap for single contact
                        HashMap<String, String> restaurantl = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        restaurantl.put(TAG_ITEMNAME, food);
                        restaurantl.put(TAG_DESCRIPTION, desc);
                        restaurantl.put(TAG_PRICE, price);
                        restaurantl.put(TAG_CART, cart);


                        // adding contact to contact list
                        foodList.add(restaurantl);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("RestaurantHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            /* ListViewLayout adapter = new
                ListViewLayout(ListViewRest.this, restaurant, image_id, status);
        list=(ListView)findViewById(R.id.rest_list);
        list.setAdapter(adapter);*/

            list = (ListView) findViewById(R.id.menuListView);
            adapter = new SimpleAdapter(
                    AddFoodItem.this, foodList,
                    R.layout.restaurant_menu, new String[]{TAG_ITEMNAME, TAG_DESCRIPTION, TAG_PRICE, TAG_CART}, new int[]{R.id.foodItemName,
                    R.id.description, R.id.basicPrice, R.id.checkBox});
            ((SimpleAdapter) adapter).setViewBinder(new MRBinder());
            list.setAdapter(adapter);

        }
    }

    class MRBinder implements SimpleAdapter.ViewBinder {
        public boolean setViewValue(View view, Object data, String textRepresentation) {
            if (view.getId() == R.id.checkBox) {

                final CheckBox cb = (CheckBox) view;
                //CheckBox food = (CheckBox)findViewById(R.id.checkBox);
                cb.setOnClickListener(new CheckBox.OnClickListener() {
                    @Override
                    public void onClick(View v){



                                if (((CheckBox) v).isChecked()) {
                                    new Add_To_Cart().execute();
                                }


                       //for (int i=0; i<adapter.getCount(); i++) {



                            //}


                        //System.out.println("Inforamtion is" + adapter.getItem());
                        /*else
                            DisplayToast("CheckBox is unchecked");*/
                    }
                });


                /*String stringval = (String) data;
                float ratingValue = Float.parseFloat(stringval);
                RatingBar ratingBar = (RatingBar) view;
                ratingBar.setRating(ratingValue);*/
                return true;
            }
            return false;
        }

    }

    public class Add_To_Cart extends AsyncTask<Void, Void, Void>{


        @Override
        protected Void doInBackground(Void... params) {

            try {
                SendFood();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    // Create GetText Metod
    public  void  SendFood()  throws UnsupportedEncodingException {



        SparseBooleanArray checkedItemPositions = null;
        checkedItemPositions = list.getCheckedItemPositions();
        int itemCount = list.getCount();
        System.out.println("Clicked position is " + checkedItemPositions);

        //Swaroop pls CHECK THE LOGIC HERE. I have tried everything
        for(int i=0; i<itemCount; i++) {
           // if (checkedItemPositions.get(i)) {
                data = URLEncoder.encode("cart", "UTF-8")
                        + "=" + URLEncoder.encode(String.valueOf(list.getItemIdAtPosition(i)), "UTF-8");
            //}



            String text = "";
            BufferedReader reader = null;

            // Send data
            try {

                // Defined URL  where to send data
                URL url = new URL("http://192.168.0.20/android/post.php");
                // Send POST data request
                URLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();

                // Get the server response

                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while ((line = reader.readLine()) != null) {
                    // Append server response in string
                    sb.append(line + "\n");
                }


                text = sb.toString();
            } catch (Exception ex) {

            } finally {
                try {

                    reader.close();
                } catch (Exception ex) {
                }
            }
            }
        }
        }



