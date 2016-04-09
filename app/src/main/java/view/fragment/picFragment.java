package view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.panshen.ajiandan.ajiandan.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

public class PicFragment extends Fragment {
    @Bind(R.id.tv)
    TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rag2, null);
        ButterKnife.bind(this, view);

        return view;
    }

}
