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
    private EditText textCountry;
    private EditText textRestaurantName;
    private EditText textRestaurantStatus;
    private EditText textOperatingHours;
    private RadioGroup rg1;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeUI();

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int id = rg1.getCheckedRadioButtonId();
                if(id == R.id.radioCustomer)
                {
                    String firstName = textFirstName.getText().toString();
                    String lastName = textLastName.getText().toString();
                    String email = textEmail.getText().toString();
                    int streetNumber = Integer.parseInt(textAddress1.getText().toString());
                    String streetName = textAddress2.getText().toString();
                    String city = textCity.getText().toString();
                    String state = textState.getText().toString();
                    int zip = Integer.parseInt(textZip.getText().toString());
                    String country = textCountry.getText().toString();
                    String password = textPassword.getText().toString();
                    VerifyUserDetails verifyUserDetails = new VerifyUserDetails(firstName, lastName, email, password);
                    if(verifyUserDetails.verify())
                    {
                        User registeredCustomer = new User(-1, email, password, firstName, lastName, streetNumber, streetName, city, state, zip, country);
                        registerCustomer(registeredCustomer);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), verifyUserDetails.verificationResult, Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    String restaurantName = textRestaurantName.getText().toString();
                    String operatingHours = textOperatingHours.getText().toString();
                    String status = textRestaurantStatus.getText().toString();
                    String email = textEmail.getText().toString();
                    int streetNumber = Integer.parseInt(textAddress1.getText().toString());
                    String streetName = textAddress2.getText().toString();
                    String city = textCity.getText().toString();
                    String state = textState.getText().toString();
                    String country = textCountry.getText().toString();
                    int zip = Integer.parseInt(textZip.getText().toString());
                    String password = textPassword.getText().toString();
                    VerifyUserDetails verifyUserDetails = new VerifyUserDetails(restaurantName, operatingHours, status, email, streetNumber, streetName, city, state, zip, password, country);
                    if(verifyUserDetails.verify())
                    {
                        User registeredVendor = new User(-1, restaurantName, operatingHours, status, email, streetNumber, streetName, city, state, zip, password, country);
                        registerVendor(registeredVendor);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), verifyUserDetails.verificationResult, Toast.LENGTH_LONG).show();
                    }
                }
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

    private void initializeUI()
    {
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
        textCountry = (EditText)findViewById(R.id.country);
        textRestaurantName = (EditText)findViewById(R.id.restaurantName);
        textRestaurantStatus = (EditText)findViewById(R.id.restaurantStatus);
        textOperatingHours = (EditText)findViewById(R.id.operatingHours);
        register = (Button)findViewById(R.id.registerButton);
    }

    private void registerCustomer(User registeredUser)
    {
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.storeCustomerDataInBackground(registeredUser, new GetUserCallBack()
        {
            @Override
            public void done(User returnedUser)
            {
                Toast.makeText(RegisterActivity.this, "You've been registered successully!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
    }

    private void registerVendor(User registeredVendor)
    {
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.storeVendorDataInBackground(registeredVendor, new GetUserCallBack()
        {
            @Override
            public void done(User returnedUser)
            {
                Toast.makeText(RegisterActivity.this, "You've been registered successully!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
    }
}
