package com.example.mavsdiner.mavsdiner;


import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userLocalStore = new UserLocalStore(this);

    }

    @Override
    protected void onStart()
    {
        super.onStart();

        if(authenticate()== true)
        {
            goToDashboard();
        }

        else
        {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    private boolean authenticate()
    {
        return userLocalStore.getUserLoggedIn();
    }

    private void goToDashboard()
    {
        User u = userLocalStore.getLoggedInUser();
        if(u.email.contains("aramark.com"))
            startActivity(new Intent(this, VendorDashboard.class));
        else
            startActivity(new Intent(this, DashboardActivity.class));
    }
}