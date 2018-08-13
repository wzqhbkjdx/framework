package cent.news.com.newscent.news.channel;

import cent.news.com.newscent.news.NewsListModel;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface NewsHttp {

    public static final String CHANNEL_URL = "api/article/channel";

    public static final String SEARCH_URL = "api/article/skeywords";

    public static final String NEWS_LIST_URL = "api/article";

    public static final String JSON_CONTENT_TYPE = "Content-Type:application/json; charset=UTF-8";

    public static final String JSON_TYPE = "application/json; charset=utf-8";

    @FormUrlEncoded
    @POST(CHANNEL_URL)
    @Headers(JSON_CONTENT_TYPE) //修改retrofit的header参数，因为okHttp的拦截器不起作用 application/json; charset=UTF-8"
    Call<ChannelModel>
    getChannels(@Field("secret") String secret);

    /**
     * 使用Json字符串的形式做post请求
     * @param secret
     * @return
     */
    //@FormUrlEncoded
    @POST(CHANNEL_URL)
    @Headers(JSON_CONTENT_TYPE) //修改retrofit的header参数，因为okHttp的拦截器不起作用 application/json; charset=UTF-8"
    Call<ChannelModel>
    getChannels(@Body RequestBody secret);


    @POST(NEWS_LIST_URL)
    @Headers(JSON_CONTENT_TYPE) //修改retrofit的header参数，因为okHttp的拦截器不起作用 application/json; charset=UTF-8"
    Call<NewsListModel>
    getNewsList(@Body RequestBody secret);


    @POST(SEARCH_URL)
    @Headers(JSON_CONTENT_TYPE) //修改retrofit的header参数，因为okHttp的拦截器不起作用 application/json; charset=UTF-8"
    Call<NewsListModel>
    getSearchResult(@Body RequestBody body);



}
