package view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;

import model.callback.Volley_NetRequestModel;

import presenter.Duanzipresenter;
import widget.UIToast;

@RequiresPresenter(Duanzipresenter.class)
public abstract class BaseFragment extends BeamFragment<Duanzipresenter> implements Volley_NetRequestModel.ListCallback  {
    protected abstract View getLayout();

    protected abstract void init();

    protected abstract void inidata();

    protected UIToast uiToast;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        uiToast = new UIToast(getActivity());
        return getLayout();
    }

    public UIToast getToast() {
        return uiToast;
    }
}
