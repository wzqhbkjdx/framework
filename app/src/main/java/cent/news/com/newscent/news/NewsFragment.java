package cent.news.com.newscent.news;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import cent.news.com.baseframe.view.BaseBuilder;
import cent.news.com.baseframe.view.BaseFragment;
import cent.news.com.newscent.R;
import cent.news.com.newscent.helper.utils.XLogUtil;
import cent.news.com.newscent.news.channel.ChannelDBBean;
import cent.news.com.newscent.view.tab.SmartTabLayout;

public class NewsFragment extends BaseFragment<NewsBiz> implements ViewPager.OnPageChangeListener {

    private String TAG = this.getClass().getSimpleName();

    @BindView(R.id.smart_tab)
    SmartTabLayout smartTab;


    @BindView(R.id.pager)
    ViewPager viewPager;

    @BindView(R.id.progress_layout)
    RelativeLayout progressLayout;


    private NewsFragmentAdapter newsListAdapter;

    private int currentPage = -1;

    @Override
    protected BaseBuilder build(BaseBuilder builder) {
        builder.layoutId(R.layout.fragment_news_layout);
        builder.tintFitsSystem(true);

        //设置返回为null的界面

        //设置loading的界面


        return builder;
    }

    public void hideProgress() {
        progressLayout.setVisibility(View.GONE);
    }

    public void showProgress() {
        progressLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //获取新的频道列表
        newsListAdapter = new NewsFragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(newsListAdapter);
        viewPager.addOnPageChangeListener(this);
        smartTab.setViewPager(viewPager);
        biz().loadTitles();
    }

    public void setTab(ArrayList<ChannelDBBean> list) {
        XLogUtil.getInstance().d(TAG,"tab size: " + list.size());
        newsListAdapter.setTitleList(list);
        smartTab.setViewPager(viewPager);
    }

    @Override
    protected void buildAfter(View view) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}













