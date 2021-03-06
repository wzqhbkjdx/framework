package cent.news.com.newscent.news;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import cent.news.com.newscent.news.channel.ChannelDBBean;

public class NewsFragmentAdapter extends FragmentStatePagerAdapter {

    private ArrayList<ChannelDBBean> newsList = new ArrayList<>();

    public NewsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setTitleList(ArrayList<ChannelDBBean> titleList) {
        newsList = titleList;
        notifyDataSetChanged();
    }


    @Override
    public Fragment getItem(int position) {
        return NewsTabFragment.getInstance(newsList.get(position).getChannelID(),
                                            newsList.get(position).getAlias(),
                                            newsList.get(position).getAttval(),
                                            newsList.get(position).getTitle(),
                                            newsList.get(position).getType());
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return newsList.get(position).getTitle();
    }

    @Override
    public int getCount() {
        return newsList.size();
    }


}



















