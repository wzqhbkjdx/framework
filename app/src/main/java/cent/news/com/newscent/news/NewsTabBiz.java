package cent.news.com.newscent.news;

import android.os.Bundle;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cent.news.com.baseframe.core.BaseBiz;
import cent.news.com.newscent.helper.utils.XLogUtil;
import cent.news.com.newscent.news.channel.NewsHttp;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import sky.Background;
import sky.BackgroundType;

public class NewsTabBiz extends BaseBiz<NewsTabFragment> {

    private List<NewsListModel.ResultBean.NewsBean> adapterList;
    List<NewsListModel.ResultBean.NewsBean> tmpList;

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
        adapterList = new ArrayList<>(0);
        tmpList = new ArrayList<>(0);
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

        ui().swipeRefreshLayout().setRefreshing(false);

        if(model.getResultCode() == 200) {
            if(adapterList == null) {
                adapterList = new ArrayList<>(0);
            }

            if(tmpList == null) {
                tmpList = new ArrayList<>(0);
            }

            if(model.getResult().getNews().size() > 0) {
                tmpList.addAll(adapterList);
                adapterList.clear();
                adapterList.addAll(model.getResult().getNews());
                adapterList.addAll(tmpList);
                tmpList.clear();
                ui().showTip(model.getResult().getNews().size());
            } else {
                ui().showTip(0);
            }

            ui().setListData(adapterList);
        } else {

        }

    }






}












