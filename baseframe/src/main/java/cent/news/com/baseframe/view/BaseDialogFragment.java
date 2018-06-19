package cent.news.com.baseframe.view;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.support.v7.widget.Toolbar;

import cent.news.com.baseframe.BaseHelper;
import cent.news.com.baseframe.base.IBaseView;
import cent.news.com.baseframe.core.IBaseBiz;
import cent.news.com.baseframe.display.BaseIDisplay;

/**
 * Created by bym on 2018/6/19.
 */

public abstract class BaseDialogFragment<B extends IBaseBiz> extends DialogFragment implements IBaseDialogFragment,
        DialogInterface.OnKeyListener, IBaseView {


    public B biz() {
        return null;
    }

    public <C extends IBaseBiz> C biz(Class<C> service) {
        return null;
    }

    public <D extends BaseIDisplay> D display(Class<D> eClass) {
        return BaseHelper.display(eClass);
    }

    private BaseBuilder baseBuilder;

    public Toolbar toolbar() {
        return baseBuilder == null ? null : baseBuilder.getToolbar();
    }

    public BaseView baseView() {
        return baseBuilder == null ? null : baseBuilder.getBaseView();
    }
}
