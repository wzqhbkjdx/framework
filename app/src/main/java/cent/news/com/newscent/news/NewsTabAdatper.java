package cent.news.com.newscent.news;

import android.view.ViewGroup;

import cent.news.com.baseframe.view.BaseActivity;
import cent.news.com.baseframe.view.BaseDialogFragment;
import cent.news.com.baseframe.view.BaseFragment;
import cent.news.com.baseframe.view.adapter.recyclerView.BaseHolder;
import cent.news.com.baseframe.view.adapter.recyclerView.BaseRVAdapter;

public class NewsTabAdatper extends BaseRVAdapter<NewsListModel, BaseHolder> {


    public NewsTabAdatper(BaseActivity BaseActivity) {
        super(BaseActivity);
    }

    public NewsTabAdatper(BaseFragment baseFragment) {
        super(baseFragment);
    }

    public NewsTabAdatper(BaseDialogFragment baseDialogFragment) {
        super(baseDialogFragment);
    }

    @Override
    public BaseHolder newViewHolder(ViewGroup viewGroup, int type) {
        return null;
    }
}
