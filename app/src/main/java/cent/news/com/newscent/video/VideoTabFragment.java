package cent.news.com.newscent.video;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import cent.news.com.baseframe.view.BaseBuilder;
import cent.news.com.baseframe.view.BaseFragment;
import cent.news.com.baseframe.view.common.BaseRefreshListener;
import cent.news.com.newscent.R;
import cent.news.com.newscent.common.APPUtils;
import cent.news.com.newscent.common.LoadMoreState;
import cent.news.com.newscent.helper.NCHelper;
import cent.news.com.newscent.news.NCFrameLayout;
import cent.news.com.newscent.news.NewsListModel;
import cent.news.com.newscent.view.CenterLayoutManager;
import cent.news.com.newscent.view.NCNewsListHeader;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class VideoTabFragment extends BaseFragment<VideoTabBiz> implements BaseRefreshListener {

    private String TAG = this.getClass().getSimpleName();

    public static String CHANNEL_ID = "channel_id";
    public static String ALIAS = "alias";
    public static String ATTVAL = "attval";
    public static String TITLE = "title";
    public static String TYPE = "type";

    @BindView(R.id.ncFrame) public NCFrameLayout ncFrameLayout;

    @BindView(R.id.tv_error_tip) public TextView tvErrorTip;

    @BindView(R.id.tv_tip) public TextView			tvBizTip;

    @BindView(R.id.flState)
    FrameLayout contentFrame;

    @BindView(R.id.empty_layout)
    ConstraintLayout emptyLayout;

    @BindView(R.id.http_error)
    ConstraintLayout httpError;

    private CountDownTimer mCaptchaCountDownTimer;

    private TextView tvTip;

    public static VideoTabFragment getInstance(int channelID, String alias, int attval, String title, int type) {
        Bundle args = new Bundle();
        args.putInt(CHANNEL_ID, channelID);
        args.putString(ALIAS, alias);
        args.putInt(ATTVAL, attval);
        args.putString(TITLE, title);
        args.putInt(TYPE, type);
        VideoTabFragment videoTabFragment = new VideoTabFragment();
        videoTabFragment.setArguments(args);
        return videoTabFragment;
    }

    @Override
    protected BaseBuilder build(BaseBuilder builder) {
        builder.layoutId(R.layout.fragment_video_tab_layout);
        builder.layoutId(R.layout.fragment_layout_news_tab);
        builder.layoutStateId(R.id.flState);
        builder.recyclerviewId(R.id.recycler_view);
        builder.layoutHttpErrorId(R.layout.http_error);
        builder.layoutEmptyId(R.layout.layout_empty);
        builder.layoutBizErrorId(R.layout.interrest_biz_error);
        builder.recyclerviewAdapter(new VideoTabAdapter(this));
        builder.recyclerviewLinearManager(new CenterLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        builder.recyclerviewSwipRefreshId(R.id.swipeRefresh, this);
        builder.recyclerviewColorResIds(R.color.orange);
        builder.tintFitsSystem(true);
        return builder;
    }


    private void configureRecyclerView4PauseVideo() {
        final GestureDetector detector = initGestureDetector(NCHelper.getInstance());

        recyclerView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return false;
            }
        });

        recyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取最后一个可见view的位置
                    int lastItemPosition = linearManager.findLastVisibleItemPosition();
                    //获取第一个可见view的位置
                    int firstItemPosition = linearManager.findFirstVisibleItemPosition();
                    if(adapter() instanceof VideoTabAdapter) {
                        ((VideoTabAdapter)adapter()).stopVideoPlay(firstItemPosition, lastItemPosition);
                    }
                }
            }
        });
    }


    public void showEmptyLayout(boolean showEmpty) {
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

        configureRecyclerView4PauseVideo();
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

        showRefreshing(true);

        biz().getVideoList(3, 10);
    }

    public void showRefreshing(boolean show) {
        if(show) {
            swipeRefreshLayout().setRefreshing(true);
        } else {
            swipeRefreshLayout().setRefreshing(false);
        }
    }


    @Override
    protected void buildAfter(View view) {

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

    @Override
    public void onPause() {
        super.onPause();
        //停止视频播放
        JCVideoPlayer.releaseAllVideos();
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

    /**
     * swipe refresh listener callback
     * @return
     */
    @Override
    public boolean onScrolledToBottom() {
        setLoadMoreState(LoadMoreState.LOADING);
        biz().loadMoreData(3, 10);
        return false;
    }

    public void setLoadMoreState(int state) {
        int position = adapter().getItemCount() - 1;
        VideoTabAdapter adapter = adapter();
        adapter.setState(state);
        adapter.notifyItemChanged(position);
    }

    /**
     * swipe refresh listener callback
     * @return
     */
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

        biz().getVideoList(1, 10);
    }

    private boolean isFastScrolling = false;
    private GestureDetector initGestureDetector(Context context) {
        return new GestureDetector(context, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if(Math.abs(velocityY) > 4000) {
                    isFastScrolling = true;
                } else {
                    isFastScrolling = false;
                }

                return false;
            }
        });
    }
}
