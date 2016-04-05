package view.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;

import presenter.mmainpresenter;

@RequiresPresenter(mmainpresenter.class)
public class mmainactivity extends BeamBaseActivity<mmainpresenter> {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
            super.onCreate(savedInstanceState, persistentState);
    }

}
