package cent.news.com.newscent.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cent.news.com.baseframe.view.BaseActivity;
import cent.news.com.baseframe.view.adapter.recyclerView.BaseHolder;
import cent.news.com.baseframe.view.adapter.recyclerView.BaseRVAdapter;
import cent.news.com.newscent.R;
import cent.news.com.newscent.common.APPUtils;
import cent.news.com.newscent.common.AppDateUtil;
import cent.news.com.newscent.common.ImageLoadUtil;
import cent.news.com.newscent.common.LoadMoreHolder;
import cent.news.com.newscent.common.LoadMoreOnClick;
import cent.news.com.newscent.common.LoadMoreUtils;
import cent.news.com.newscent.helper.NCHelper;
import cent.news.com.newscent.helper.utils.XLogUtil;
import cent.news.com.newscent.news.NewsListModel;
import cent.news.com.newscent.view.NCDefaultImageView;
import cent.news.com.newscent.webview.WebViewActivity;

public class SearchAdapter extends BaseRVAdapter<NewsListModel.ResultBean.NewsBean, BaseHolder> {

    int	width;

    private static final int TYPE_MAX_IMAGE = 3;

    private static final int TYPE_TEXT = 1;

    private static final int TYPE_VIDEO = 7;

    private static final int TYPE_REFRESH_HERE_BEFORE = 10;

    private static final int TYPE_SMALL_IMAGE = 5;

    private static final int TYPE_THREE_IMAGES = 4;

    private String TAG = this.getClass().getSimpleName();


    public SearchAdapter(BaseActivity activity) {
        super(activity);
        width = APPUtils.getWindowWidth() / 3 - 40;
    }


    @Override
    public int getCustomViewType(int position) {
        return getItem(position).getType();
        //return TYPE_SMALL_IMAGE; //使用小图进行测试
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
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_video_small, viewGroup, false);
                baseHolder = new VideoHolder(view);
                break;
                //case TYPE_REFRESH_HERE_BEFORE:
                //    break;

            case TYPE_MAX_IMAGE: //单图-大图
            case TYPE_TEXT: //纯文本
            case TYPE_SMALL_IMAGE: //单图-小图
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_one_new, viewGroup, false);
                baseHolder = new OneHolder(view);
                break;

            case TYPE_THREE_IMAGES: //三图
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_three, viewGroup, false);
                baseHolder = new ThreeHolder(view);
                break;
        }

        return baseHolder;
    }


    class OneHolder extends BaseHolder<NewsListModel.ResultBean.NewsBean> {

        @BindView(R.id.constrain_item)
        LinearLayout itemLayout;

        @BindView(R.id.tv_title) //标题
                TextView title;

        @BindView(R.id.tv_sub_title)
        TextView subTitle;

        @BindView(R.id.siv_img) //图片
                NCDefaultImageView sivImg;

        @BindView(R.id.tv_update_time)
        TextView tvUpdateTime;	// 更新时间

        @BindView(R.id.tv_name)
        TextView newsSource;

        public OneHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(final NewsListModel.ResultBean.NewsBean newsBean, int position) {
            title.setMaxLines(2);
            subTitle.setVisibility(View.GONE);
            title.setText(newsBean.getTitle());

            //sivImg.setScale(1);
            //sivImg.setupCenterCrop();

            sivImg.post(new Runnable() {

                @Override public void run() {
                    sivImg.load(newsBean.getLogoImageUrl());
                }
            });


            XLogUtil.getInstance().d(TAG, "logoUrl: " + newsBean.getLogoImageUrl());

            tvUpdateTime.setText(AppDateUtil.DateCompare(
                    AppDateUtil.changeDateStr2TimeMills(newsBean.getPublishTime()), System.currentTimeMillis()));
            newsSource.setText(newsBean.getSource());
        }

        @OnClick(R.id.constrain_item) public void onItem(View view) {
            NewsListModel.ResultBean.NewsBean newsBean = getItem(getAdapterPosition());
            if (newsBean == null) {
                return;
            }
            gotoWeb(newsBean, getAdapterPosition(), SearchAdapter.this, 1);
        }
    }


    class BigHolder extends BaseHolder<NewsListModel.ResultBean.NewsBean> {

        @BindView(R.id.constrain_item)
        LinearLayout itemLayout;

        @BindView(R.id.tv_title)
        TextView title;

        @BindView(R.id.tv_sub_title)
        TextView subTitle;

        @BindView(R.id.siv_img)
        NCDefaultImageView sivImg;

        @BindView(R.id.tv_update_time)
        TextView tvUpdateTime;	// 更新时间

        @BindView(R.id.tv_name)
        TextView newsSource;


        public BigHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(NewsListModel.ResultBean.NewsBean newsBean, int position) {
            title.setMaxLines(2);
            subTitle.setVisibility(View.GONE);
            title.setText(newsBean.getTitle());
            sivImg.getLayoutParams().width = width;
            sivImg.getLayoutParams().height = (int) (width / 1.33);
            sivImg.load(newsBean.getLogoImageUrl());

            XLogUtil.getInstance().d(TAG, "logoUrl: " + newsBean.getLogoImageUrl());

            tvUpdateTime.setText(AppDateUtil.DateCompare(
                    AppDateUtil.changeDateStr2TimeMills(newsBean.getPublishTime()), System.currentTimeMillis()));
            newsSource.setText(newsBean.getSource());
        }

        @OnClick(R.id.constrain_item) public void onItem(View view) {
            NewsListModel.ResultBean.NewsBean newsBean = getItem(getAdapterPosition());
            if (newsBean == null) {
                return;
            }
            gotoWeb(newsBean, getAdapterPosition(), SearchAdapter.this, 1);
        }
    }


    class ThreeHolder extends BaseHolder<NewsListModel.ResultBean.NewsBean> {

        @BindView(R.id.constrain_item)
        LinearLayout itemLayout;

        @BindView(R.id.siv_img1) //图片
                NCDefaultImageView sivImg1;

        @BindView(R.id.siv_img2) //图片
                NCDefaultImageView sivImg2;

        @BindView(R.id.siv_img3) //图片
                NCDefaultImageView sivImg3;

        @BindView(R.id.tv_name)
        TextView newsSource;

        @BindView(R.id.tv_update_time)
        TextView tvUpdateTime;	// 更新时间

        @BindView(R.id.tv_title) //标题
                TextView title;

        @BindView(R.id.tv_sub_title)
        TextView subTitle;

        public ThreeHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(NewsListModel.ResultBean.NewsBean newsBean, int position) {
            title.setMaxLines(2);
            subTitle.setVisibility(View.GONE);
            title.setText(newsBean.getTitle());

            sivImg1.getLayoutParams().width = width;
            sivImg1.getLayoutParams().height = (int) (width / 1);
            sivImg2.getLayoutParams().width = width;
            sivImg2.getLayoutParams().height = (int) (width / 1);
            sivImg3.getLayoutParams().width = width;
            sivImg3.getLayoutParams().height = (int) (width / 1);

            for(int i = 0; i < newsBean.getImages().size(); i++) {
                if(i == 0) {
                    sivImg1.load(newsBean.getImages().get(i));
                }
                if(i == 1) {
                    sivImg2.load(newsBean.getImages().get(i));
                }
                if(i == 2) {
                    sivImg3.load(newsBean.getImages().get(i));
                }
            }

            tvUpdateTime.setText(AppDateUtil.DateCompare(
                    AppDateUtil.changeDateStr2TimeMills(newsBean.getPublishTime()), System.currentTimeMillis()));
            newsSource.setText(newsBean.getSource());

        }

        @OnClick(R.id.constrain_item) public void onItem(View view) {
            NewsListModel.ResultBean.NewsBean newsBean = getItem(getAdapterPosition());
            if (newsBean == null) {
                return;
            }
            gotoWeb(newsBean, getAdapterPosition(), SearchAdapter.this, 1);
        }

    }


    class VideoHolder extends BaseHolder<NewsListModel.ResultBean.NewsBean> {
        @BindView(R.id.constrain_item)
        LinearLayout itemLayout;

        @BindView(R.id.siv_img)
        NCDefaultImageView videoImage;

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
        public void bindData(NewsListModel.ResultBean.NewsBean newsBean, int position) {
            ImageLoadUtil.displayWithCropPlaceHolder(NCHelper.getInstance(), newsBean.getLogoImageUrl(),
                    videoImage, -1);

            title.setText(newsBean.getTitle());

            subTitle.setVisibility(View.GONE);
            source.setText(newsBean.getSource());

            updateTime.setText(AppDateUtil.DateCompare(
                    AppDateUtil.changeDateStr2TimeMills(newsBean.getPublishTime()), System.currentTimeMillis()));

        }

        @OnClick(R.id.constrain_item) public void onItem(View view) {
            NewsListModel.ResultBean.NewsBean newsBean = getItem(getAdapterPosition());
            if (newsBean == null) {
                return;
            }
            gotoVideoWeb(newsBean, getAdapterPosition(), SearchAdapter.this, 1);
        }

    }

    public static void gotoVideoWeb(NewsListModel.ResultBean.NewsBean newsBean, int position, BaseRVAdapter adapter, int state) {
        WebViewActivity.intent(newsBean.getLinkUrl(), newsBean.getPlayUrl(), newsBean.getTitle(), newsBean.getLogoImageUrl());
    }

    public static void gotoWeb(NewsListModel.ResultBean.NewsBean newsBean, int position, BaseRVAdapter adapter, int state) {
        WebViewActivity.intent(newsBean.getLinkUrl(), newsBean.getTitle());
    }


}
