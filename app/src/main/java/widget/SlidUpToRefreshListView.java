package widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.panshen.ajiandan.ajiandan.R;


public class SlidUpToRefreshListView extends ListView implements ListView.OnScrollListener {
    RefreshListner RefreshListner = null;
    View footer;
    boolean isLoading;
    int lastVisibleItem;
    int mListViewFirstItem = 0;
    int totalItemCount;

    public SlidUpToRefreshListView(Context context) {
        super(context);
        initView(context);
    }

    public SlidUpToRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SlidUpToRefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context paramContext) {
        this.footer = LayoutInflater.from(paramContext).inflate(R.layout.listview_onload_more, null);
        this.footer.findViewById(R.id.list_footerprogress).setVisibility(View.INVISIBLE);
        addFooterView(this.footer);
        setOnScrollListener(this);
    }

    public void LoadCompleted() {
        isLoading = false;
        footer.findViewById(R.id.list_footerprogress).setVisibility(View.INVISIBLE);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        //当滚到最后一行且停止滚动时，执行加载
//        if (isLastRow && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
//            RefreshListner.onLoad();
//            isLastRow = false;
//            this.isLoading = false;
//            this.footer.findViewById(R.id.progress).setVisibility(View.VISIBLE);
//        }
        if ((this.totalItemCount == this.lastVisibleItem) && (scrollState == 0) && (!this.isLoading))
        {
            this.isLoading = true;
            this.footer.findViewById(R.id.list_footerprogress).setVisibility(View.VISIBLE);
            this.RefreshListner.onLoad();
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }

    public interface RefreshListner {
        void onLoad();
    }

    public void setOnRefreshListner(RefreshListner listener) {
        RefreshListner = listener;
    }
}
