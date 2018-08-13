package cent.news.com.newscent.search;

import android.os.Bundle;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cent.news.com.baseframe.core.BaseBiz;
import cent.news.com.newscent.helper.utils.XLogUtil;
import cent.news.com.newscent.news.NewsListModel;
import cent.news.com.newscent.news.channel.NewsHttp;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import sky.Background;
import sky.BackgroundType;

public class SearchBiz extends BaseBiz<SearchActivity> {

    private List<NewsListModel.ResultBean.NewsBean> adapterList;

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void initBiz(Bundle bundle) {
        super.initBiz(bundle);
        adapterList = new ArrayList<>(0);
    }

    @Override
    public void initBundle() {
        super.initBundle();
    }

    @Background(BackgroundType.HTTP)
    public void search(int page, int pageSize, String keyWords) {
        XLogUtil.getInstance().d(TAG,"search");

        SearchListRequestModel requestModel = new SearchListRequestModel();
        requestModel.keyWords = keyWords;
        requestModel.page = page;
        requestModel.pageSize = pageSize;

        RequestBody body = RequestBody.create(MediaType.parse(NewsHttp.JSON_TYPE), new Gson().toJson(requestModel));
        Call<NewsListModel> call = http(NewsHttp.class).getSearchResult(body);
        NewsListModel responseModel = httpBody(call);

        if(responseModel.getResultCode() == 200) {
            XLogUtil.getInstance().d(TAG,"response list size: " + responseModel.getResult().getNews().size());

            if(adapterList == null) {
                adapterList = new ArrayList<>(0);
            }

            if(responseModel.getResult().getNews().size() > 0) {
                adapterList.addAll(responseModel.getResult().getNews());
                ui().setListData(adapterList);
            }


        }

    }

}
