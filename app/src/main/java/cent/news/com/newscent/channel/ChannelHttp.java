package cent.news.com.newscent.channel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ChannelHttp {

    public static final String CHANNEL_URL = "api/article/channel";

    @FormUrlEncoded
    @POST(CHANNEL_URL)
    @Headers("Content-Type:application/json; charset=UTF-8") //修改retrofit的header参数，因为okHttp的拦截器不起作用 application/json; charset=UTF-8"
    Call<ChannelModel>
    getChannels(@Field("secret") String secret);

    /**
     * 使用Json字符串的形式做post请求
     * @param secret
     * @return
     */
    //@FormUrlEncoded
    @POST(CHANNEL_URL)
    @Headers("Content-Type:application/json; charset=UTF-8") //修改retrofit的header参数，因为okHttp的拦截器不起作用 application/json; charset=UTF-8"
    Call<ChannelModel>
    getChannels(@Body RequestBody secret);

}
