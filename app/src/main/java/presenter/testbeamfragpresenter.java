package presenter;

import android.util.Log;
import com.jude.beam.bijection.Presenter;
import view.fragment.testfrag;


public class testbeamfragpresenter extends Presenter<testfrag> {
    @Override
    protected void onCreateView(testfrag view) {
        super.onCreateView(view);
    }

    public void ttt(){
        getView().callbackpre();
    }

}
