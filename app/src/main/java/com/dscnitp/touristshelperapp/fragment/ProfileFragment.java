package com.dscnitp.touristshelperapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dscnitp.touristshelperapp.activity.EditProfile;
import com.dscnitp.touristshelperapp.R;
import com.dscnitp.touristshelperapp.model.UserSuggestion;
import com.dscnitp.touristshelperapp.adapter.ProfileRecycleAdapter;

import java.util.ArrayList;


public class ProfileFragment extends Fragment{
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ImageView editProfile;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.activity_profile, container, false);
        recyclerView =(RecyclerView) view.findViewById(R.id.recyclerView);
        editProfile = view.findViewById(R.id.edit_profile);
        layoutManager=new LinearLayoutManager(getContext());
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
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EditProfile.class));
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}