package cent.news.com.newscent.news;

import android.view.ViewGroup;

import cent.news.com.baseframe.view.BaseFragment;
import cent.news.com.baseframe.view.adapter.recyclerView.BaseHolder;
import cent.news.com.baseframe.view.adapter.recyclerView.BaseRVAdapter;

public class NewsListAdatper extends BaseRVAdapter<NewsListModel.ResultBean.NewsBean, BaseHolder> {

    public NewsListAdatper(BaseFragment baseFragment) {
        super(baseFragment);
    }


    @Override
    public int getCustomViewType(int position) {
        return getItem(position).getType();
    }


    @Override
    public BaseHolder newLoadMoreHolder(ViewGroup viewGroup, int type) {
        return super.newLoadMoreHolder(viewGroup, type);
    }

    @Override
    public BaseHolder newViewHolder(ViewGroup viewGroup, int type) {
        return null;
    }
}
