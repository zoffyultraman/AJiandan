package view.fragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.panshen.ajiandan.ajiandan.Config;
import com.panshen.ajiandan.ajiandan.R;

import java.util.ArrayList;
import java.util.List;

import adapter.PicAdapter;
import model.callback.Volley_PicNetRequestModel;
import widget.SlidUpToRefreshListView;

public class PicFragment extends BaseFragment implements  Volley_PicNetRequestModel.ListCallback {
    SlidUpToRefreshListView mListView;
    View view;
    Context mContext;
    public static int mPage = 0;
    List list = new ArrayList();
    PicAdapter picAdapter;
    @Override
    protected View getLayout() {
        return view;
    }

    @Override
    protected void init() {
        mContext = getActivity();
        view = LayoutInflater.from(mContext).inflate(R.layout.fragment_pic, null);
        mListView = (SlidUpToRefreshListView) view.findViewById(R.id.pic_listview);
        picAdapter = new PicAdapter(mContext, list);
        mListView.setAdapter(picAdapter);
        mListView.setOnRefreshListner(new SlidUpToRefreshListView.RefreshListner() {
            @Override
            public void onLoad() {
                Volley_PicNetRequestModel.getInstance(getActivity()).addToQueue(Config.FULL_PIC_URL.replace("0", PicFragment.mPage-- + ""), PicFragment.this);
            }
        });
    }

    @Override
    protected void inidata() {
        Volley_PicNetRequestModel.getInstance(getActivity()).addToQueue(Config.FULL_PIC_URL, PicFragment.this);
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
