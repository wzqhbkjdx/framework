package cent.news.com.baseframe.display;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import org.jetbrains.annotations.NotNull;

import cent.news.com.baseframe.BaseHelper;
import cent.news.com.baseframe.R;
import cent.news.com.baseframe.utils.BaseCheckUtils;

/**
 * Created by bym on 2018/6/18.
 *
 * 用于界面之间的来回跳转， Activity Fragment
 *
 */

public class BaseDisplay implements BaseIDisplay {

    @Override
    public Context context() {
        return BaseHelper.screenHelper().getCurrentActivity();
    }

    @Override
    public <T extends FragmentActivity> T activity() {
        T activity = BaseHelper.screenHelper().getCurrentIsRunningActivity();
        if(activity != null) {
            return activity;
        } else {
            return BaseHelper.screenHelper().getCurrentActivity();
        }
    }

    @Override
    public void intentFromFragment(@NotNull Class clazz, @NotNull Fragment fragment, int requestCode) {
        Intent intent = new Intent();
        if(activity() == null) {
            return;
        }
        intent.setClass(activity(), clazz);
        intentFromFragment(intent, fragment, requestCode);
    }

    @Override
    public void intentFromFragment(@NotNull Intent intent, @NotNull Fragment fragment, int requestCode) {
        if(activity() == null) {
            return;
        }
        activity().startActivityFromFragment(fragment, intent, requestCode);
    }

    @Override
    public void onKeyHome() {
        if(activity() == null) {
            return;
        }
        activity().moveTaskToBack(true);
    }

    @Override public void popBackStack() {
        if (activity() == null) {
            return;
        }
        activity().getSupportFragmentManager().popBackStackImmediate();
    }

    @Override public void popBackStack(Class clazz) {
        if (activity() == null) {
            return;
        }
        activity().getSupportFragmentManager().popBackStackImmediate(clazz.getName(), 0);
    }

    @Override public void popBackStack(String clazzName) {
        if (activity() == null) {
            return;
        }
        activity().getSupportFragmentManager().popBackStackImmediate(clazzName, 0);
    }

    @Override public void popBackStackAll() {
        if (activity() == null) {
            return;
        }
        activity().getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void commitAdd(@NotNull Fragment fragment) {
        commitAdd(R.id.sky_home, fragment);
    }

    @Override
    public void commitAdd(int layoutId, @NotNull Fragment fragment) {
        BaseCheckUtils.checkArgument((int)layoutId > 0, "布局ID 不能为空~");
        BaseCheckUtils.checkNotNull(fragment, "fragment不能为空~");
        if (activity() == null) {
            return;
        }
        activity().getSupportFragmentManager().beginTransaction().add(layoutId, fragment,
                fragment.getClass().getName()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commitAllowingStateLoss();
    }

    @Override
    public void commitReplace(@NotNull Fragment fragment) {
        commitReplace(R.id.sky_home, fragment);
    }

    @Override
    public void commitChildReplace(@NotNull Fragment srcFragment, int layoutId, @NotNull Fragment fragment) {
        BaseCheckUtils.checkArgument((int)layoutId > 0, "提交布局ID 不能为空~");
        BaseCheckUtils.checkNotNull(fragment, "fragment不能为空~");
        if (activity() == null) {
            return;
        }
        srcFragment.getChildFragmentManager().beginTransaction().replace(layoutId, fragment,
                fragment.getClass().getName()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commitAllowingStateLoss();
    }

    @Override
    public void commitReplace(int layoutId, @NotNull Fragment fragment) {
        BaseCheckUtils.checkArgument((int)layoutId > 0, "提交布局ID 不能为空~");
        BaseCheckUtils.checkNotNull(fragment, "fragment不能为空~");
        if (activity() == null) {
            return;
        }
        activity().getSupportFragmentManager().beginTransaction().replace(layoutId, fragment,
                fragment.getClass().getName()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commitAllowingStateLoss();
    }

    @Override
    public void commitBackStack(@NotNull Fragment fragment) {
        commitBackStack(R.id.sky_home, fragment);
    }

    @Override
    public void commitHideAndBackStack(@NotNull Fragment srcFragment, @NotNull Fragment fragment) {
        BaseCheckUtils.checkNotNull(fragment, "fragment不能为空~");
        if (activity() == null) {
            return;
        }
        FragmentTransaction transaction = activity().getSupportFragmentManager().beginTransaction();
        transaction.hide(srcFragment);
        transaction.add(R.id.sky_home, fragment, fragment.getClass().getName()).addToBackStack(fragment.getClass().getName()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commitAllowingStateLoss();
    }

    @Override
    public void commitDetachAndBackStack(@NotNull Fragment srcFragment, @NotNull Fragment fragment) {

    }

    @Override
    public void commitBackStack(int layoutId, @NotNull Fragment fragment) {

    }

    @Override
    public void commitBackStack(int layoutId, @NotNull Fragment fragment, int animation) {

    }

    @Override
    public void intent(@NotNull Class clazz) {

    }

    @Override
    public void intent(@NotNull String className) {

    }

    @Override
    public void intentNotAnimation(@NotNull Class clazz) {

    }

    @Override
    public void intent(@NotNull Class clazz, Bundle bundle) {

    }

    @Override
    public void intentNotAnimation(@NotNull Class clazz, Bundle bundle) {

    }

    @Override
    public void intent(@NotNull Intent intent) {

    }

    @Override
    public void intentForResult(@NotNull Class clazz, int requestCode) {

    }

    @Override
    public void intentForResultFromFragment(@NotNull Class clazz, Bundle bundle, int requestCode, @NotNull Fragment fragment) {

    }

    @Override
    public void intentForResult(@NotNull Class clazz, Bundle bundle, int requestCode) {

    }

    @Override
    public void intentForResult(@NotNull Intent intent, int requestCode) {

    }

    @Override
    public void intentForResult(@NotNull Intent intent, @NotNull Bundle options, int requestCode) {

    }

    @Override
    public void intentAnimation(@NotNull Class clazz, @NotNull View view, Bundle bundle) {

    }

    @Override
    public void intentAnimation(@NotNull Class clazz, int in, int out) {

    }

    @Override
    public void intentAnimation(@NotNull Class clazz, int in, int out, @NotNull Bundle bundle) {

    }

    @Override
    public void intentForResultAnimation(@NotNull Class clazz, @NotNull View view, int requestCode) {

    }

    @Override
    public void intentForResultAnimation(@NotNull Class clazz, @NotNull View view, @NotNull Bundle bundle, int requestCode) {

    }

    @Override
    public void intentForResultAnimation(@NotNull Class clazz, int in, int out, int requestCode) {

    }

    @Override
    public void intentForResultAnimation(@NotNull Class clazz, int in, int out, @NotNull Bundle bundle, int requestCode) {

    }

    @Override
    public void intentCustomAnimation(@NotNull Class clazz, int in, int out) {

    }

    @Override
    public void intentCustomAnimation(@NotNull Class clazz, int in, int out, @NotNull Bundle options) {

    }

    @Override
    public void intentScaleUpAnimation(@NotNull Class clazz, @NotNull View view, int startX, int startY, int startWidth, int startHeight) {

    }

    @Override
    public void intentScaleUpAnimation(@NotNull Class clazz, @NotNull View view, int startX, int startY, int startWidth, int startHeight, @NotNull Bundle options) {

    }

    @Override
    public void intentSceneTransitionAnimation(@NotNull Class clazz, BaseDisplayModel... skyDisplayModel) {

    }

    @Override
    public void intentSceneTransitionAnimation(@NotNull Class clazz, @NotNull Bundle options, BaseDisplayModel... skyDisplayModel) {

    }

    @Override
    public void intentSceneTransitionAnimation(@NotNull Class clazz, View first, String second) {

    }

    @Override
    public void intentSceneTransitionAnimation(@NotNull Class clazz, View first, String second, @NotNull Bundle options) {

    }

    @Override
    public void intentClipRevealAnimation(@NotNull Class clazz, @NotNull View view, int startX, int startY, int width, int height) {

    }

    @Override
    public void intentClipRevealAnimation(@NotNull Class clazz, @NotNull View view, int startX, int startY, int width, int height, @NotNull Bundle options) {

    }

    @Override
    public void intentThumbnailScaleUpAnimation(@NotNull Class clazz, @NotNull View view, @NotNull Bitmap thumbnail, int startX, int startY) {

    }

    @Override
    public void intentThumbnailScaleUpAnimation(@NotNull Class clazz, @NotNull View view, @NotNull Bitmap thumbnail, int startX, int startY, @NotNull Bundle options) {

    }
}
