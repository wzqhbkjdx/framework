package cent.news.com.newscent.helper.utils;

import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;

import cent.news.com.baseframe.BaseHelper;

public class XLogUtil {

    private static boolean enableLog = true;

    public synchronized static void enableLog() {
        enableLog = true;
    }

    public synchronized static void disableLog() {
        enableLog = false;
    }

    private XLogUtil() {
        if(BaseHelper.isLogOpen()) {
            enableLog();
        } else {
            disableLog();
        }

        if(enableLog) {
            XLog.init(LogLevel.ALL);
        } else {
            XLog.init(LogLevel.NONE);
        }
    }

    public static XLogUtil getInstance() {
        return InnerClass.instance;
    }

    private static class InnerClass {
        public static final XLogUtil instance = new XLogUtil();
    }

    public void d(String tag, String content) {
        if(enableLog) {
            XLog.tag(tag).d(content);
        }
    }

    public void v(String tag, String content) {
        if(enableLog) {
            XLog.tag(tag).v(content);
        }
    }

    public void i(String tag, String content) {
        if(enableLog) {
            XLog.tag(tag).i(content);
        }
    }

    public void w(String tag, String content) {
        if(enableLog) {
            XLog.tag(tag).w(content);
        }
    }

    public void json(String tag, String content) {
        if(enableLog) {
            XLog.tag(tag).json(content);
        }
    }


}
