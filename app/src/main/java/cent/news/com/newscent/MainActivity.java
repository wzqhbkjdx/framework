package cent.news.com.newscent;

import android.os.Bundle;

import cent.news.com.baseframe.view.BaseActivity;
import cent.news.com.baseframe.view.BaseBuilder;

public class MainActivity extends BaseActivity<MainBiz> {

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

    }


}
