package cent.news.com.newscent.news;

import android.os.Bundle;

import com.google.gson.JsonObject;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import cent.news.com.baseframe.core.BaseBiz;
import cent.news.com.newscent.db.GreenDAOManager;
import cent.news.com.newscent.db.graeendao.ChannelDBBeanDao;
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


    //在线程池里执行网络请求
    @Background(BackgroundType.HTTP)
    private void getTitles() {
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

            loadTitles();

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
        //type == 0 是新闻，type == 1 是视频
        List<ChannelDBBean> queryResult = query.where(ChannelDBBeanDao.Properties.Type.eq(0)).list();
        if(queryResult.size() > 0) {
            ArrayList<ChannelDBBean> list = new ArrayList<>(queryResult);
            ui().setTab(list);
            ui().hideProgress();
        } else {
            getTitles();
        }

    }



}













