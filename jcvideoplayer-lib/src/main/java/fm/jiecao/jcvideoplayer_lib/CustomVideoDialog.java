package fm.jiecao.jcvideoplayer_lib;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by bym on 2017/8/14.
 */

public class CustomVideoDialog extends Dialog {

    private TextView title;
    private TextView cancel;
    private TextView confirm;
    private Context context;

    private DialogCallback callback;

    public CustomVideoDialog(@NonNull Context context) {
        super(context);
    }

    public CustomVideoDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected CustomVideoDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public CustomVideoDialog(Context context, DialogCallback callback) {
        super(context, R.style.CustomDialog);
        this.context = context;
        this.callback = callback;
        setupDialog();
    }



    private void setupDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.layout_custom_dialog, null);
        title = (TextView) mView.findViewById(R.id.dialog_confirm_title);
        cancel = (TextView) mView.findViewById(R.id.dialog_cancel);
        confirm = (TextView) mView.findViewById(R.id.dialog_confirm_sure);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callback != null) {
                    callback.dialogCancel();
                }
                CustomVideoDialog.this.cancel();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(callback != null) {
                    callback.confirm();
                }
                CustomVideoDialog.this.cancel();
            }
        });

        super.setContentView(mView);
    }

    public void setConfirmText(String confirmText) {
        confirm.setText(confirmText);
    }

    public void setCancelText(String cancelText) {
        cancel.setText(cancelText);
    }


    public CustomVideoDialog setDialogTitle(String title) {
        this.title.setText(title);
        return this;
    }


    public interface DialogCallback {
        void confirm();
        void dialogCancel();
    }


}
