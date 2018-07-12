package cent.news.com.newscent;

import android.os.Bundle;

import butterknife.BindView;
import cent.news.com.baseframe.BaseHelper;
import cent.news.com.baseframe.view.BaseActivity;
import cent.news.com.baseframe.view.BaseBuilder;
import cent.news.com.newscent.display.NCDisplay;
import cent.news.com.newscent.view.Navigation;

public class MainActivity extends BaseActivity<MainBiz> {

    public static String NEW = "new";

    public static void intent() {
        BaseHelper.display(NCDisplay.class).intent(MainActivity.class);
    }

    @BindView(R.id.navigation_bottom)
    Navigation navigationBottom;




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

    }


}
