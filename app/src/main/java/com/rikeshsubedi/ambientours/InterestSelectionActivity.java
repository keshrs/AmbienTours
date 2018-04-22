package com.rikeshsubedi.ambientours;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class InterestSelectionActivity extends AppCompatActivity {

    ViewFlipper v_flipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_selection);


        int images[] = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3};
        v_flipper = findViewById(R.id.v_flipper);

        //for loop
        for (int image: images) {
            flipperImages(image);
        }

        final CheckBox chkArt = findViewById(R.id.chkArtCulture);
        final CheckBox chkEntertainment = findViewById(R.id.chkEntertainment);
        final CheckBox chkHistory = findViewById(R.id.chkHistory);
        final CheckBox chkNature = findViewById(R.id.chkNature);
        final CheckBox chkCommerce = findViewById(R.id.chkRestaurantsCommerce);
        final CheckBox chkTourist = findViewById(R.id.chkTouristAttractions);
        final Button btnTour = findViewById(R.id.btnTour);
        btnTour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean wantArt = chkArt.isChecked();
                boolean wantEntertainment = chkEntertainment.isChecked();
                boolean wantHistory = chkHistory.isChecked();
                boolean wantNature = chkNature.isChecked();
                boolean wantCommerce = chkCommerce.isChecked();
                boolean wantTourist = chkTourist.isChecked();

                if (!wantArt && !wantEntertainment && !wantHistory && !wantNature && !wantCommerce
                        && !wantTourist) {
                    // Handle if no items selected
                    String warning = "Select at least one topic.";
                    Toast.makeText(InterestSelectionActivity.this, warning, Toast.LENGTH_SHORT).show();
                } else {
                    Intent goHome = new Intent(InterestSelectionActivity.this,
                            HomeActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putBoolean("art", wantArt);
                    bundle.putBoolean("entertainment", wantEntertainment);
                    bundle.putBoolean("history", wantHistory);
                    bundle.putBoolean("nature", wantNature);
                    bundle.putBoolean("commerce", wantCommerce);
                    bundle.putBoolean("tourist", wantTourist);
                    goHome.putExtras(bundle);

                    startActivity(goHome);
                }
            }
        });
    }

    /**
     * Method to set up images in the ViewFlipper and set its parameters.
     * @param image to add to the flipper.
     */
    public void flipperImages(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        //animation
        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
