package cent.news.com.newscent.channel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ChannelHttp {

    public static final String CHANNEL_URL = "api/article/channel";

    @FormUrlEncoded
    @POST(CHANNEL_URL)
    Call<ChannelModel>
    getChannels(@Field("secret") String secret);

}
