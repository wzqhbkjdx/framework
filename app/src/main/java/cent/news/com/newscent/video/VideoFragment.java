package cent.news.com.newscent.video;

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

public class VideoFragment extends BaseFragment<VideoBiz> implements ViewPager.OnPageChangeListener {

    private String TAG = this.getClass().getSimpleName();

    @BindView(R.id.smart_tab)
    SmartTabLayout smartTab;


    @BindView(R.id.pager)
    ViewPager viewPager;

    @BindView(R.id.progress_layout)
    RelativeLayout progressLayout;

    private VideoFragmentAdapter videoFragmentAdapter;

    @Override
    protected BaseBuilder build(BaseBuilder builder) {
        builder.layoutId(R.layout.fragment_layout_video);
        builder.tintFitsSystem(false);
        return builder;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        videoFragmentAdapter = new VideoFragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(videoFragmentAdapter);
        viewPager.addOnPageChangeListener(this);
        smartTab.setViewPager(viewPager);

        biz().loadTitles();
    }

    public void setTab(ArrayList<ChannelDBBean> list) {
        XLogUtil.getInstance().d(TAG,"tab size: " + list.size());
        videoFragmentAdapter.setTitleList(list);
        smartTab.setViewPager(viewPager);
    }

    public void hideProgress() {
        progressLayout.setVisibility(View.GONE);
    }

    public void showProgress() {
        progressLayout.setVisibility(View.VISIBLE);
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
