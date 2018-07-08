package cent.news.com.baseframe.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cent.news.com.baseframe.BaseHelper;
import cent.news.com.baseframe.base.IBaseView;
import cent.news.com.baseframe.core.IBaseBiz;
import cent.news.com.baseframe.display.BaseIDisplay;
import cent.news.com.baseframe.modules.structure.BaseStructureModel;
import cent.news.com.baseframe.utils.BaseAppUtil;
import cent.news.com.baseframe.utils.BaseKeyboardUtils;

/**
 * Created by bym on 2018/6/19.
 */

public abstract class BaseDialogFragment<B extends IBaseBiz> extends DialogFragment implements IBaseDialogFragment,
        DialogInterface.OnKeyListener, IBaseView {

    private boolean targetActivity;

    protected int requestCode = 2013 << 5;

    public final static String ARG_REQUEST_CODE = "base_request_code";

    //View层编辑器
    public BaseBuilder baseBuilder;

    BaseStructureModel baseStructureModel;

    private Unbinder unbinder;

    protected abstract BaseBuilder build(BaseBuilder baseBuilder);

    protected void buildAfter(View view) {

    }

    protected void initDagger() {

    }

    protected void createData(Bundle savedInstanceState) {

    }

    protected abstract void initData(Bundle bundle);

    protected abstract int getDialogStyle();

    protected boolean isCancel() {
        return false;
    }

    protected void setDialogCancel(boolean fg) {
        getDialog().setCanceledOnTouchOutside(fg);
    }

    protected boolean isFull() {
        return false;
    }

    protected boolean isFullWidth() {
        return false;
    }

    protected boolean isFullHeight() {
        return false;
    }

    public boolean isTargetActivity() {
        return targetActivity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), getDialogStyle());
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        final Fragment fragment = getTargetFragment();

        if(fragment != null) {
            requestCode = fragment.getTargetRequestCode();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        initCore();
        BaseHelper.structureHelper().attach(baseStructureModel);
        baseBuilder = new BaseBuilder(this, inflater);
        View view = build(baseBuilder).create();
        unbinder = ButterKnife.bind(this, view);
        setDialogCancel(isCancel());
        getDialog().setOnKeyListener(this);
        buildAfter(view);
        return view;
    }

    private void initCore() {
        baseStructureModel = new BaseStructureModel(this, getArguments());
    }

    public Object model() {
        return baseStructureModel.getBaseProxy().impl;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(isFull()) {
            Window window = getDialog().getWindow();
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        } else if(isFullWidth()) {
            Window window = getDialog().getWindow();
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        } else if(isFullHeight()) {
            Window window = getDialog().getWindow();
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }

        baseStructureModel.initBizBundle();

        initDagger();

        createData(savedInstanceState);

        initData(getArguments());
    }

    @Override
    public void onResume() {
        super.onResume();
        BaseHelper.structureHelper().printBackStackEntry(getFragmentManager());
    }

    @Override
    public void onPause() {
        super.onPause();
        recyclerRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(baseBuilder != null) {
            baseBuilder.detach();
            baseBuilder = null;
        }

        if(baseStructureModel != null) {
            BaseHelper.structureHelper().detach(baseStructureModel);
        }

        unbinder.unbind();

        BaseKeyboardUtils.hideSoftInput(getActivity());

        if(getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
    }

    protected void detach() {

    }

    public void setSoftInput(int mode) {
        getActivity().getWindow().setSoftInputMode(mode);
    }

    public B biz() {
        if (baseStructureModel == null || baseStructureModel.getBaseProxy() == null || baseStructureModel.getBaseProxy().proxy == null) {
            Class service = BaseAppUtil.getSuperClassGenricType(getClass(), 0);
            return (B) BaseHelper.structureHelper().createNullService(service);
        }
        return (B) baseStructureModel.getBaseProxy().proxy;
    }

    public <C extends IBaseBiz> C biz(Class<C> service) {
        if (baseStructureModel != null && service.equals(baseStructureModel.getService())) {
            if (baseStructureModel == null || baseStructureModel.getBaseProxy() == null || baseStructureModel.getBaseProxy().proxy == null) {
                return BaseHelper.structureHelper().createNullService(service);
            }
            return (C) baseStructureModel.getBaseProxy().proxy;
        }
        return BaseHelper.biz(service);
    }

    public <D extends BaseIDisplay> D display(Class<D> eClass) {
        return BaseHelper.display(eClass);
    }

    public Toolbar toolbar() {
        return baseBuilder == null ? null : baseBuilder.getToolbar();
    }

    public BaseView baseView() {
        return baseBuilder == null ? null : baseBuilder.getBaseView();
    }


    public boolean onKeyBack() {
        dismissAllowingStateLoss();
        return true;
    }

    public void recyclerRefreshing(boolean bool) {
        if(baseBuilder != null) {
            baseBuilder.recyclerRefreshing(bool);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (baseBuilder.getToolbarMenuId() > 0) {
            menu.clear();
            this.getActivity().getMenuInflater().inflate(baseBuilder.getToolbarMenuId(), menu);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        BaseHelper.methodsProxy().baseActivityInterceptor().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}









