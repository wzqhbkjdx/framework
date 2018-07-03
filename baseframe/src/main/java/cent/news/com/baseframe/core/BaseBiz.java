package cent.news.com.baseframe.core;

import java.util.Vector;

import cent.news.com.baseframe.BaseHelper;
import cent.news.com.baseframe.base.IBaseView;
import cent.news.com.baseframe.display.BaseIDisplay;
import cent.news.com.baseframe.modules.structure.BaseStructureModel;
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




}
