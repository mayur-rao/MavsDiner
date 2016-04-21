package com.example.mavsdiner.mavsdiner;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mayur on 3/28/2016.
 */
public class ListViewRest extends AppCompatActivity {

    private ProgressDialog pDialog;

    // URL to get contacts JSON
    private static String url = "http://www.json-generator.com/api/json/get/cbIAwzBtQi?indent=2";

    // JSON Node names
    private static final String TAG_RESTAURANTS = "restaurants";
    private static final String TAG_RESTAURANT = "restaurant";
    private static final String TAG_STATUS = "status";
    private static final String TAG_RATING = "rating";

    // contacts JSONArray
    JSONArray restaurants = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> restaurantList = new ArrayList<>();

    ListView list;
    String[] restaurant = {
            "Chick-fill-A",
            "Subway",
            "Panda Express",
            "Moe's Southwest Grill",
            "Sushic",
            "Pie Five",
            "Texadelphia"
    } ;

    String[] status = {
            "Open",
            "Closed",
            "Open",
            "Open",
            "Open",
            "Closed",
            "Open"
    } ;

    Integer[] image_id = {
            R.drawable.fill,
            R.drawable.subway,
            R.drawable.panda,
            R.drawable.moes,
            R.drawable.sushi,
            R.drawable.pie,
            R.drawable.tex

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewrestaurant);
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
            pDialog = new ProgressDialog(ListViewRest.this);
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
                    restaurants = jsonObj.getJSONArray(TAG_RESTAURANTS);

                    // looping through All Contacts
                    for (int i = 0; i < restaurants.length(); i++) {
                        JSONObject c = restaurants.getJSONObject(i);

                        String restaurant = c.getString(TAG_RESTAURANT);
                        String status = c.getString(TAG_STATUS);
                        String rating = c.getString(TAG_RATING);

                        // tmp hashmap for single contact
                        HashMap<String, String> restaurantl = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        restaurantl.put(TAG_RESTAURANT, restaurant);
                        restaurantl.put(TAG_STATUS, status);
                        restaurantl.put(TAG_RATING, rating);


                        // adding contact to contact list
                        restaurantList.add(restaurantl);
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

            list = (ListView) findViewById(R.id.list);
            ListAdapter adapter = new SimpleAdapter(
                    ListViewRest.this, restaurantList,
                    R.layout.listviewsingle, new String[]{TAG_RESTAURANT, TAG_STATUS, TAG_RATING}, new int[]{R.id.txt,
                    R.id.txt1, R.id.ratingBar});
            ((SimpleAdapter) adapter).setViewBinder(new MRBinder());
            list.setAdapter(adapter);

            /*RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
            //float rate = Float.parseFloat(TAG_RATING);
            ratingBar.setRating(4.0f);*/

            //setListAdapter(adapter);
            //list = getListView();
        }

        class MRBinder implements SimpleAdapter.ViewBinder {
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                if (view.getId() == R.id.ratingBar) {
                    String stringval = (String) data;
                    float ratingValue = Float.parseFloat(stringval);
                    RatingBar ratingBar = (RatingBar) view;
                    ratingBar.setRating(ratingValue);
                    return true;
                }
                return false;
            }

        }
    }
}
