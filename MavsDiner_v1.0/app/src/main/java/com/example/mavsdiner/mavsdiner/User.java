package com.example.mavsdiner.mavsdiner;

/**
 * Created by Swaroop on 4/9/16.
 */
public class User {
    String email, password, firstName, lastName, streetName, city, state, country;
    int customer_id, streetNumber, zip, restaurant_id;
    String restaurantName, operatingHours, status;
    // Customer during login
    public User(int customer_id, String email, String password)
    {
        this.customer_id = customer_id;
        this.email = email;
        this.password = password;
    }
    // Customer during registration
    public User(int customer_id, String email, String password, String firstName, String lastName, int streetNumber, String streetName, String city, String state, int zip, String country)
    {
        this.customer_id = customer_id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }
    // Vendor during registration
    public User(int restaurant_id, String restaurantName, String operatingHours, String status, String email, int streetNumber, String streetName, String city, String state, int zip, String password, String country)
    {
        this.restaurant_id = restaurant_id;
        this.restaurantName = restaurantName;
        this.operatingHours = operatingHours;
        this.status = status;
        this.email = email;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.password = password;
        this.country = country;
    }
}
