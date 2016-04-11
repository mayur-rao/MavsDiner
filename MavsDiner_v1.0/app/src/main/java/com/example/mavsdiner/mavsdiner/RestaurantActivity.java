package com.example.mavsdiner.mavsdiner;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class RestaurantActivity extends AppCompatActivity {

    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Menu
        TextView textview = (TextView) findViewById(R.id.Menu);
        textview.setPaintFlags(textview.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestaurantActivity.this, RestaurantMenu.class));
            }
        });

        //Rating
        TextView tv = (TextView)findViewById(R.id.RateUs);
        tv.setPaintFlags(textview.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestaurantActivity.this, Rating.class));
            }
        });

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID Logout was selected
            case R.id.Logout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(RestaurantActivity.this, MainActivity.class));
                break;
            case R.id.ViewCart:
                break;
            default:
                break;
        }

        return true;
    }
}
