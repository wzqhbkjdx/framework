package cent.news.com.newscent;

import android.os.Bundle;

import cent.news.com.baseframe.core.BaseBiz;
import cent.news.com.baseframe.modules.log.L;
import cent.news.com.newscent.channel.ChannelHttp;
import cent.news.com.newscent.channel.ChannelModel;
import cent.news.com.newscent.helper.utils.ToolUtils;
import retrofit2.Call;
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
    @Background(BackgroundType.HTTP)
    public void getUrl() {

        L.tag("bob_test").d("getUrl");

        Call<ChannelModel> call = http(ChannelHttp.class).getChannels(ToolUtils.generateSecretKey(ChannelHttp.CHANNEL_URL));

        ChannelModel model = httpBody(call);




    }




}
