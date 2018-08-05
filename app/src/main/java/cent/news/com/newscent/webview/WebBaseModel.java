package cent.news.com.newscent.webview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WebBaseModel {

    @SerializedName("backUrl") @Expose
    public String	backurl;

    @SerializedName("callback") @Expose public String	callback;
}
