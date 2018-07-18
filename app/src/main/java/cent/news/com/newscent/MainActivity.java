package cent.news.com.newscent;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.ArrayList;

import butterknife.BindView;
import cent.news.com.baseframe.BaseHelper;
import cent.news.com.baseframe.view.BaseActivity;
import cent.news.com.baseframe.view.BaseBuilder;
import cent.news.com.newscent.display.NCDisplay;
import cent.news.com.newscent.helper.utils.XLogUtil;
import cent.news.com.newscent.news.NewsFragment;
import cent.news.com.newscent.video.VideoFragment;
import cent.news.com.newscent.view.FragmentTabManage;
import cent.news.com.newscent.view.Navigation;

public class MainActivity extends BaseActivity<MainBiz> implements Navigation.OnTabSelectedListener, Navigation.OnTabNotSelectedListener {

    private final String TAG = MainActivity.class.getSimpleName();

    public static String NEW = "new";

    @BindView(R.id.navigation_bottom)
    Navigation navigationBottom;

    @BindView(R.id.search_area)
    LinearLayout searchArea;

    @BindView(R.id.search_content)
    EditText searchContent;

    private final ArrayList<AHBottomNavigationItem>	tabs = new ArrayList<>();

    private FragmentTabManage fragmentTabManage;


    public static void intent() {
        BaseHelper.display(NCDisplay.class).intent(MainActivity.class);
    }


    @Override
    protected BaseBuilder build(BaseBuilder builder) {
        builder.layoutId(R.layout.activity_main);
        builder.tintIs(true);
        builder.tintColor(R.color.transparent);
        builder.tintFitsSystem(false);
        return builder;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initTab();
    }

    private void initTab() {
        // fragment 管理器
        fragmentTabManage = new FragmentTabManage(this, getSupportFragmentManager());
        fragmentTabManage.addTab(getResources().getString(R.string.tab_home), NewsFragment.class, null, 0);
        fragmentTabManage.addTab(getResources().getString(R.string.tab_video), VideoFragment.class, null, 1);
        fragmentTabManage.addTab(getResources().getString(R.string.tab_cents), NewsFragment.class, null, 2);
        fragmentTabManage.addTab(getResources().getString(R.string.tab_my), NewsFragment.class, null, 3);

        // Tab 管理器
        AHBottomNavigationItem hi = new AHBottomNavigationItem(R.string.tab_home, R.mipmap.tab1_off, android.R.color.transparent);

        AHBottomNavigationItem address = new AHBottomNavigationItem(R.string.tab_video, R.mipmap.tab2_off, android.R.color.transparent);

        AHBottomNavigationItem discover = new AHBottomNavigationItem(R.string.tab_cents, R.mipmap.tab3_off, android.R.color.transparent);

        AHBottomNavigationItem my = new AHBottomNavigationItem(R.string.tab_my, R.mipmap.tab4_off, android.R.color.transparent);

        tabs.add(hi);
        tabs.add(address);
        tabs.add(discover);
        tabs.add(my);
        navigationBottom.addItems(tabs);
        // 文字大小
        navigationBottom.setTitleTextSize(getResources().getDimension(R.dimen.number_11), getResources().getDimension(R.dimen.number_10));
        // 选中颜色
        navigationBottom.setAccentColor(Color.parseColor("#ff9200"));
        // 不选中颜色
        navigationBottom.setInactiveColor(Color.parseColor("#b8b8b8"));
        // 标题默认显示
        navigationBottom.setForceTitlesDisplay(true);
        // 提醒背景颜色
        navigationBottom.setNotificationBackgroundColor(ContextCompat.getColor(this, R.color.rad));

        // navigationBottom.setNoSelectitem2(2);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        navigationBottom.setOnTabSelectedListener(this);
        navigationBottom.setOnTabNotSelectedListener(this);
        setSelectTab(biz().getInitIndex());
    }


    public void setSelectTab(int position) {
        navigationBottom.setCurrentItem(position);
        //biz(IInterestVPBiz.class).selectSubscribe();
    }


    @Override
    public void onTabSelected(int position, boolean wasSelected) {
        if(fragmentTabManage != null) {
            if(fragmentTabManage.mLastTab != null) {
                int lastTab =  fragmentTabManage.mLastTab.position;
                int lastRes = 0;
                switch (lastTab) {
                    case 0:
                        lastRes = R.mipmap.tab1_off;
                        break;

                    case 1:
                        lastRes = R.mipmap.tab2_off;
                        break;

                    case 2:
                        lastRes = R.mipmap.tab3_off;
                        break;

                    case 3:
                        lastRes = R.mipmap.tab4_off;
                        break;
                }
                navigationBottom.getItem(lastTab).setDrawable(lastRes);
            }

            int res = 0;
            int tintColor = 0;
            switch(position) {
                case 0:
                    res = R.mipmap.tab1_on;
                    tintColor = ContextCompat.getColor(this, R.color.transparent);
                    break;
                case 1:
                    res = R.mipmap.tab2_on;
                    tintColor = ContextCompat.getColor(this, R.color.black);
                    break;
                case 2:
                    res = R.mipmap.tab3_on;
                    tintColor = ContextCompat.getColor(this, R.color.black);
                    break;
                case 3:
                    res = R.mipmap.tab4_on;
                    tintColor = ContextCompat.getColor(this, R.color.transparent);
                    break;
            }

            XLogUtil.getInstance().d(TAG, "onTabSelected");

            tintManager().setTintColor(tintColor);
            navigationBottom.getItem(position).setDrawable(res);
            fragmentTabManage.onTabChanged(position);
        }
    }

    @Override
    public void onNotSelected(int position) {

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        fragmentTabManage.onAttachedToWindow();
    }
}











































