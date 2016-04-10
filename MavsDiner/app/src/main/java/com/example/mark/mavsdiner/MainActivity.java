package com.example.mark.mavsdiner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button nav_view_orders;
    private Button nav_modify_menu;
    private Button nav_change_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav_view_orders = (Button)findViewById(R.id.nav_view_orders);
        nav_modify_menu = (Button)findViewById(R.id.nav_modify_menu);
        nav_change_status = (Button)findViewById(R.id.nav_change_status);

        nav_view_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),ViewOrders.class);
                startActivity(i);
            }
        });

        nav_modify_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),ModifyMenu.class);
                startActivity(i);
            }
        });

        nav_change_status.setOnClickListener(new View.OnClickListener(){
            @Override
             public void onClick(View v) {
                 Intent i=new Intent(getApplicationContext(),ModifyRestaurantStatus.class);
                 startActivity(i);
             }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
