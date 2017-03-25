package com.group6.cse5324.mavsdiner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Mayur on 3/28/2016.
 */
public class ListViewRest extends Activity {

    ListView list;
    String[] restaurant = {
            "       Chick-fill-A",
            "       Subway",
            "       Panda Express",
            "       Moe's Southwest Grill",
            "       Sushic",
            "       Pie Five",
            "       Texadelphia"
    } ;

    String[] status = {
            "       Open",
            "       Closed",
            "       Open",
            "       Open",
            "       Open",
            "       Closed",
            "       Open"
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

        ListViewLayout adapter = new
                ListViewLayout(ListViewRest.this, restaurant, image_id, status);
        list=(ListView)findViewById(R.id.rest_list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            }
        });

    }
}
