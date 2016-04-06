package com.example.mavsdiner.mavsdiner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ResetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=(int)Math.round(dm.widthPixels*0.75);
        int height = (int)Math.round(dm.heightPixels*0.5);

        getWindow().setLayout(width, height);

        Button reset = (Button)findViewById(R.id.buttonReset);
        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Password Successfully Reset", Toast.LENGTH_LONG).show();
            }
        });
    }
}
