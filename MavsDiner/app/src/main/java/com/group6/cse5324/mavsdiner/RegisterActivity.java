package com.example.mavsdiner.mavsdiner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private String op1;
    private EditText textEmail;
    private EditText textPassword;
    private EditText textFirstName;
    private EditText textLastName;
    private EditText textAddress1;
    private EditText textAddress2;
    private EditText textCity;
    private EditText textState;
    private EditText textZip;
    private EditText textRestaurantName;
    private EditText textRestaurantStatus;
    private EditText textOperatingHours;
    private RadioGroup rg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rg1 = (RadioGroup) findViewById(R.id.radioGroup1);
        rg1.setOnCheckedChangeListener(this);
        textEmail = (EditText)findViewById(R.id.email);
        textPassword = (EditText)findViewById(R.id.password);
        textFirstName = (EditText)findViewById(R.id.firstName);
        textLastName = (EditText)findViewById(R.id.lastName);
        textAddress1 = (EditText)findViewById(R.id.address1);
        textAddress2 = (EditText)findViewById(R.id.address2);
        textCity = (EditText)findViewById(R.id.city);
        textState = (EditText)findViewById(R.id.state);
        textZip = (EditText)findViewById(R.id.zip);
        textRestaurantName = (EditText)findViewById(R.id.restaurantName);
        textRestaurantStatus = (EditText)findViewById(R.id.restaurantStatus);
        textOperatingHours = (EditText)findViewById(R.id.operatingHours);

        Button register = (Button)findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Account created successfully", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId)
        {
            case R.id.radioCustomer:
                textFirstName.setEnabled(true);
                textFirstName.requestFocus();
                textLastName.setEnabled(true);
                textLastName.requestFocus();
                textRestaurantName.setEnabled(false);
                textRestaurantStatus.setEnabled(false);
                textOperatingHours.setEnabled(false);
                break;

            case R.id.radioVendor:
                textFirstName.setEnabled(false);
                textLastName.setEnabled(false);
                textRestaurantName.setEnabled(true);
                textRestaurantName.requestFocus();
                textRestaurantStatus.setEnabled(true);
                textRestaurantStatus.requestFocus();
                textOperatingHours.setEnabled(true);
                textOperatingHours.requestFocus();
                break;
        }
    }
}
