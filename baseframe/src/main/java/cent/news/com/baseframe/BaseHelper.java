package cent.news.com.baseframe;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Looper;

import cent.news.com.baseframe.core.IBaseBind;
import cent.news.com.baseframe.core.IBaseViewCommon;
import cent.news.com.baseframe.core.SynchronousExecutor;
import cent.news.com.baseframe.display.BaseIDisplay;
import cent.news.com.baseframe.modules.BaseModule;
import cent.news.com.baseframe.modules.BaseModuleManage;
import cent.news.com.baseframe.modules.DaggerBaseComponent;
import cent.news.com.baseframe.modules.methodsProxy.BaseMethods;
import cent.news.com.baseframe.modules.structure.BaseStructureManage;
import cent.news.com.baseframe.modules.threadPool.BaseThreadPoolManager;
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
        IBaseBind iBaseBind;

        public Bind setBaseBind(IBaseBind iBaseBind) {
            this.iBaseBind = iBaseBind;
            return this;
        }

        IBaseViewCommon baseViewCommon;

        public Bind setBaseViewCommon(IBaseViewCommon baseViewCommon) {
            this.baseViewCommon = baseViewCommon;
            return this;
        }

        public void inject(Application application) {
            if(application == null) {
                throw new RuntimeException("base framework: Application is not set");
            }

            if(this.iBaseBind == null) {
                this.iBaseBind = IBaseBind.DEFAULT_BIND;
            }

            if(this.baseViewCommon == null) {
                this.baseViewCommon = IBaseViewCommon.BASE_VIEW_COMMON;
            }

            mModulesManage = iBaseBind.getModuleManage();
            if(mModulesManage == null) {
                throw new RuntimeException("base framework: BaseModuleManage is not set");
            }

            DaggerBaseComponent.builder().baseModule(new BaseModule(application)).build().inject(mModulesManage);
            mModulesManage.init(iBaseBind, baseViewCommon);
        }

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

    public static BaseMethods methodsProxy() {
        return mModulesManage.getBaseMethods();
    }

    public static boolean isMainLooperThread() {
        return Looper.getMainLooper().getThread() != Thread.currentThread();
    }

    public static SynchronousExecutor mainLooper() {
        return mModulesManage.getSynchronousExecutor();
    }

    public static BaseThreadPoolManager threadPoolHelper() {
        return mModulesManage.getBaseThreadPoolManager();
    }

    public static BaseStructureManage structureHelper() {
        return mModulesManage.getBaseStructureManage();
    }

    public static IBaseViewCommon getCommonView() {
        return mModulesManage.getBaseViewCommon();
    }
}




















