package cent.news.com.newscent.news;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import cent.news.com.baseframe.core.BaseBiz;
import cent.news.com.newscent.helper.utils.XLogUtil;
import cent.news.com.newscent.news.channel.ChannelModel;
import cent.news.com.newscent.news.channel.NewsHttp;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import sky.Background;
import sky.BackgroundType;

public class NewsBiz extends BaseBiz<NewsFragment> {

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void initBiz(Bundle bundle) {
        super.initBiz(bundle);
    }


    @Background(BackgroundType.HTTP)
    public void getNewsList() {
        XLogUtil.getInstance().d(TAG,"getNewsList");

        NewsListRequestModel requestModel = new NewsListRequestModel();

        requestModel.action = 1; //下拉刷新
        requestModel.devicenum = "";
        requestModel.channelID = 1; //推荐频道
        requestModel.latitude = 0.0;
        requestModel.longitude = 0.0;
        requestModel.pageSize = 10;
        requestModel.newsids = ""; //缓存文章重新获取
        requestModel.type = 1;
        requestModel.dt = 3;
        requestModel.version = "1.0.1";

        RequestBody body = RequestBody.create(MediaType.parse(NewsHttp.JSON_TYPE), new Gson().toJson(requestModel));

        Call<NewsListModel> call = http(NewsHttp.class).getNewsList(body);

        NewsListModel model = httpBody(call);

        XLogUtil.getInstance().d(TAG,"getNewsList complete");
    }


    //在线程池里执行网络请求
    @Background(BackgroundType.HTTP)
    public void getTitles() {

        XLogUtil.getInstance().d(TAG,"getUrl");

        //因为该接口不需要传递请求参数，所以传了一个空JSON字符串
        RequestBody body = RequestBody.create(MediaType.parse(NewsHttp.JSON_TYPE), new JsonObject().toString());

        Call<ChannelModel> call = http(NewsHttp.class).getChannels(body);

        ChannelModel model = httpBody(call);

        XLogUtil.getInstance().d(TAG, "result size :" + model.result.channels.size());

        if(model.code == 200) {
            //保存到数据库中
            //DaoSession daoSession =


        } else {



        }

    }

    @Background(BackgroundType.WORK)
    public void initTitles() {
        //从数据库中读取缓存的titles

    }
}













