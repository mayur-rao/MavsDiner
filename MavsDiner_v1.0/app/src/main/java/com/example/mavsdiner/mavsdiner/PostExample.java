package com.example.mavsdiner.mavsdiner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class PostExample extends AppCompatActivity {

    TextView content;
    EditText fname, email, login, pass;
    String Name, Email, Login, Pass;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.php_post);

        content    =   (TextView)findViewById( R.id.content );
        fname      =   (EditText)findViewById(R.id.name);
        email      =   (EditText)findViewById(R.id.email);

        Button saveme=(Button)findViewById(R.id.gals);

        saveme.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v)
            {
                try{

                    // CALL GetText method to make post method call
                    new GetText().execute();

                }
                catch(Exception ex)
                {
                    content.setText(" url exeption! " );
                }
            }
        });
    }

    public class GetText extends AsyncTask<Void, Void, Void>{


        @Override
        protected Void doInBackground(Void... params) {
            try {
                GetText();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    // Create GetText Metod
    public  void  GetText()  throws  UnsupportedEncodingException
    {
        // Get user defined values
        Name = fname.getText().toString();
        Email   = email.getText().toString();


        // Create data variable for sent values to server

        String data = URLEncoder.encode("name", "UTF-8")
                + "=" + URLEncoder.encode(Name, "UTF-8");

        data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                + URLEncoder.encode(Email, "UTF-8");


        String text = "";
        BufferedReader reader=null;

        // Send data
        try
        {

            // Defined URL  where to send data
            URL url = new URL("http://192.168.0.20/android/post.php");
            content.setText(" url connected! " );

            // Send POST data request

            URLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }


            text = sb.toString();
        }
        catch(Exception ex)
        {

        }
        finally
        {
            try
            {

                reader.close();
            }

            catch(Exception ex) {}
        }

        // Show response on activity
        content.setText( text  );

    }
}
