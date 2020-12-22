package com.dscnitp.touristshelperapp.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dscnitp.touristshelperapp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class EditProfile extends AppCompatActivity {
    private String username,email,contact,desc,gender,location;
    EditText editUserName,editEmail,editContact,editDesc,editGender,editLocation;
    Button saveBtn;
    private String URL="http://dscnitp.pythonanywhere.com/api/user_profile/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        bindViewId();
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEditTextData();
                URL=URL+email;
                postData();
                Toast.makeText(EditProfile.this,contact,Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void bindViewId()
    {
        editUserName=(EditText) findViewById(R.id.edit_username);
        editEmail=(EditText) findViewById(R.id.edit_email);
        editContact=(EditText) findViewById(R.id.edit_contact);
        editDesc=(EditText) findViewById(R.id.edit_description);
        editGender=(EditText) findViewById(R.id.edit_gender);
        editLocation=(EditText) findViewById(R.id.edit_location);
        saveBtn=(Button) findViewById(R.id.save_btn);
    }
    private void setEditTextData()
    {
        username=editUserName.getText().toString();
        email=editEmail.getText().toString();
        contact=editContact.getText().toString();
        desc=editDesc.getText().toString();
        gender=editGender.getText().toString();
        location=editLocation.getText().toString();
    }
    private void postData()
    {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JSONObject object=new JSONObject();
        try {
            object.put("username",username);
            object.put("email",email);
            object.put("password","ayushman");
            object.put("contact",contact);
            object.put("description",desc);
            object.put("gender",gender);
            object.put("location",location);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, URL, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(EditProfile.this, "POST request successful!:" + "\n" + response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("onErrorResponse",error.getMessage());
                Toast.makeText(EditProfile.this,"POST request failed!"+URL,Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}
