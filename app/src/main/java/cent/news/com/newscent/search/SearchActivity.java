package cent.news.com.newscent.search;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import cent.news.com.baseframe.BaseHelper;
import cent.news.com.baseframe.display.BaseIDisplay;
import cent.news.com.baseframe.utils.BaseKeyboardUtils;
import cent.news.com.baseframe.view.BaseActivity;
import cent.news.com.baseframe.view.BaseBuilder;
import cent.news.com.baseframe.view.common.BaseFooterListener;
import cent.news.com.newscent.R;
import cent.news.com.newscent.helper.utils.XLogUtil;
import cent.news.com.newscent.news.NewsListModel;
import cent.news.com.newscent.view.SearchEditText;
import cent.news.com.newscent.view.TagGroup;

public class SearchActivity extends BaseActivity<SearchBiz> implements BaseFooterListener,
        View.OnKeyListener, TagGroup.OnTagClickListener, TextView.OnEditorActionListener {

    public static void intent() {
        BaseHelper.display(BaseIDisplay.class).intent(SearchActivity.class);
    }

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.cons_default)
    ConstraintLayout consDefault;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.edit_search)
    SearchEditText searchEditText;

    @BindView(R.id.ll_background)
    ConstraintLayout llBackground;


    @Override
    protected BaseBuilder build(BaseBuilder builder) {
        builder.layoutId(R.layout.activity_search_layout);
        builder.toolbarIsOpen(true);
        builder.toolbarLayoutId(R.layout.include_title_search);
        builder.layoutEmptyId(R.layout.layout_search_none);
        builder.layoutHttpErrorId(R.layout.http_error);
        builder.recyclerviewId(R.id.recycler_view);
        builder.recyclerviewAdapter(new SearchAdapter(this));
        builder.recyclerviewLinearLayoutManager(LinearLayoutManager.VERTICAL, null, null);
        return builder;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        searchEditText.setOnEditorActionListener(this);
    }

    public void showProgressBar(boolean show) {
        if(show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onScrolledToBottom() {
        return false;
    }

    public void setListData(List<NewsListModel.ResultBean.NewsBean> dataList) {
        showContent();
        adapter().setItems(dataList);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        XLogUtil.getInstance().d("bob_test", "on key down");

        if (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_SEARCH && event.getAction() == KeyEvent.ACTION_DOWN) {// 修改回车键功能
            BaseKeyboardUtils.hideSoftInput(this);
            searchEditText.clearFocus();
            llBackground.setFocusable(true);
            llBackground.setFocusableInTouchMode(true);
            //搜索
            biz().search(1, 10, searchEditText.getText().toString());
            showProgressBar(true);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (consDefault.getVisibility() == View.GONE) {
                finish();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        XLogUtil.getInstance().d("bob_test", "onEditorAction");

        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            BaseKeyboardUtils.hideSoftInput(this);
            searchEditText.clearFocus();
            llBackground.setFocusable(true);
            llBackground.setFocusableInTouchMode(true);
            //开始搜索
            biz().search(1, 10, searchEditText.getText().toString());
            showProgressBar(true);
            return true;
        }
        return false;
    }

    @Override
    public void onTagClick(TagGroup.TagView tag) {

    }
}











