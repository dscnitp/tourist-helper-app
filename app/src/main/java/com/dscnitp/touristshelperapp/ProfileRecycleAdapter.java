package com.dscnitp.touristshelperapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfileRecycleAdapter extends RecyclerView.Adapter<ProfileRecycleAdapter.LocationHolder> {
    ArrayList<UserSuggestion> list;

    public ProfileRecycleAdapter(ArrayList<UserSuggestion> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public LocationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_element,parent,false);
        LocationHolder myHolder=new LocationHolder(myView);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationHolder holder, int position) {
        holder.city.setText(list.get(position).getCity());
        holder.state.setText("State: "+list.get(position).getState());
        holder.price.setText("Price: "+list.get(position).getPrice()+"/head");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LocationHolder extends RecyclerView.ViewHolder {
        TextView city;
        TextView state;
        TextView price;
        public LocationHolder(@NonNull View itemView) {
            super(itemView);
            city=(TextView) itemView.findViewById(R.id.city);
            state=(TextView) itemView.findViewById(R.id.state);
            price=(TextView) itemView.findViewById(R.id.price);
        }
    }
}
