package com.dscnitp.touristshelperapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.dscnitp.touristshelperapp.R;
import com.ncorti.slidetoact.SlideToActView;

public class SplashScreen extends AppCompatActivity {

    SlideToActView slideToActView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        slideToActView = findViewById(R.id.slider);

        slideToActView.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(SlideToActView slideToActView) {
                startActivity(new Intent(getApplicationContext(), LoginScreen.class));
                finish();
            }
        });
    }
}
