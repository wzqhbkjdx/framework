package cent.news.com.newscent.helper;

import cent.news.com.baseframe.BaseHelper;
import cent.news.com.baseframe.modules.toast.BaseCustomToast;

public class NCHelper extends BaseHelper {


    public static BaseCustomToast toast() {
        NCModuleManage manage = getManage();
        return manage.getCustomToast();
    }

}
