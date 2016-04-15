package view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;
import com.panshen.ajiandan.ajiandan.R;

import presenter.testbeamfragpresenter;

@RequiresPresenter(testbeamfragpresenter.class)
public class testfrag extends BeamFragment<testbeamfragpresenter> {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getPresenter().ttt();
        return inflater.inflate(R.layout.testbeamfraglayout, null);
    }
    public void callbackpre(){
        Log.i("ttt","ttt");
    }

}
