package cent.news.com.newscent;

import android.os.Bundle;

import com.google.gson.JsonObject;

import cent.news.com.baseframe.core.BaseBiz;
import cent.news.com.newscent.news.channel.NewsHttp;
import cent.news.com.newscent.news.channel.ChannelModel;
import cent.news.com.newscent.helper.utils.XLogUtil;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import sky.Background;
import sky.BackgroundType;

/**
 * Created by bym on 2018/7/4.
 */

public class MainBiz extends BaseBiz<MainActivity> {

    private int	initIndex;

    private String TAG = this.getClass().getSimpleName();

    public int getInitIndex() {
        return initIndex;
    }

    @Override
    protected void initBiz(Bundle bundle) {
        super.initBiz(bundle);

    }

    //在线程池里执行网络请求
    @Background(BackgroundType.HTTP)
    public void getUrl() {

        XLogUtil.getInstance().d(TAG,"getUrl");

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new JsonObject().toString());

        Call<ChannelModel> call = http(NewsHttp.class).getChannels(body);

        ChannelModel model = httpBody(call);

        XLogUtil.getInstance().d(TAG, "result size :" + model.result.channels.size());

    }




}
