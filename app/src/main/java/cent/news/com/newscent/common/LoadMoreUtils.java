package cent.news.com.newscent.common;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cent.news.com.baseframe.modules.log.L;
import cent.news.com.baseframe.view.adapter.recyclerView.BaseRVAdapter;
import cent.news.com.newscent.R;

public class LoadMoreUtils {


    /**
     * 改变状态
     *
     * @param recyclerView
     * @param state
     */
    public static final void changeState(RecyclerView recyclerView, BaseRVAdapter adapter, int state) {
        if (recyclerView == null || adapter == null) {
            L.i("加载更多不能为空~");
            return;
        }
        int position = adapter.getItemCount() - 1;
        adapter.setState(state);
        adapter.notifyItemChanged(position);
    }

    /**
     * 是否有网
     *
     * @param recyclerView
     * @param skyrvAdapter
     * @return
     */
    public static final boolean isNotHttp(RecyclerView recyclerView, BaseRVAdapter skyrvAdapter) {
        if (recyclerView == null || skyrvAdapter == null) {
            L.i("加载更多不能为空~");
            return false;
        }
        int position = skyrvAdapter.getItemCount() - 1;
        return skyrvAdapter.getState() == LoadMoreState.NOT_HTTP ? true : false;
    }

    /**
     * 获取holder
     *
     * @param viewGroup
     * @return holder
     */
    public static final LoadMoreHolder getHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_load_more, viewGroup, false);
        final LoadMoreHolder loadMoreHolder = new LoadMoreHolder(view);
        return loadMoreHolder;
    }

}
