package presenter;

import android.util.Log;

import com.jude.beam.bijection.Presenter;
import com.jude.http.RequestListener;

import model.testmode;
import view.activity.MainActivity;

public class MainActivityPresenter extends Presenter<MainActivity> {
    @Override
    protected void onCreateView(MainActivity view) {
        super.onCreateView(view);
    }

    public void fun(){
        testmode.getInstance().fun1(new RequestListener() {
            @Override
            public void onRequest() {
                Log.i("test fun","onRequest");
            }

            @Override
            public void onSuccess(String response) {
                Log.i("test fun",response);
            }

            @Override
            public void onError(String errorMsg) {
                Log.i("test fun",errorMsg);
            }
        });
    }
}
