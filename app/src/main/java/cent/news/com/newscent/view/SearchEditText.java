package cent.news.com.newscent.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import cent.news.com.newscent.R;

public class SearchEditText extends AppCompatEditText implements View.OnTouchListener, View.OnFocusChangeListener, TextWatcher {

    private Drawable				mClearTextIcon;

    private OnFocusChangeListener	mOnFocusChangeListener;

    private OnTouchListener			mOnTouchListener;

    private View					line;

    private Drawable                leftIcon;

    public SearchEditText(final Context context) {
        super(context);
        init(context);
    }

    public SearchEditText(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SearchEditText);
        Drawable drawable = typedArray.getDrawable(R.styleable.SearchEditText_edit_clean_icon);
        Drawable leftdrawable = typedArray.getDrawable(R.styleable.SearchEditText_edit_left_icon);

        if (drawable != null) {
            mClearTextIcon = DrawableCompat.wrap(drawable);
        } else {
            drawable = ContextCompat.getDrawable(context, R.mipmap.ic_x_back);
            Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
            mClearTextIcon = wrappedDrawable;
        }
        if (leftdrawable != null) {
            leftIcon = DrawableCompat.wrap(leftdrawable);
            leftIcon.setBounds(0,0,leftIcon.getIntrinsicHeight(), leftIcon.getIntrinsicHeight());
        }
        typedArray.recycle();
        init(context);
    }

    public SearchEditText(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SearchEditText);
        Drawable drawable = typedArray.getDrawable(R.styleable.SearchEditText_edit_clean_icon);
        Drawable leftdrawable = typedArray.getDrawable(R.styleable.SearchEditText_edit_left_icon);

        if (drawable != null) {
            mClearTextIcon = DrawableCompat.wrap(drawable);
        } else {
            drawable = ContextCompat.getDrawable(context, R.mipmap.ic_x_back);
            Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
            mClearTextIcon = wrappedDrawable;
        }
        if (leftdrawable != null) {
            leftIcon = DrawableCompat.wrap(leftdrawable);
            leftIcon.setBounds(0, 0, mClearTextIcon.getIntrinsicHeight(), mClearTextIcon.getIntrinsicHeight());
        }
        typedArray.recycle();
        init(context);
    }

    private void init(final Context context) {
//		final Drawable drawable = ContextCompat.getDrawable(context, R.mipmap.ic_x_back);
//		final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
//		mClearTextIcon = wrappedDrawable;
        mClearTextIcon.setBounds(0, 0, mClearTextIcon.getIntrinsicHeight(), mClearTextIcon.getIntrinsicHeight());
        setClearIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    @Override public void setOnFocusChangeListener(OnFocusChangeListener l) {
        mOnFocusChangeListener = l;
    }

    @Override public void setOnTouchListener(OnTouchListener l) {
        mOnTouchListener = l;
    }

    @Override public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
            if (line != null) {
                line.setSelected(true);
            }
        } else {
            if (line != null) {
                line.setSelected(false);
            }
            setClearIconVisible(false);
        }
        if (mOnFocusChangeListener != null) {
            mOnFocusChangeListener.onFocusChange(v, hasFocus);
        }
    }

    @Override public boolean onTouch(View view, MotionEvent motionEvent) {
        final int x = (int) motionEvent.getX();
        if (mClearTextIcon.isVisible() && x > getWidth() - getPaddingRight() - mClearTextIcon.getIntrinsicWidth()) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                setError(null);
                setText("");
            }
            return true;
        }
        if (leftIcon != null && leftIcon.isVisible() && x < getPaddingLeft() + leftIcon.getIntrinsicWidth()) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//				Toast.makeText(getContext(), "搜索", Toast.LENGTH_SHORT).show();
                String content = getText().toString().trim();

                if (editLeftListener != null) {
                    editLeftListener.leftCallClik(content);
                    setEnabled(false);

                }
                return true;

            }
        }
        return mOnTouchListener != null && mOnTouchListener.onTouch(view, motionEvent);
    }

    @Override public final void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (isFocused()) {
            setClearIconVisible(text.length() > 0);
        }
    }

    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override public void afterTextChanged(Editable s) {

    }

    private void setClearIconVisible(final boolean visible) {
        mClearTextIcon.setVisible(visible, false);
        final Drawable[] compoundDrawables = getCompoundDrawables();
        setCompoundDrawables(leftIcon, compoundDrawables[1], visible ? mClearTextIcon : null, compoundDrawables[3]);
        setCompoundDrawablePadding(dp2px(getContext(), 6));
    }

    public static int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

    public void setLine(View line) {
        this.line = line;
    }

    public interface EditLeftListener {
        void leftCallClik(String content);

    }

    private EditLeftListener editLeftListener;

    public void setEditLeftListener(EditLeftListener editLeftListener) {
        this.editLeftListener = editLeftListener;
    }


}
