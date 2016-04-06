package com.example.mavsdiner.mavsdiner;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView listRestaurant = (TextView) findViewById(R.id.textViewListRestaurant);
        listRestaurant.setPaintFlags(listRestaurant.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        listRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, RestaurantActivity.class));
            }
        });

        TextView mapRestaurnt = (TextView) findViewById(R.id.textViewMapRestaurant);
        mapRestaurnt.setPaintFlags(mapRestaurnt.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
}
