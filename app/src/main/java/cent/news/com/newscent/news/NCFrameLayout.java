package cent.news.com.newscent.news;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import in.srain.cube.views.ptr.PtrFrameLayout;

public class NCFrameLayout extends PtrFrameLayout {

    boolean isTouch;

    public NCFrameLayout(Context context) {
        super(context);
    }

    public NCFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NCFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setTouch(boolean isTouch) {
        this.isTouch = isTouch;
    }

    @Override public boolean dispatchTouchEvent(MotionEvent e) {
        return isTouch ? true : super.dispatchTouchEvent(e);
    }


}
