package cent.news.com.newscent.video;

import android.view.View;

import cent.news.com.baseframe.view.BaseBuilder;
import cent.news.com.baseframe.view.BaseFragment;
import cent.news.com.newscent.R;

public class VideoFragment extends BaseFragment<VideoBiz> {

    @Override
    protected BaseBuilder build(BaseBuilder builder) {
        builder.layoutId(R.layout.fragment_layout_video);
        builder.tintFitsSystem(false);
        return builder;
    }

    @Override
    protected void buildAfter(View view) {

    }



}
