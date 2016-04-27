package view.fragment;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.panshen.ajiandan.ajiandan.Config;
import com.panshen.ajiandan.ajiandan.R;

import java.util.ArrayList;
import java.util.List;

import adapter.MeiziAdapter;
import model.callback.Volley_MeiziNetRequestModel;
import widget.SlidUpToRefreshListView;

public class MeiziFragment extends BaseFragment implements Volley_MeiziNetRequestModel.ListCallback {
    SlidUpToRefreshListView mListView;
    View view;
    Context mContext;
    public static int mPage = 0;
    List list = new ArrayList();
    MeiziAdapter picAdapter;
    @Override
    protected void init() {
        mContext = getActivity();
        view = LayoutInflater.from(mContext).inflate(R.layout.fragment_meizi, null);
        mListView = (SlidUpToRefreshListView) view.findViewById(R.id.pic_listview);
        picAdapter = new MeiziAdapter(mContext, list);
        mListView.setAdapter(picAdapter);
        mListView.setOnRefreshListner(new SlidUpToRefreshListView.RefreshListner() {
            @Override
            public void onLoad() {
                Volley_MeiziNetRequestModel.getInstance(getActivity()).addToQueue(Config.FULL_MEIZI_URL.replace("0", MeiziFragment.mPage-- + ""), MeiziFragment.this);
            }
        });
    }

    @Override
    protected View getLayout() {
        return view;
    }

    @Override
    protected void inidata() {
        Volley_MeiziNetRequestModel.getInstance(getActivity()).addToQueue(Config.FULL_MEIZI_URL, MeiziFragment.this);
    }

    @Override
    public void onSuccess(List list) {
        picAdapter.ondatachange(list);
        mListView.LoadCompleted();
    }

    @Override
    public void onError(String error) {
        getToast().showToast("refresh faild", 1);
        Log.e("TAG", error);
        mListView.LoadCompleted();
    }
}
