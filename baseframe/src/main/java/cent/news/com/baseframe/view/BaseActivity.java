package cent.news.com.baseframe.view;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import cent.news.com.baseframe.BaseHelper;
import cent.news.com.baseframe.base.IBaseView;
import cent.news.com.baseframe.common.BaseSwipeWindowHelper;
import cent.news.com.baseframe.core.IBaseBiz;
import cent.news.com.baseframe.display.BaseIDisplay;
import cent.news.com.baseframe.modules.structure.BaseStructureModel;

/**
 * Created by bym on 2018/6/16.
 */

public abstract class BaseActivity<B extends IBaseBiz> extends AppCompatActivity implements IBaseView {

    protected abstract BaseBuilder build(BaseBuilder builder);

    protected void buildBefore(Bundle bundle) {

    }

    protected void initDagger() {

    }

    protected void createData(Bundle savedInstanceState) {

    }

    protected abstract void initData(Bundle savedInstanceState);

    private BaseBuilder baseBuilder;

    BaseStructureModel baseStructureModel;

    private SystemBarTintManager systemBarTintManager;

    private BaseSwipeWindowHelper baseSwipeWindowHelper;

    private boolean isFinish;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        buildBefore(savedInstanceState);
        super.onCreate(savedInstanceState);

        //初始化核心
        initCore();

        //初始化screen堆栈
        BaseHelper.screenHelper().onCreate(this);

        //Activity拦截器，如果BaseStructureManage中的BaseMethods被初始化了，则
        //BaseActivityInterceptor的onCreate()函数可以在所有继承了BaseActivity的子Activity中执行。
        BaseHelper.methodsProxy().baseActivityInterceptor().onCreate(this,
                getIntent().getExtras(), savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        baseBuilder = new BaseBuilder(this, inflater);

    }

    private void initCore() {
        baseStructureModel = new BaseStructureModel(this, getIntent() == null ? null : getIntent().getExtras());
        BaseHelper.structureHelper().attach(baseStructureModel);
    }

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

    public boolean canBeSlideBack() {
        return true;
    }
}
