package model.callback;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

public class Volley_NetRequestModel {
    private volatile static Volley_NetRequestModel instance;
    Context context;
    RequestQueue mQueue = null;

    private Volley_NetRequestModel(Context context) {
        this.context = context;
        mQueue = Volley.newRequestQueue(context);
    }

    public static Volley_NetRequestModel getInstance(Context context) {
        try {
            if (instance == null) {
                synchronized (Volley_NetRequestModel.class) {
                    if (instance == null) {
                        instance = new Volley_NetRequestModel(context);
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
                            DuanziParser DuanziParser = new DuanziParser(response);
                            callback.onSuccess(DuanziParser.getResult());
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
