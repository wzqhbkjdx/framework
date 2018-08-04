package cent.news.com.newscent.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import cent.news.com.baseframe.view.BaseFragment;
import cent.news.com.baseframe.view.adapter.recyclerView.BaseHolder;
import cent.news.com.baseframe.view.adapter.recyclerView.BaseRVAdapter;
import cent.news.com.newscent.R;
import cent.news.com.newscent.common.APPUtils;
import cent.news.com.newscent.common.AppDateUtil;
import cent.news.com.newscent.common.LoadMoreHolder;
import cent.news.com.newscent.common.LoadMoreOnClick;
import cent.news.com.newscent.common.LoadMoreUtils;
import cent.news.com.newscent.view.NCDefaultImageView;

public class NewsListAdapter extends BaseRVAdapter<NewsListModel.ResultBean.NewsBean, BaseHolder> {

    int	width;

    private static final int TYPE_MAX_IMAGE = 3;

    private static final int TYPE_TEXT = 1;

    private static final int TYPE_VIDEO = 7;

    private static final int TYPE_REFRESH_HERE_BEFORE = 10;

    private static final int TYPE_SMALL_IMAGE = 5;

    private static final int TYPE_THREE_IMAGES = 4;


    public NewsListAdapter(BaseFragment baseFragment) {
        super(baseFragment);
        width = APPUtils.getWindowWidth() / 3 - 40;
    }


    @Override
    public int getCustomViewType(int position) {
        //return getItem(position).getType();
        return TYPE_SMALL_IMAGE; //使用小图进行测试
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
            case TYPE_MAX_IMAGE: //单图-大图

                break;

            case TYPE_TEXT: //纯文本

                break;

            case TYPE_VIDEO:

                break;

            case TYPE_REFRESH_HERE_BEFORE:

                break;

            case TYPE_SMALL_IMAGE: //单图-小图
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_one_new, viewGroup, false);
                baseHolder = new OneHolder(view);
                break;

            case TYPE_THREE_IMAGES:

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
        public void bindData(NewsListModel.ResultBean.NewsBean newsBean, int position) {
            title.setMaxLines(2);
            subTitle.setVisibility(View.GONE);
            title.setText(newsBean.getTitle());
            sivImg.getLayoutParams().width = width;
            sivImg.getLayoutParams().height = (int) (width / 1.33);
            sivImg.load(newsBean.getLogoImageUrl());
            tvUpdateTime.setText(AppDateUtil.DateCompare(
                    AppDateUtil.changeDateStr2TimeMills(newsBean.getPublishTime()), System.currentTimeMillis()));
            newsSource.setText(newsBean.getSource());
        }
    }


}



















