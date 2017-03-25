package com.example.mavsdiner.mavsdiner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=(int)Math.round(dm.widthPixels*0.75);
        int height = (int)Math.round(dm.heightPixels*0.6);

        getWindow().setLayout(width, height);

        Button reset = (Button)findViewById(R.id.buttonReset);
        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText txtEmail=(EditText)findViewById(R.id.editTextEmailID);
                EditText txtOldPwd=(EditText)findViewById(R.id.editTextOldPassword);
                EditText txtNewPwd=(EditText)findViewById(R.id.editTextNewPassword);

                String emailID = txtEmail.getText().toString().trim();
                String oldPassword = txtOldPwd.getText().toString().trim();
                String newPassword = txtNewPwd.getText().toString().trim();

                VerifyUserDetails verifyUserDetails = new VerifyUserDetails(emailID, oldPassword, newPassword);
                if(verifyUserDetails.verify()){
                    resetPassword(emailID, oldPassword, newPassword);
                }
                else{
                    Toast.makeText(getApplicationContext(), verifyUserDetails.verificationResult, Toast.LENGTH_LONG).show();
                }

            }
        });

        Button cancel = (Button)findViewById(R.id.buttonCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }


    public void resetPassword(String email, String oldPassword, String newPassword){
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.resetUserPasswordInBackground(email, oldPassword, newPassword, new GetResetPasswordResult()
        {
            @Override
            public void done(String result)
            {
                if (result.contains("Either email-id or password does not exist"))
                {
                    Toast.makeText(getApplicationContext(), "Either email-id or password does not exist", Toast.LENGTH_LONG).show();
                }

                else
                {
                    Toast.makeText(getApplicationContext(), "Password Reset Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        });
    }
}
