package com.example.mavsdiner.mavsdiner;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by Mayur on 3/28/2016.
 */
public class ListViewLayout extends ArrayAdapter {

    private final Activity context;
    private final String[] restaurant;
    //private final Integer[] image_id;
    private final String[] status;
    public ListViewLayout(Activity context, String[] restaurant, Integer[] image_id, String[] status) {
        super(context, R.layout.listviewsingle, restaurant);
        this.context = context;
        this.restaurant = restaurant;
        //this.image_id = image_id;
        this.status = status;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View restaurantView= inflater.inflate(R.layout.listviewsingle, null, true);
        TextView txtTitle = (TextView) restaurantView.findViewById(R.id.txt);
        txtTitle.setText(restaurant[position]);

        /*ImageView imageView = (ImageView) restaurantView.findViewById(R.id.img);
        imageView.setImageResource(image_id[position]);*/

       /* RatingBar ratingBar = (RatingBar) restaurantView.findViewById(R.id.ratingBar);
        ratingBar.setRating(4.0f);*/
        TextView txtTitle1 = (TextView) restaurantView.findViewById(R.id.txt1);
        txtTitle1.setText(status[position]);
        return restaurantView;
    }

}
