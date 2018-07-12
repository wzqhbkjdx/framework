package cent.news.com.newscent.channel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// FIXME generate failure  field _$Result238
public class ChannelModel {

    /**
     *  code : 200
     *  result  : {"channels":[{"channelID":0,"title":"推荐"},{"channelID":1,"title":"热点"},{"channelID":2,"title":"娱乐"},{"channelID":3,"title":"健康"},{"channelID":4,"title":"生活"},{"channelID":5,"title":"情感"},{"channelID":6,"title":"时尚"},{"channelID":7,"title":"科技"},{"channelID":8,"title":"财经"},{"channelID":9,"title":"汽车"},{"channelID":10,"title":"旅行"},{"channelID":11,"title":"美食"},{"channelID":12,"title":"育儿"},{"channelID":13,"title":"历史"},{"channelID":14,"title":"奇闻"},{"channelID":15,"title":"搞笑"}]}
     */

    @SerializedName("statusCode") @Expose public String code;


    @SerializedName("result")  @Expose public Result result;

    public static class Result {
        @SerializedName("channels") @Expose public java.util.List<ChannelsBean> channels;

        public static class ChannelsBean {
            /**
             * channelID : 0
             * title : 推荐
             */
            @SerializedName("ID") @Expose public int channelID;
            @SerializedName("title") @Expose private String title;
            @SerializedName("attval") @Expose private int attval;
            @SerializedName("type") @Expose private int type;
            @SerializedName("alias") @Expose private String alias;


        }
    }
}
