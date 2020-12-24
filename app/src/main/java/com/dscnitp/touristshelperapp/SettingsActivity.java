package com.dscnitp.touristshelperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.dscnitp.touristshelperapp.activity.LoginScreen;

import java.io.File;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void logOut(View view) {
        File f = new File( "/data/data/your_application_package/shared_prefs/googleSignIn.xml");
        if (f. exists()){
            f.delete();
        }else{
            f = new File("/data/data/your_application_package/shared_prefs/numSignIn.xml");
            f.delete();
        }
        Intent set_log_intent = new Intent(getApplicationContext(), LoginScreen.class);
        startActivity(set_log_intent);
        finish();
    }
}