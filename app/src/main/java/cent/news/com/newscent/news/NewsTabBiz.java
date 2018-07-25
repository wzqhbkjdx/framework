package cent.news.com.newscent.news;

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


    @Background(BackgroundType.HTTP)
    public void getNewsList(int action, int channelID, int pageSize, String newsIds, int type, int dt) {
        XLogUtil.getInstance().d(TAG,"getNewsList");

        NewsListRequestModel requestModel = new NewsListRequestModel();

        requestModel.action = action; //下拉刷新
        requestModel.devicenum = "";
        requestModel.channelID = channelID; //推荐频道
        requestModel.latitude = 0.0;
        requestModel.longitude = 0.0;
        requestModel.pageSize = pageSize;
        requestModel.newsids = newsIds; //缓存文章重新获取
        requestModel.type = type;
        requestModel.dt = dt;
        requestModel.version = "1.0.1";

        RequestBody body = RequestBody.create(MediaType.parse(NewsHttp.JSON_TYPE), new Gson().toJson(requestModel));

        Call<NewsListModel> call = http(NewsHttp.class).getNewsList(body);

        NewsListModel model = httpBody(call);

        XLogUtil.getInstance().d(TAG,"getNewsList complete");
    }


}
