package com.dscnitp.touristshelperapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dscnitp.touristshelperapp.R;
import com.dscnitp.touristshelperapp.adapter.ExploreRecycleAdapter;
import com.dscnitp.touristshelperapp.model.Places;

import java.util.ArrayList;


public class ExploreFragment extends Fragment {
    RecyclerView horizontalRecyclerView;
    RecyclerView gridAllCitiesRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_explore, container, false);
        horizontalRecyclerView=view.findViewById(R.id.top_places);
        gridAllCitiesRecyclerView=view.findViewById(R.id.grid_all_cities);
        horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        ArrayList<Places> list=new ArrayList<>();
        list.add(new Places("Lucknow",""));
        list.add(new Places("Lucknow",""));
        list.add(new Places("Lucknow",""));
        list.add(new Places("Lucknow",""));
        list.add(new Places("Lucknow",""));
        list.add(new Places("Lucknow",""));
        ExploreRecycleAdapter exploreRecycleAdapter=new ExploreRecycleAdapter(list,getContext());
        horizontalRecyclerView.setAdapter(exploreRecycleAdapter);
        ExploreRecycleAdapter adapter=new ExploreRecycleAdapter(list,getContext(),1);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.HORIZONTAL,false);
        gridAllCitiesRecyclerView.setLayoutManager(gridLayoutManager);
        gridAllCitiesRecyclerView.setAdapter(adapter);
        return view;
    }
}