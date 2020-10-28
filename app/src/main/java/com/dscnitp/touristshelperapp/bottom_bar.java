package com.dscnitp.touristshelperapp;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class bottom_bar extends AppCompatActivity {
    ChipNavigationBar bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_bar);
        bottomNav=findViewById(R.id.bottom_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new ExploreFragment()).commit();
        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment=null;
                switch (id)
                {
                    case R.id.explore:
                        fragment=new ExploreFragment();
                        break;
                    case R.id.profile:
                        fragment=new ProfileFragment();
                        break;
                    case R.id.notification:
                        fragment=new LikesFragment();
                        break;
                    case R.id.locate:
                        fragment=new MapsFragment();
                        break;

                }
                if(fragment!=null)
                {getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();



                }
            }

        });




    }

}

