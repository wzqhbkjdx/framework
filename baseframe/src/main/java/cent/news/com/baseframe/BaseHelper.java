package cent.news.com.baseframe;

import android.annotation.SuppressLint;
import android.app.Application;

import cent.news.com.baseframe.display.BaseIDisplay;
import cent.news.com.baseframe.modules.BaseModuleManage;
import cent.news.com.baseframe.screen.BaseScreenManager;

/**
 * Created by bym on 2018/6/18.
 */

public class BaseHelper {

    @SuppressLint("StaticFieldLeak")
    private static BaseModuleManage mModulesManage = null;

    public static Bind newBind() {
        return new Bind();
    }

    public static class Bind {

    }


    public static boolean isLogOpen() {
        return mModulesManage.isLog();
    }

    public static BaseScreenManager screenHelper() {
        return mModulesManage.screenManager;
    }

    public static <D extends BaseIDisplay> D display(Class<D> eClass) {
        return mModulesManage.getCacheManager().display(eClass);
    }

    public static Application getInstance() {
        return mModulesManage.getApplication();
    }

}




















