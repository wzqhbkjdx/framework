package cent.news.com.baseframe.view;

import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import cent.news.com.baseframe.BaseHelper;
import cent.news.com.baseframe.base.IBaseView;
import cent.news.com.baseframe.core.IBaseBiz;
import cent.news.com.baseframe.display.BaseIDisplay;
import cent.news.com.baseframe.view.adapter.recyclerView.BaseRVAdapter;

/**
 * Created by bym on 2018/6/19.
 */

public abstract class BaseFragment<B extends IBaseBiz> extends Fragment implements View.OnTouchListener, IBaseView {


    public B biz() {
        return null;
    }

    public <C extends IBaseBiz> C biz(Class<C> service) {
        return null;
    }

    public <D extends BaseIDisplay> D display(Class<D> eClass) {
        return BaseHelper.display(eClass);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void showContent() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showBizError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showHttpError() {

    }

    @Override
    public int showState() {
        return 0;
    }

    @Override
    public <T extends BaseRVAdapter> T adapter() {
        return null;
    }

    private BaseBuilder baseBuilder;

    public Toolbar toolbar() {
        return baseBuilder == null ? null : baseBuilder.getToolbar();
    }

    public BaseView baseView() {
        return baseBuilder == null ? null : baseBuilder.getBaseView();
    }

    public boolean onKeyBack() {
        getFragmentManager().popBackStack();
        return true;
    }
}
