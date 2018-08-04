package cent.news.com.newscent.view.pulldown;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;

import cent.news.com.newscent.R;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public abstract class NCHandler implements PtrHandler {

    public static boolean canChildScrollUp(View view) {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (view instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) view;
                return absListView.getChildCount() > 0 && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop());
            } else {
                return view.getScrollY() > 0;
            }
        } else if (view instanceof FrameLayout) {
            View rv  = view.findViewById(R.id.recyclerView);
            if(rv == null){
                return view.canScrollVertically(-1);
            }
            RecyclerView recyclerView = (RecyclerView) rv;
            return recyclerView.canScrollVertically(-1);
        } else {
            return view.canScrollVertically(-1);
        }
    }

    /**
     * Default implement for check can perform pull to refresh
     *
     * @param frame
     * @param content
     * @param header
     * @return
     */
    public static boolean checkContentCanBePulledDown(PtrFrameLayout frame, View content, View header) {
        return !canChildScrollUp(content);
    }

    @Override public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return checkContentCanBePulledDown(frame, content, header);
    }


}
