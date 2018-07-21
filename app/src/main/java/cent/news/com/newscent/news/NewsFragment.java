package cent.news.com.newscent.news;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import butterknife.BindView;
import cent.news.com.baseframe.view.BaseBuilder;
import cent.news.com.baseframe.view.BaseFragment;
import cent.news.com.newscent.R;
import cent.news.com.newscent.view.tab.SmartTabLayout;

public class NewsFragment extends BaseFragment<NewsBiz> implements ViewPager.OnPageChangeListener {

    @BindView(R.id.smart_tab)
    SmartTabLayout smartTab;


    @BindView(R.id.pager)
    ViewPager viewPager;



    @Override
    protected BaseBuilder build(BaseBuilder builder) {
        builder.layoutId(R.layout.fragment_news_layout);
        builder.tintFitsSystem(false);

        //设置返回为null的界面

        //设置loading的界面



        return builder;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //获取新的频道列表
        biz().getTitles();

        //biz().getNewsList();

        //读取缓存中的频道列表，如果存在直接加载，如果不存在就再次获取，重试次数要合理，如果多次获取不到，则展示空页面


        //对比缓存的频道和新获取的频道，如果有不同则进行更新，增或者删除，这样Server端可以完全控制

        //

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













