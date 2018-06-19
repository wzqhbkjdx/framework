package cent.news.com.baseframe.modules.cache;

import cent.news.com.baseframe.core.IBaseCommonBiz;
import cent.news.com.baseframe.display.BaseIDisplay;

/**
 * Created by bym on 2018/6/17.
 */

public class CacheManager implements ICacheManager {


    @Override
    public <D extends BaseIDisplay> D display(Class<D> displayClazz) {
        return null;
    }

    @Override
    public <B extends IBaseCommonBiz> B common(Class<B> service) {
        return null;
    }

    @Override
    public <I> I interfaces(Class<I> implClazz) {
        return null;
    }

    @Override
    public <H> H http(Class<H> httpClazz) {
        return null;
    }

    @Override
    public void printState() {

    }
}
