package cent.news.com.newscent.news;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cent.news.com.baseframe.view.BaseBuilder;
import cent.news.com.baseframe.view.BaseFragment;
import cent.news.com.baseframe.view.common.BaseRefreshListener;
import cent.news.com.newscent.R;
import cent.news.com.newscent.common.APPUtils;
import cent.news.com.newscent.common.LoadMoreState;
import cent.news.com.newscent.helper.NCHelper;
import cent.news.com.newscent.helper.utils.XLogUtil;
import cent.news.com.newscent.view.CenterLayoutManager;
import cent.news.com.newscent.view.NCNewsListHeader;

public class NewsTabFragment extends BaseFragment<NewsTabBiz> implements BaseRefreshListener {

    public static String CHANNEL_ID = "channel_id";
    public static String ALIAS = "alias";
    public static String ATTVAL = "attval";
    public static String TITLE = "title";
    public static String TYPE = "type";

    private String TAG = this.getClass().getSimpleName();

    @BindView(R.id.ncFrame) public NCFrameLayout ncFrameLayout;

    @BindView(R.id.tv_error_tip) public TextView tvErrorTip;

    @BindView(R.id.tv_tip) public TextView			tvBizTip;

    @BindView(R.id.flState)
    FrameLayout contentFrame;

    @BindView(R.id.empty_layout)
    ConstraintLayout emptyLayout;

    @BindView(R.id.http_error)
    ConstraintLayout httpError;

    @BindView(R.id.reload_btn)
    TextView reload;

    private CountDownTimer mCaptchaCountDownTimer;

    private TextView tvTip;

    public static NewsTabFragment getInstance(int channelID, String alias, int attval, String title, int type) {
        Bundle args = new Bundle();
        args.putInt(CHANNEL_ID, channelID);
        args.putString(ALIAS, alias);
        args.putInt(ATTVAL, attval);
        args.putString(TITLE, title);
        args.putInt(TYPE, type);
        NewsTabFragment newsTabFragment = new NewsTabFragment();
        newsTabFragment.setArguments(args);
        return newsTabFragment;
    }


    @Override
    protected BaseBuilder build(BaseBuilder builder) {
        builder.layoutId(R.layout.fragment_layout_news_tab);
        builder.layoutStateId(R.id.flState);
        builder.recyclerviewId(R.id.recycler_view);
        builder.layoutHttpErrorId(R.layout.http_error);
        builder.layoutEmptyId(R.layout.layout_empty);
        builder.layoutBizErrorId(R.layout.interrest_biz_error);
        builder.recyclerviewAdapter(new NewsListAdapter(this));
        builder.recyclerviewLinearManager(new CenterLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        builder.recyclerviewSwipRefreshId(R.id.swipeRefresh, this);
        builder.recyclerviewColorResIds(R.color.orange);
        builder.tintFitsSystem(true);
        return builder;
    }

    public void showEmptyManully(boolean showEmpty) {
        if(showEmpty) {
            emptyLayout.setVisibility(View.VISIBLE);
        } else {
            emptyLayout.setVisibility(View.GONE);
        }
    }

    public void showHttpError(boolean showError) {
        if(showError) {
            httpError.setVisibility(View.VISIBLE);
        } else {
            httpError.setVisibility(View.GONE);
        }
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        initTip();

        recyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        if(!APPUtils.isNetConnect()) {
            showHttpError();
            return;
        }

        load();
    }

    public void setListData(List<NewsListModel.ResultBean.NewsBean> dataList) {
        showContent();
        adapter().setItems(dataList);
    }


    public void initTip() {
        ncFrameLayout.setEnabled(false);
        NCNewsListHeader ncNewsListHeader = new NCNewsListHeader(getContext());
        tvTip = ncNewsListHeader.getTip();
        ncFrameLayout.disableWhenHorizontalMove(true);
        ncFrameLayout.setKeepHeaderWhenRefresh(true);
        ncFrameLayout.setDurationToCloseHeader(1000);
        ncFrameLayout.setHeaderView(ncNewsListHeader);
        ncFrameLayout.setResistance(1.7f);
        ncFrameLayout.setRatioOfHeaderHeightToRefresh(1.0f);
        ncFrameLayout.addPtrUIHandler(ncNewsListHeader);
    }

    private void load() {
        if(!APPUtils.isNetConnect()) {
            swipeRefreshLayout().setRefreshing(false);
            showTipCustom("请检查网络");
        }

        if(!APPUtils.isNetConnect() && !biz().adapterListExist()) {
            showHttpError(true);
            swipeRefreshLayout().setRefreshing(false);
        } else {
            showHttpError(false);
        }

        biz().getNewsList(3, 10);
    }


    @Override
    protected void buildAfter(View view) {

    }

    @Override
    public boolean onScrolledToBottom() {
        setLoadMoreState(LoadMoreState.LOADING);
        biz().loadMoreData(3, 10);
        return false;
    }


    @Override
    public void onRefresh() {
        if(!APPUtils.isNetConnect()) {
            swipeRefreshLayout().setRefreshing(false);
            showTipCustom("请检查网络");
        }

        if(!APPUtils.isNetConnect() && !biz().adapterListExist()) {
            showHttpError(true);
            swipeRefreshLayout().setRefreshing(false);
        } else {
            showHttpError(false);
        }

        biz().getNewsList(1, 10);
    }

    public void showTip(int count) {
        if (mCaptchaCountDownTimer != null) {
            return;
        }

        swipeRefreshLayout().setEnabled(false);

        NCHelper.pull().autoRefresh(ncFrameLayout);

        tvTip.setText(count != 0 ? tvTip.getContext().getString(R.string.discover_tip, count) : tvTip.getContext().getString(R.string.discover_tip_null, count));

        mCaptchaCountDownTimer = new CountDownTimer(3000, 1000) {

            @Override public void onTick(long l) {}

            @Override public void onFinish() {
                if (ncFrameLayout == null) {
                    mCaptchaCountDownTimer = null;
                    return;
                }
                // rlTip.setVisibility(View.GONE);
                // rlTip.setAnimation(AnimationUtils.loadAnimation(getContext(),
                // R.anim.anim_mask_out));
                swipeRefreshLayout().setEnabled(true);
                ncFrameLayout.refreshComplete();
                mCaptchaCountDownTimer = null;
            }
        };
        mCaptchaCountDownTimer.start();
    }

    public void showTipCustom(String text) {
        if (mCaptchaCountDownTimer != null) {
            return;
        }

        swipeRefreshLayout().setEnabled(false);

        NCHelper.pull().autoRefresh(ncFrameLayout);

        tvTip.setText(text);

        mCaptchaCountDownTimer = new CountDownTimer(3000, 1000) {

            @Override public void onTick(long l) {}

            @Override public void onFinish() {
                if (ncFrameLayout == null) {
                    mCaptchaCountDownTimer = null;
                    return;
                }
                // rlTip.setVisibility(View.GONE);
                // rlTip.setAnimation(AnimationUtils.loadAnimation(getContext(),
                // R.anim.anim_mask_out));
                swipeRefreshLayout().setEnabled(true);
                ncFrameLayout.refreshComplete();
                mCaptchaCountDownTimer = null;
            }
        };
        mCaptchaCountDownTimer.start();
    }

    public void setLoadMoreState(int state) {
        int position = adapter().getItemCount() - 1;
        NewsListAdapter discoverTabAdapter = adapter();
        discoverTabAdapter.setState(state);
        discoverTabAdapter.notifyItemChanged(position);
    }

    @OnClick({R.id.reload_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reload_btn:
                XLogUtil.getInstance().d("bob_test", "reload click");
                swipeRefreshLayout().setRefreshing(true);
                load();
                break;

                default:
                    break;
        }
    }
}













