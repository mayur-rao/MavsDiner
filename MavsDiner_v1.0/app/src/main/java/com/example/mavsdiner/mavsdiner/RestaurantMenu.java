package com.example.mavsdiner.mavsdiner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class RestaurantMenu extends AppCompatActivity {

    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);

        ListView list = (ListView)findViewById(R.id.menuListView);
        list.setAdapter(new MenuAdapter(this));

        Button addToCart = (Button)findViewById(R.id.button4);
        addToCart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Food item(s) added to the cart", Toast.LENGTH_LONG).show();
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
                startActivity(new Intent(RestaurantMenu.this, MainActivity.class));
                break;
            case R.id.ViewCart:
                break;
            default:
                break;
        }

        return true;
    }
}
