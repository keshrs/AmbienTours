package com.rikeshsubedi.ambientours;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class HomeActivity extends AppCompatActivity {
    /* These variables contain user location information. */
    ProviderLocationTracker locationTracker;
    Location location;
    double longitude;
    double latitude;

    final int REQUEST_CODE_FINE_GPS = 19;

    PointOfInterestManager poiMan;
    PriorityQueue<OurPlaces> availablePOIs;

    private final int maxVolume = 100;
    private float currVol =  (float)(1 - (Math.log(maxVolume-50) / Math.log(maxVolume)));
    MediaPlayer music;
    MediaPlayer localSounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /* Ensure that location permissions have been given so tour can begin.  */
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_FINE_GPS);
        }

        locationTracker = new ProviderLocationTracker(this, ProviderLocationTracker.ProviderType.GPS);
        locationTracker.start();
        updateLocation();

        Intent intent = getIntent();
        Bundle intentBundle = intent.getExtras();

        poiMan = new PointOfInterestManager(location, intentBundle);
        availablePOIs = new PriorityQueue<>();

        /* Below is just testing code which starts background music. */
        PointOfInterest moon = new PointOfInterest();   // Background music, not location
        music = MediaPlayer.create(HomeActivity.this, moon.getLocationSoundID());
        music.setLooping(true);
        music.setVolume(currVol, currVol);
        music.start();

        Button btnUpdateLocation = findViewById(R.id.btnUpdateLocation);
        btnUpdateLocation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateLocation();
                Toast.makeText(HomeActivity.this, "Lat: " + latitude + " Long: " + longitude, Toast.LENGTH_SHORT).show();
            }
        });

        Button btnNextPOI = findViewById(R.id.btnNextPOI);
        btnNextPOI.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                availablePOIs = poiMan.getPrimedPOIs();
                if (!availablePOIs.isEmpty()) {
                    OurPlaces place = availablePOIs.poll();
                    music = MediaPlayer.create(HomeActivity.this, place.getFileID());
                    music.setVolume(maxVolume, maxVolume);
                    music.start();
                    Toast.makeText(HomeActivity.this, "" + place, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HomeActivity.this, "Failed to find a POI.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        /* Below we want to query for nearby places using Google Places API. */


    }

    /**
     * Function to update location, longitude, and latitude.
     */
    private void updateLocation() {
        if (locationTracker.hasLocation()) {
            location = locationTracker.getLocation();

            longitude = location.getLongitude();
            latitude = location.getLatitude();
        } else {
            location = locationTracker.getPossiblyStaleLocation();

            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }
        if (poiMan != null) {
            poiMan.update(location);
            availablePOIs = poiMan.getPrimedPOIs();
        }
    }

    @Override
    public void onBackPressed() {
        locationTracker.stop();
        music.stop();
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        locationTracker.stop();
        music.stop();
        finish();
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_FINE_GPS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.v("PERMISSION", "LOCATION PERMISSION GRANTED");

                } else {
                    music.stop();
                    finish();
                }
            }
        }
    }
}
