package cent.news.com.newscent.splash;

import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import cent.news.com.baseframe.view.BaseActivity;
import cent.news.com.baseframe.view.BaseBuilder;
import cent.news.com.newscent.MainActivity;
import cent.news.com.newscent.R;
import cent.news.com.newscent.common.APPUtils;
import cent.news.com.newscent.helper.NCHelper;

public class SplashActivity extends BaseActivity<SplashBiz> {

    @BindView(R.id.splash_image)
    ImageView background;

    @Override
    protected BaseBuilder build(BaseBuilder builder) {
        builder.layoutId(R.layout.activity_splash_layout);
        return builder;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        biz().initBackground();
        netCheck();
    }

    private void netCheck() {
        if(APPUtils.isNetConnect()) {
            biz().getTitles();
        } else {
            NCHelper.toast().show(NCHelper.getInstance().getString(R.string.please_check_network));
        }
    }

    public ImageView getBackground() {
        return background;
    }


    public void jump2MainActivity() {
        MainActivity.intent();
        finish();
    }
}













