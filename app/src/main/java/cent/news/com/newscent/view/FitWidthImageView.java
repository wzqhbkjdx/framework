package cent.news.com.newscent.view;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class FitWidthImageView extends ScaleImageView {

    boolean isTop;

    public FitWidthImageView(Context context) {
        super(context);
        setupCenter();
    }

    public FitWidthImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupCenter();
    }

    public FitWidthImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupCenter();
    }

    public void setupTop() {
        isTop = true;
        setScaleType(ScaleType.MATRIX);
    }

    public void setupCenterCrop() {
        isTop = true;
        setScaleType(ScaleType.CENTER_CROP);
    }

    public void setupCenter() {
        isTop = false;
        setScaleType(ScaleType.FIT_XY);
    }

    @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        recomputeImgMatrix();
    }

    @Override protected boolean setFrame(int l, int t, int r, int b) {
        recomputeImgMatrix();
        return super.setFrame(l, t, r, b);
    }

    private void recomputeImgMatrix() {

        final Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }

        final Matrix matrix = getImageMatrix();

        float scale;
        final int viewWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        final int viewHeight = getHeight() - getPaddingTop() - getPaddingBottom();
        final int drawableWidth = drawable.getIntrinsicWidth();
        final int drawableHeight = drawable.getIntrinsicHeight();

        if (drawableWidth * viewHeight > drawableHeight * viewWidth) {
            scale = (float) viewHeight / (float) drawableHeight;
        } else {
            scale = (float) viewWidth / (float) drawableWidth;
        }

        matrix.setScale(scale, scale);
        setImageMatrix(matrix);
    }

}
