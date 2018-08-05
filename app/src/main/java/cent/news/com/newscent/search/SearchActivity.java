package cent.news.com.newscent.search;

import android.os.Bundle;

import cent.news.com.baseframe.BaseHelper;
import cent.news.com.baseframe.display.BaseIDisplay;
import cent.news.com.baseframe.view.BaseActivity;
import cent.news.com.baseframe.view.BaseBuilder;
import cent.news.com.newscent.R;

public class SearchActivity extends BaseActivity<SearchBiz> {

    public static void intent() {
        BaseHelper.display(BaseIDisplay.class).intent(SearchActivity.class);
    }

    @Override
    protected BaseBuilder build(BaseBuilder builder) {
        builder.layoutId(R.layout.activity_search_layout);
        builder.toolbarIsOpen(true);
        builder.toolbarLayoutId(R.layout.include_title_search);
        return builder;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

}
