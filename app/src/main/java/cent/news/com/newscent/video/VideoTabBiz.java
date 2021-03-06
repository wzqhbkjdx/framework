package cent.news.com.newscent.video;

import android.os.Bundle;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cent.news.com.baseframe.core.BaseBiz;
import cent.news.com.newscent.common.LoadMoreState;
import cent.news.com.newscent.helper.utils.XLogUtil;
import cent.news.com.newscent.news.NewsListModel;
import cent.news.com.newscent.news.NewsListRequestModel;
import cent.news.com.newscent.news.channel.NewsHttp;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import sky.Background;
import sky.BackgroundType;

public class VideoTabBiz extends BaseBiz<VideoTabFragment> {

    private List<NewsListModel.ResultBean.NewsBean> adapterList;
    List<NewsListModel.ResultBean.NewsBean> tmpList;

    private String TAG = this.getClass().getSimpleName();

    public int channelID;

    private String alias;

    private int attval;

    private String title;

    private int type;

    public boolean adapterListExist() {
        if(adapterList != null && adapterList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void initBiz(Bundle bundle) {
        super.initBiz(bundle);
        if(bundle == null) {
            return;
        }
        channelID = bundle.getInt(VideoTabFragment.CHANNEL_ID);
        alias = bundle.getString(VideoTabFragment.ALIAS);
        attval = bundle.getInt(VideoTabFragment.ATTVAL);
        title = bundle.getString(VideoTabFragment.TITLE);
        type = bundle.getInt(VideoTabFragment.TYPE);
        adapterList = new ArrayList<>(0);
        tmpList = new ArrayList<>(0);
    }

    @Background(BackgroundType.HTTP)
    public void getVideoList(int action, int pageSize) {
        XLogUtil.getInstance().d(TAG,"getVideoList");

        NewsListRequestModel requestModel = new NewsListRequestModel();

        requestModel.action = action; // 1-down 2-up 3-init
        requestModel.devicenum = "866790037218095";
        requestModel.channelID = channelID; //推荐频道
        requestModel.latitude = 0;
        requestModel.longitude = 0;
        requestModel.pageSize = pageSize;
        //requestModel.newsids = newsIds; //缓存文章重新获取
        requestModel.newsID = 0;
        requestModel.type = 1; //0-普通 1-视频 2-地方
        requestModel.dt = 3; //设备类型 2-iphone 3-android
        //requestModel.version = "1.0.1";
        requestModel.secret = "{\"account\":\"inews\",\"key\":\"eca37b1f6066d2e28755178b016f0b57\"}";

        RequestBody body = RequestBody.create(MediaType.parse(NewsHttp.JSON_TYPE), new Gson().toJson(requestModel));

        Call<NewsListModel> call = http(NewsHttp.class).getNewsList(body);

        NewsListModel model = httpBody(call);

        ui().showRefreshing(false);

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
                ui().showEmptyLayout(false);
                ui().showHttpError(false);
            } else if(adapterList == null || adapterList.size() <= 0) {
                //ui().showBizError();
                ui().showEmptyLayout(true);
            } else {
                ui().showTip(0);
            }

            ui().setListData(adapterList);
        } else {

        }

    }


    @Background(BackgroundType.HTTP)
    public void loadMoreData(int action, int pageSize) {
        XLogUtil.getInstance().d(TAG,"loadMoreData");

        NewsListRequestModel requestModel = new NewsListRequestModel();

        requestModel.action = action; // 1-down 2-up 3-init
        requestModel.devicenum = "866790037218095";
        requestModel.channelID = channelID; //推荐频道
        requestModel.latitude = 0;
        requestModel.longitude = 0;
        requestModel.pageSize = pageSize;
        requestModel.type = 1; //0-普通 1-视频 2-地方
        requestModel.dt = 3; //设备类型 2-iphone 3-android
        requestModel.secret = "{\"account\":\"inews\",\"key\":\"eca37b1f6066d2e28755178b016f0b57\"}";

        RequestBody body = RequestBody.create(MediaType.parse(NewsHttp.JSON_TYPE), new Gson().toJson(requestModel));

        Call<NewsListModel> call = http(NewsHttp.class).getNewsList(body);

        NewsListModel model = httpBody(call);

        ui().swipeRefreshLayout().setRefreshing(false);

        if(model.getResultCode() == 200) {
            if(adapterList == null) {
                adapterList = new ArrayList<>(0);
            }

            if(model.getResult().getNews().size() > 0) {
                adapterList.addAll(model.getResult().getNews());
                ui().setListData(adapterList);
                ui().showEmptyLayout(false);
                ui().showHttpError(false);
            } else if(adapterList == null || adapterList.size() <= 0) {
                //ui().showBizError();
                ui().showEmptyLayout(true);
            } else {
                ui().setLoadMoreState(LoadMoreState.NOT_DATA);
            }

        } else {

        }
    }


}












