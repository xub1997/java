package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
* 日期处理工具类
* author：xub
* */
public class DateUtil {


    /**
     * 将DATE转换为String类型 format "yyyy-MM-dd"
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = "";
        if (date != null) {
            s = format.format(date);
        }
        return s;
    }

    /**
     * 将DATE转换为String类型 format 自定义
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToString(Date date,String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String s = "";
        if (date != null) {
            s = format.format(date);
        }
        return s;
    }
}
