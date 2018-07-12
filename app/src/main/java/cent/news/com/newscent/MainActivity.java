package cent.news.com.newscent;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.ArrayList;

import butterknife.BindView;
import cent.news.com.baseframe.BaseHelper;
import cent.news.com.baseframe.view.BaseActivity;
import cent.news.com.baseframe.view.BaseBuilder;
import cent.news.com.newscent.display.NCDisplay;
import cent.news.com.newscent.view.FragmentTabManage;
import cent.news.com.newscent.view.Navigation;

public class MainActivity extends BaseActivity<MainBiz> {

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
        biz().getUrl();

        initTab();
    }

    private void initTab() {
        fragmentTabManage = new FragmentTabManage(this, getSupportFragmentManager());

    }


}




















