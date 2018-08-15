package cent.news.com.newscent.common;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;

public class ImageLoadUtil {

    public static void displayWithCropPlaceHolder(Context context, String url, final ImageView imageView, int placeHolder) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(placeHolder)
                .priority(Priority.HIGH);

        Glide.with(context).load(url).apply(options).into(imageView);
    }

}
