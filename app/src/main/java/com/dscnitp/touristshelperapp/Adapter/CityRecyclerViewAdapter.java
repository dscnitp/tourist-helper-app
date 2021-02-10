package com.dscnitp.touristshelperapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dscnitp.touristshelperapp.R;

import java.util.ArrayList;


public class CityRecyclerViewAdapter extends RecyclerView.Adapter<CityRecyclerViewAdapter.ViewHolder> {
    Context mContext;
    private  OnItemClickListener mListener;
    private final ArrayList<String> CityList,DistrictList,StateList,PinCodeList,ImageList;
    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener=listener;
    }

    public CityRecyclerViewAdapter(Context mContext, ArrayList<String> cityList, ArrayList<String> districtList, ArrayList<String> stateList, ArrayList<String> pinCodeList, ArrayList<String> ImageList) {
        this.mContext = mContext;
        this.CityList = cityList;
        this.DistrictList = districtList;
        this.StateList = stateList;
        this.PinCodeList = pinCodeList;
        this.ImageList=ImageList;
    }

    @NonNull
    @Override
    public CityRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.city_list_layout, parent, false);
        return new CityRecyclerViewAdapter.ViewHolder(view,mListener);
    }


    @Override
    public void onBindViewHolder(@NonNull CityRecyclerViewAdapter.ViewHolder holder, int position) {
     holder.City.setText(CityList.get(position));
     holder.State.setText(StateList.get(position));
     holder.PinCode.setText(PinCodeList.get(position));
     holder.District.setText(DistrictList.get(position));
     Glide.with(mContext).load(ImageList.get(position)).into(holder.City_Image);
    }

    @Override
    public int getItemCount() {
        return CityList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView City,District,State,PinCode;
        public ImageView City_Image;
        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            City=itemView.findViewById(R.id.City);
            District=itemView.findViewById(R.id.District);
            State=itemView.findViewById(R.id.State);
            PinCode=itemView.findViewById(R.id.Pin);
            City_Image=itemView.findViewById(R.id.city_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }
}
