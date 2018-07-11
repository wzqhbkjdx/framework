package cent.news.com.newscent.helper;

import android.annotation.SuppressLint;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import cent.news.com.baseframe.core.IBaseBind;
import cent.news.com.baseframe.modules.BaseModuleManage;
import cent.news.com.baseframe.modules.methodsProxy.BaseMethods;
import cent.news.com.newscent.NCConstants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class NCBind implements IBaseBind {


    @Override
    public boolean isLogOpen() {
        return false;
    }

    @Override
    public Retrofit getRestAdapter(Retrofit.Builder builder) {
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();

        // 超时
        okhttpBuilder.connectTimeout(60, TimeUnit.SECONDS);
        okhttpBuilder.readTimeout(60, TimeUnit.SECONDS);
        okhttpBuilder.writeTimeout(60, TimeUnit.SECONDS);

        @SuppressLint("HardwareIds")
        CommonParamsInterceptor commonParamsInterceptor = new CommonParamsInterceptor.Builder()// 创建
                .addHeaderParam("Accept-Encoding", "gzip, deflate")// 头信息
                .addQueryParam(NCConstants.KEY_BRAND, android.os.Build.BRAND)
                .setOnHeaderParams(new CommonParamsInterceptor.IParams() {
                    @Override
                    public void addParamsMap(Map<String, String> paramsMap) {

                    }
                }).build();



        return null;
    }

    @Override
    public BaseMethods getMethodInterceptor(BaseMethods.Builder builder) {


        return builder.build();
    }

    @Override
    public BaseModuleManage getModuleManage() {
        return new NCModuleManage();
    }

}
