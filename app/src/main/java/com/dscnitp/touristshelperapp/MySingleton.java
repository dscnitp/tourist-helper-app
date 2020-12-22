package com.dscnitp.touristshelperapp;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private static MySingleton mySingleton;
    private RequestQueue requestQueue;
    private static Context context;
    MySingleton(Context ctx)
    {
        context=ctx;
        requestQueue=getRequestQueue();
    }
    public static synchronized MySingleton getInstance(Context context)
    {
        if(mySingleton==null)
        {
            mySingleton=new MySingleton(context);
        }
        return mySingleton;
    }
    public RequestQueue getRequestQueue()
    {
        if(requestQueue==null)
        {
            requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }
}
