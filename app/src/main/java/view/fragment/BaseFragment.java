package view.fragment;

import android.os.Bundle;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panshen.ajiandan.ajiandan.Volley_NetRequest;
import com.panshen.ajiandan.ajiandan.Config;

import widget.UIToast;


public abstract class BaseFragment extends Fragment implements Volley_NetRequest.ListCallback {
    public abstract View getLayout();

    public abstract void init();

    public abstract void inidata();

    public UIToast uiToast;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        uiToast = new UIToast(getActivity());
        return getLayout();
    }
    public UIToast getToast(){
        return uiToast;
    }

}
