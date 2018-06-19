package cent.news.com.baseframe.modules;

import android.app.Application;

import javax.inject.Inject;

import cent.news.com.baseframe.core.SynchronousExecutor;
import cent.news.com.baseframe.modules.cache.CacheManager;
import cent.news.com.baseframe.modules.methodsProxy.BaseMethods;
import cent.news.com.baseframe.modules.threadPool.BaseThreadPoolManager;
import cent.news.com.baseframe.screen.BaseScreenManager;

/**
 * Created by bym on 2018/6/17.
 */

public class BaseModuleManage {

    @Inject public Application application;

    @Inject public BaseScreenManager screenManager;

    @Inject public CacheManager cacheManager;

    @Inject public SynchronousExecutor synchronousExecutor;

    @Inject public BaseThreadPoolManager baseThreadPoolManager;

    public BaseMethods baseMethods;

    public boolean isLog;

    public boolean isLog() {
        return isLog;
    }

    public BaseScreenManager getScreenManager() {
        return screenManager;
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public Application getApplication() {
        return application;
    }

    public BaseMethods getBaseMethods() {
        return baseMethods;
    }

    public SynchronousExecutor getSynchronousExecutor() {
        return synchronousExecutor;
    }

    public BaseThreadPoolManager getBaseThreadPoolManager() {
        return baseThreadPoolManager;
    }
}













