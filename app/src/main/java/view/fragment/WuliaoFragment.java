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

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class WuliaoFragment extends Fragment {
    @Bind(R.id.tv)
    TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rag1, null);

        ButterKnife.bind(this, view);
        Observable<String> myObservable = Observable.just("hello word");
//        Subscriber<String> mySubscriber = new Subscriber<String>() {
//            @Override
//            public void onNext(String s) {
//                Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//            }
//        };

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
            }
        };

        myObservable.subscribe(onNextAction);
        return view;
    }
}
