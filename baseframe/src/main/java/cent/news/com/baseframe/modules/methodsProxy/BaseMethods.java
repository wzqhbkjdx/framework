package cent.news.com.baseframe.modules.methodsProxy;

import java.util.ArrayList;

import cent.news.com.baseframe.core.plugin.BaseActivityInterceptor;
import cent.news.com.baseframe.core.plugin.BaseBizErrorInterceptor;
import cent.news.com.baseframe.core.plugin.BaseFragmentInterceptor;
import cent.news.com.baseframe.core.plugin.BaseHttpErrorInterceptor;
import cent.news.com.baseframe.core.plugin.BaseLayoutInterceptor;
import cent.news.com.baseframe.core.plugin.BizEndInterceptor;
import cent.news.com.baseframe.core.plugin.BizStartInterceptor;
import cent.news.com.baseframe.core.plugin.DisplayEndInterceptor;
import cent.news.com.baseframe.core.plugin.DisplayStartInterceptor;
import cent.news.com.baseframe.core.plugin.ImplEndInterceptor;
import cent.news.com.baseframe.core.plugin.ImplStartInterceptor;

/**
 * Created by bym on 2018/6/18.
 */

public final class BaseMethods {

    final BaseActivityInterceptor skyActivityInterceptor;

    final BaseLayoutInterceptor skyLayoutInterceptor;

    final BaseFragmentInterceptor skyFragmentInterceptor;

    final ArrayList<BizStartInterceptor> bizStartInterceptor;		// 方法开始拦截器

    final DisplayStartInterceptor displayStartInterceptor;	// 方法开始拦截器

    final ArrayList<BizEndInterceptor>			bizEndInterceptor;			// 方法结束拦截器

    final DisplayEndInterceptor displayEndInterceptor;		// 方法结束拦截器

    private ArrayList<ImplStartInterceptor>		implStartInterceptors;		// 方法开始拦截器

    private ArrayList<ImplEndInterceptor>		implEndInterceptors;		// 方法结束拦截器

    final ArrayList<BaseBizErrorInterceptor>		skyErrorInterceptor;		// 方法错误拦截器

    final ArrayList<BaseHttpErrorInterceptor>	skyHttpErrorInterceptors;	// 网络错误拦截器

    public BaseMethods(BaseLayoutInterceptor skyLayoutInterceptor, BaseActivityInterceptor SKYActivityInterceptor, BaseFragmentInterceptor SKYFragmentInterceptor,
                      ArrayList<BizStartInterceptor> bizStartInterceptor, DisplayStartInterceptor displayStartInterceptor, ArrayList<BizEndInterceptor> bizEndInterceptor,
                      DisplayEndInterceptor displayEndInterceptor, ArrayList<ImplStartInterceptor> implStartInterceptors, ArrayList<ImplEndInterceptor> implEndInterceptors,
                      ArrayList<BaseBizErrorInterceptor> SKYErrorInterceptor, ArrayList<BaseHttpErrorInterceptor> skyHttpErrorInterceptors) {
        this.skyLayoutInterceptor = skyLayoutInterceptor;
        this.bizEndInterceptor = bizEndInterceptor;
        this.displayEndInterceptor = displayEndInterceptor;
        this.displayStartInterceptor = displayStartInterceptor;
        this.bizStartInterceptor = bizStartInterceptor;
        this.skyErrorInterceptor = SKYErrorInterceptor;
        this.implStartInterceptors = implStartInterceptors;
        this.implEndInterceptors = implEndInterceptors;
        this.skyActivityInterceptor = SKYActivityInterceptor;
        this.skyFragmentInterceptor = SKYFragmentInterceptor;
        this.skyHttpErrorInterceptors = skyHttpErrorInterceptors;
    }

//    public <T> BaseProxy createNotInf(final Class superClazz, Object impl) {
//        final BaseProxy BaseProxy = new BaseProxy();
//        BaseProxy.impl = impl;
//        Enhancer e = new Enhancer(BaseHelper.getInstance());
//        e.setSuperclass(superClazz);
//        e.setInterceptor(new MethodInterceptor() {
//
//            @Override public Object intercept(String name, Class[] argsType, Object[] args) throws Exception {
//                Method method = superClazz.getMethod(name, argsType);
//
//                // 如果有返回值 - 直接执行
//                if (!method.getReturnType().equals(void.class)) {
//                    return method.invoke(BaseProxy.impl, args);
//                }

//                SKYMethod SKYMethod = loadSKYMethod(BaseProxy, method, superClazz);
//                // 开始
//                if (!SKYHelper.isLogOpen()) {
//                    return SKYMethod.invoke(BaseProxy.impl, args);
//                }
//                enterMethod(method, args);
//                long startNanos = System.nanoTime();
//
//                Object result = SKYMethod.invoke(BaseProxy.impl, args);
//
//                long stopNanos = System.nanoTime();
//                long lengthMillis = TimeUnit.NANOSECONDS.toMillis(stopNanos - startNanos);
//                exitMethod(method, result, lengthMillis);

//                return result;
//            }
//        });
//        BaseProxy.proxy = e.create();
//        return BaseProxy;
//    }
//
//    public <T> BaseProxy create(final Class<T> service, Object impl) {
//        BaseCheckUtils.validateServiceInterface(service);
//
//        final BaseProxy BaseProxy = new BaseProxy();
//        BaseProxy.impl = impl;
//        BaseProxy.proxy = Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[] { service }, new BaseInvocationHandler() {
//
//            @Override public Object invoke(Object proxy, Method method, Object... args) throws Throwable {
//                // 如果有返回值 - 直接执行
//                if (!method.getReturnType().equals(void.class)) {
//                    return method.invoke(BaseProxy.impl, args);
//                }

//                SKYMethod SKYMethod = loadSKYMethod(BaseProxy, method, service);
//                // 开始
//                if (!SKYHelper.isLogOpen()) {
//                    return SKYMethod.invoke(BaseProxy.impl, args);
//                }
//                enterMethod(method, args);
//                long startNanos = System.nanoTime();
//
//                Object result = SKYMethod.invoke(BaseProxy.impl, args);
//
//                long stopNanos = System.nanoTime();
//                long lengthMillis = TimeUnit.NANOSECONDS.toMillis(stopNanos - startNanos);
//                exitMethod(method, result, lengthMillis);

//                return result;
//            }
//        });
//
//        return BaseProxy;
//    }

}








