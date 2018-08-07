package cent.news.com.newscent.common;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;

import cent.news.com.newscent.R;

public class ImageLoadUtil {

    public static void displayWithCropCircle(Context context, String url, final ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.home_img_default)
                .priority(Priority.HIGH);

        Glide.with(context).load(url).apply(options).into(imageView);
    }

}
