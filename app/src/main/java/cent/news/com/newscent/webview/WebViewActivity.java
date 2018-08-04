package cent.news.com.newscent.webview;

import android.os.Bundle;

import cent.news.com.baseframe.BaseHelper;
import cent.news.com.baseframe.display.BaseIDisplay;
import cent.news.com.baseframe.view.BaseActivity;
import cent.news.com.baseframe.view.BaseBuilder;
import cent.news.com.newscent.R;

public class WebViewActivity extends BaseActivity<WebViewBiz> {

    public static void intent(String url) {
        //BaseHelper.display(BaseIDisplay.class).intent(WebViewActivity.class);
        BaseHelper.display(BaseIDisplay.class).intentAnimation(WebViewActivity.class, R.anim.push_left_in, R.anim.stay_not_move);
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
        setFinishAnim(R.anim.stay_not_move, R.anim.slide_out_right);
    }


}
