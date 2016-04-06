package presenter;


import com.jude.beam.bijection.Presenter;

import view.activity.mmainactivity;

public class mmainpresenter extends Presenter<mmainactivity> {

    @Override
    protected void onCreateView(mmainactivity view) {
        super.onCreateView(view);
    }

    public void changetextpersenter() {
        getView().changeview();
    }
}
