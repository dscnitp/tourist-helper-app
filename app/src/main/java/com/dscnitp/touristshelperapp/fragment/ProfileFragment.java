package com.dscnitp.touristshelperapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dscnitp.touristshelperapp.activity.EditProfile;
import com.dscnitp.touristshelperapp.R;
import com.dscnitp.touristshelperapp.model.UserSuggestion;
import com.dscnitp.touristshelperapp.adapter.ProfileRecycleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "http://dscnitp.pythonanywhere.com/api/user_profile/user_email", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String city = response.getString("city_name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    String city_district = response.getString("city_district");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    int city_zip = response.getInt("city_zip");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });

        requestQueue.add(jsonObjectRequest);

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