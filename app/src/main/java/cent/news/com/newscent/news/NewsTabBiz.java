package cent.news.com.newscent.news;

import android.os.Bundle;

import com.google.gson.Gson;

import cent.news.com.baseframe.core.BaseBiz;
import cent.news.com.newscent.helper.utils.XLogUtil;
import cent.news.com.newscent.news.channel.NewsHttp;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import sky.Background;
import sky.BackgroundType;

public class NewsTabBiz extends BaseBiz<NewsTabFragment> {

    private String TAG = this.getClass().getSimpleName();

    public int channelID;

    private String alias;

    private int attval;

    private String title;

    private int type;

    @Override
    protected void initBiz(Bundle bundle) {
        super.initBiz(bundle);
        if(bundle == null) {
            return;
        }
        channelID = bundle.getInt(NewsTabFragment.CHANNEL_ID);
        alias = bundle.getString(NewsTabFragment.ALIAS);
        attval = bundle.getInt(NewsTabFragment.ATTVAL);
        title = bundle.getString(NewsTabFragment.TITLE);
        type = bundle.getInt(NewsTabFragment.TYPE);
    }

    @Background(BackgroundType.HTTP)
    public void getNewsList(int action, int pageSize, String newsIds, int type) {
        XLogUtil.getInstance().d(TAG,"getNewsList");

        NewsListRequestModel requestModel = new NewsListRequestModel();

        requestModel.action = action; // 1-down 2-up 3-init
        requestModel.devicenum = "";
        requestModel.channelID = channelID; //推荐频道
        requestModel.latitude = 0.0;
        requestModel.longitude = 0.0;
        requestModel.pageSize = pageSize;
        requestModel.newsids = newsIds; //缓存文章重新获取
        requestModel.type = type; //0-普通 1-视频 2-地方
        requestModel.dt = 3; //设备类型 2-iphone 3-android
        requestModel.version = "1.0.1";

        RequestBody body = RequestBody.create(MediaType.parse(NewsHttp.JSON_TYPE), new Gson().toJson(requestModel));

        Call<NewsListModel> call = http(NewsHttp.class).getNewsList(body);

        NewsListModel model = httpBody(call);

        if(model.getResultCode() == 200) {
            ui().swipeRefreshLayout().setRefreshing(false);
        }

        XLogUtil.getInstance().d(TAG,"news list size: " + model.getResult().getNews().size());

        XLogUtil.getInstance().d(TAG,"getNewsList complete");
    }






}












