package com.example.mavsdiner.mavsdiner;


import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Button goVendor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_login);
        //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_bar);

        //Login

         goVendor= (Button)findViewById(R.id.goVendor);

        TextView textview = (TextView) findViewById(R.id.Register);
        textview.setPaintFlags(textview.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        TextView textview1 = (TextView) findViewById(R.id.ResetPassword);
        textview1.setPaintFlags(textview.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        textview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ResetPassword.class));
            }
        });

        Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DashboardActivity.class));
            }
        });


        goVendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),VendorDashboard.class);
                startActivity(i);
            }
        });

    }

    }


