package com.rikeshsubedi.ambientours;

import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    /* These variables contain the state of the tour parameters */
    boolean isArt = false;
    boolean isEntertainment = false;
    boolean isHistory = false;
    boolean isNature = false;
    boolean isCommerce = false;
    boolean isTourist = false;

    MediaPlayer playback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        Bundle intentBundle = intent.getExtras();
        if (intentBundle != null) {
            isArt = intentBundle.getBoolean("art");
            isEntertainment = intentBundle.getBoolean("entertainment");
            isHistory = intentBundle.getBoolean("history");
            isNature = intentBundle.getBoolean("nature");
            isCommerce = intentBundle.getBoolean("commerce");
            isTourist = intentBundle.getBoolean("tourist");
        }

        /* Below is just testing code */
        String text = isArt + " " + isEntertainment + " " + isHistory + " " + isNature + " "
                + isCommerce + " " + isTourist;
        TextView tvTest = findViewById(R.id.tvTest);
        tvTest.setText(text);

        PointOfInterest moon = new PointOfInterest();   // Background music, not location
        playback = MediaPlayer.create(HomeActivity.this, moon.getLocationSoundID());
        playback.setLooping(true);
        playback.start();

    }

    @Override
    public void onBackPressed() {
        playback.stop();
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        playback.stop();
        finish();
        return true;
    }
}
