package cent.news.com.newscent.webview;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import cent.news.com.newscent.helper.NCHelper;

class AndroidToJs {

    private WebView webView;

    private ConvertCore	convert;

    public AndroidToJs(WebView webView) {
        this.webView = webView;
        convert = new ConvertCore(webView);
    }

    @JavascriptInterface
    public void goBack() {
        webView.post(new Runnable() {

            @Override public void run() {
                if (webView.canGoBack()) {
                    webView.goBack();
                }
            }
        });
    }
    @JavascriptInterface public void activityFinish() {
        NCHelper.screenHelper().getCurrentIsRunningActivity().finish();
    }

    @JavascriptInterface public void execute(String json) {
        convert.convert(json);
    }

    public void clean() {
        webView = null;
        convert = null;
    }

    public void executeJS(String callback, String value) {
        convert.callback(callback, value);
    }


}
