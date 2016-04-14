package widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;

import com.panshen.ajiandan.ajiandan.R;


public class SlidUpToRefreshGridView extends GridView implements GridView.OnScrollListener {
    private boolean isLastRow = false;
    RefreshListner RefreshListner = null;
    View footer;

    public SlidUpToRefreshGridView(Context context) {
        super(context);
        initView(context);
    }

    public SlidUpToRefreshGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SlidUpToRefreshGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context paramContext) {
        this.footer = LayoutInflater.from(paramContext).inflate(R.layout.loadmorelayout, null);
        this.footer.findViewById(R.id.progress).setVisibility(View.INVISIBLE);
        //addFooterView(this.footer);
        setOnScrollListener(this);
    }

    public void loadComplete() {
        this.isLastRow = false;
        Log.e("TAG", "load completed");

        //设置footer view 不可见
        //this.footer.findViewById(2131492989).setVisibility(8);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        //当滚到最后一行且停止滚动时，执行加载
        if (isLastRow && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            RefreshListner.onLoad();
            isLastRow = false;
            Log.e("log", "加载");
        }
        Log.e("log", "Scroll state change");
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (visibleItemCount + firstVisibleItem == totalItemCount) {
            Log.e("log", "滑到底部");
            isLastRow = true;
        }
        Log.e("log", "onScrolling");
    }

    public interface RefreshListner {
        void onLoad();
    }

    public void setOnRefreshListner(RefreshListner listener) {
        RefreshListner = listener;
    }
}
