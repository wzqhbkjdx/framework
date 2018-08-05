package cent.news.com.newscent.webview;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cent.news.com.baseframe.BaseHelper;
import cent.news.com.baseframe.display.BaseIDisplay;
import cent.news.com.baseframe.view.BaseActivity;
import cent.news.com.baseframe.view.BaseBuilder;
import cent.news.com.newscent.R;

public class WebViewActivity extends BaseActivity<WebViewBiz> {

    public static final String CONTENT_URL = "content_url";

    public static final String CONTENT_TITLE = "content_title";

    private String TAG = this.getClass().getSimpleName();

    @BindView(R.id.webview)
    WebView webView;

    WebViewManage webViewManage;

    @BindView(R.id.tv_title)
    public TextView mTitle;

    @BindView(R.id.loading_layout)
    RelativeLayout mLoadingLayout;

    @BindView(R.id.tv_close)
    TextView closeText;

    @BindView(R.id.rl_title_back)
    RelativeLayout back;


    public static void intent(String url, String title) {
        Bundle bundle = new Bundle();
        bundle.putString(CONTENT_URL, url);
        bundle.putString(CONTENT_TITLE, title);
        BaseHelper.display(BaseIDisplay.class).intentAnimation(WebViewActivity.class,
                R.anim.push_left_in, R.anim.stay_not_move, bundle);
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
        webViewManage = new WebViewManage();
        webViewManage.init(webView);

        setLanding();

        mLoadingLayout.setVisibility(View.VISIBLE);
        closeText.setVisibility(View.GONE);

    }

    private String mUrl = "";

    public void initWebView(final String url, String title) {

        if(title != null) {
            mTitle.setText(title);
        }

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mLoadingLayout.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed(); // 接受所有证书
            }
        });

        WebChromeClient webChromeClient = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mTitle.setText(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {

            }
        };

        webView.setWebChromeClient(webChromeClient);

        webView.postDelayed(new Runnable() {
            @Override public void run() {
                webView.loadUrl(url);
            }
        }, 500);

        this.mUrl = url;

    }

    @OnClick({R.id.rl_title_back}) public void onClick(View view) {
        switch(view.getId()) {
            case R.id.rl_title_back:
                finish();
                break;
        }
    }

}










