package cent.news.com.newscent.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

import cent.news.com.baseframe.BaseHelper;
import cent.news.com.newscent.MainBiz;
import cent.news.com.newscent.R;

public class FragmentTabManage {


    private final ArrayList<HomeInfo> mTabs	= new ArrayList<>();

    private final FragmentManager mFragmentManager;

    public HomeInfo						mLastTab;

    private boolean						mAttached;

    private Context mContext;

    public FragmentTabManage(Context context, FragmentManager fragmentManager) {
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
    }

    public void addTab(String tag, Class<?> clss, Bundle args, int position) {
        HomeInfo info = new HomeInfo(tag, clss, args, position);
        if (mAttached) {
            info.fragment = mFragmentManager.findFragmentByTag(tag);
            if (info.fragment != null && !info.fragment.isDetached()) {
                FragmentTransaction ft = mFragmentManager.beginTransaction();
                ft.detach(info.fragment);
                ft.commit();
            }
        }
        mTabs.add(info);
    }

    public void setAttached(boolean isAttached) {
        mAttached = isAttached;
    }

    private String getCurrentTabTag() {
        if (mLastTab != null) {
            return mLastTab.tag;
        }
        return mTabs.get(BaseHelper.biz(MainBiz.class).getInitIndex()).tag;
    }

    private String getCurrentTabTag(int postion) {
        if (mTabs.get(postion) == null) {
            return "";
        }
        return mTabs.get(postion).tag;
    }

    /**
     * 初始化
     */
    public void onAttachedToWindow() {
        String currentTab = getCurrentTabTag();

        FragmentTransaction ft = null;
        for (int i = 0; i < mTabs.size(); i++) {
            HomeInfo tab = mTabs.get(i);
            tab.fragment = mFragmentManager.findFragmentByTag(tab.tag);
            if (tab.fragment != null && !tab.fragment.isDetached()) {
                if (tab.tag.equals(currentTab)) {
                    mLastTab = tab;
                } else {
                    if (ft == null) {
                        ft = mFragmentManager.beginTransaction();
                    }
                    ft.detach(tab.fragment);
                }
            }
        }

        mAttached = true;
        ft = doTabChanged(currentTab, ft);
        if (ft != null) {
            ft.commit();
            mFragmentManager.executePendingTransactions();
        }
    }

    private FragmentTransaction doTabChanged(String tabId, FragmentTransaction ft) {
        HomeInfo newTab = null;
        for (int i = 0; i < mTabs.size(); i++) {
            HomeInfo tab = mTabs.get(i);
            if (tab.tag.equals(tabId)) {
                newTab = tab;
                break;
            }
        }
        if (newTab == null) {
            throw new IllegalStateException("No tab known for tag " + tabId);
        }
        if (mLastTab != newTab) {
            if (ft == null) {
                ft = mFragmentManager.beginTransaction();
            }
            if (mLastTab != null) {
                if (mLastTab.fragment != null) {
                    ft.hide(mLastTab.fragment);
                }
            }
            if (newTab != null) {
                if (newTab.fragment == null || !newTab.fragment.isAdded()) {
                    newTab.fragment = Fragment.instantiate(mContext, newTab.clss.getName(), newTab.args);
                    ft.add(R.id.fragment_container, newTab.fragment, newTab.tag);
                } else {
                    ft.show(newTab.fragment);
                }
            }

            mLastTab = newTab;
        }
        return ft;
    }

    public void onDetachedFromWindow() {
        mAttached = false;
        mContext = null;
        mLastTab = null;
        mTabs.clear();
    }

    public void onTabChanged(int position) {
        if (mAttached) {
            String tabId = getCurrentTabTag(position);
            if (StringUtils.isBlank(tabId)) {
                return;
            }
            FragmentTransaction ft = doTabChanged(tabId, null);
            if (ft != null) {
                ft.commitAllowingStateLoss();
            }
        }
    }


}
