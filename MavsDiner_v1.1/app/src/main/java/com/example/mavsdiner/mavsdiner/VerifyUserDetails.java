package com.example.mavsdiner.mavsdiner;

/**
 * Created by Swaroop on 4/9/16.
 */
public class VerifyUserDetails {

    String firstName, lastName, email, password, oldPassword;
    String restaurantName, operatingHours, status, streetName, city, state, country;
    int streetNumber, zip;
    String verificationResult = "";
    // This is during login
    public VerifyUserDetails(String email, String password)
    {
        this.email=email;
        this.password=password;

        verifyIfEmptyOnLogin();
        verifyEmail();
    }
    // This is during customer registration
    public VerifyUserDetails(String firstName, String lastName, String email, String password)
    {
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.password=password;

        verifyIfEmptyOnRegisterCustomer();
        verifyEmail();

    }
    // This is during vendor registration
    public VerifyUserDetails(String restaurantName, String operatingHours, String status, String email, int streetNumber, String streetName, String city, String state, int zip, String password, String country)
    {
        this.restaurantName=restaurantName;
        this.operatingHours=operatingHours;
        this.status=status;
        this.email=email;
        this.streetNumber=streetNumber;
        this.streetName=streetName;
        this.city=city;
        this.state=state;
        this.zip=zip;
        this.password=password;
        this.country = country;
        verifyIfEmptyOnRegisterVendor();
        verifyEmail();
        verifyEmailAramark();
    }

    // This is during reset password
    public VerifyUserDetails(String email, String oldPassword, String newPassword)
    {
        this.email = email;
        this.password = newPassword;
        this.oldPassword = oldPassword;
        verifyIfEmptyOnResetPassword();
        verifyEmail();
    }

    public void verifyIfEmptyOnLogin()
    {
        if(email.equals("") || password.equals("") /*|| age.toString()==null || name.toString()==null*/)
        {
            verificationResult += "Please enter all fields \n";
        }
    }

    public void verifyIfEmptyOnResetPassword()
    {
        if(email.equals("") || password.equals("") || oldPassword.equals(""))
        {
            verificationResult += "Please enter all fields \n";
        }
    }

    public void verifyEmail()
    {

        if (!email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"))
        {
            verificationResult += "Please enter email in correct format i.e. a@b.c \n";
        }
    }
    public void verifyIfEmptyOnRegisterCustomer()
    {
        if(email.equals(""))
            verificationResult += "Please enter email-id \n";
        if(password.equals(""))
            verificationResult += "Please enter password \n";
        if(firstName.equals(""))
            verificationResult += "Please enter first name \n";
        if(lastName.equals(""))
            verificationResult += "Please enter last name \n";
    }
    public void verifyIfEmptyOnRegisterVendor()
    {
        if(restaurantName.equals(""))
            verificationResult += "Please enter Restaurant Name \n";
        if(operatingHours.equals(""))
            verificationResult += "Please enter Operating Hours \n";
        if(status.equals(""))
            verificationResult += "Please enter Restaurant Status \n";
        if(email.equals(""))
            verificationResult += "Please enter email \n";
        if(Integer.toString(streetNumber).equals(""))
            verificationResult += "Please enter street number \n";
        if(streetName.equals(""))
            verificationResult += "Please enter street name \n";
        if(city.equals(""))
            verificationResult += "Please enter city \n";
        if(state.equals(""))
            verificationResult += "Please enter state \n";
        if(country.equals(""))
            verificationResult += "Please enter country \n";
        if(Integer.toString(zip).equals(""))
            verificationResult += "Please enter zip \n";
    }
    public void verifyStreetNumber()
    {
        if(Integer.toString(streetNumber).matches("[0-9]+"))
            verificationResult += "Enter a valid street number \n";
    }
    public void zip()
    {
        if(Integer.toString(zip).matches("[0-9]+"))
            verificationResult += "Enter a valid zip \n";
    }
    public void verifyEmailAramark()
    {
        if(!email.contains("@aramark.com"))
            verificationResult += "Not an Aramark-id, can't register as vendor \n";
    }
    boolean verify()
    {
        boolean flag = true;

        if (!verificationResult.equalsIgnoreCase(""))
        {
            flag = false;
        }

        return flag;
    }

}
