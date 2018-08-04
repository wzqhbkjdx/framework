package cent.news.com.newscent.helper;

import android.graphics.Typeface;

import cent.news.com.baseframe.BaseHelper;
import cent.news.com.baseframe.modules.BaseModuleManage;
import cent.news.com.baseframe.modules.toast.BaseCustomToast;

public class NCHelper extends BaseHelper {


    public static BaseCustomToast toast() {
        NCModuleManage manage = getManage();
        return manage.getCustomToast();
    }

    public static Typeface typeface() {
        NCModuleManage manage = getManage();
        return manage.getTypeface();
    }
}
