package cent.news.com.newscent.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;

import cent.news.com.newscent.R;

public class NCDefaultImageView extends FitWidthImageView {

    float				scale;

    int					isOval;

    float				h_radius;

    float				h_radius_top_left;

    float				h_radius_top_right;

    float				h_radius_bottom_left;

    float				h_radius_bottom_right;

    int					logoSize;


    public NCDefaultImageView(Context context) {
        this(context, null);
    }

    public NCDefaultImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NCDefaultImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.HNADefaultImageView, defStyleAttr, 0);
        scale = a.getFloat(R.styleable.HNADefaultImageView_g_scale, 0);
        logoSize = a.getDimensionPixelOffset(R.styleable.HNADefaultImageView_logoSize, getResources().getDimensionPixelOffset(R.dimen.default_logo_size));
        isOval = a.getInteger(R.styleable.HNADefaultImageView_isOval, 0);
        h_radius = a.getDimension(R.styleable.HNADefaultImageView_h_radius, 0);
        h_radius_top_left = a.getDimension(R.styleable.HNADefaultImageView_h_radius_top_left, 0);
        h_radius_top_right = a.getDimension(R.styleable.HNADefaultImageView_h_radius_top_right, 0);
        h_radius_bottom_left = a.getDimension(R.styleable.HNADefaultImageView_h_radius_bottom_left, 0);
        h_radius_bottom_right = a.getDimension(R.styleable.HNADefaultImageView_h_radius_bottom_right, 0);
        a.recycle();
        //mutateBackground(true);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        if (h_radius != 0) {
            setCornerRadius(h_radius);
        } else {
            setCornerRadius(h_radius_top_left, h_radius_top_right, h_radius_bottom_left, h_radius_bottom_right);
        }

        if (isOval == 1) {
            setBackgroundResource(R.drawable.shape_circle_default);
        } else {
            if(h_radius == 0 && h_radius_top_left == 0 && h_radius_top_right == 0 && h_radius_bottom_left ==0 && h_radius_bottom_right == 0){
                setBackgroundColor(Color.parseColor("#F9FAFC"));
            }else {
                setBackgroundColor(Color.parseColor("#00000000"));
            }
        }

        setScale(scale);
    }

    public void scaleType(ImageView.ScaleType type) {
        setScaleType(type);
    }

    public void load(String url) {
        //String urlTmp = "https://img.zcool.cn/community/01b34f58eee017a8012049efcfaf50.jpg@1280w_1l_2o_100sh.webp";
        //Glide.with(getContext()).load(url).asBitmap().placeholder(R.drawable.home_img_default).centerCrop().into(this);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.home_img_default)
                .priority(Priority.HIGH);

        Glide.with(getContext()).load(url).apply(options).into(this);
    }

    public void loadBackgroundWhite(String url) {
        setBackgroundColor(Color.parseColor("#FFFFFF"));
        //Glide.with(getContext()).load(url).asBitmap().placeholder(R.drawable.home_img_default).centerCrop().into(this);
        Glide.with(getContext()).load(url).into(this);
    }

    public void load(String url, int placeholder, int error) {
        //Glide.with(getContext()).load(url).asBitmap().placeholder(placeholder).error(error).centerCrop().into(this);
        Glide.with(getContext()).load(url).into(this);
    }

    public void loadFix(String url) {
        setScaleType(ScaleType.FIT_XY);
        //Glide.with(getContext()).load(url).asBitmap().placeholder(R.drawable.home_img_default).into(this);
        Glide.with(getContext()).load(url).into(this);
    }

    public ImageView getImageView() {
        return this;
    }

}
