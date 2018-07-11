package cent.news.com.newscent.helper.interceptor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LogoutInterceptor implements Interceptor {


    final Gson gson;

    public LogoutInterceptor() {
        gson = new GsonBuilder().create();
    }

    @Override public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        // 网络请求
        Response response = chain.proceed(request);
        if (response.isSuccessful()) {
            MediaType mediaType = response.body().contentType();
            String content = response.body().string();
//            BaseModel baseModel = gson.fromJson(content, BaseModel.class);
//            if (baseModel.status.code == 10000) { //todo 这里需要后续代码
//                //退出登录
//                HNAHelper.user().logout();
//                //todo 弹框提示
//            }
            return response.newBuilder().body(ResponseBody.create(mediaType, content)).build();
        }
        return response;
    }
}
