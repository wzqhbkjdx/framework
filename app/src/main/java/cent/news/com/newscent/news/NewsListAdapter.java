package cent.news.com.newscent.news;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import cent.news.com.newscent.news.channel.ChannelModel;

public class NewsListAdapter extends FragmentStatePagerAdapter {

    private ArrayList<ChannelModel> newsList = new ArrayList<>();


    public NewsListAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setTitleList(ArrayList<ChannelModel> titleList) {
        newsList = titleList;
        notifyDataSetChanged();
    }



    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }


}



















