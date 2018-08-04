package cent.news.com.newscent.helper;

import android.graphics.Typeface;

import cent.news.com.baseframe.BaseHelper;
import cent.news.com.baseframe.modules.BaseModuleManage;
import cent.news.com.newscent.db.GreenDAOManager;
import cent.news.com.newscent.view.NCToast;
import cent.news.com.newscent.view.pulldown.PullManage;

public class NCModuleManage extends BaseModuleManage {

    private NCToast			hnaToast;				// 自定义toast

    private Typeface			typeface;				// 字体

    private PullManage			pullManage;				// 下拉刷新



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

    public Typeface getTypeface() {
        if (typeface == null) {
            synchronized (Typeface.class) {
                if (typeface == null) {
                    typeface = Typeface.createFromAsset(BaseHelper.getInstance().getAssets(), "iconfont/default_blue_font.ttf");
                }
            }
        }
        return typeface;
    }

    public PullManage getPullManage() {
        if (pullManage == null) {
            synchronized (PullManage.class) {
                if (pullManage == null) {
                    pullManage = new PullManage();
                }
            }
        }

        return pullManage;
    }

}
