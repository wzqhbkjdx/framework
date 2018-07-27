package cent.news.com.newscent.common;

import android.util.DisplayMetrics;

import cent.news.com.baseframe.BaseHelper;

public class APPUtils {

    /**
     * 获取屏幕高度
     *
     * @return height
     */
    public static int getWindowHeight() {
        DisplayMetrics metric = new DisplayMetrics();
        BaseHelper.screenHelper().getCurrentActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels; // 屏幕宽度（像素）
        int height = metric.heightPixels; // 屏幕高度（像素）
        float density = metric.density; // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi; // 屏幕密度DPI（120 / 160 / 240）
        return height;
    }

    /**
     * 获取屏幕宽度
     *
     * @return width
     */
    public static int getWindowWidth() {
        DisplayMetrics metric = new DisplayMetrics();
        BaseHelper.screenHelper().getCurrentActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels; // 屏幕宽度（像素）
        int height = metric.heightPixels; // 屏幕高度（像素）
        float density = metric.density; // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi; // 屏幕密度DPI（120 / 160 / 240）
        return width;
    }


}
