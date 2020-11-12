package com.dscnitp.touristshelperapp.activity;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.dscnitp.touristshelperapp.fragment.ExploreFragment;
import com.dscnitp.touristshelperapp.fragment.LikesFragment;
import com.dscnitp.touristshelperapp.fragment.MapsFragment;
import com.dscnitp.touristshelperapp.fragment.ProfileFragment;
import com.dscnitp.touristshelperapp.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.bottom_nav)
    ChipNavigationBar bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_bar);
        ButterKnife.bind(this);

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

