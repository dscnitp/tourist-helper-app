package com.dscnitp.touristshelperapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dscnitp.touristshelperapp.R;
import com.dscnitp.touristshelperapp.model.Places;

import java.util.ArrayList;

public class ExploreRecycleAdapter extends RecyclerView.Adapter<ExploreRecycleAdapter.LocationHolder> {

private ArrayList<Places> list;
Context context;
LayoutInflater inflater;

    public ExploreRecycleAdapter(ArrayList<Places> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public ExploreRecycleAdapter(ArrayList<Places> list, Context context,int allCities) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public LocationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView= LayoutInflater.from(parent.getContext()).inflate(R.layout.places_elements,parent,false);
        LocationHolder myHolder=new LocationHolder(myView);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationHolder holder, int position) {
        holder.locationTitle.setText(list.get(position).getPlaceTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LocationHolder extends RecyclerView.ViewHolder {
        TextView locationTitle;
        ImageView locationImage;
        public LocationHolder(@NonNull View itemView)
        {
            super(itemView);
            locationTitle=(TextView) itemView.findViewById(R.id.place_title);
            locationImage=(ImageView) itemView.findViewById(R.id.place_img);
        }
    }
}
