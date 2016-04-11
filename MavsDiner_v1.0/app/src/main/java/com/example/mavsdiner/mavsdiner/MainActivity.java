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
        //requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_login);
        //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_bar);

        //Login

        userLocalStore = new UserLocalStore(this);
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

        Button button = (Button) findViewById(R.id.buttonLogin);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText txtemail=(EditText)findViewById(R.id.editTextEmail);
                EditText txtpwd=(EditText)findViewById(R.id.editTextPassword);
                String emailID = txtemail.getText().toString().trim();
                String password = txtpwd.getText().toString().trim();
                VerifyUserDetails verifyUserDetails = new VerifyUserDetails(emailID, password);
                if (verifyUserDetails.verify())
                {
                    if(!emailID.contains("aramark.com")) {
                        int customer_id = -1;
                        User user = new User(customer_id, emailID, password);
                        User returnedUser = null;
                        authenticateCustomer(user);
                    }
                    else{
                        int vendor_id = -1;
                        User user = new User(vendor_id, emailID, password);
                        User returnedUser = null;
                        authenticateVendor(user);
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), verifyUserDetails.verificationResult, Toast.LENGTH_LONG).show();
                }


            }
        });

    }

    public void authenticateCustomer(User user)
    {
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.fetchUserDataInBackground(user, new GetUserCallBack()
        {
            @Override
            public void done(User returnedUser)
            {
                if (returnedUser == null)
                {
                    showErrorMessage();
                }

                else
                {
                    logCustomerIn(returnedUser);
                }
            }
        });
    }

    public void authenticateVendor(User user)
    {
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.fetchVendorDataInBackground(user, new GetUserCallBack()
        {
            @Override
            public void done(User returnedUser)
            {
                if (returnedUser == null)
                {
                    showErrorMessage();
                }

                else
                {
                    logVendorIn(returnedUser);
                }
            }
        });
    }

    private void showErrorMessage()
    {
        Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_LONG).show();
    }


    public void logCustomerIn(User returnedUser)
    {
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);
        System.out.println("Just before start_main");
        startActivity(new Intent(this, DashboardActivity.class));
        finish();
    }

    public void logVendorIn(User returnedUser)
    {
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);
        System.out.println("Just before start_main of vendor");
        startActivity(new Intent(this, VendorDashboard.class));
        finish();
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onBackPressed();
    }
}