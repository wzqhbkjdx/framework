package cent.news.com.newscent.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.makeramen.roundedimageview.RoundedImageView;

import cent.news.com.newscent.R;

public class ScaleImageView extends RoundedImageView {

    float scale;

    public ScaleImageView(Context context) {
        super(context);
    }

    public ScaleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ScaleImageView, defStyleAttr, 0);
        scale = a.getFloat(R.styleable.ScaleImageView_scale, 0);
        a.recycle();
    }

    public void setScale(float scale) {
        if (this.scale != scale) {
            this.scale = scale;
            requestLayout();
        }
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (scale > 0.0) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) ((float) width / scale);
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }


}
