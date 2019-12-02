package com.example.spiseapp3.ui.CustomerLocationFiles;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.spiseapp3.Menu.Lists;
import com.example.spiseapp3.R;
import com.example.spiseapp3.chome;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;

public class CustomerLocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng userCurrentLocation;
    private static final int LOCATION_REQUEST = 50;
    Button b1;
    private String[] LOCATION_PERMS = {android.Manifest.permission.ACCESS_FINE_LOCATION };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_location);
        b1=findViewById(R.id.find_restaurants);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(v, "Empty List", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Asking for location permission to request the last user location
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions(this,
                        LOCATION_PERMS,
                        LOCATION_REQUEST);
            } else {
                ActivityCompat.requestPermissions(this,
                        LOCATION_PERMS,
                        LOCATION_REQUEST);
            }

        } else {
            //This will show the current user location marker
            mMap.setMyLocationEnabled(true);

            //Getting location updates and focus the camera with the latest location

            FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // ...
                                userCurrentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                                Lists.user_location=userCurrentLocation;
                                CameraPosition cp = CameraPosition.builder().target(userCurrentLocation).zoom(15).build();
                                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp), 500, null);

                            }
                        }
                    });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        switch (requestCode) {
            case LOCATION_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay!
//                    onMapReady(mMap);

                } else {
                    // permission denied,
                    // Ask again for the permission
//                    Toast.makeText(this, "Permission required", Toast.LENGTH_SHORT).show();
//                    finish();
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
