package cent.news.com.newscent.webview;

import android.os.Bundle;

public abstract class ActionService<T> {

    public abstract void execute(T param, CallBackJs callBackJs);

    public abstract T fromJson(String params);


    /**
     * 登录
     *
     * @param param
     *            参数
     */
    protected void login(WebBaseModel param,String tag) {
        //BaseActivityTransporter activityTransporter = new BaseActivityTransporter(LoginAction.class);
        Bundle bundle = new Bundle();
        bundle.putString(WebViewManage.KEY_TAG, tag);
        bundle.putString(WebViewManage.BACK_URL, param.backurl);
        bundle.putString(WebViewManage.CALL_BACK, param.callback);
        //activityTransporter.setBundle(bundle);
        //NCHelper.screenHelper().setNextStep(activityTransporter);
        //LoginDialogFragment.getInstance().show(HNAHelper.screenHelper().getCurrentActivity().getSupportFragmentManager());
        //UserLoginActivity.intent(); todo
    }

}
