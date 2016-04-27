package model;

import android.content.Context;

import com.jude.beam.model.AbsModel;
import com.jude.http.RequestListener;

public class testmainactivitymode extends AbsModel {
    public static testmainactivitymode getInstance() {
        return getInstance(testmainactivitymode.class);
    }

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
    }

    public void fun1(RequestListener listener){
        //RequestManager.getInstance().post("http://www.baidu.com", null, listener) ;
    }
}
