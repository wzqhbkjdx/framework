package cent.news.com.baseframe.core.plugin;

import java.lang.reflect.Method;

/**
 * Created by bym on 2018/6/19.
 */

public interface ImplEndInterceptor {

    <T> void interceptEnd(String viewName, Class<T> service, Method method, Object[] objects, Object backgroundResult);

}
