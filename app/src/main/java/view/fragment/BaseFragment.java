package view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.beam.bijection.RequiresPresenter;

import model.callback.Volley_DuanziNetRequestModel;

import presenter.Duanzipresenter;
import widget.UIToast;

@RequiresPresenter(Duanzipresenter.class)
public abstract class BaseFragment extends Fragment {
    protected abstract View getLayout();

    protected abstract void init();

    protected abstract void inidata();

    protected UIToast uiToast;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        uiToast = new UIToast(getActivity());
        init();
        inidata();
        return getLayout();
    }

    public UIToast getToast() {
        return uiToast;
    }
}
