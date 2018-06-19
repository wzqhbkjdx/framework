package cent.news.com.baseframe;

import android.annotation.SuppressLint;

import cent.news.com.baseframe.modules.BaseModuleManage;
import cent.news.com.baseframe.screen.BaseScreenManager;

/**
 * Created by bym on 2018/6/18.
 */

public class BaseHelper {

    @SuppressLint("StaticFieldLeak")
    private static BaseModuleManage mModulesManage = null;

    public static class Bind {

    }


    public static boolean isLogOpen() {
        return mModulesManage.isLog();
    }

    public static BaseScreenManager screenHelper() {
        return mModulesManage.screenManager;
    }




}




















