package com.example.mavsdiner.mavsdiner;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Swaroop on 4/9/16.
 */

public class UserLocalStore {
    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context)
    {
        System.out.println("Inside constructor");
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user)
    {
        System.out.println("Commiting to user local store");
        Log.d("UserLocalStore", user.email + user.password);
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("email", user.email);
        spEditor.putString("password", user.password);
        spEditor.putInt("customer_id", user.customer_id);
        spEditor.commit();
        System.out.println("Commited to user local store");
    }

    public User getLoggedInUser()
    {

        String email = userLocalDatabase.getString("email", "");
        String pass = userLocalDatabase.getString("password", "");
        int customer_id = userLocalDatabase.getInt("customer_id", -1);

        User loggedInUser = new User(customer_id, email, pass);

        return loggedInUser;
    }

    /* MF04152016 Created to return only vendor ID. Used in VendorDashboard.java*/
    public int getVendorId()
    {

        String email = userLocalDatabase.getString("email", "");
        String pass = userLocalDatabase.getString("password", "");
        int customer_id = userLocalDatabase.getInt("customer_id", -1);

        User loggedInUser = new User(customer_id, email, pass);
        //System.out.println("call to get vendor_id");
        return customer_id;
    }

    public void setUserLoggedIn(boolean flag)
    {
        System.out.println("Entered setUserLoggedIn"/* + " " +userLocalDatabase.getString("name", "")+ " " +userLocalDatabase.getString("uname", "")+ " " +userLocalDatabase.getString("pass", "")+ " " +userLocalDatabase.getString("age", "")*/);
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", flag);
        spEditor.commit();
    }

    public void clearUserData()
    {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
    }

    public boolean getUserLoggedIn()
    {
        if(userLocalDatabase.getBoolean("loggedIn", false) == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
