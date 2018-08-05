package cent.news.com.newscent.webview;

import android.os.Bundle;

import cent.news.com.baseframe.core.BaseBiz;
import cent.news.com.newscent.helper.utils.XLogUtil;

import static cent.news.com.newscent.webview.WebViewActivity.CONTENT_TITLE;
import static cent.news.com.newscent.webview.WebViewActivity.CONTENT_URL;

public class WebViewBiz extends BaseBiz<WebViewActivity> {

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void initBiz(Bundle bundle) {
        super.initBiz(bundle);
        String url = bundle.getString(CONTENT_URL);
        String title = bundle.getString(CONTENT_TITLE);
        XLogUtil.getInstance().d(TAG, "url: " + url);
        ui().initWebView(url, title);
    }

    @Override
    public void initBundle() {
        super.initBundle();

    }
}
