package cent.news.com.newscent.webview;

import android.webkit.WebView;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import cent.news.com.newscent.helper.NCHelper;

class ConvertCore implements CallBackJs {


    private WebView webView;

    public ConvertCore(WebView webView) {
        this.webView = webView;
    }

    public void convert(String param) {
        String actoin = null;
        String paramContent = null;
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(param);
            actoin = jsonObject.getString("action");
            paramContent = jsonObject.getString("params");
        } catch (JSONException e) {
            if (NCHelper.isLogOpen()) {
                e.printStackTrace();
            }
        }
        try {
            Class<?> aClass = Class.forName(NCHelper.getInstance().getPackageName() + ".webview.action." + actoin);
            Constructor constructor = aClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            /** 创建类 **/
            ActionService actionParam = (ActionService) constructor.newInstance();
            actionParam.execute(actionParam.fromJson(paramContent), this);
        } catch (ClassNotFoundException e) {
            if (NCHelper.isLogOpen()) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            if (NCHelper.isLogOpen()) {
                e.printStackTrace();
            }
        } catch (InstantiationException e) {
            if (NCHelper.isLogOpen()) {
                e.printStackTrace();
            }
        } catch (IllegalAccessException e) {
            if (NCHelper.isLogOpen()) {
                e.printStackTrace();
            }
        } catch (InvocationTargetException e) {
            if (NCHelper.isLogOpen()) {
                e.printStackTrace();
            }
        }
    }

    @Override public void callback(final String callBack, final String json) {
        webView.post(new Runnable() {

            @Override public void run() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("javascript:");
                stringBuilder.append(callBack);
                stringBuilder.append("('");
                stringBuilder.append(json);
                stringBuilder.append("')");
                webView.loadUrl(stringBuilder.toString());
            }
        });
    }

}
