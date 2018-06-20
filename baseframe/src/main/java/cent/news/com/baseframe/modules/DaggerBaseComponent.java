package cent.news.com.baseframe.modules;

import android.app.Application;

import javax.inject.Provider;

import cent.news.com.baseframe.modules.cache.CacheManager;

/**
 * Created by bym on 2018/6/21.
 */

public final class DaggerBaseComponent implements IBaseComponent {

    private Provider<Application> provideApplicationProvider;
    private Provider<CacheManager> provideCacheManagerProvider;



    @Override
    public void inject(BaseModuleManage baseModuleManage) {

    }
}
