package model;

import android.content.Context;

import com.jude.beam.model.AbsModel;
import com.jude.http.RequestListener;
import com.jude.http.RequestManager;

public class testmode extends AbsModel {
    public static testmode getInstance() {
        return getInstance(testmode.class);
    }

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
    }

    public void fun1(RequestListener listener){
        //RequestManager.getInstance().post("http://www.baidu.com", null, listener) ;
    }
}
