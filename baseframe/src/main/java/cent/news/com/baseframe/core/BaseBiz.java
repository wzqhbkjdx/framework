package cent.news.com.baseframe.core;

import android.os.Bundle;

import java.util.Vector;

import cent.news.com.baseframe.BaseHelper;
import cent.news.com.baseframe.base.IBaseView;
import cent.news.com.baseframe.display.BaseIDisplay;
import cent.news.com.baseframe.modules.structure.BaseStructureModel;
import cent.news.com.baseframe.utils.BaseAppUtil;
import retrofit2.Call;

/**
 * Created by bym on 2018/6/19.
 */

public abstract class BaseBiz<U> implements IBaseBiz, IBaseIntercept, IBaseView {

    private U u;

    private Class ui;

    private BaseStructureModel baseStructureModel;

    private Vector<Call> callVector;

    protected <H> H http(Class<H> clazz) {
        return BaseHelper.http(clazz);
    }

    protected <I> I interfaces(Class<I> inter) {
        return BaseHelper.interfaces(inter);
    }

    protected <D extends BaseIDisplay> D display(Class<D> eClass) {
        return BaseHelper.display(eClass);
    }

    public <C extends IBaseBiz> C biz(Class<C> service) {
        if (baseStructureModel != null && baseStructureModel.isSuperClass(service)) {
            if (baseStructureModel.getBaseProxy() == null || baseStructureModel.getBaseProxy().proxy == null) {
                return BaseHelper.structureHelper().createNullService(service);
            }
            return (C) baseStructureModel.getBaseProxy().proxy;
        } else if (baseStructureModel != null && service.equals(baseStructureModel.getService())) {
            if (baseStructureModel.getBaseProxy() == null || baseStructureModel.getBaseProxy().proxy == null) {
                return BaseHelper.structureHelper().createNullService(service);
            }
            return (C) baseStructureModel.getBaseProxy().proxy;
        } else {
            return BaseHelper.biz(service);
        }
    }

    protected void initBiz(Bundle bundle) {

    }

    public U ui() {
        if(u == null) {
            Class ui = BaseAppUtil.getSuperClassGenricType(this.getClass(), 0);
            return (U) BaseHelper.structureHelper().createNullService(ui);
        }
        return u;
    }

    public <V> V ui(Class<V> clazz) {
        if(clazz.equals(ui)) {
            return (V) ui();
        } else {
            return BaseHelper.structureHelper().createNullService(clazz);
        }
    }

    protected <D> D httpBody(Call<D> call) {
        callVector.add(call);
        return BaseHelper.httpBody(call);
    }

    public boolean isUI() {
        return u != null;
    }

    @Override
    public void initUI(BaseStructureModel baseView) {
        this.baseStructureModel = baseView;
        //BaseBiz就是继承其子类的父类，所以获取父类的泛型参数，就是获取BaseBiz<U>的U的类型，也就是BaseActivity或者
        //BaseFragment的子类
        ui = BaseAppUtil.getSuperClassGenricType(this.getClass(), 0);
        if(!ui.isInterface()) {
            //生成该UI的代理类
            u = (U) BaseHelper.structureHelper().createMainLooperNotIntf(ui, baseStructureModel.getView());
        } else {
            //生成该UI的代理类
            u = (U) BaseHelper.structureHelper().createMainLooper(ui, baseStructureModel.getView());
        }
        callVector = new Vector<>();
    }

    @Override
    public void initBundle() {
        initBiz(this.baseStructureModel.getBundle());
    }

    @Override
    public void detach() {
        u = null;
        ui = null;
        baseStructureModel = null;
        httpCancel();
    }

    protected void httpCancel() {
        int count = callVector.size();
        if(count < 1) {
            return;
        }

        for(int i = 0; i < count; i++) {
            Call call = callVector.get(i);
            BaseHelper.httpCancel(call);
        }

        callVector.removeAllElements();
    }

    @Override
    public void showEmpty() {
        final IBaseView baseView = (IBaseView) this.baseStructureModel.getView();

        if(baseView == null) {
            return;
        }

        if(!BaseHelper.isMainLooperThread()) {
            baseView.showEmpty();
            return;
        }

        BaseHelper.mainLooper().execute(new Runnable() {
            @Override
            public void run() {
                if(baseView == null) {
                    return;
                }
                baseView.showEmpty();
            }
        });

    }

    @Override
    public void showContent() {
        final IBaseView baseView = (IBaseView) this.baseStructureModel.getView();

        if(baseView == null) {
            return;
        }

        if(!BaseHelper.isMainLooperThread()) {
            baseView.showContent();
            return;
        }

        BaseHelper.mainLooper().execute(new Runnable() {
            @Override
            public void run() {
                if(baseView == null) {
                    return;
                }
                baseView.showContent();
            }
        });
    }

    @Override
    public void showHttpError() {
        final IBaseView baseView = (IBaseView) this.baseStructureModel.getView();

        if(baseView == null) {
            return;
        }

        if(!BaseHelper.isMainLooperThread()) {
            baseView.showHttpError();
            return;
        }

        BaseHelper.mainLooper().execute(new Runnable() {
            @Override
            public void run() {
                if(baseView == null) {
                    return;
                }
                baseView.showHttpError();
            }
        });
    }


}


















