package cent.news.com.newscent.cents;

import android.view.View;

import cent.news.com.baseframe.view.BaseBuilder;
import cent.news.com.baseframe.view.BaseFragment;
import cent.news.com.newscent.R;

public class CentsFragment extends BaseFragment<CentsBiz> {


    @Override
    protected BaseBuilder build(BaseBuilder builder) {
        builder.layoutId(R.layout.fragment_cents_layout);
        return builder;
    }

    @Override
    protected void buildAfter(View view) {

    }
}
