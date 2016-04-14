package model.model;

import com.jude.http.RequestListener;
import com.jude.http.RequestManager;

import java.util.List;

public class Netrequestmodel {
    public static void getImageList(String url, final NetImageListCallback callback) {

        RequestManager.getInstance().get(url,null, new RequestListener() {
            @Override
            public void onRequest() {

            }

            @Override
            public void onSuccess(String response) {
                duanziParser duanziParser = new duanziParser(response);
                callback.onSuccess( duanziParser.getResult());
            }

            @Override
            public void onError(String errorMsg) {
                callback.onError(errorMsg);
            }
        }, true);
    }

    public interface NetImageListCallback {

        public void onSuccess(List list);

        public void onError(String error);
    }
}
