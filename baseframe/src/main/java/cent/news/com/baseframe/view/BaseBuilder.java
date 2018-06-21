package cent.news.com.baseframe.view;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by bym on 2018/6/19.
 */

public final class BaseBuilder {

    private BaseView baseView;

    private LayoutInflater mInflater;

    private Toolbar toolbar;

    public BaseBuilder(@NonNull BaseActivity baseActivity, @NonNull LayoutInflater inflater) {
        baseView = new BaseView();
        baseView.initUI(baseActivity);
        this.mInflater = inflater;
    }


    public BaseBuilder(@NonNull BaseFragment baseFragment, @NonNull LayoutInflater inflater) {
        baseView = new BaseView();
        baseView.initUI(baseFragment);
        this.mInflater = inflater;
    }

    public BaseBuilder(@NonNull BaseDialogFragment baseDialogFragment, @NonNull LayoutInflater inflater) {
        baseView = new BaseView();
        baseView.initUI(baseDialogFragment);
        this.mInflater = inflater;
    }


    @Nullable Toolbar getToolbar() {
        return toolbar;
    }

    @Nullable BaseView getBaseView() {
        return baseView;
    }

    private int layoutId;

    private int layoutStateId;

    private FrameLayout contentRoot;

    private View contentRootView;

    int getLayoutId() {
        return layoutId;
    }

    public void layoutId(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
    }

    public void layoutStateId(@IdRes int layoutStateId) {
        this.layoutId = layoutStateId;
    }

    /**
     * 显示状态切换
     */


}



















