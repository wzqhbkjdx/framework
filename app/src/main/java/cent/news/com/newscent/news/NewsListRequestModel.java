package cent.news.com.newscent.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsListRequestModel {

    @SerializedName("devicenum") @Expose
    public String devicenum;

    @SerializedName("channelID") @Expose
    public int channelID;

    @SerializedName("latitude") @Expose
    public double latitude;

    @SerializedName("longitude") @Expose
    public double longitude;

    @SerializedName("pageSize") @Expose
    public int pageSize;

    //@SerializedName("newsids") @Expose
    //public String newsids;

    @SerializedName("action") @Expose
    public int action;

    @SerializedName("type") @Expose
    public int type;

    @SerializedName("dt") @Expose
    public int dt;

    //@SerializedName("version") @Expose
    //public String version;

    @SerializedName("secret") @Expose
    public String secret;

    @SerializedName("newsID") @Expose
    public int newsID;

}
