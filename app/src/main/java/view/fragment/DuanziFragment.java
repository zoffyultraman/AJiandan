package view.fragment;

import android.util.Log;
import android.view.View;
import com.panshen.ajiandan.ajiandan.R;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import model.DuanziAdapter;
import model.callback.Volley_NetRequestModel;
import com.panshen.ajiandan.ajiandan.Config;
import widget.SlidUpToRefreshGridView;

public class DuanziFragment extends BaseFragment {
    @Bind(R.id.gview)
    SlidUpToRefreshGridView mGridView;
    List mList = new ArrayList();
    DuanziAdapter mAdapter;
    String mPageUrl = "http://jandan.net/duan/page-0#comments";
    View view;
    public static int mPage = 0;
    @Override
    public View getLayout() {
        init();
        inidata();
        return view;
    }

    @Override
    public void init() {
        view = getActivity().getLayoutInflater().inflate(R.layout.fragment_duanzi, null);
        ButterKnife.bind(this, view);
        mAdapter = new DuanziAdapter(getActivity(), mList);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnRefreshListner(new SlidUpToRefreshGridView.RefreshListner() {
            @Override
            public void onLoad() {
                Volley_NetRequestModel.getInstance(getActivity()).addToQueue(mPageUrl.replace("0", DuanziFragment.mPage-- + ""), DuanziFragment.this);
            }
        });
    }

    @Override
    public void inidata() {
        Volley_NetRequestModel.getInstance(getActivity()).addToQueue(Config.DUANZIHOST, this);
    }

    //Volley请求成功回调
    @Override
    public void onSuccess(List mlist) {
        mAdapter.ondatachange(mlist);
    }

    //Volley请求失败回调
    @Override
    public void onError(String error) {
        getToast().showToast("error to refresh", 1);
        Log.e("TAG", error);
    }

}
