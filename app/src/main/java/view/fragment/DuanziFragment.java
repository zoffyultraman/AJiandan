package view.fragment;

import android.util.Log;
import android.view.View;

import com.panshen.ajiandan.ajiandan.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import adapter.DuanziAdapter;
import model.callback.Volley_DuanziNetRequestModel;

import com.panshen.ajiandan.ajiandan.Config;

import widget.SlidUpToRefreshListView;

public class DuanziFragment extends BaseFragment implements Volley_DuanziNetRequestModel.ListCallback {
    @Bind(R.id.listview)
    SlidUpToRefreshListView mListView;
    List mList = new ArrayList();
    DuanziAdapter mAdapter;
    View view;
    public static int mPage = 0;

    @Override
    public void init() {
        view = getActivity().getLayoutInflater().inflate(R.layout.fragment_duanzi, null);
        ButterKnife.bind(this, view);
        mAdapter = new DuanziAdapter(getActivity(), mList);
        mListView.setAdapter(mAdapter);
        mListView.setOnRefreshListner(new SlidUpToRefreshListView.RefreshListner() {
            @Override
            public void onLoad() {
                Volley_DuanziNetRequestModel.getInstance(getActivity()).addToQueue(Config.FULL_DUANZI_URL.replace("0", DuanziFragment.mPage-- + ""), DuanziFragment.this);

            }
        });
    }

    @Override
    public View getLayout() {
        return view;
    }

    @Override
    public void inidata() {
        Volley_DuanziNetRequestModel.getInstance(getActivity()).addToQueue(Config.DUANZI_HOST, this);
    }

    //Volley请求成功回调
    @Override
    public void onSuccess(List mlist) {
        mAdapter.ondatachange(mlist);
        mListView.LoadCompleted();
    }

    //Volley请求失败回调
    @Override
    public void onError(String error) {
        getToast().showToast("error to refresh", 1);
        Log.e("TAG", error);
        mListView.LoadCompleted();
    }
}
