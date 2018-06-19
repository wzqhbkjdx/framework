package cent.news.com.baseframe.view.adapter.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by bym on 2018/6/18.
 */

public abstract class BaseRVAdapter<T, V extends BaseHolder> extends RecyclerView.Adapter<V> implements BaseIRefresh {

    private static final int	VIEW_ITEM	= 0;

    private static final int	VIEW_PROG	= 9999;

    private static final int	VIEW_TOP	= 10000;

    public abstract V newViewHolder(ViewGroup viewGroup, int type);

    public V newLoadMoreHolder(ViewGroup viewGroup, int type) {
        return null;
    }

    public V newTopHolder(ViewGroup viewGroup, int type) {
        return null;
    }

    private BaseRVAdapter() {}



}
