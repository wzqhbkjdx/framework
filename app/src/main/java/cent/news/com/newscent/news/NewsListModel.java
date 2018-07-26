package cent.news.com.newscent.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsListModel {

    /**
     * resultCode : 200
     * result : {"news":[{"id":1,"title":"长生生物 6 个跌停，如何警惕医药风口背后的风险？","abstract":"","logoImageUrl":"https://cdn.520igo.cn/news/nd/9/12.png","images":["https://cdn.520igo.cn/news/nd/9/12.png"],"scanCount":"0","publishTime":"2018-07-23 18:59:58","type":5,"playUrl":"","linkUrl":"http://cdn.520igo.cn/monoploy/articles/2018-07-23/1a2c7432472790ee5d56b415ae64bbf2.html","original_link":"http://www.myzaker.com/article/5b55aa7177ac6429eb3cd758/","videolength":0,"width":"0","height":"0","gallerycount":0,"channelid":9,"source":"毒鸡汤八妹","str":"1a2c7432472790ee5d56b415ae64bbf2","goodnum":0,"commentCount":0,"keywords":"","shareUrl":"http://news.520igo.cn/article/share?detailid=1","nearbyViewCount":0,"friendsViewCount":0,"friendsCommentCount":0,"sourceImageUrl":"","commentstate":0},{"id":2,"title":"分析师展望：欧元基本构成清晰楔形 短线料向下突破","abstract":"","logoImageUrl":"https://cdn.520igo.cn/news/nd/9/3.png","images":["https://cdn.520igo.cn/news/nd/9/3.png"],"scanCount":"0","publishTime":"2018-07-23 19:00:11","type":5,"playUrl":"","linkUrl":"http://cdn.520igo.cn/monoploy/articles/2018-07-23/84fd6dfc14ef35360bf7cd7227d34285.html","original_link":"http://www.myzaker.com/article/5b55b2f477ac6447a75f1c08/","videolength":0,"width":"0","height":"0","gallerycount":0,"channelid":9,"source":"毒鸡汤八妹","str":"84fd6dfc14ef35360bf7cd7227d34285","goodnum":0,"commentCount":0,"keywords":"","shareUrl":"http://news.520igo.cn/article/share?detailid=2","nearbyViewCount":0,"friendsViewCount":0,"friendsCommentCount":0,"sourceImageUrl":"","commentstate":0},{"id":3,"title":"央行和财政的冲突 ( 2.0 )","abstract":"","logoImageUrl":"https://cdn.520igo.cn/news/nd/9/20.png","images":["https://cdn.520igo.cn/news/nd/9/20.png"],"scanCount":"0","publishTime":"2018-07-23 18:59:56","type":5,"playUrl":"","linkUrl":"http://cdn.520igo.cn/monoploy/articles/2018-07-23/6c08005336ca9b5157ac9d281dada323.html","original_link":"http://www.myzaker.com/article/5b55a82d77ac6427e8449ea2/","videolength":0,"width":"0","height":"0","gallerycount":0,"channelid":9,"source":"毒鸡汤八妹","str":"6c08005336ca9b5157ac9d281dada323","goodnum":0,"commentCount":0,"keywords":"","shareUrl":"http://news.520igo.cn/article/share?detailid=3","nearbyViewCount":0,"friendsViewCount":0,"friendsCommentCount":0,"sourceImageUrl":"","commentstate":0}],"top_news":[],"total_ids":[],"weatherForecast":null}
     * maxID : 0
     * minID : 0
     */

    @SerializedName("resultCode") @Expose
    private int resultCode;

    @SerializedName("result") @Expose
    private ResultBean result;

    @SerializedName("maxID") @Expose
    private int maxID;

    @SerializedName("minID") @Expose
    private int minID;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getMaxID() {
        return maxID;
    }

    public void setMaxID(int maxID) {
        this.maxID = maxID;
    }

    public int getMinID() {
        return minID;
    }

    public void setMinID(int minID) {
        this.minID = minID;
    }

    public static class ResultBean {
        /**
         * news : [{"id":1,"title":"长生生物 6 个跌停，如何警惕医药风口背后的风险？","abstract":"","logoImageUrl":"https://cdn.520igo.cn/news/nd/9/12.png","images":["https://cdn.520igo.cn/news/nd/9/12.png"],"scanCount":"0","publishTime":"2018-07-23 18:59:58","type":5,"playUrl":"","linkUrl":"http://cdn.520igo.cn/monoploy/articles/2018-07-23/1a2c7432472790ee5d56b415ae64bbf2.html","original_link":"http://www.myzaker.com/article/5b55aa7177ac6429eb3cd758/","videolength":0,"width":"0","height":"0","gallerycount":0,"channelid":9,"source":"毒鸡汤八妹","str":"1a2c7432472790ee5d56b415ae64bbf2","goodnum":0,"commentCount":0,"keywords":"","shareUrl":"http://news.520igo.cn/article/share?detailid=1","nearbyViewCount":0,"friendsViewCount":0,"friendsCommentCount":0,"sourceImageUrl":"","commentstate":0},{"id":2,"title":"分析师展望：欧元基本构成清晰楔形 短线料向下突破","abstract":"","logoImageUrl":"https://cdn.520igo.cn/news/nd/9/3.png","images":["https://cdn.520igo.cn/news/nd/9/3.png"],"scanCount":"0","publishTime":"2018-07-23 19:00:11","type":5,"playUrl":"","linkUrl":"http://cdn.520igo.cn/monoploy/articles/2018-07-23/84fd6dfc14ef35360bf7cd7227d34285.html","original_link":"http://www.myzaker.com/article/5b55b2f477ac6447a75f1c08/","videolength":0,"width":"0","height":"0","gallerycount":0,"channelid":9,"source":"毒鸡汤八妹","str":"84fd6dfc14ef35360bf7cd7227d34285","goodnum":0,"commentCount":0,"keywords":"","shareUrl":"http://news.520igo.cn/article/share?detailid=2","nearbyViewCount":0,"friendsViewCount":0,"friendsCommentCount":0,"sourceImageUrl":"","commentstate":0},{"id":3,"title":"央行和财政的冲突 ( 2.0 )","abstract":"","logoImageUrl":"https://cdn.520igo.cn/news/nd/9/20.png","images":["https://cdn.520igo.cn/news/nd/9/20.png"],"scanCount":"0","publishTime":"2018-07-23 18:59:56","type":5,"playUrl":"","linkUrl":"http://cdn.520igo.cn/monoploy/articles/2018-07-23/6c08005336ca9b5157ac9d281dada323.html","original_link":"http://www.myzaker.com/article/5b55a82d77ac6427e8449ea2/","videolength":0,"width":"0","height":"0","gallerycount":0,"channelid":9,"source":"毒鸡汤八妹","str":"6c08005336ca9b5157ac9d281dada323","goodnum":0,"commentCount":0,"keywords":"","shareUrl":"http://news.520igo.cn/article/share?detailid=3","nearbyViewCount":0,"friendsViewCount":0,"friendsCommentCount":0,"sourceImageUrl":"","commentstate":0}]
         * top_news : []
         * total_ids : []
         * weatherForecast : null
         */

        //private Object weatherForecast;
        //private List<?> top_news;
        //private List<?> total_ids;

        @SerializedName("news") @Expose
        private List<NewsBean> news;

        public List<NewsBean> getNews() {
            return news;
        }

        public void setNews(List<NewsBean> news) {
            this.news = news;
        }


        public static class NewsBean {
            /**
             * id : 1
             * title : 长生生物 6 个跌停，如何警惕医药风口背后的风险？
             * abstract :
             * logoImageUrl : https://cdn.520igo.cn/news/nd/9/12.png
             * images : ["https://cdn.520igo.cn/news/nd/9/12.png"]
             * scanCount : 0
             * publishTime : 2018-07-23 18:59:58
             * type : 5
             * playUrl :
             * linkUrl : http://cdn.520igo.cn/monoploy/articles/2018-07-23/1a2c7432472790ee5d56b415ae64bbf2.html
             * original_link : http://www.myzaker.com/article/5b55aa7177ac6429eb3cd758/
             * videolength : 0
             * width : 0
             * height : 0
             * gallerycount : 0
             * channelid : 9
             * source : 毒鸡汤八妹
             * str : 1a2c7432472790ee5d56b415ae64bbf2
             * goodnum : 0
             * commentCount : 0
             * keywords :
             * shareUrl : http://news.520igo.cn/article/share?detailid=1
             * nearbyViewCount : 0
             * friendsViewCount : 0
             * friendsCommentCount : 0
             * sourceImageUrl :
             * commentstate : 0
             */

            @SerializedName("id") @Expose
            private int id;

            @SerializedName("title") @Expose
            private String title;

            @SerializedName("abstract") @Expose
            private String abstractX;

            @SerializedName("logoImageUrl") @Expose
            private String logoImageUrl;

            @SerializedName("scanCount") @Expose
            private String scanCount;

            @SerializedName("publishTime") @Expose
            private String publishTime;

            @SerializedName("type") @Expose
            private int type;

            @SerializedName("playUrl") @Expose
            private String playUrl;

            @SerializedName("linkUrl") @Expose
            private String linkUrl;

            @SerializedName("original_link") @Expose
            private String original_link;

            @SerializedName("videolength") @Expose
            private int videolength;

            @SerializedName("width") @Expose
            private String width;

            @SerializedName("height") @Expose
            private String height;

            @SerializedName("gallerycount") @Expose
            private int gallerycount;

            @SerializedName("channelid") @Expose
            private int channelid;

            @SerializedName("source") @Expose
            private String source;

            @SerializedName("str") @Expose
            private String str;

            @SerializedName("goodnum") @Expose
            private int goodnum;

            @SerializedName("commentCount") @Expose
            private int commentCount;

            @SerializedName("keywords") @Expose
            private String keywords;

            @SerializedName("shareUrl") @Expose
            private String shareUrl;

            @SerializedName("nearbyViewCount") @Expose
            private int nearbyViewCount;

            @SerializedName("friendsViewCount") @Expose
            private int friendsViewCount;

            @SerializedName("friendsCommentCount") @Expose
            private int friendsCommentCount;

            @SerializedName("sourceImageUrl") @Expose
            private String sourceImageUrl;

            @SerializedName("commentstate") @Expose
            private int commentstate;

            @SerializedName("images") @Expose
            private List<String> images;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAbstractX() {
                return abstractX;
            }

            public void setAbstractX(String abstractX) {
                this.abstractX = abstractX;
            }

            public String getLogoImageUrl() {
                return logoImageUrl;
            }

            public void setLogoImageUrl(String logoImageUrl) {
                this.logoImageUrl = logoImageUrl;
            }

            public String getScanCount() {
                return scanCount;
            }

            public void setScanCount(String scanCount) {
                this.scanCount = scanCount;
            }

            public String getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getPlayUrl() {
                return playUrl;
            }

            public void setPlayUrl(String playUrl) {
                this.playUrl = playUrl;
            }

            public String getLinkUrl() {
                return linkUrl;
            }

            public void setLinkUrl(String linkUrl) {
                this.linkUrl = linkUrl;
            }

            public String getOriginal_link() {
                return original_link;
            }

            public void setOriginal_link(String original_link) {
                this.original_link = original_link;
            }

            public int getVideolength() {
                return videolength;
            }

            public void setVideolength(int videolength) {
                this.videolength = videolength;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public int getGallerycount() {
                return gallerycount;
            }

            public void setGallerycount(int gallerycount) {
                this.gallerycount = gallerycount;
            }

            public int getChannelid() {
                return channelid;
            }

            public void setChannelid(int channelid) {
                this.channelid = channelid;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getStr() {
                return str;
            }

            public void setStr(String str) {
                this.str = str;
            }

            public int getGoodnum() {
                return goodnum;
            }

            public void setGoodnum(int goodnum) {
                this.goodnum = goodnum;
            }

            public int getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(int commentCount) {
                this.commentCount = commentCount;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }

            public int getNearbyViewCount() {
                return nearbyViewCount;
            }

            public void setNearbyViewCount(int nearbyViewCount) {
                this.nearbyViewCount = nearbyViewCount;
            }

            public int getFriendsViewCount() {
                return friendsViewCount;
            }

            public void setFriendsViewCount(int friendsViewCount) {
                this.friendsViewCount = friendsViewCount;
            }

            public int getFriendsCommentCount() {
                return friendsCommentCount;
            }

            public void setFriendsCommentCount(int friendsCommentCount) {
                this.friendsCommentCount = friendsCommentCount;
            }

            public String getSourceImageUrl() {
                return sourceImageUrl;
            }

            public void setSourceImageUrl(String sourceImageUrl) {
                this.sourceImageUrl = sourceImageUrl;
            }

            public int getCommentstate() {
                return commentstate;
            }

            public void setCommentstate(int commentstate) {
                this.commentstate = commentstate;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }
    }
}
