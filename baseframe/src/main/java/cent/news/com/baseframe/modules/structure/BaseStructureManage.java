package cent.news.com.baseframe.modules.structure;

import android.support.v4.app.FragmentManager;
import android.support.v4.util.SimpleArrayMap;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import cent.news.com.baseframe.BaseHelper;
import cent.news.com.baseframe.core.IBaseBiz;
import cent.news.com.baseframe.view.BaseActivity;

/**
 * Created by bym on 2018/6/20.
 */

public class BaseStructureManage implements IBaseStructureManage {

    private final ConcurrentHashMap<Class<?>, SimpleArrayMap<Integer, BaseStructureModel>> statckRepeatBiz;

    public BaseStructureManage() {
        statckRepeatBiz = new ConcurrentHashMap<>();
    }

    @Override
    public void attach(BaseStructureModel view) {
        synchronized (statckRepeatBiz) {
            if (view.getService() == null) {
                return;
            }
            SimpleArrayMap<Integer, BaseStructureModel> stack = statckRepeatBiz.get(view.getService());
            if (stack == null) {
                stack = new SimpleArrayMap();
            }
            stack.put(view.key, view);

            statckRepeatBiz.put(view.getService(), stack);

            if (BaseHelper.isLogOpen()) {
                //L.tag("SKYStructureManage");
                //L.i(view.getView().getClass().getSimpleName() + " -- stack:put(" + view.key + ")");
            }
        }
    }

    @Override
    public void detach(BaseStructureModel view) {
        synchronized (statckRepeatBiz) {
            if(view.getService() == null) {
                return;
            }



        }
    }

    @Override
    public <B extends IBaseBiz> B biz(Class<B> bizClass) {
        return null;
    }

    @Override
    public <B extends IBaseBiz> B biz(Class<B> bizClass, int position) {
        return null;
    }

    @Override
    public <B extends IBaseBiz> boolean isExist(Class<B> bizClazz) {
        return false;
    }

    @Override
    public <B extends IBaseBiz> boolean isExist(Class<B> biz, int position) {
        return false;
    }

    @Override
    public <B extends IBaseBiz> List<B> bizList(Class<B> service) {
        return null;
    }

    @Override
    public <T> T createMainLooper(Class<T> service, Object ui) {
        return null;
    }

    @Override
    public <T> T createMainLooperNotIntf(Class<T> service, Object ui) {
        return null;
    }

    @Override
    public <U> U createNullService(Class<U> service) {
        return null;
    }

    @Override
    public boolean onKeyBack(int keyCode, FragmentManager fragmentManager, BaseActivity baseActivity) {
        return false;
    }

    @Override
    public void printBackStackEntry(FragmentManager fragmentManager) {

    }
}
