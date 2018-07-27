package cent.news.com.newscent.common;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import butterknife.BindView;
import butterknife.OnClick;
import cent.news.com.baseframe.view.adapter.recyclerView.BaseHolder;
import cent.news.com.newscent.R;

public class LoadMoreHolder extends BaseHolder<Object> {

    LoadMoreOnClick loadMoreOnClick;

    @BindView(R.id.progress_spinner)
    CircularProgressView mProgressSpinner;

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @BindView(R.id.ll_item)
    LinearLayout llItem;

    @BindView(R.id.ll_http_error)
    LinearLayout llHttpError;

    public LoadMoreHolder(View itemView) {
        super(itemView);
    }

    public void setLoadMoreOnClick(LoadMoreOnClick loadMoreOnClick) {
        this.loadMoreOnClick = loadMoreOnClick;
    }

    @Override
    public void bindData(Object o, int position) {
        switch (getAdapter().getState()) {
            case LoadMoreState.LOADING:
                llItem.setVisibility(View.VISIBLE);
                llHttpError.setVisibility(View.GONE);
                mProgressSpinner.setVisibility(View.VISIBLE);
                mTvTitle.setText(R.string.load_more);
                break;
            case LoadMoreState.NOT_DATA:
                llItem.setVisibility(View.VISIBLE);
                llHttpError.setVisibility(View.GONE);
                mProgressSpinner.setVisibility(View.GONE);
                mTvTitle.setText(R.string.load_more_not_date);
                break;
            case LoadMoreState.NOT_HTTP:
                llItem.setVisibility(View.GONE);
                llHttpError.setVisibility(View.VISIBLE);
                break;
            case LoadMoreState.NOT_AUTO_LOAD:
                llItem.setVisibility(View.VISIBLE);
                llHttpError.setVisibility(View.GONE);
                mProgressSpinner.setVisibility(View.GONE);
                mTvTitle.setText(R.string.load_more_not_http);
                break;
        }
    }

    @OnClick({ R.id.tv_reload, R.id.tv_title }) public void onReload() {
        if (loadMoreOnClick != null && getAdapter().getState() == LoadMoreState.NOT_HTTP) {
            loadMoreOnClick.onNotHttp();
        } else if (loadMoreOnClick != null && getAdapter().getState() == LoadMoreState.NOT_AUTO_LOAD) {
            loadMoreOnClick.onNotHttp();
        }
    }
}












