package cent.news.com.newscent.news;

import android.view.ViewGroup;

import cent.news.com.baseframe.view.BaseFragment;
import cent.news.com.baseframe.view.adapter.recyclerView.BaseHolder;
import cent.news.com.baseframe.view.adapter.recyclerView.BaseRVAdapter;
import cent.news.com.newscent.common.APPUtils;
import cent.news.com.newscent.common.LoadMoreHolder;
import cent.news.com.newscent.common.LoadMoreOnClick;
import cent.news.com.newscent.common.LoadMoreUtils;

public class NewsListAdatper extends BaseRVAdapter<NewsListModel.ResultBean.NewsBean, BaseHolder> {

    int					width;

    public NewsListAdatper(BaseFragment baseFragment) {
        super(baseFragment);
        width = APPUtils.getWindowWidth() / 3 - 40;
    }


    @Override
    public int getCustomViewType(int position) {
        return getItem(position).getType();
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
        return null;
    }
}



















