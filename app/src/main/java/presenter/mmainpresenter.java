package presenter;

import android.os.Bundle;

import com.jude.beam.bijection.Presenter;

import view.activity.mmainactivity;

/**
 * Created by Zhou7 on 2016/4/6.
 */
public class mmainpresenter  extends Presenter<mmainactivity> {

    @Override
    protected void onCreateView(mmainactivity view) {
        super.onCreateView(view);
    }
    public void changetextpersenter(){
        getView().changeview();
    }
}
