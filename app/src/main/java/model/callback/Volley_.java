package model.callback;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.jude.http.RequestListener;
import com.jude.http.RequestManager;
import com.jude.http.RequestMap;

public class Volley_ {
    private volatile static Volley_ instance;
    Context context;
    RequestQueue mQueue = null;
    static String str = "i.jandan.net/index.php?";
    private Volley_(Context context) {
        this.context = context;
        mQueue = Volley.newRequestQueue(context);
    }

    public static Volley_ getInstance(Context context) {
        try {
            if (instance == null) {
                synchronized (Volley_.class) {
                    if (instance == null) {
                        instance = new Volley_(context);
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
    public static void addToQueue() {
        RequestMap params = new RequestMap();
        params.put("ID=","3115015");
        params.put("acv_ajax=","true");
        params.put("option=","1");
        RequestManager.getInstance().post(str,params, new RequestListener(){

            @Override
            public void onRequest() {

            }

            @Override
            public void onSuccess(String response) {
                Log.i("RESPONCE",response);
            }

            @Override
            public void onError(String errorMsg) {
                Log.i("RESPONCE",errorMsg);
            }
        });



////        try {
////            StringRequest stringRequest = new StringRequest(str,null,null){
////                @Override
////                protected Map<String, String> getParams() throws AuthFailureError {
////                    Map<String, String> map = new HashMap<String, String>();
////                    map.put("ID=", "3115061");
////                    return map;
////                }
////            };
////            if (mQueue != null)
////                mQueue.add(stringRequest);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
