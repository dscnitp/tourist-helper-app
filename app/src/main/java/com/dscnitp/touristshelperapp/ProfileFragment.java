package com.dscnitp.touristshelperapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    public ProfileFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.activity_profile, container, false);
        recyclerView =(RecyclerView) v.findViewById(R.id.recyclerView);
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

        // Inflate the layout for this fragment
        return v;
    }
}