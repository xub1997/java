package com.xub.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @description: 时间处理工具类
 * @author: 黎清许
 * @create: 2019-11-01 18:09
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class DateUtil {

    private DateUtil() {
    }

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_PATTERN = "HH:mm:ss";
    static DateFormat apacheDf;


    public static String format(long millis, String pattern) {
        return format(new Date(millis), pattern);
    }

    public static String format(Date date, String pattern) {
        DateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static String formartDateTime(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatUTC(Date date, String pattern) {
        DateFormat formatter = new SimpleDateFormat(pattern);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(date);
    }

    public static String formartDate(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    public static String formatDate(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String formatTime(Date date) {
        return format(date, TIME_PATTERN);
    }

    public static String formatDateTime(Date date) {
        return format(date, DATE_TIME_PATTERN);
    }




    public static Date getLastDayInThisMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, calendar.getActualMaximum(5));
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar.getTime();
    }

    public static Date getFirstDayInThisMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, calendar.getActualMinimum(5));
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Date getLastDateInThisWeek() {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, 23);
        now.set(Calendar.MINUTE, 59);
        now.set(Calendar.SECOND, 59);
        int today = now.get(Calendar.DAY_OF_WEEK);
        int firstDayOfWeek = now.get(Calendar.DATE) + 2 - today; // 星期一
        int lastDayOfWeek = firstDayOfWeek + 6; // 星期日
        now.set(now.DATE, lastDayOfWeek);
        return now.getTime();
    }

    public static Date getFirstDateInThisWeek() {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        int today = now.get(Calendar.DAY_OF_WEEK);
        int first_day_of_week = now.get(Calendar.DATE) + 2 - today; // 星期一
        now.set(now.DATE, first_day_of_week);
        return now.getTime();
    }

    public static Date getTodayLastMinutes() {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, 23);
        now.set(Calendar.MINUTE, 59);
        now.set(Calendar.SECOND, 59);
        return now.getTime();
    }

    public static Date getCurrentUtcTime() {
        Calendar cal = Calendar.getInstance();
        int zoneOffset = cal.get(15);
        int dstOffset = cal.get(16);
        cal.add(14, -(zoneOffset + dstOffset));
        return cal.getTime();
    }


    public static Date parse(String dateString, String pattern) {
        if (pattern.contains(":start")) {
            pattern = pattern.replace(":start", "HH:mm:ss");
            dateString = dateString + " 00:00:00";
        }

        if (pattern.contains(":end")) {
            pattern = pattern.replace(":end", "HH:mm:ss");
            dateString = dateString + " 23:59:59";
        }

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);

        try {
            return formatter.parse(dateString);
        } catch (ParseException var4) {
            throw new RuntimeException(var4);
        }
    }

    public static Date parseUTC(String dateString, String pattern) {
        if (pattern.contains(":start")) {
            pattern = pattern.replace(":start", "HH:mm:ss");
            dateString = dateString + " 00:00:00";
        }

        if (pattern.contains(":end")) {
            pattern = pattern.replace(":end", "HH:mm:ss");
            dateString = dateString + " 23:59:59";
        }

        DateFormat formatter = new SimpleDateFormat(pattern);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            return formatter.parse(dateString);
        } catch (ParseException var4) {
            throw new RuntimeException(var4);
        }
    }

    public static Date addYears(Date date, int amount) {
        return add(date, 1, amount);
    }

    public static Date addMonths(Date date, int amount) {
        return add(date, 2, amount);
    }

    public static Date addWeeks(Date date, int amount) {
        return add(date, 3, amount);
    }

    public static Date addDays(Date date, int amount) {
        return add(date, 5, amount);
    }

    public static Date addHours(Date date, int amount) {
        return add(date, 11, amount);
    }

    public static Date addMinutes(Date date, int amount) {
        return add(date, 12, amount);
    }

    public static Date addSeconds(Date date, int amount) {
        return add(date, 13, amount);
    }

    public static Date addMilliseconds(Date date, int amount) {
        return add(date, 14, amount);
    }

    private static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("日期对象不允许为null!");
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(calendarField, amount);
            return c.getTime();
        }
    }

    public static final int daysBetween(Date early, Date late) {
        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        calst.set(11, 0);
        calst.set(12, 0);
        calst.set(13, 0);
        caled.set(11, 0);
        caled.set(12, 0);
        caled.set(13, 0);
        int days = ((int)(caled.getTime().getTime() / 1000L) - (int)(calst.getTime().getTime() / 1000L)) / 3600 / 24;
        return days;
    }


    static {
        apacheDf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
    }
}
