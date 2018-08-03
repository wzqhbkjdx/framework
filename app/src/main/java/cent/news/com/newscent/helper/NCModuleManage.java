package cent.news.com.newscent.helper;

import cent.news.com.baseframe.modules.BaseModuleManage;
import cent.news.com.newscent.db.GreenDAOManager;
import cent.news.com.newscent.view.NCToast;

public class NCModuleManage extends BaseModuleManage {

    private NCToast			hnaToast;				// 自定义toast


    @Override
    public void initDatabase() {
        GreenDAOManager.instance().init();
    }


    public NCToast getCustomToast() {
        if (hnaToast == null) {
            synchronized (NCToast.class) {
                if (hnaToast == null) {
                    hnaToast = new NCToast();
                }
            }
        }
        return hnaToast;
    }

}
