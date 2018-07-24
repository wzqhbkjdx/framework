package cent.news.com.newscent.news;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import cent.news.com.baseframe.core.BaseBiz;
import cent.news.com.newscent.db.GreenDAOManager;
import cent.news.com.newscent.helper.utils.XLogUtil;
import cent.news.com.newscent.news.channel.ChannelDBBean;
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

        XLogUtil.getInstance().d(TAG,"getTitles");

        //因为该接口不需要传递请求参数，所以传了一个空JSON字符串
        RequestBody body = RequestBody.create(MediaType.parse(NewsHttp.JSON_TYPE), new JsonObject().toString());

        Call<ChannelModel> call = http(NewsHttp.class).getChannels(body);

        ChannelModel model = httpBody(call);

        if(model.code == 200) {
            //保存到数据库中
            if(model.result.channels != null && model.result.channels.size() > 0) {
                for(ChannelModel.Result.ChannelsBean bean : model.result.channels) {
                    ChannelDBBean dbBean = new ChannelDBBean();
                    dbBean.setChannelID(bean.channelID);
                    dbBean.setAlias(bean.alias);
                    dbBean.setAttval(bean.attval);
                    dbBean.setTitle(bean.title);
                    dbBean.setType(bean.type);
                    GreenDAOManager.instance().getDaoSession().insertOrReplace(dbBean);
                }
            }

            XLogUtil.getInstance().d(TAG,"green dao insert channel " + model.result.channels.size());

        } else {
            //展示从服务器返回的toast
            // TODO: 2018/7/24
        }

    }

    //从数据库里查询title，如果第一次查不到，就再查一次，如果多次查不到，就展示空白页面
    @Background(BackgroundType.WORK)
    public void loadTitles() {

        //从数据库中查询出来
        QueryBuilder<ChannelDBBean> query = GreenDAOManager.instance().getChannelDBDao().queryBuilder();
        List<ChannelDBBean> queryResult = query.list();

        ArrayList<ChannelDBBean> list = new ArrayList<>(queryResult);
        ui().setTab(list);
    }



}













