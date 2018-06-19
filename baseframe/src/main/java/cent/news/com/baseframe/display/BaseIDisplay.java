package cent.news.com.baseframe.display;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import org.jetbrains.annotations.NotNull;

import sky.Impl;


/**
 * Created by bym on 2018/6/18.
 */

@Impl(BaseDisplay.class)
public interface BaseIDisplay {

    /**
     * 获取上下文
     * @return
     */
    Context context();

    /**
     *
     * @param <T>
     * @return
     */
    <T extends FragmentActivity> T activity();

    /**
     *
     * @param clazz
     * @param fragment
     * @param requestCode
     */
    void intentFromFragment(@NotNull Class clazz, @NotNull Fragment fragment, int requestCode);

    /**
     *
     * @param intent
     * @param fragment
     * @param requestCode
     */
    void intentFromFragment(@NotNull Intent intent, @NotNull Fragment fragment, int requestCode);

    /**
     *
     */
    void onKeyHome();

    /**
     *
     */
    void popBackStack(@NotNull Class clazz);


    void popBackStackAll();


    void commitAdd(@NotNull Fragment fragment);

    void commitAdd(@IdRes int layoutId, @NotNull Fragment fragment);

    void commitReplace(@NotNull Fragment fragment);

    void commitChildReplace(@NotNull Fragment srcFragment, @IdRes int layoutId, @NotNull Fragment fragment);

    void commitReplace(@IdRes int layoutId, @NotNull Fragment fragment);


    void commitBackStack(@NotNull Fragment fragment);

    void commitHideAndBackStack(@NotNull Fragment srcFragment, @NotNull Fragment fragment);

    void commitDetachAndBackStack(@NotNull Fragment srcFragment, @NotNull Fragment fragment);

    void commitBackStack(@IdRes int layoutId, @NotNull Fragment fragment);

    void commitBackStack(@IdRes int layoutId, @NotNull Fragment fragment, int animation);



    void intent(@NotNull Class clazz);

    void intent(@NotNull String className);

    void intentNotAnimation(@NotNull Class clazz);

    void intent(@NotNull Class clazz, Bundle bundle);

    void intentNotAnimation(@NotNull Class clazz, Bundle bundle);

    void intent(@NotNull Intent intent);



    void intentForResult(@NotNull Class clazz, int requestCode);

    void intentForResultFromFragment(@NotNull Class clazz, Bundle bundle, int requestCode, @NotNull Fragment fragment);

    void intentForResult(@NotNull Class clazz, Bundle bundle, int requestCode);

    void intentForResult(@NotNull Intent intent, int requestCode);

    void intentForResult(@NotNull Intent intent, @NotNull Bundle options, int requestCode);

    void intentAnimation(@NotNull Class clazz, @NotNull View view, Bundle bundle);

    void intentAnimation(@NotNull Class clazz, @AnimRes int in, @AnimRes int out);

    void intentAnimation(@NotNull Class clazz, @AnimRes int in, @AnimRes int out, @NotNull Bundle bundle);

    void intentForResultAnimation(@NotNull Class clazz, @NotNull View view, int requestCode);

    void intentForResultAnimation(@NotNull Class clazz, @NotNull View view, @NotNull Bundle bundle, int requestCode);

    void intentForResultAnimation(@NotNull Class clazz, @AnimRes int in, @AnimRes int out, int requestCode);

    void intentForResultAnimation(@NotNull Class clazz, @AnimRes int in, @AnimRes int out, @NotNull Bundle bundle, int requestCode);

    void intentCustomAnimation(@NotNull Class clazz, @AnimRes int in, @AnimRes int out);

    void intentCustomAnimation(@NotNull Class clazz, @AnimRes int in, @AnimRes int out, @NotNull Bundle options);

    void intentScaleUpAnimation(@NotNull Class clazz, @NotNull View view, int startX, int startY, int startWidth, int startHeight);

    void intentScaleUpAnimation(@NotNull Class clazz, @NotNull View view, int startX, int startY, int startWidth, int startHeight, @NotNull Bundle options);

    void intentSceneTransitionAnimation(@NotNull Class clazz, BaseDisplayModel... skyDisplayModel);

    void intentSceneTransitionAnimation(@NotNull Class clazz, @NotNull Bundle options, BaseDisplayModel... skyDisplayModel);

    void intentSceneTransitionAnimation(@NotNull Class clazz, View first, String second);

    void intentSceneTransitionAnimation(@NotNull Class clazz, View first, String second, @NotNull Bundle options);


    void intentClipRevealAnimation(@NotNull Class clazz, @NotNull View view, int startX, int startY, int width, int height);

    void intentClipRevealAnimation(@NotNull Class clazz, @NotNull View view, int startX, int startY, int width, int height, @NotNull Bundle options);

    void intentThumbnailScaleUpAnimation(@NotNull Class clazz, @NotNull View view, @NotNull Bitmap thumbnail, int startX, int startY);

    void intentThumbnailScaleUpAnimation(@NotNull Class clazz, @NotNull View view, @NotNull Bitmap thumbnail, int startX, int startY, @NotNull Bundle options);


}


















