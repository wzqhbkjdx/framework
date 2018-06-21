package cent.news.com.newscent.helper;

import cent.news.com.baseframe.core.IBaseBind;
import cent.news.com.baseframe.modules.BaseModuleManage;
import cent.news.com.baseframe.modules.methodsProxy.BaseMethods;
import retrofit2.Retrofit;

public class NCBind implements IBaseBind {


    @Override
    public boolean isLogOpen() {
        return false;
    }

    @Override
    public Retrofit getRestAdapter(Retrofit.Builder builder) {
        return null;
    }

    @Override
    public BaseMethods getMethodInterceptor(BaseMethods.Builder builder) {
        return null;
    }

    @Override
    public BaseModuleManage getModuleManage() {
        return null;
    }

}
