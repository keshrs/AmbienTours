package com.rikeshsubedi.ambientours;

import android.content.Intent;
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

        String text = isArt + " " + isEntertainment + " " + isHistory + " " + isNature + " "
                + isCommerce + " " + isTourist;
        TextView tvTest = findViewById(R.id.tvTest);
        tvTest.setText(text);
    }




    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
