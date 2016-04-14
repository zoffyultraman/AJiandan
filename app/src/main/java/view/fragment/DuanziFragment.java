package view.fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.panshen.ajiandan.ajiandan.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import com.panshen.ajiandan.ajiandan.Volley_NetRequest;
import com.panshen.ajiandan.ajiandan.Config;
import model.bean.duanzi;
import widget.SlidUpToRefreshGridView;
import widget.UIToast;

public class DuanziFragment extends BaseFragment {
    SlidUpToRefreshGridView gridview;
    List list = new ArrayList();
    mAdapter adapter;
    duanzi duanzi = new duanzi();

    View view;
    public Handler mHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.ondatachange();
            gridview.loadComplete();
            Log.i("TAG", "handleMSG");
        }
    };

    @Override
    public View getLayout() {
        inidata();
        init();
        return view;
    }


    @Override
    public void init() {
        ButterKnife.bind(this, view);
        view = getActivity().getLayoutInflater().inflate(R.layout.fragment_duanzi, null);
        gridview = (SlidUpToRefreshGridView) view.findViewById(R.id.gview);

        adapter = new mAdapter(list);
        gridview.setAdapter(adapter);
        gridview.setOnRefreshListner(new SlidUpToRefreshGridView.RefreshListner() {
            @Override
            public void onLoad() {
                Log.e("TAG", "call back");
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        List mapp = new ArrayList();
                        for (int i = 0; i < 30; i++) {
                            duanzi duan = new duanzi();
                            duan.setContent(i + "");
                            mapp.add(duan);
                        }
                        list.addAll(mapp);
                        mHandler.sendEmptyMessage(0);
                    }
                }.start();
            }
        });
    }

    @Override
    public void inidata() {
        list.add(duanzi);
        Volley_NetRequest.getInstance(getActivity()).addToQueue(Config.DUANZIHOST, this);
    }


    @Override
    public void onSuccess(List mlist) {
        list.addAll(mlist);
        adapter.ondatachange();
        Log.i("TAG", "recived data in subfragment");
    }

    @Override
    public void onError(String error) {
        getToast().showToast("error to refresh",1);
        Log.e("TAG", error);
    }

    class mAdapter extends ArrayAdapter<duanzi> {
        public mAdapter(List list) {
            super(getActivity(), 0, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            duanzi duanzi = getItem(position);
            viewholder viewholder;
            if (convertView == null) {
                viewholder = new viewholder();
                convertView = getActivity().getLayoutInflater().inflate(R.layout.fragment_duanzi_item, null);
                viewholder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
                viewholder.tv_author = (TextView) convertView.findViewById(R.id.tv_author);
                convertView.setTag(viewholder);
            } else {
                viewholder = (DuanziFragment.viewholder) convertView.getTag();
            }

            viewholder.tv_content.setText(duanzi.getContent());
            viewholder.tv_author.setText(duanzi.getAuthor());

            return convertView;
        }

        public void ondatachange() {
            notifyDataSetChanged();
        }
    }

    class viewholder {
        TextView tv_content;
        TextView tv_author;
    }
}
