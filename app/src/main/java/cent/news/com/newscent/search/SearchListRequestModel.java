package cent.news.com.newscent.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchListRequestModel {

    @SerializedName("keywords") @Expose
    public String keyWords;

    @SerializedName("secret") @Expose
    public String secret;

    @SerializedName("page") @Expose
    public int page;

    @SerializedName("pagesize") @Expose
    public int pageSize;


}
