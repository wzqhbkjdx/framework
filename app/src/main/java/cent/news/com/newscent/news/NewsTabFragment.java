package cent.news.com.newscent.news;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import cent.news.com.baseframe.view.BaseBuilder;
import cent.news.com.baseframe.view.BaseFragment;
import cent.news.com.baseframe.view.common.BaseRefreshListener;
import cent.news.com.newscent.R;
import cent.news.com.newscent.view.CenterLayoutManager;
import cent.news.com.newscent.view.NCNewsListHeader;

public class NewsTabFragment extends BaseFragment<NewsTabBiz> implements BaseRefreshListener {

    public static String CHANNEL_ID = "channel_id";
    public static String ALIAS = "alias";
    public static String ATTVAL = "attval";
    public static String TITLE = "title";
    public static String TYPE = "type";

    @BindView(R.id.ncFrame) public NCFrameLayout ncFrameLayout;

    @BindView(R.id.tv_error_tip) public TextView tvErrorTip;

    @BindView(R.id.tv_tip) public TextView			tvBizTip;

    TextView										tvTip;

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
        //builder.layoutStateId(R.id.flState);
        builder.recyclerviewId(R.id.recycler_view);
        builder.layoutHttpErrorId(R.layout.http_error);
        builder.layoutEmptyId(R.layout.layout_empty);
        builder.layoutBizErrorId(R.layout.interrest_biz_error);
        builder.recyclerviewAdapter(new NewsListAdatper(this));
        builder.recyclerviewLinearManager(new CenterLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        builder.recyclerviewSwipRefreshId(R.id.swipeRefresh, this);
        builder.recyclerviewColorResIds(R.color.orange);
        builder.tintFitsSystem(false);
        return builder;
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        biz().getNewsList(3, 10, "1,2,3", 0);

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













