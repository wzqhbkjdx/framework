package cent.news.com.newscent.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.ArrowKeyMovementMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import cent.news.com.newscent.R;
import cent.news.com.newscent.helper.NCHelper;

public class TagGroup extends ViewGroup {

    public final int				default_border_color				= Color.rgb(0x49, 0xC1, 0x20);

    public final int				default_text_color					= Color.rgb(0x49, 0xC1, 0x20);

    public final int				default_background_color			= Color.WHITE;

    public final int				default_dash_border_color			= Color.rgb(0xAA, 0xAA, 0xAA);

    public final int				default_input_hint_color			= Color.argb(0x80, 0x00, 0x00, 0x00);

    public final int				default_input_text_color			= Color.argb(0xDE, 0x00, 0x00, 0x00);

    public final int				default_checked_border_color		= Color.rgb(0x49, 0xC1, 0x20);

    public final int				default_checked_text_color			= Color.WHITE;

    public final int				default_checked_marker_color		= Color.WHITE;

    public final int				default_checked_background_color	= Color.rgb(0x49, 0xC1, 0x20);

    public final int				default_pressed_background_color	= Color.rgb(0xED, 0xED, 0xED);

    public final float				default_border_stroke_width;

    public final float				default_text_size;

    public final float				default_horizontal_spacing;

    public final float				default_vertical_spacing;

    public final float				default_horizontal_padding;

    public final float				default_vertical_padding;

    /**
     * Indicates whether this TagGroup is set up to APPEND mode or DISPLAY mode.
     * Default is false.
     */
    public boolean					isAppendMode;

    /** The text to be displayed when the text of the INPUT tag is empty. */
    public CharSequence				inputHint;

    /** The tag outline border color. */
    public int						borderColor;

    /** The tag text color. */
    public int						textColor;

    /** The tag background color. */
    public int						backgroundColor;

    /** The dash outline border color. */
    public int						dashBorderColor;

    /** The input tag hint text color. */
    public int						inputHintColor;

    /** The input tag type text color. */
    public int						inputTextColor;

    /** The checked tag outline border color. */
    public int						checkedBorderColor;

    /** The check text color */
    public int						checkedTextColor;

    /** The checked marker color. */
    public int						checkedMarkerColor;

    /** The checked tag background color. */
    public int						checkedBackgroundColor;

    /** The tag background color, when the tag is being pressed. */
    public int						pressedBackgroundColor;

    /** The tag outline border stroke width, default is 0.5dp. */
    public float					borderStrokeWidth;

    /** The tag text size, default is 13sp. */
    public float					textSize;

    /** The horizontal tag spacing, default is 8.0dp. */
    public int						horizontalSpacing;

    /** The vertical tag spacing, default is 4.0dp. */
    public int						verticalSpacing;

    /** The horizontal tag padding, default is 12.0dp. */
    public int						horizontalPadding;

    /** The vertical tag padding, default is 3.0dp. */
    public int						verticalPadding;

    /** Listener used to dispatch tag change event. */
    public OnTagChangeListener		mOnTagChangeListener;

    /** Listener used to dispatch tag click event. */
    public OnTagClickListener		mOnTagClickListener;

    /** Listener used to handle tag click event. */
    public InternalTagClickListener	mInternalTagClickListener			= new InternalTagClickListener();

    public TagGroup(Context context) {
        this(context, null);
    }

    public TagGroup(Context context, AttributeSet attrs) {
        this(context, attrs, me.gujun.android.taggroup.R.attr.tagGroupStyle);
    }

    public TagGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        default_border_stroke_width = dp2px(0.5f);
        default_text_size = sp2px(13.0f);
        default_horizontal_spacing = dp2px(8.0f);
        default_vertical_spacing = dp2px(4.0f);
        default_horizontal_padding = dp2px(12.0f);
        default_vertical_padding = dp2px(3.0f);

        // Load styled attributes.
        final TypedArray a = context.obtainStyledAttributes(attrs, me.gujun.android.taggroup.R.styleable.TagGroup, defStyleAttr, me.gujun.android.taggroup.R.style.TagGroup);
        try {
            isAppendMode = a.getBoolean(me.gujun.android.taggroup.R.styleable.TagGroup_atg_isAppendMode, false);
            inputHint = a.getText(me.gujun.android.taggroup.R.styleable.TagGroup_atg_inputHint);
            borderColor = a.getColor(me.gujun.android.taggroup.R.styleable.TagGroup_atg_borderColor, default_border_color);
            textColor = a.getColor(me.gujun.android.taggroup.R.styleable.TagGroup_atg_textColor, default_text_color);
            backgroundColor = a.getColor(me.gujun.android.taggroup.R.styleable.TagGroup_atg_backgroundColor, default_background_color);
            dashBorderColor = a.getColor(me.gujun.android.taggroup.R.styleable.TagGroup_atg_dashBorderColor, default_dash_border_color);
            inputHintColor = a.getColor(me.gujun.android.taggroup.R.styleable.TagGroup_atg_inputHintColor, default_input_hint_color);
            inputTextColor = a.getColor(me.gujun.android.taggroup.R.styleable.TagGroup_atg_inputTextColor, default_input_text_color);
            checkedBorderColor = a.getColor(me.gujun.android.taggroup.R.styleable.TagGroup_atg_checkedBorderColor, default_checked_border_color);
            checkedTextColor = a.getColor(me.gujun.android.taggroup.R.styleable.TagGroup_atg_checkedTextColor, default_checked_text_color);
            checkedMarkerColor = a.getColor(me.gujun.android.taggroup.R.styleable.TagGroup_atg_checkedMarkerColor, default_checked_marker_color);
            checkedBackgroundColor = a.getColor(me.gujun.android.taggroup.R.styleable.TagGroup_atg_checkedBackgroundColor, default_checked_background_color);
            pressedBackgroundColor = a.getColor(me.gujun.android.taggroup.R.styleable.TagGroup_atg_pressedBackgroundColor, default_pressed_background_color);
            borderStrokeWidth = a.getDimension(me.gujun.android.taggroup.R.styleable.TagGroup_atg_borderStrokeWidth, default_border_stroke_width);
            textSize = a.getDimension(me.gujun.android.taggroup.R.styleable.TagGroup_atg_textSize, default_text_size);
            horizontalSpacing = (int) a.getDimension(me.gujun.android.taggroup.R.styleable.TagGroup_atg_horizontalSpacing, default_horizontal_spacing);
            verticalSpacing = (int) a.getDimension(me.gujun.android.taggroup.R.styleable.TagGroup_atg_verticalSpacing, default_vertical_spacing);
            horizontalPadding = (int) a.getDimension(me.gujun.android.taggroup.R.styleable.TagGroup_atg_horizontalPadding, default_horizontal_padding);
            verticalPadding = (int) a.getDimension(me.gujun.android.taggroup.R.styleable.TagGroup_atg_verticalPadding, default_vertical_padding);
        } finally {
            a.recycle();
        }

        if (isAppendMode) {
            // Append the initial INPUT tag.
            appendInputTag();

            // Set the click listener to detect the end-input event.
            setOnClickListener(new OnClickListener() {

                @Override public void onClick(View v) {
                    submitTag();
                }
            });
        }
    }

    /**
     * Call this to submit the INPUT tag.
     */
    public void submitTag() {
        final TagView inputTag = getInputTag();
        String currentText = inputTag.getText().toString();
        String currentTextTemp = currentText.replace(" ", "");
        if (StringUtils.isBlank(currentTextTemp)) {
            return;
        }
        if (currentText.length() < 19 && currentText.length() > 0) {
            String b = "";
            for (int i = 0; i < getTags().length; i++) {
                b += getTags()[i];
            }
            if (b.contains(currentText) && StringUtils.isNotBlank(currentText)) {
                // J2WHelper.toast().show("不能有重复标签");
            } else {
                if (inputTag != null && inputTag.isInputAvailable()) {
                    inputTag.endInput();

                    if (mOnTagChangeListener != null) {
                        mOnTagChangeListener.onAppend(TagGroup.this, inputTag.getText().toString());
                    }
                    appendInputTag();
                }
            }
        } else {
            // J2WHelper.toast().show("标签字数不能超过18个字");
        }
    }

    public void setMaxLine(int maxLine) {
        this.maxLine = maxLine - 1;
    }

    private int maxLine = -1;

    @Override public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int width = 0;
        int height = 0;

        int row = 0; // The row counter.
        int rowWidth = 0; // Calc the current row width.
        int rowMaxHeight = 0; // Calc the max tag height, in current row.

        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            final int childWidth = child.getMeasuredWidth();
            final int childHeight = child.getMeasuredHeight();

            if (child.getVisibility() != GONE) {
                rowWidth += childWidth;
                // 判断剩下的空间是否小于当前输入框

                if (rowWidth > widthSize) { // Next line.
                    if (row >= maxLine && maxLine != -1) {
                        break;
                    }

                    rowWidth = childWidth; // The next row width.
                    height += rowMaxHeight + verticalSpacing;
                    rowMaxHeight = childHeight; // The next row max height.
                    row++;
                } else { // This line.
                    rowMaxHeight = Math.max(rowMaxHeight, childHeight);
                }
                rowWidth += horizontalSpacing;
            }
        }
        // Account for the last row height.
        height += rowMaxHeight;

        // Account for the padding too.
        height += getPaddingTop() + getPaddingBottom();

        // If the tags grouped in one row, set the width to wrap the tags.
        if (row == 0) {
            width = rowWidth;
            width += getPaddingLeft() + getPaddingRight();
        } else {// If the tags grouped exceed one line, set the width to match
            // the parent.
            width = widthSize;
        }

        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width, heightMode == MeasureSpec.EXACTLY ? heightSize : height);
    }

    @Override public void onLayout(boolean changed, int l, int t, int r, int b) {
        final int parentLeft = getPaddingLeft();
        final int parentRight = r - l - getPaddingRight();
        final int parentTop = getPaddingTop();
        final int parentBottom = b - t - getPaddingBottom();

        int childLeft = parentLeft;
        int childTop = parentTop;

        int rowMaxHeight = 0;

        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            final int width = child.getMeasuredWidth();
            final int height = child.getMeasuredHeight();

            if (child.getVisibility() != GONE) {
                if (childLeft + width > parentRight) { // Next line
                    childLeft = parentLeft;
                    childTop += rowMaxHeight + verticalSpacing;
                    rowMaxHeight = height;
                } else {
                    rowMaxHeight = Math.max(rowMaxHeight, height);
                }
                child.layout(childLeft, childTop, childLeft + width, childTop + height);

                childLeft += width + horizontalSpacing;
            }
        }
        requestLayout();
    }

    @Override public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.tags = getTags();
        ss.checkedPosition = getCheckedTagIndex();
        if (getInputTag() != null) {
            ss.input = getInputTag().getText().toString();
        }
        return ss;
    }

    @Override public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        setTags(ss.tags);
        TagView checkedTagView = getTagAt(ss.checkedPosition);
        if (checkedTagView != null) {
            checkedTagView.setChecked(true);
        }
        if (getInputTag() != null) {
            getInputTag().setText(ss.input);
        }
    }

    /**
     * Returns the INPUT tag view in this group.
     *
     * @return the INPUT state tag view or null if not exists
     */
    public TagView getInputTag() {
        if (isAppendMode) {
            final int inputTagIndex = getChildCount() - 1;
            final TagView inputTag = getTagAt(inputTagIndex);
            if (inputTag != null && inputTag.mState == TagView.STATE_INPUT) {
                return inputTag;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Returns the INPUT state tag in this group.
     *
     * @return the INPUT state tag view or null if not exists
     */
    public String getInputTagText() {
        final TagView inputTagView = getInputTag();
        if (inputTagView != null) {
            return inputTagView.getText().toString();
        }
        return null;
    }

    /**
     * Return the last NORMAL state tag view in this group.
     *
     * @return the last NORMAL state tag view or null if not exists
     */
    public TagView getLastNormalTagView() {
        final int lastNormalTagIndex = isAppendMode ? getChildCount() - 2 : getChildCount() - 1;
        TagView lastNormalTagView = getTagAt(lastNormalTagIndex);
        return lastNormalTagView;
    }

    /**
     * Returns the tag array in group, except the INPUT tag.
     *
     * @return the tag array.
     */
    public String[] getTags() {
        final int count = getChildCount();
        final List<String> tagList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            final TagView tagView = getTagAt(i);
            if (tagView.mState == TagView.STATE_NORMAL) {
                tagList.add(tagView.getText().toString());
            }
        }

        return tagList.toArray(new String[tagList.size()]);
    }

    /**
     * @see #setTags(String...)
     */
    public void setTags(List<String> tagList) {
        setTags(tagList.toArray(new String[tagList.size()]));
    }

    /**
     * @see #setTags(String...)
     */
    public void setBeanTags(List<CommonTagModel> tagList) {
        removeAllViews();
        for (CommonTagModel tag : tagList) {
            appendBeanTag(tag);
        }
    }

    public void appendBeanTag(CommonTagModel tag) {
        final TagView newTag = new TagView(getContext(), TagView.STATE_NORMAL, tag);
        newTag.setOnClickListener(mInternalTagClickListener);
        addView(newTag);
    }

    /**
     * Set the tags. It will remove all previous tags first.
     *
     * @param tags
     *            the tag list to set.
     */
    public void setTags(String... tags) {
        removeAllViews();
        for (final String tag : tags) {
            appendTag(tag);
        }

        if (isAppendMode) {
            appendInputTag();
        }
    }

    /**
     * Returns the tag view at the specified position in the group.
     *
     * @param index
     *            the position at which to get the tag view from.
     * @return the tag view at the specified position or null if the position
     *         does not exists within this group.
     */
    public TagView getTagAt(int index) {
        return (TagView) getChildAt(index);
    }

    /**
     * Returns the checked tag view in the group.
     *
     * @return the checked tag view or null if not exists.
     */
    public TagView getCheckedTag() {
        final int checkedTagIndex = getCheckedTagIndex();
        if (checkedTagIndex != -1) {
            return getTagAt(checkedTagIndex);
        }
        return null;
    }

    /**
     * Return the checked tag index.
     *
     * @return the checked tag index, or -1 if not exists.
     */
    public int getCheckedTagIndex() {
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final TagView tag = getTagAt(i);
            if (tag.isChecked) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Register a callback to be invoked when this tag group is changed.
     *
     * @param l
     *            the callback that will run
     */
    public void setOnTagChangeListener(OnTagChangeListener l) {
        mOnTagChangeListener = l;
    }

    /**
     * @see #appendInputTag(String)
     */
    public void appendInputTag() {
        appendInputTag(null);
    }

    /**
     * Append a INPUT tag to this group. It will throw an exception if there has
     * a previous INPUT tag.
     *
     * @param tag
     *            the tag text.
     */
    public void appendInputTag(String tag) {
        final TagView previousInputTag = getInputTag();
        if (previousInputTag != null) {
            throw new IllegalStateException("Already has a INPUT tag in group.");
        }

        final TagView newInputTag = new TagView(getContext(), TagView.STATE_INPUT, tag);
        newInputTag.setOnClickListener(mInternalTagClickListener);
        addView(newInputTag);
    }

    /**
     * Append tag to this group.
     *
     * @param tag
     *            the tag to append.
     */
    public void appendTag(CharSequence tag) {
        final TagView newTag = new TagView(getContext(), TagView.STATE_NORMAL, tag);
        newTag.setOnClickListener(mInternalTagClickListener);
        addView(newTag);
    }

    public float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    public float sp2px(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    @Override public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    /**
     * Register a callback to be invoked when a tag is clicked.
     *
     * @param l
     *            the callback that will run.
     */
    public void setOnTagClickListener(OnTagClickListener l) {
        mOnTagClickListener = l;
    }

    public void deleteTag(TagView tagView) {
        removeView(tagView);
        if (mOnTagChangeListener != null) {
            mOnTagChangeListener.onDelete(TagGroup.this, tagView.getText().toString());
        }
    }

    /**
     * Interface definition for a callback to be invoked when a tag group is
     * changed.
     */
    public interface OnTagChangeListener {

        /**
         * Called when a tag has been appended to the group.
         *
         * @param tag
         *            the appended tag.
         */
        void onAppend(TagGroup tagGroup, String tag);

        /**
         * Called when a tag has been deleted from the the group.
         *
         * @param tag
         *            the deleted tag.
         */
        void onDelete(TagGroup tagGroup, String tag);
    }

    /**
     * Interface definition for a callback to be invoked when a tag is clicked.
     */
    public interface OnTagClickListener {

        /**
         * Called when a tag has been clicked.
         *
         * @param tag
         *            The tag text of the tag that was clicked.
         */
        void onTagClick(final TagView tag);
    }

    /**
     * Per-child layout information for layouts.
     */
    public static class LayoutParams extends ViewGroup.LayoutParams {

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }
    }

    /**
     * For {@link TagGroup} save and restore state.
     */
    static class SavedState extends BaseSavedState {

        public static final Creator<SavedState>	CREATOR	= new Creator<SavedState>() {

            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };

        int										tagCount;

        String[]								tags;

        int										checkedPosition;

        String									input;

        public SavedState(Parcel source) {
            super(source);
            tagCount = source.readInt();
            tags = new String[tagCount];
            source.readStringArray(tags);
            checkedPosition = source.readInt();
            input = source.readString();
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        @Override public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            tagCount = tags.length;
            dest.writeInt(tagCount);
            dest.writeStringArray(tags);
            dest.writeInt(checkedPosition);
            dest.writeString(input);
        }
    }

    /**
     * The tag view click listener for internal use.
     */
    class InternalTagClickListener implements OnClickListener {

        @Override public void onClick(View v) {
            final TagView tag = (TagView) v;
            if (isAppendMode) {
                if (tag.mState == TagView.STATE_INPUT) {
                    // If the clicked tag is in INPUT state, uncheck the
                    // previous checked tag if exists.
                    final TagView checkedTag = getCheckedTag();
                    if (checkedTag != null) {
                        checkedTag.setChecked(false);
                    }
                } else {
                    // If the clicked tag is currently checked, delete the tag.
                    if (tag.isChecked) {
                        deleteTag(tag);
                    } else {
                        // If the clicked tag is unchecked, uncheck the previous
                        // checked tag if exists,
                        // then check the clicked tag.
                        final TagView checkedTag = getCheckedTag();
                        if (checkedTag != null) {
                            checkedTag.setChecked(false);
                        }
                        tag.setChecked(true);
                    }
                }
            } else {
                if (mOnTagClickListener != null) {
                    mOnTagClickListener.onTagClick(tag);
                }
            }
        }
    }

    /**
     * The tag view which has two states can be either NORMAL or INPUT.
     */
    public class TagView extends TextView {

        public static final int	STATE_NORMAL				= 1;

        public static final int	STATE_INPUT					= 2;

        /** The offset to the text. */
        public static final int	CHECKED_MARKER_OFFSET		= 3;

        /** The stroke width of the checked marker */
        public static final int	CHECKED_MARKER_STROKE_WIDTH	= 4;

        /** The current state. */
        public int				mState;

        /** Indicates the tag if checked. */
        public boolean			isChecked					= false;

        /** Indicates the tag if pressed. */
        public boolean			isPressed					= false;

        public Paint mBorderPaint				= new Paint(Paint.ANTI_ALIAS_FLAG);

        public Paint			mBackgroundPaint			= new Paint(Paint.ANTI_ALIAS_FLAG);

        public Paint			mCheckedMarkerPaint			= new Paint(Paint.ANTI_ALIAS_FLAG);

        /** The rect for the tag's left corner drawing. */
        public RectF mLeftCornerRectF			= new RectF();

        /** The rect for the tag's right corner drawing. */
        public RectF			mRightCornerRectF			= new RectF();

        /** The rect for the tag's horizontal blank fill area. */
        public RectF			mHorizontalBlankFillRectF	= new RectF();

        /** The rect for the tag's vertical blank fill area. */
        public RectF			mVerticalBlankFillRectF		= new RectF();

        /** The rect for the checked mark draw bound. */
        public RectF			mCheckedMarkerBound			= new RectF();

        /** Used to detect the touch event. */
        public Rect mOutRect					= new Rect();

        /** The path for draw the tag's outline border. */
        public Path mBorderPath					= new Path();

        /** The path effect provide draw the dash border. */
        public PathEffect mPathEffect					= new DashPathEffect(new float[] { 10, 5 }, 0);

        {
            mBorderPaint.setStyle(Paint.Style.STROKE);
            mBorderPaint.setStrokeWidth(borderStrokeWidth);
            mBackgroundPaint.setStyle(Paint.Style.FILL);
            mCheckedMarkerPaint.setStyle(Paint.Style.FILL);
            mCheckedMarkerPaint.setStrokeWidth(CHECKED_MARKER_STROKE_WIDTH);
            mCheckedMarkerPaint.setColor(checkedMarkerColor);
        }

        public long tagId;
        public String cityPath;

        public TagView(final Context context, final int state, CommonTagModel tag) {
            super(context);
            setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
            setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

            setGravity(Gravity.CENTER);
            setText(tag.labelName); // 展示标签名字
            setTagId(tag.labelId); // 保存标签对应的ID
            setTagSelected(tag.isSelected); // 标签是否选中
            setPath(tag.path);       //保存cityPath
            setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

            mState = state;

            setClickable(isAppendMode);
            setFocusable(state == STATE_INPUT);
            setFocusableInTouchMode(state == STATE_INPUT);
            setHint(state == STATE_INPUT ? inputHint : null);
            setMovementMethod(state == STATE_INPUT ? ArrowKeyMovementMethod.getInstance() : null);

            // Interrupted long click event to avoid PAUSE popup.
            setOnLongClickListener(new OnLongClickListener() {

                @Override public boolean onLongClick(View v) {
                    return state != STATE_INPUT;
                }
            });

            if (state == STATE_INPUT) {
                requestFocus();

                // Handle the ENTER key down.
                setOnEditorActionListener(new OnEditorActionListener() {

                    @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_NULL && (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                            if (isInputAvailable()) {
                                // If the input content is available, end the
                                // input and dispatch
                                // the event, then append a new INPUT state tag.
                                String currentText = (getText().toString()).replace(" ", "");
                                if (currentText.length() < 19 && currentText.length() > 0) {
                                    endInput();
                                    if (mOnTagChangeListener != null) {
                                        mOnTagChangeListener.onAppend(TagGroup.this, getText().toString());
                                    }
                                    appendInputTag();
                                } else if (currentText.length() == 0) {

                                } else {
                                    // J2WHelper.toast().show("标签字数不能超过18个字");
                                }
                            }
                            return true;
                        }
                        return false;
                    }
                });

                // Handle the BACKSPACE key down.
                setOnKeyListener(new OnKeyListener() {

                    @Override public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                            // If the input content is empty, check or remove
                            // the last NORMAL state tag.
                            if (TextUtils.isEmpty(getText().toString())) {
                                TagView lastNormalTagView = getLastNormalTagView();
                                if (lastNormalTagView != null) {
                                    if (lastNormalTagView.isChecked) {
                                        removeView(lastNormalTagView);
                                        if (mOnTagChangeListener != null) {
                                            mOnTagChangeListener.onDelete(TagGroup.this, lastNormalTagView.getText().toString());
                                        }
                                    } else {
                                        final TagView checkedTagView = getCheckedTag();
                                        if (checkedTagView != null) {
                                            checkedTagView.setChecked(false);
                                        }
                                        lastNormalTagView.setChecked(true);
                                    }
                                    return true;
                                }
                            }
                        }
                        return false;
                    }
                });

                // Handle the INPUT tag content changed.
                addTextChangedListener(new TextWatcher() {

                    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        // When the INPUT state tag changed, uncheck the checked
                        // tag if exists.
                        final TagView checkedTagView = getCheckedTag();
                        if (checkedTagView != null) {
                            checkedTagView.setChecked(false);
                        }
                    }

                    @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override public void afterTextChanged(Editable s) {}
                });
            }

            invalidatePaint();
        }

        public TagView(final Context context, final int state, CharSequence text) {
            super(context);
            setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
            setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

            setGravity(Gravity.CENTER);
            setText(text); // 展示标签名字
            setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

            mState = state;

            setClickable(isAppendMode);
            setFocusable(state == STATE_INPUT);
            setFocusableInTouchMode(state == STATE_INPUT);
            setHint(state == STATE_INPUT ? inputHint : null);
            setMovementMethod(state == STATE_INPUT ? ArrowKeyMovementMethod.getInstance() : null);

            // Interrupted long click event to avoid PAUSE popup.
            setOnLongClickListener(new OnLongClickListener() {

                @Override public boolean onLongClick(View v) {
                    return state != STATE_INPUT;
                }
            });

            if (state == STATE_INPUT) {
                requestFocus();

                // Handle the ENTER key down.
                setOnEditorActionListener(new OnEditorActionListener() {

                    @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_NULL && (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                            if (isInputAvailable()) {
                                // If the input content is available, end the
                                // input and dispatch
                                // the event, then append a new INPUT state tag.
                                String currentText = (getText().toString()).replace(" ", "");
                                if (currentText.length() < 19 && currentText.length() > 0) {
                                    endInput();
                                    if (mOnTagChangeListener != null) {
                                        mOnTagChangeListener.onAppend(TagGroup.this, getText().toString());
                                    }
                                    appendInputTag();
                                } else if (currentText.length() == 0) {

                                } else {
                                    // J2WHelper.toast().show("标签字数不能超过18个字");
                                }
                            }
                            return true;
                        }
                        return false;
                    }
                });

                // Handle the BACKSPACE key down.
                setOnKeyListener(new OnKeyListener() {

                    @Override public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                            // If the input content is empty, check or remove
                            // the last NORMAL state tag.
                            if (TextUtils.isEmpty(getText().toString())) {
                                TagView lastNormalTagView = getLastNormalTagView();
                                if (lastNormalTagView != null) {
                                    if (lastNormalTagView.isChecked) {
                                        removeView(lastNormalTagView);
                                        if (mOnTagChangeListener != null) {
                                            mOnTagChangeListener.onDelete(TagGroup.this, lastNormalTagView.getText().toString());
                                        }
                                    } else {
                                        final TagView checkedTagView = getCheckedTag();
                                        if (checkedTagView != null) {
                                            checkedTagView.setChecked(false);
                                        }
                                        lastNormalTagView.setChecked(true);
                                    }
                                    return true;
                                }
                            }
                        }
                        return false;
                    }
                });

                // Handle the INPUT tag content changed.
                addTextChangedListener(new TextWatcher() {

                    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        // When the INPUT state tag changed, uncheck the checked
                        // tag if exists.
                        final TagView checkedTagView = getCheckedTag();
                        if (checkedTagView != null) {
                            checkedTagView.setChecked(false);
                        }
                    }

                    @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override public void afterTextChanged(Editable s) {}
                });
            }

            invalidatePaint();
        }

        public void setTagId(long id) {
            tagId = id;
        }

        public void setPath(String mCityPath){
            cityPath = mCityPath;
        }

        public void setTagSelected(int isSelected) {
            this.isChecked = isSelected == 1;
        }

        public long getTagId() {
            return tagId;
        }

        public String getPath() {
            return cityPath;
        }

        /**
         * Set whether this tag view is in the checked state.
         *
         * @param checked
         *            true is checked, false otherwise
         */
        public void setChecked(boolean checked) {
            isChecked = checked;
            // Make the checked mark drawing region.
            // setPadding(horizontalPadding, verticalPadding, isChecked ? (int)
            // (horizontalPadding + getHeight() / 2.5f + CHECKED_MARKER_OFFSET)
            // : horizontalPadding, verticalPadding);
            invalidatePaint();
        }

        /**
         * Call this method to end this tag's INPUT state.
         */
        public void endInput() {
            // Make the view not focusable.
            setFocusable(false);
            setFocusableInTouchMode(false);
            // Set the hint empty, make the TextView measure correctly.
            setHint(null);
            // Take away the cursor.
            setMovementMethod(null);

            mState = STATE_NORMAL;
            invalidatePaint();
            requestLayout();
        }

        @Override public boolean getDefaultEditable() {
            return true;
        }

        /**
         * Indicates whether the input content is available.
         *
         * @return True if the input content is available, false otherwise.
         */
        public boolean isInputAvailable() {
            return getText() != null && getText().length() > 0;
        }

        public void invalidatePaint() {
            if (isAppendMode) {
                if (mState == STATE_INPUT) {
                    mBorderPaint.setColor(dashBorderColor);
                    mBorderPaint.setPathEffect(mPathEffect);
                    mBackgroundPaint.setColor(backgroundColor);
                    setHintTextColor(inputHintColor);
                    setTextColor(inputTextColor);
                } else {
                    mBorderPaint.setPathEffect(null);
                    if (isChecked) {
                        mBorderPaint.setColor(checkedBorderColor);
                        mBackgroundPaint.setColor(checkedBackgroundColor);
                        setTextColor(checkedTextColor);
                    } else {
                        mBorderPaint.setColor(borderColor);
                        mBackgroundPaint.setColor(backgroundColor);
                        setTextColor(textColor);
                    }
                }
            } else {
                mBorderPaint.setColor(borderColor);
                mBackgroundPaint.setColor(backgroundColor);
                setTextColor(textColor);
            }

            if (isPressed) {
                // mBackgroundPaint.setColor(pressedBackgroundColor);
            }

            if (isChecked) {
              setTextColor(ContextCompat.getColor(NCHelper.getInstance(), R.color.white));
                mBorderPaint.setColor(ContextCompat.getColor(NCHelper.getInstance(), R.color.orange));
                mBackgroundPaint.setColor(ContextCompat.getColor(NCHelper.getInstance(), R.color.orange));
            }
        }

        @Override public void onDraw(Canvas canvas) {
            canvas.drawRoundRect(mLeftCornerRectF, 5, 5, mBackgroundPaint);
            canvas.drawRoundRect(mRightCornerRectF, 5, 5, mBackgroundPaint);
            // canvas.drawArc(mLeftCornerRectF, -180, 90, true,
            // mBackgroundPaint);
            // canvas.drawArc(mLeftCornerRectF, -270, 90, true,
            // mBackgroundPaint);
            // canvas.drawArc(mRightCornerRectF, -90, 90, true,
            // mBackgroundPaint);
            // canvas.drawArc(mRightCornerRectF, 0, 90, true, mBackgroundPaint);
//			canvas.drawRect(mHorizontalBlankFillRectF, mBackgroundPaint);
//			canvas.drawRect(mVerticalBlankFillRectF, mBackgroundPaint);

            // if (isChecked) {
            // canvas.save();
            //// canvas.rotate(45, mCheckedMarkerBound.centerX(),
            // mCheckedMarkerBound.centerY());
            //// canvas.drawLine(mCheckedMarkerBound.left,
            // mCheckedMarkerBound.centerY(), mCheckedMarkerBound.right,
            // mCheckedMarkerBound.centerY(), mCheckedMarkerPaint);
            //// canvas.drawLine(mCheckedMarkerBound.centerX(),
            // mCheckedMarkerBound.top, mCheckedMarkerBound.centerX(),
            // mCheckedMarkerBound.bottom, mCheckedMarkerPaint);
            // canvas.restore();
            // }
            canvas.drawPath(mBorderPath, mBorderPaint);
            super.onDraw(canvas);
        }

        @Override public void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            int left = (int) borderStrokeWidth;
            int top = (int) borderStrokeWidth;
            int right = (int) (left + w - borderStrokeWidth * 2);
            int bottom = (int) (top + h - borderStrokeWidth * 2);

            int d = bottom - top;

            mLeftCornerRectF.set(left, top, 0, 0);
            mRightCornerRectF.set(0, 0, right, top + d);

            mBorderPath.reset();
            mBorderPath.addRoundRect(mLeftCornerRectF, 5, 5, Path.Direction.CW);
            mBorderPath.addRoundRect(mRightCornerRectF, 5, 5, Path.Direction.CW);
            // mBorderPath.addArc(mLeftCornerRectF, -180, 90);
            // mBorderPath.addArc(mLeftCornerRectF, -270, 90);
            // mBorderPath.addArc(mRightCornerRectF, -90, 90);
            // mBorderPath.addArc(mRightCornerRectF, 0, 90);

            int l = (int) (d / 2.0f);
//			mBorderPath.moveTo(left + l, top);
//			mBorderPath.lineTo(right - l, top);
//
//			mBorderPath.moveTo(left + l, bottom);
//			mBorderPath.lineTo(right - l, bottom);

//			mBorderPath.moveTo(left, top + l);
//			mBorderPath.lineTo(left, bottom - l);
//
//			mBorderPath.moveTo(right, top + l);
//			mBorderPath.lineTo(right, bottom - l);

            mHorizontalBlankFillRectF.set(left, top + l, right, bottom - l);
            mVerticalBlankFillRectF.set(left + l, top, right - l, bottom);

            int m = (int) (h / 2.5f);
            h = bottom - top;
            mCheckedMarkerBound.set(right - m - horizontalPadding + CHECKED_MARKER_OFFSET, top + h / 2 - m / 2, right - horizontalPadding + CHECKED_MARKER_OFFSET, bottom - h / 2 + m / 2);

            // Ensure the checked mark drawing region is correct across screen
            // orientation changes.
            if (isChecked) {
                // setPadding(horizontalPadding, verticalPadding, (int)
                // (horizontalPadding + h / 2.5f + CHECKED_MARKER_OFFSET),
                // verticalPadding);
            }
        }

        @Override public boolean onTouchEvent(MotionEvent event) {
            if (mState == STATE_INPUT) {
                // The INPUT tag doesn't change background color on the touch
                // event.
                return super.onTouchEvent(event);
            }

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    getDrawingRect(mOutRect);
                    isPressed = true;
                    invalidatePaint();
                    invalidate();
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    if (!mOutRect.contains((int) event.getX(), (int) event.getY())) {
                        isPressed = false;
                        invalidatePaint();
                        invalidate();
                    }
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    isPressed = false;
                    invalidatePaint();
                    invalidate();
                    break;
                }
            }
            return super.onTouchEvent(event);
        }

        @Override public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
            return new ZanyInputConnection(super.onCreateInputConnection(outAttrs), true);
        }

        /**
         * Solve edit text delete(backspace) key detect, see
         * <a href="http://stackoverflow.com/a/14561345/3790554"> Android:
         * Backspace in WebView/BaseInputConnection</a>
         */
        public class ZanyInputConnection extends InputConnectionWrapper {

            public ZanyInputConnection(InputConnection target, boolean mutable) {
                super(target, mutable);
            }

            @Override public boolean deleteSurroundingText(int beforeLength, int afterLength) {
                // magic: in latest Android, deleteSurroundingText(1, 0) will be
                // called for backspace
                if (beforeLength == 1 && afterLength == 0) {
                    // backspace
                    return sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL)) && sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));
                }
                return super.deleteSurroundingText(beforeLength, afterLength);
            }
        }
    }

}
