package com.panshen.ajiandan.ajiandan;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import model.model.duanziParser;

public class Volley_NetRequest {
    private volatile static Volley_NetRequest instance;
    Context context;
    RequestQueue mQueue = null;

    private Volley_NetRequest(Context context) {
        this.context = context;
        mQueue = Volley.newRequestQueue(context);
    }

    public static Volley_NetRequest getInstance(Context context) {
        try {
            if (instance == null) {
                synchronized (Volley_NetRequest.class) {
                    if (instance == null) {
                        instance = new Volley_NetRequest(context);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    /*
    添加一个任务到队列中
    * */
    public void addToQueue(String str,final ListCallback callback) {
        try {
            StringRequest stringRequest = new StringRequest(str,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            duanziParser duanziParser = new duanziParser(response);
                            callback.onSuccess(duanziParser.getResult());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    callback.onError("Volley Error");
                }
            });
            if (mQueue != null)
                mQueue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface ListCallback {

        public void onSuccess(List list);

        public void onError(String error);
    }
}
