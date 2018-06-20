package cent.news.com.newscent;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by bym on 2018/6/18.
 */

@SuppressWarnings("ALL")
public class MainApplication extends Application {

    @Override protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
