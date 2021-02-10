package com.dscnitp.touristshelperapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dscnitp.touristshelperapp.Adapter.CityRecyclerViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class homeActivity extends AppCompatActivity {
     public String Id;
     RecyclerView recyclerView;
     ProgressBar progressBar;
     CityRecyclerViewAdapter cityRecyclerViewAdapter;
     ArrayList<String> ImageList,CityList,DistrictList,StateList,PinCodeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        progressBar=findViewById(R.id.progressBar);
        Intent intent=getIntent();
        Id=intent.getStringExtra("Id");
        ImageList=new ArrayList<>();
        CityList=new ArrayList<>();
        DistrictList=new ArrayList<>();
        StateList=new ArrayList<>();
        PinCodeList=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        retrieveData();
    }

    private void retrieveData() {
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference().child("CityDetails");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    HashMap<String,Object> hashMap= (HashMap<String, Object>) snapshot.getValue();
                    for(String key:hashMap.keySet())
                    {
                        Object data=hashMap.get(key);
                        HashMap<String,Object> hashMap1= (HashMap<String, Object>) data;
                        String city=hashMap1.get("city_name").toString();
                        String district=hashMap1.get("district").toString();
                        String state=hashMap1.get("state").toString();
                        String pincode=hashMap1.get("pincode").toString();
                        String url=hashMap1.get("url").toString();
                        CityList.add(city);
                        DistrictList.add(district);
                        StateList.add(state);
                        PinCodeList.add(pincode);
                        ImageList.add(url);
                        System.out.print("dsusdfbsuidf "+ ImageList.size());
                        initRecyclerView();
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initRecyclerView() {
        cityRecyclerViewAdapter=new CityRecyclerViewAdapter(homeActivity.this,CityList,DistrictList,StateList,PinCodeList,ImageList);
        recyclerView.setAdapter(cityRecyclerViewAdapter);
        cityRecyclerViewAdapter.setOnItemClickListener(new CityRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(homeActivity.this,"Clicked!!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}