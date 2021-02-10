package com.dscnitp.touristshelperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.ncorti.slidetoact.SlideToActView;

public class SplashScreen extends AppCompatActivity {

    SlideToActView slideToActView;
    FirebaseAuth auth;
    public String Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        slideToActView = findViewById(R.id.slider);

        slideToActView.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(SlideToActView slideToActView) {
                auth= FirebaseAuth.getInstance();
                if(auth.getCurrentUser()!=null) {
                    Id = auth.getCurrentUser().getUid();
                    Intent intent = new Intent(SplashScreen.this, homeActivity.class);
                    intent.putExtra("Id",Id);
                    startActivity(intent);
                }
                else
                {
                    startActivity(new Intent(getApplicationContext(), LoginScreen.class));
                    finish();
                }
                SplashScreen.this.finish();
            }
        });
    }
}
