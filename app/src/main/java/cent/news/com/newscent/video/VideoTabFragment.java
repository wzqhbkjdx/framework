package cent.news.com.newscent.video;

import android.os.Bundle;
import android.view.View;

import cent.news.com.baseframe.view.BaseBuilder;
import cent.news.com.baseframe.view.BaseFragment;
import cent.news.com.baseframe.view.common.BaseRefreshListener;
import cent.news.com.newscent.R;

public class VideoTabFragment extends BaseFragment<VideoTabBiz> implements BaseRefreshListener {

    public static String CHANNEL_ID = "channel_id";
    public static String ALIAS = "alias";
    public static String ATTVAL = "attval";
    public static String TITLE = "title";
    public static String TYPE = "type";

    public static VideoTabFragment getInstance(int channelID, String alias, int attval, String title, int type) {
        Bundle args = new Bundle();
        args.putInt(CHANNEL_ID, channelID);
        args.putString(ALIAS, alias);
        args.putInt(ATTVAL, attval);
        args.putString(TITLE, title);
        args.putInt(TYPE, type);
        VideoTabFragment videoTabFragment = new VideoTabFragment();
        videoTabFragment.setArguments(args);
        return videoTabFragment;
    }

    @Override
    protected BaseBuilder build(BaseBuilder builder) {
        builder.layoutId(R.layout.fragment_video_tab_layout);
        return builder;
    }

    @Override
    protected void buildAfter(View view) {

    }

    /**
     * swipe refresh listener callback
     * @return
     */
    @Override
    public boolean onScrolledToBottom() {
        return false;
    }

    /**
     * swipe refresh listener callback
     * @return
     */
    @Override
    public void onRefresh() {

    }
}
