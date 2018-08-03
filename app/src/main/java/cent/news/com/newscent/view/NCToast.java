package cent.news.com.newscent.view;

import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import cent.news.com.baseframe.modules.toast.BaseCustomToast;
import cent.news.com.newscent.R;

public class NCToast extends BaseCustomToast {
    @Override public int layoutId() {
        return R.layout.toast_layout;
    }

    @Override public void init(View view, String s) {
        TextView textView = ButterKnife.findById(view, R.id.tv_content);
        textView.setText(s);
    }
}
