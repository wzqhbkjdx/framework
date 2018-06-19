package cent.news.com.baseframe.display;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import org.jetbrains.annotations.NotNull;

/**
 * Created by bym on 2018/6/18.
 */

public class BaseDisplay implements BaseIDisplay {

    @Override
    public Context context() {
        return null;
    }

    @Override
    public <T extends FragmentActivity> T activity() {
        return null;
    }

    @Override
    public void intentFromFragment(@NotNull Class clazz, @NotNull Fragment fragment, int requestCode) {

    }

    @Override
    public void intentFromFragment(@NotNull Intent intent, @NotNull Fragment fragment, int requestCode) {

    }

    @Override
    public void onKeyHome() {

    }

    @Override
    public void popBackStack(@NotNull Class clazz) {

    }

    @Override
    public void popBackStackAll() {

    }

    @Override
    public void commitAdd(@NotNull Fragment fragment) {

    }

    @Override
    public void commitAdd(int layoutId, @NotNull Fragment fragment) {

    }

    @Override
    public void commitReplace(@NotNull Fragment fragment) {

    }

    @Override
    public void commitChildReplace(@NotNull Fragment srcFragment, int layoutId, @NotNull Fragment fragment) {

    }

    @Override
    public void commitReplace(int layoutId, @NotNull Fragment fragment) {

    }

    @Override
    public void commitBackStack(@NotNull Fragment fragment) {

    }

    @Override
    public void commitHideAndBackStack(@NotNull Fragment srcFragment, @NotNull Fragment fragment) {

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
