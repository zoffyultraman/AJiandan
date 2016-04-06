package view.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.panshen.ajiandan.ajiandan.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import presenter.mmainpresenter;

@RequiresPresenter(mmainpresenter.class)
public class mmainactivity extends BeamBaseActivity<mmainpresenter> {
    @Bind(R.id.text_view)
    TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        ButterKnife.bind(this);
        tv.setText("ttttttt");
    }

    public void changeview() {
        tv.setText("aaaaaa");
    }

    @OnClick(R.id.text_view)
    void submit() {
        getPresenter().changetextpersenter();
    }
}
