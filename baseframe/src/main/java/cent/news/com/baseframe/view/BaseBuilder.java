package cent.news.com.baseframe.view;

import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

/**
 * Created by bym on 2018/6/19.
 */

public final class BaseBuilder {

    private BaseView baseView;

    private Toolbar toolbar;

    @Nullable Toolbar getToolbar() {
        return toolbar;
    }

    @Nullable BaseView getBaseView() {
        return baseView;
    }


}
