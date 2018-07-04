package cent.news.com.newscent;

import android.os.Bundle;

import cent.news.com.baseframe.core.BaseBiz;
import sky.Background;
import sky.BackgroundType;

/**
 * Created by bym on 2018/7/4.
 */

public class MainBiz extends BaseBiz<MainActivity> {

    @Override
    protected void initBiz(Bundle bundle) {
        super.initBiz(bundle);

    }

    //在线程池里执行网络请求
    @Background(BackgroundType.HTTP) public void getUrl() {

    }

}
