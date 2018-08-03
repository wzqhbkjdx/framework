package cent.news.com.newscent.splash;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;

import cent.news.com.baseframe.BaseHelper;
import cent.news.com.baseframe.core.BaseBiz;
import cent.news.com.newscent.R;
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

public class SplashBiz extends BaseBiz<SplashActivity> {

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void initBiz(Bundle bundle) {
        super.initBiz(bundle);
    }

    @Override
    public void initBundle() {
        super.initBundle();
    }

    /**
     * 该方法放到线程池里执行不起作用
     */
    public void initBackground() {
        ImageView bg = ui().getBackground();
        if(bg != null) {
            Glide.with(BaseHelper.getInstance()).load(R.drawable.splash_new).into(bg);
        }

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

            ui().jump2MainActivity();

        } else {
            //展示从服务器返回的toast
            //NCHelper.toast().show(model.msg);
        }

    }
}
