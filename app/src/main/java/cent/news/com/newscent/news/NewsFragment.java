package cent.news.com.newscent.news;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import cent.news.com.baseframe.view.BaseBuilder;
import cent.news.com.baseframe.view.BaseFragment;
import cent.news.com.newscent.R;

public class NewsFragment extends BaseFragment<NewsBiz> implements ViewPager.OnPageChangeListener {


    @Override
    protected BaseBuilder build(BaseBuilder builder) {
        builder.layoutId(R.layout.fragment_news_layout);
        builder.tintFitsSystem(false);
        return builder;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

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
