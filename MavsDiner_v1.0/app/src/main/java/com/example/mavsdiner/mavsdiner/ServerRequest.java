package com.example.mavsdiner.mavsdiner;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Swaroop on 4/9/16.
 */
public class ServerRequest {
    ProgressDialog progressDialog;
    String url;
    UserLocalStore userLocalStore;
    public static final int CONNECTION_TIMEOUT = 1000*10;
    public static final String SERVER_ADDRESS = "http://omega.uta.edu/~sxr5833/";

    public ServerRequest(Context context)
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Connecting");
        progressDialog.setMessage("Please wait...");
    }

    public void fetchUserDataInBackground(User user, GetUserCallBack userCallBack)
    {
        progressDialog.show();
        new FetchCustomerDataAsyncTask(user, userCallBack).execute();
    }

    public void resetUserPasswordInBackground(String email, String oldPwd, String newPwd, GetResetPasswordResult resetPasswordResult)
    {
        progressDialog.show();
        new resetUserPasswordAsyncTask(email, oldPwd, newPwd, resetPasswordResult).execute();
    }

    public void fetchVendorDataInBackground(User user, GetUserCallBack userCallBack)
    {
        progressDialog.show();
        new FetchVendorDataAsyncTask(user, userCallBack).execute();
    }

    public void storeCustomerDataInBackground(User user, GetUserCallBack userCallBack)
    {
        progressDialog.show();
        new StoreCustomerDataAsyncTask(user, userCallBack).execute();
    }

    public void storeVendorDataInBackground(User user, GetUserCallBack userCallBack)
    {
        progressDialog.show();
        new StoreVendorDataAsyncTask(user, userCallBack).execute();
    }
    public class FetchCustomerDataAsyncTask extends AsyncTask<Void, Void, User>
    {
        User user;
        GetUserCallBack userCallBack;

        public FetchCustomerDataAsyncTask(User user, GetUserCallBack userCallBack)
        {
            this.user = user;
            this.userCallBack = userCallBack;
        }

        @Override
        protected void onPostExecute(User returnedUser)
        {
            progressDialog.dismiss();
            //System.out.println(returnedUser.email+returnedUser.password+returnedUser.name);
            userCallBack.done(returnedUser);  //got a user
            super.onPostExecute(returnedUser);
        }

        @Override
        protected User doInBackground(Void... params)
        {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            //dataToSend.add(new BasicNameValuePair("name", user.name));
            dataToSend.add(new BasicNameValuePair("email", user.email));
            dataToSend.add(new BasicNameValuePair("password", user.password));
            //dataToSend.add(new BasicNameValuePair("pass", user.pass));
            System.out.println(dataToSend);
            //dataToSend.add(new BasicNameValuePair("age", user.age + ""));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);

            HttpPost post = new HttpPost(SERVER_ADDRESS + "login_customer.php");

            User returnedUser = null;
            try
            {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                System.out.println("result: "+result);
                JSONObject jsonObject = new JSONObject(result.substring(result.indexOf('{'), result.lastIndexOf('}')+1));

                if (jsonObject.length() == 0)
                {
                    //doNothing;
                }

                else
                {
                    int customer_id = Integer.parseInt(jsonObject.getString("customer_id"));

                    returnedUser = new User(customer_id, user.email, user.password);

                    System.out.println("ServerRequest's doInBackground : "+returnedUser.email+returnedUser.password);
                }
            }

            catch(Exception e)
            {
                e.printStackTrace();
            }

            return returnedUser;
        }
    }


    public class StoreVendorDataAsyncTask extends AsyncTask<Void, Void, Void>
    {
        User user;
        GetUserCallBack userCallBack;

        public StoreVendorDataAsyncTask(User user, GetUserCallBack userCallBack)
        {
            this.user = user;
            this.userCallBack = userCallBack;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            progressDialog.dismiss();
            userCallBack.done(null);
            super.onPostExecute(aVoid);
        }


        @Override
        protected Void doInBackground(Void... params)
        {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("restaurantName", user.restaurantName));
            dataToSend.add(new BasicNameValuePair("operatingHours", user.operatingHours));
            dataToSend.add(new BasicNameValuePair("status", user.status));
            dataToSend.add(new BasicNameValuePair("email", user.email));
            dataToSend.add(new BasicNameValuePair("streetNumber", Integer.toString(user.streetNumber)));
            dataToSend.add(new BasicNameValuePair("streetName", user.streetName));
            dataToSend.add(new BasicNameValuePair("city", user.city));
            dataToSend.add(new BasicNameValuePair("state", user.state));
            dataToSend.add(new BasicNameValuePair("country", user.country));
            dataToSend.add(new BasicNameValuePair("zip", Integer.toString(user.zip)));
            dataToSend.add(new BasicNameValuePair("password", user.password));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);

            HttpPost post = new HttpPost(SERVER_ADDRESS + "vendor_register.php");

            System.out.println("Before try of register vendor" + dataToSend);
            try
            {
                System.out.println("Inside try of register vendor" + dataToSend);
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class StoreCustomerDataAsyncTask extends AsyncTask<Void, Void, Void>
    {
        User user;
        GetUserCallBack userCallBack;

        public StoreCustomerDataAsyncTask(User user, GetUserCallBack userCallBack)
        {
            this.user = user;
            this.userCallBack = userCallBack;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            progressDialog.dismiss();
            userCallBack.done(null);
            super.onPostExecute(aVoid);
        }


        @Override
        protected Void doInBackground(Void... params)
        {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("firstName", user.firstName));
            dataToSend.add(new BasicNameValuePair("lastName", user.lastName));
            dataToSend.add(new BasicNameValuePair("email", user.email));
            dataToSend.add(new BasicNameValuePair("streetNumber", Integer.toString(user.streetNumber)));
            dataToSend.add(new BasicNameValuePair("streetName", user.streetName));
            dataToSend.add(new BasicNameValuePair("city", user.city));
            dataToSend.add(new BasicNameValuePair("state", user.state));
            dataToSend.add(new BasicNameValuePair("country", user.country));
            dataToSend.add(new BasicNameValuePair("zip", Integer.toString(user.zip)));
            dataToSend.add(new BasicNameValuePair("password", user.password));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);

            HttpPost post = new HttpPost(SERVER_ADDRESS + "customer_register.php");

            System.out.println("Before try of register customer" + dataToSend);
            try
            {
                System.out.println("Inside try of register customer" + dataToSend);
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class FetchVendorDataAsyncTask extends AsyncTask<Void, Void, User>
    {
        User user;
        GetUserCallBack userCallBack;

        public FetchVendorDataAsyncTask(User user, GetUserCallBack userCallBack)
        {
            this.user = user;
            this.userCallBack = userCallBack;
        }

        @Override
        protected void onPostExecute(User returnedUser)
        {
            progressDialog.dismiss();
            //System.out.println(returnedUser.email+returnedUser.password+returnedUser.name);
            userCallBack.done(returnedUser);  //got a user
            super.onPostExecute(returnedUser);
        }

        @Override
        protected User doInBackground(Void... params)
        {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            //dataToSend.add(new BasicNameValuePair("name", user.name));
            dataToSend.add(new BasicNameValuePair("email", user.email));
            dataToSend.add(new BasicNameValuePair("password", user.password));
            //dataToSend.add(new BasicNameValuePair("pass", user.pass));
            System.out.println(dataToSend);
            //dataToSend.add(new BasicNameValuePair("age", user.age + ""));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);

            HttpPost post = new HttpPost(SERVER_ADDRESS + "login_vendor.php");

            User returnedUser = null;
            try
            {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                System.out.println("result: "+result);
                JSONObject jsonObject = new JSONObject(result.substring(result.indexOf('{'), result.lastIndexOf('}')+1));

                if (jsonObject.length() == 0)
                {
                    //doNothing;
                }

                else
                {
                    int vendor_id = Integer.parseInt(jsonObject.getString("restaurant_id"));

                    returnedUser = new User(vendor_id, user.email, user.password);

                    System.out.println("ServerRequest's doInBackground : "+returnedUser.email+returnedUser.password);
                }
            }

            catch(Exception e)
            {
                e.printStackTrace();
            }

            return returnedUser;
        }
    }

    public class resetUserPasswordAsyncTask extends AsyncTask<Void, Void, String>
    {
        String email, newPwd, oldPwd;
        GetResetPasswordResult resetPasswordResult;

        public resetUserPasswordAsyncTask(String email, String oldPwd, String newPwd, GetResetPasswordResult resetPasswordResult)
        {
            this.email = email;
            this.newPwd = newPwd;
            this.oldPwd = oldPwd;
            this.resetPasswordResult = resetPasswordResult;
        }

        @Override
        protected void onPostExecute(String result)
        {
            progressDialog.dismiss();
            resetPasswordResult.done(result);
            super.onPostExecute(result);
        }

        @Override
        protected String doInBackground(Void... params)
        {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("email", email));
            dataToSend.add(new BasicNameValuePair("oldPassword", oldPwd));
            dataToSend.add(new BasicNameValuePair("newPassword", newPwd));
            System.out.println(dataToSend);


            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post;
            if(!email.contains("aramark.com")) {
                System.out.println("calling resetPassword_customer");
                post = new HttpPost(SERVER_ADDRESS + "resetPassword_customer.php");
            }
            else{
                System.out.println("calling resetPassword_vendor");
                post = new HttpPost(SERVER_ADDRESS + "resetPassword_vendor.php");
            }

            String result = null;
            try
            {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                result = EntityUtils.toString(entity);
                System.out.println("resultgot");
            }

            catch(Exception e)
            {
                System.out.println("exception caught");
                e.printStackTrace();
            }

            return result;
        }
    }
}
