package com.example.mavsdiner.mavsdiner;

/**
 * Created by Swaroop on 3/14/16.
 */
public class SingleFoodItem {

    String foodItemName;
    String foodItemDescription;
    String foodItemPrice;

    SingleFoodItem(String foodItemName, String foodItemDescription, String foodItemPrice)
    {
        this.foodItemDescription = foodItemDescription;
        this.foodItemName = foodItemName;
        this.foodItemPrice = foodItemPrice;
    }

}
