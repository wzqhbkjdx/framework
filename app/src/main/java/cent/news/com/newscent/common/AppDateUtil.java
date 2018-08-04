package cent.news.com.newscent.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppDateUtil {

    public static String DateCompare(long pubTime, long now) {
        String data = "";
        try {
            //设定时间的模板
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //得到指定模范的时间
            Date d1 = sdf.parse(sdf.format(new Date(pubTime)));
            Date d2 = sdf.parse(sdf.format(new Date(now)));
            long diff = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别
            //long days = diff / (1000 * 60 * 60 * 24);
            //long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
            //long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
            if (((int) (diff / (1000 * 60 * 60 * 24))) >= 1) {
                long days = diff / (1000 * 60 * 60 * 24);
                data = days + "天前";
            } else if (((int) ((diff - (diff / (1000 * 60 * 60 * 24)) * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60))) >= 1) {
                long hours = ((diff - (diff / (1000 * 60 * 60 * 24)) * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                data = hours + "小時前";
            } else if (((int) (diff - (diff / (1000 * 60 * 60 * 24)) * (1000 * 60 * 60 * 24) -
                    ((diff - (diff / (1000 * 60 * 60 * 24)) * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)) * (1000 * 60 * 60)) / (1000 * 60)) < 1) {
                data = "刚刚";
            } else if (((int) (diff - (diff / (1000 * 60 * 60 * 24)) * (1000 * 60 * 60 * 24) -
                    ((diff - (diff / (1000 * 60 * 60 * 24)) * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)) * (1000 * 60 * 60)) / (1000 * 60)) >= 1) {
                long minutes = (diff - (diff / (1000 * 60 * 60 * 24)) * (1000 * 60 * 60 * 24) -
                        ((diff - (diff / (1000 * 60 * 60 * 24)) * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)) * (1000 * 60 * 60)) / (1000 * 60);
                data = minutes + "分钟前";
            }

            return data;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static long changeDateStr2TimeMills(String date) {
        long timeMills = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            timeMills = sdf.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeMills;
    }

}
