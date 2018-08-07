package cent.news.com.newscent.video;

import android.os.Bundle;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import cent.news.com.baseframe.core.BaseBiz;
import cent.news.com.newscent.db.GreenDAOManager;
import cent.news.com.newscent.db.graeendao.ChannelDBBeanDao;
import cent.news.com.newscent.news.channel.ChannelDBBean;
import sky.Background;
import sky.BackgroundType;

public class VideoBiz extends BaseBiz<VideoFragment> {

    @Override
    public void initBundle() {
        super.initBundle();
    }

    @Override
    protected void initBiz(Bundle bundle) {
        super.initBiz(bundle);
    }

    //从数据库里查询title，如果第一次查不到，就再查一次，如果多次查不到，就展示空白页面
    @Background(BackgroundType.WORK)
    public void loadTitles() {
        //从数据库中查询出来
        QueryBuilder<ChannelDBBean> query = GreenDAOManager.instance().getChannelDBDao().queryBuilder();
        //type == 0 是新闻，type == 1 是视频
        List<ChannelDBBean> queryResult = query.where(ChannelDBBeanDao.Properties.Type.eq(1)).list();
        if(queryResult.size() > 0) {
            ArrayList<ChannelDBBean> list = new ArrayList<>(queryResult);
            ui().setTab(list);
            ui().hideProgress();
        } else {
            //getTitles();
        }

    }

}
