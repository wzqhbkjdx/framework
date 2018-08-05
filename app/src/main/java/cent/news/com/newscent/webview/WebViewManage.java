package cent.news.com.newscent.webview;

import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import cent.news.com.baseframe.modules.log.L;
import cent.news.com.newscent.helper.NCHelper;

public class WebViewManage {

    public static final String	KEY_TAG			= "key_type";

    public static final String	BACK_URL		= "key_back_url";

    public static final String	CALL_BACK		= "key_call_back";

    public static final String	CALL_BACK_VALUE	= "key_call_back_value";

    AndroidToJs androidToJs;

    public void init(WebView webView) {
        webView.requestFocus();
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setWebChromeClient(new WebChromeClient());

        WebSettings setting = webView.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setCacheMode(WebSettings.LOAD_DEFAULT); // 默认
        setting.setJavaScriptCanOpenWindowsAutomatically(true);
        setting.setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setting.setAllowFileAccessFromFileURLs(true);
        }
        setting.setUseWideViewPort(true);
        setting.setLoadWithOverviewMode(true);
        setting.setDomStorageEnabled(true);

        setting.setTextZoom(100);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setting.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        setting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 添加ua
        String ua = webView.getSettings().getUserAgentString();
        webView.getSettings().setUserAgentString(ua + " " + NCHelper.getInstance().getPackageName());
        if (NCHelper.isLogOpen()) {
            L.i(ua);
        }

        // 1.首先在WebView初始化时添加如下代码
        if (Build.VERSION.SDK_INT >= 19) {
            /*
             * 对系统API在19以上的版本作了兼容。因为4.4以上系统在onPageFinished时再恢复图片加载时,
             * 如果存在多张图片引用的是相同的src时，会只有一个image标签得到加载，因而对于这样的系统我们就先直接加载。
             */
            setting.setLoadsImagesAutomatically(true);
        } else {
            setting.setLoadsImagesAutomatically(false);
        }
        CookieManager cookieManager = CookieManager.getInstance();
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        } else {
            cookieManager.setAcceptCookie(true);
        }
        // JS
        androidToJs = new AndroidToJs(webView);
        webView.addJavascriptInterface(androidToJs, "LocalNative");
    }

    public AndroidToJs getAndroidToJs() {
        return androidToJs;
    }

    public void clean() {
        if (androidToJs != null) {
            androidToJs.clean();
        }
    }


}
