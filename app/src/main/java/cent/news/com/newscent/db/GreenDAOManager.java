package cent.news.com.newscent.db;

import android.database.sqlite.SQLiteDatabase;

import cent.news.com.baseframe.BaseHelper;
import cent.news.com.newscent.db.graeendao.ChannelDBBeanDao;
import cent.news.com.newscent.db.graeendao.DaoMaster;
import cent.news.com.newscent.db.graeendao.DaoSession;

public final class GreenDAOManager {

    private static final String DB_NAME = "data_db";

    private DaoSession daoSession;

    private DaoMaster.DevOpenHelper helper;

    private GreenDAOManager() {

    }

    public void init() {
        if(BaseHelper.getInstance() != null) {
            helper = new DaoMaster.DevOpenHelper(BaseHelper.getInstance(), DB_NAME, null);
            SQLiteDatabase db = helper.getWritableDatabase();
            DaoMaster master = new DaoMaster(db);
            daoSession = master.newSession();
        }
    }

    public static GreenDAOManager instance() {
        return innerClass.instance;
    }

    private static class innerClass {
        static final GreenDAOManager instance = new GreenDAOManager();
    }

    public ChannelDBBeanDao getChannelDBDao() {
        return daoSession.getChannelDBBeanDao();
    }


    public DaoSession getDaoSession() {
        return daoSession;
    }
}
