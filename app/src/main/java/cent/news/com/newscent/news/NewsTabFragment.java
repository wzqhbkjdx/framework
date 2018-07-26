package cent.news.com.newscent.news;

import android.os.Bundle;
import android.view.View;

import cent.news.com.baseframe.view.BaseBuilder;
import cent.news.com.baseframe.view.BaseFragment;
import cent.news.com.baseframe.view.common.BaseRefreshListener;
import cent.news.com.newscent.R;

public class NewsTabFragment extends BaseFragment<NewsTabBiz> implements BaseRefreshListener {

    public static String CHANNEL_ID = "channel_id";
    public static String ALIAS = "alias";
    public static String ATTVAL = "attval";
    public static String TITLE = "title";
    public static String TYPE = "type";


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
        //builder.layoutStateId(R.id.frame_state);
        //builder.recyclerviewId(R.id.recycler_view);
        //builder.layoutHttpErrorId(R.layout.http_error);
        //builder.layoutEmptyId(R.layout.layout_empty);
        return builder;
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        biz().getNewsList(3, 10, "1,2,3", 0);
    }

    @Override
    protected void buildAfter(View view) {

    }

    @Override
    public boolean onScrolledToBottom() {
        return false;
    }

    @Override
    public void onRefresh() {

    }
}













