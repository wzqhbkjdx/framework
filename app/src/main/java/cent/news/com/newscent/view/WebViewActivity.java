package cent.news.com.newscent.view;

import android.os.Bundle;

import cent.news.com.baseframe.view.BaseActivity;
import cent.news.com.baseframe.view.BaseBuilder;
import cent.news.com.newscent.R;

public class WebViewActivity extends BaseActivity<WebViewBiz> {

    public static void intent(String url) {

    }

    @Override
    protected BaseBuilder build(BaseBuilder builder) {
        builder.layoutId(R.layout.activity_webview);
        builder.toolbarIsOpen(true);
        builder.toolbarLayoutId(R.layout.toolbar_webview_close);
        return builder;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

}
