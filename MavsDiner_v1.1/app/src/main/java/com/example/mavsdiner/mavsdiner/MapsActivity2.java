package com.example.mavsdiner.mavsdiner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity2 extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        userLocalStore = new UserLocalStore(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        final View myContentsView = null;
        // Add a marker in Sydney and move the camera
        LatLng MOES = new LatLng(32.732142, -97.116033);
        mMap.addMarker(new MarkerOptions().position(MOES).title("Moes"));
        LatLng TEXADELPHIA = new LatLng(32.735687, -97.108066);
        mMap.addMarker(new MarkerOptions().position(TEXADELPHIA).title("Texadelphia"));




        mMap.moveCamera(CameraUpdateFactory.newLatLng(MOES));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {

                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                int i = marker.getTitle().compareTo("Texadelphia");
                //int j = marker.getTitle().compareTo("Texadelphia");

                if (i == 0) {
                    View v = getLayoutInflater().inflate(R.layout.content_dummy, null);
                    mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick(Marker marker) {
                            Intent it = new Intent(MapsActivity2.this, RestaurantActivity.class);
                            it.putExtra("restaurantName", "Texadelphia");
                            startActivity(it);
                        }
                    });
                    return v;
                }
                else
                {

                    View v = getLayoutInflater().inflate(R.layout.content_dummy2, null);
                   mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick(Marker marker) {
                           // Intent it = new Intent(MapsActivity2.this, RestaurantActivity.class);
                            //it.putExtra("restaurantName", "Moes");
                            //startActivity(it);
                        }
                    });
                    return v;
                }
            }


        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID Logout was selected
            case R.id.Logout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(MapsActivity2.this, MainActivity.class));
                break;
            case R.id.ViewCart:
                startActivity(new Intent(MapsActivity2.this, CartActivity.class));
                break;
            default:
                break;
        }

        return true;
    }
}
