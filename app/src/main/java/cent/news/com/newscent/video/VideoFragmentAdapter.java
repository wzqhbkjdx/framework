package cent.news.com.newscent.video;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import cent.news.com.newscent.news.NewsTabFragment;
import cent.news.com.newscent.news.channel.ChannelDBBean;

public class VideoFragmentAdapter extends FragmentStatePagerAdapter {

    private ArrayList<ChannelDBBean> videoList = new ArrayList<>();

    public void setTitleList(ArrayList<ChannelDBBean> titleList) {
        videoList = titleList;
        notifyDataSetChanged();
    }

    public VideoFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return videoList.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return VideoTabFragment.getInstance(videoList.get(position).getChannelID(),
                videoList.get(position).getAlias(),
                videoList.get(position).getAttval(),
                videoList.get(position).getTitle(),
                videoList.get(position).getType());
    }

    @Override
    public int getCount() {
        return videoList.size();
    }
}
