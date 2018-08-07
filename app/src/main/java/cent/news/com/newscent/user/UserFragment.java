package cent.news.com.newscent.user;

import android.view.View;

import cent.news.com.baseframe.view.BaseBuilder;
import cent.news.com.baseframe.view.BaseFragment;
import cent.news.com.newscent.R;

public class UserFragment extends BaseFragment<UserBiz> {

    @Override
    protected BaseBuilder build(BaseBuilder builder) {
        builder.layoutId(R.layout.fragment_user_layout);
        return builder;
    }

    @Override
    protected void buildAfter(View view) {

    }
}
