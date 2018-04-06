package com.rikeshsubedi.ambientours;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final Button btnBegin = findViewById(R.id.btnBegin);
        btnBegin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gotoOptions = new Intent(WelcomeActivity.this, InterestSelectionActivity.class);
                startActivity(gotoOptions);
            }
        });
    }
}
