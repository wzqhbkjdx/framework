package cent.news.com.newscent.video;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cent.news.com.baseframe.view.BaseFragment;
import cent.news.com.baseframe.view.adapter.recyclerView.BaseHolder;
import cent.news.com.baseframe.view.adapter.recyclerView.BaseRVAdapter;
import cent.news.com.newscent.R;
import cent.news.com.newscent.common.AppDateUtil;
import cent.news.com.newscent.common.ImageLoadUtil;
import cent.news.com.newscent.common.LoadMoreHolder;
import cent.news.com.newscent.common.LoadMoreOnClick;
import cent.news.com.newscent.common.LoadMoreUtils;
import cent.news.com.newscent.helper.NCHelper;
import cent.news.com.newscent.news.NewsListModel;
import cent.news.com.newscent.webview.WebViewActivity;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoTabAdapter extends BaseRVAdapter<NewsListModel.ResultBean.NewsBean, BaseHolder> {

    private String TAG = this.getClass().getSimpleName();

    private static final int TYPE_VIDEO = 7;

    private int videoPosition;

    private boolean videoIsClicked;

    public int getVideoPosition() {
        return videoPosition;
    }

    public boolean isVideoIsClicked() {
        return videoIsClicked;
    }

    @Override
    public int getCustomViewType(int position) {
        //return getItem(position).getType();
        return TYPE_VIDEO; //只加载一种样式
    }

    public VideoTabAdapter(BaseFragment baseFragment) {
        super(baseFragment);
    }

    @Override
    public BaseHolder newLoadMoreHolder(ViewGroup viewGroup, int type) {
        final LoadMoreHolder loadMoreHolder = LoadMoreUtils.getHolder(viewGroup);
        loadMoreHolder.setLoadMoreOnClick(new LoadMoreOnClick() {
            @Override public void onNotHttp() {
                //NewsTabFragment tabFragment = fragment();
                //tabFragment.biz().setAutoLoadNext(false, -1);
                //setState(LoadMoreState.LOADING);
                //notifyItemChanged(getItemCount() - 1);
                //NewsTabBiz biz = (NewsTabBiz) fragment().biz();
                //if (biz != null) {
                //    biz.loadNextData();
                //}
            }
        });

        return loadMoreHolder;
    }

    @Override
    public BaseHolder newViewHolder(ViewGroup viewGroup, int type) {
        BaseHolder baseHolder = null;
        View view;
        switch (type) {
            case TYPE_VIDEO:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_video_card, viewGroup, false);
                baseHolder = new VideoHolder(view);
                break;
        }

        return baseHolder;
    }

    class VideoHolder extends BaseHolder<NewsListModel.ResultBean.NewsBean> {

        @BindView(R.id.constrain_item)
        LinearLayout itemLayout;

        @BindView(R.id.VideoPlayLayout)
        JCVideoPlayerStandard videoPlayer;

        @BindView(R.id.tv_title)
        TextView title;

        @BindView(R.id.tv_sub_title)
        TextView subTitle;

        @BindView(R.id.tv_name)
        TextView source;

        @BindView(R.id.tv_update_time)
        TextView updateTime;

        public VideoHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(NewsListModel.ResultBean.NewsBean newsBean, final int position) {
            videoPlayer.setUp(newsBean.getPlayUrl(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,
                    newsBean.getTitle());

            ImageLoadUtil.displayWithCropCircle(NCHelper.getInstance(), newsBean.getLogoImageUrl(),
                    videoPlayer.thumbImageView, -1);

            subTitle.setVisibility(View.GONE);
            source.setText(newsBean.getSource());

            updateTime.setText(AppDateUtil.DateCompare(
                    AppDateUtil.changeDateStr2TimeMills(newsBean.getPublishTime()), System.currentTimeMillis()));

            videoPlayer.setOnTouchVideoListener(new JCVideoPlayer.onTouchVideoListener() {
                @Override
                public void onTouch() {
                    videoPosition = position;
                    videoIsClicked = true;
                }
            });
        }

        @OnClick(R.id.constrain_item) public void onItem(View view) {
            NewsListModel.ResultBean.NewsBean newsBean = getItem(getAdapterPosition());
            if (newsBean == null) {
                return;
            }
            gotoWeb(newsBean, getAdapterPosition(), VideoTabAdapter.this, 1);
        }
    }

    public static void gotoWeb(NewsListModel.ResultBean.NewsBean newsBean, int position, BaseRVAdapter adapter, int state) {
        WebViewActivity.intent(newsBean.getLinkUrl(), newsBean.getPlayUrl(), newsBean.getTitle(), newsBean.getLogoImageUrl());
    }

    public void stopVideoPlay(int firstVisibleItemPos, int lastVisibleItemPos) {
        if(videoIsClicked) {
            if(videoPosition < firstVisibleItemPos - 1 || videoPosition > lastVisibleItemPos - 1) {
                JCVideoPlayer.releaseAllVideos();
            }
        }

    }

}
















