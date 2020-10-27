package com.dscnitp.touristshelperapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<UserSuggestion> list=new ArrayList<>();
        list.add(new UserSuggestion("Mumbai","Maharashtra","2000"));
        list.add(new UserSuggestion("Lucknow","Uttar Pradesh","1500"));
        list.add(new UserSuggestion("Mirzapur","Uttar Pradesh","1000"));
        list.add(new UserSuggestion("New Delhi","Delhi","2000"));
        list.add(new UserSuggestion("Mumbai","Maharashtra","2000"));
        list.add(new UserSuggestion("Lucknow","Uttar Pradesh","1500"));
        list.add(new UserSuggestion("Mirzapur","Uttar Pradesh","1000"));
        list.add(new UserSuggestion("New Delhi","Delhi","2000"));
        list.add(new UserSuggestion("Mumbai","Maharashtra","2000"));
        list.add(new UserSuggestion("Lucknow","Uttar Pradesh","1500"));
        list.add(new UserSuggestion("Mirzapur","Uttar Pradesh","1000"));
        list.add(new UserSuggestion("New Delhi","Delhi","2000"));
        ProfileRecycleAdapter mAdapter=new ProfileRecycleAdapter(list);
        recyclerView.setAdapter(mAdapter);


    }

}