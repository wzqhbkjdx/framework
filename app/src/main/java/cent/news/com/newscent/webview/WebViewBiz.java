package cent.news.com.newscent.webview;

import android.os.Bundle;

import org.apache.commons.lang3.StringUtils;

import cent.news.com.baseframe.core.BaseBiz;

import static cent.news.com.newscent.webview.WebViewActivity.CONTENT_TITLE;
import static cent.news.com.newscent.webview.WebViewActivity.CONTENT_URL;
import static cent.news.com.newscent.webview.WebViewActivity.IMAGE_COVER_URL;
import static cent.news.com.newscent.webview.WebViewActivity.PLAY_URL;

public class WebViewBiz extends BaseBiz<WebViewActivity> {

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void initBiz(Bundle bundle) {
        super.initBiz(bundle);
        String url = bundle.getString(CONTENT_URL);
        String title = bundle.getString(CONTENT_TITLE);
        String playUrl = bundle.getString(PLAY_URL);
        String imageCoverUrl = bundle.getString(IMAGE_COVER_URL);

        ui().initWebView(url, title);

        if(!StringUtils.isEmpty(playUrl)) {
            ui().initVideo(playUrl, imageCoverUrl, title);
        } else {
            ui().hideVideoPlayer();
        }
    }

    @Override
    public void initBundle() {
        super.initBundle();
    }
}
