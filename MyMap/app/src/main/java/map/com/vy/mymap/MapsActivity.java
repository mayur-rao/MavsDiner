package map.com.vy.mymap;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mMap.addMarker(new MarkerOptions().position(MOES));
        LatLng TEXADELPHIA = new LatLng(38.879970, -77.106770);
        mMap.addMarker(new MarkerOptions().position(TEXADELPHIA));
        LatLng PIEFIVE = new LatLng(38.879970, -77.106770);
        mMap.addMarker(new MarkerOptions().position(PIEFIVE));


        mMap.moveCamera(CameraUpdateFactory.newLatLng(MOES));
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {

                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View v = getLayoutInflater().inflate(R.layout.content_dummy, null);
                mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        Intent it = new Intent(MapsActivity.this,test.class);
                        startActivity(it);
                    }
                });

                // Getting the position from the marker


                // Getting reference to the TextView to set latitude


                // Getting reference to the TextView to set longitude
                //TextView tvLng = (TextView) v.findViewById(R.id.tv_lng);

                // Setting the latitude
                //tvLat.setText("Latitude:" + latLng.latitude);

                // Setting the longitude
                //tvLng.setText("Longitude:"+ latLng.longitude);

                // Returning the view containing InfoWindow contents
                return v;
            }

        });

    }


}