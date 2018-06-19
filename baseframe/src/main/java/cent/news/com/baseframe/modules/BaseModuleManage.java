package cent.news.com.baseframe.modules;

import android.app.Application;

import javax.inject.Inject;

import cent.news.com.baseframe.screen.BaseScreenManager;

/**
 * Created by bym on 2018/6/17.
 */

public class BaseModuleManage {

    @Inject public Application application;

    @Inject public BaseScreenManager screenManager;

    public boolean isLog;

    public boolean isLog() {
        return isLog;
    }

    public BaseScreenManager getScreenManager() {
        return screenManager;
    }
}
