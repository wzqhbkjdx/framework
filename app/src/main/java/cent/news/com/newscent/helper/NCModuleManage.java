package cent.news.com.newscent.helper;

import cent.news.com.baseframe.modules.BaseModuleManage;
import cent.news.com.newscent.db.GreenDAOManager;

public class NCModuleManage extends BaseModuleManage {

    @Override
    public void initDatabase() {
        GreenDAOManager.instance().init();
    }
}
