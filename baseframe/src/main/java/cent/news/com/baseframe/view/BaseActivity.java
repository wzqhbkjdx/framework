package cent.news.com.baseframe.view;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import cent.news.com.baseframe.BaseHelper;
import cent.news.com.baseframe.base.IBaseView;
import cent.news.com.baseframe.core.IBaseBiz;
import cent.news.com.baseframe.display.BaseIDisplay;

/**
 * Created by bym on 2018/6/16.
 */

public abstract class BaseActivity<B extends IBaseBiz> extends AppCompatActivity implements IBaseView {

    private BaseBuilder baseBuilder;

    public Toolbar toolbar() {
        return baseBuilder == null ? null : baseBuilder.getToolbar();
    }

    public B biz() {
        return null;
    }

    public <C extends IBaseBiz> C biz(Class<C> service) {
        return null;
    }

    public <D extends BaseIDisplay> D display(Class<D> eClass) {
        return BaseHelper.display(eClass);
    }

    public BaseView baseView() {
        return baseBuilder == null ? null : baseBuilder.getBaseView();
    }

    public boolean onKeyBack() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
        return true;
    }

}
