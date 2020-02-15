package com.xub.java.utils.date;

import com.xub.java.utils.AssertUtil;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: 时间工具类（Java8）
 * @author: 黎清许
 * @create: 2019-11-07 13:45
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public final class DateTimeUtil {

    private DateTimeUtil() {
    }

    private static Map<String, String> patternMap = new ConcurrentHashMap<>(9);

    static {
        patternMap.put("yyyy-MM-dd", "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$");
        patternMap.put("yyyy/MM/dd", "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})\\/(((0[13578]|1[02])\\/(0[1-9]|[12][0-9]|3[01]))| ((0[469]|11)\\/(0[1-9]|[12][0-9]|30))|(02\\/(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))\\/02\\/29)$");
        patternMap.put("yyyyMMdd", "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))| ((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229)$");
        patternMap.put("yyyy-MM-dd HH:mm", "((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))\\s([0-1][0-9]|2[0-3]):([0-5][0-9])$");
        patternMap.put("yyyy/MM/dd HH:mm", "((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})\\/(((0[13578]|1[02])\\/(0[1-9]|[12][0-9]|3[01]))| ((0[469]|11)\\/(0[1-9]|[12][0-9]|30))|(02\\/(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))\\/02\\/29))\\s([0-1][0-9]|2[0-3]):([0-5][0-9])$");
        patternMap.put("yyyyMMdd HHmm", "((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))| ((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229))\\s([0-1][0-9]|2[0-3])([0-5][0-9])$");
        patternMap.put("yyyy-MM-dd HH:mm:ss", "((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))\\s([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$");
        patternMap.put("yyyy/MM/dd HH:mm:ss", "((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})\\/(((0[13578]|1[02])\\/(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)\\/(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))\\/02-29))\\s([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$");
        patternMap.put("yyyyMMdd HHmmss", "((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))| ((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229))\\s([0-1][0-9]|2[0-3])([0-5][0-9])([0-5][0-9])$");
    }

    /**
     * yyyy-MM-dd 正则表达式
     */
    public static final String YEAR_MONTH_DAY_PATTERN_STRING1 = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$";

    /**
     * yyyy/MM/dd 正则表达式
     */
    public static final String YEAR_MONTH_DAY_PATTERN_STRING2 = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})\\/(((0[13578]|1[02])\\/(0[1-9]|[12][0-9]|3[01]))| ((0[469]|11)\\/(0[1-9]|[12][0-9]|30))|(02\\/(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))\\/02\\/29)$";


    /**
     * yyyyMMdd 正则表达式
     */
    public static final String YEAR_MONTH_DAY_PATTERN_STRING3 = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))| ((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229)$";

    /**
     * yyyy-MM-dd HH:mm 正则表达式
     */
    public static final String DATE_MINUTE_PATTERN_STRING1 = "((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))\\s([0-1][0-9]|2[0-3]):([0-5][0-9])$";

    /**
     * yyyy/MM/dd HH:mm 正则表达式
     */
    public static final String DATE_MINUTE_PATTERN_STRING2 = "((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})\\/(((0[13578]|1[02])\\/(0[1-9]|[12][0-9]|3[01]))| ((0[469]|11)\\/(0[1-9]|[12][0-9]|30))|(02\\/(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))\\/02\\/29))\\s([0-1][0-9]|2[0-3]):([0-5][0-9])$";


    /**
     * yyyyMMdd HHmm 正则表达式
     */
    public static final String DATE_MINUTE_PATTERN_STRING3 = "((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))| ((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229))\\s([0-1][0-9]|2[0-3])([0-5][0-9])$";

    /**
     * yyyy-MM-dd HH:mm:ss 正则表达式
     */
    public static final String DATE_SECOND_PATTERN_STRING1 = "((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))\\s([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";

    /**
     * yyyy/MM/dd HH:mm:ss 正则表达式
     */
    public static final String DATE_SECOND_PATTERN_STRING2 = "((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})\\/(((0[13578]|1[02])\\/(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)\\/(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))\\/02-29))\\s([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";


    /**
     * yyyyMMdd HHmmss 正则表达式
     */
    public static final String DATE_SECOND_PATTERN_STRING3 = "((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))| ((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229))\\s([0-1][0-9]|2[0-3])([0-5][0-9])([0-5][0-9])$";

    /**
     * 时间格式化  年月日
     */
    public static final String YEAR_MONTH_DAY_PATTERN_FORMAT1 = "yyyy-MM-dd";

    /**
     * 时间格式化  年月日
     */
    public static final String YEAR_MONTH_DAY_PATTERN_FORMAT2 = "yyyy/MM/dd";

    /**
     * 时间格式化  年月日
     */
    public static final String YEAR_MONTH_DAY_PATTERN_FORMAT3 = "yyyyMMdd";

    /**
     * 时间格式化  年月日 时分
     */
    public static final String DATE_MINUTE_PATTERN_FORMAT1 = "yyyy-MM-dd HH:mm";

    /**
     * 时间格式化  年月日 时分
     */
    public static final String DATE_MINUTE_PATTERN_FORMAT2 = "yyyy/MM/dd HH:mm";

    /**
     * 时间格式化  年月日 时分
     */
    public static final String DATE_MINUTE_PATTERN_FORMAT3 = "yyyyMMdd HHmm";

    /**
     * 时间格式化  年月日 时分秒
     */
    public static final String DATE_SECOND_PATTERN_FORMAT1 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间格式化  年月日 时分秒
     */
    public static final String DATE_SECOND_PATTERN_FORMAT2 = "yyyy/MM/dd HH:mm:ss";

    /**
     * 时间格式化  年月日 时分秒
     */
    public static final String DATE_SECOND_PATTERN_FORMAT3 = "yyyyMMdd HHmmss";

    public static final String GREEN_FORMAT = "EEE MMM dd HH:mm:ss zzz yyyy";

    /**
     * yyyy-MM-dd 格式校验
     */
    public static final Pattern YEAR_MONTH_DAY_PATTERN1 = Pattern.compile(YEAR_MONTH_DAY_PATTERN_STRING1);

    /**
     * yyyy/MM/dd 格式校验
     */
    public static final Pattern YEAR_MONTH_DAY_PATTERN2 = Pattern.compile(YEAR_MONTH_DAY_PATTERN_STRING2);

    /**
     * yyyyMMdd 格式校验
     */
    public static final Pattern YEAR_MONTH_DAY_PATTERN3 = Pattern.compile(YEAR_MONTH_DAY_PATTERN_STRING3);

    /**
     * yyyy-MM-dd HH:mm 格式校验
     */
    public static final Pattern DATE_MINUTE_PATTERN1 = Pattern.compile(DATE_MINUTE_PATTERN_STRING1);

    /**
     * yyyy/MM/dd HH:mm 格式校验
     */
    public static final Pattern DATE_MINUTE_PATTERN2 = Pattern.compile(DATE_MINUTE_PATTERN_STRING2);

    /**
     * yyyyMMdd HHmm 格式校验
     */
    public static final Pattern DATE_MINUTE_PATTERN3 = Pattern.compile(DATE_MINUTE_PATTERN_STRING3);

    /**
     * yyyy-MM-dd HH:mm:ss 格式校验
     */
    public static final Pattern DATE_SECOND_PATTERN1 = Pattern.compile(DATE_SECOND_PATTERN_STRING1);

    /**
     * yyyy/MM/dd HH:mm:ss 格式校验
     */
    public static final Pattern DATE_SECOND_PATTERN2 = Pattern.compile(DATE_SECOND_PATTERN_STRING2);

    /**
     * yyyyMMdd HHmmss 格式校验
     */
    public static final Pattern DATE_SECOND_PATTERN3 = Pattern.compile(DATE_SECOND_PATTERN_STRING3);

    /**
     * 年月日：  yyyy-MM-dd
     */
    public static final DateTimeFormatter YEAR_MONTH_DAY1 = DateTimeFormatter.ofPattern(YEAR_MONTH_DAY_PATTERN_FORMAT1);

    /**
     * 年月日：  yyyy/MM/dd
     */
    public static final DateTimeFormatter YEAR_MONTH_DAY2 = DateTimeFormatter.ofPattern(YEAR_MONTH_DAY_PATTERN_FORMAT2);

    /**
     * 年月日：  yyyyMMdd
     */
    public static final DateTimeFormatter YEAR_MONTH_DAY3 = DateTimeFormatter.ofPattern(YEAR_MONTH_DAY_PATTERN_FORMAT3);

    /**
     * 年月日 时分：  yyyy-MM-dd HH:mm
     */
    public static final DateTimeFormatter DATE_MINUTE1 = DateTimeFormatter.ofPattern(DATE_MINUTE_PATTERN_FORMAT1);

    /**
     * 年月日 时分:  yyyy/MM/dd HH:mm
     */
    public static final DateTimeFormatter DATE_MINUTE2 = DateTimeFormatter.ofPattern(DATE_MINUTE_PATTERN_FORMAT2);

    /**
     * 年月日 时分:  yyyyMMdd HHmm
     */
    public static final DateTimeFormatter DATE_MINUTE3 = DateTimeFormatter.ofPattern(DATE_MINUTE_PATTERN_FORMAT3);

    /**
     * 年月日 时分秒:  yyyy-MM-dd HH:mm:ss
     */
    public static final DateTimeFormatter DATE_SECOND1 = DateTimeFormatter.ofPattern(DATE_SECOND_PATTERN_FORMAT1);

    /**
     * 年月日 时分秒:  yyyy/MM/dd HH:mm:ss
     */
    public static final DateTimeFormatter DATE_SECOND2 = DateTimeFormatter.ofPattern(DATE_SECOND_PATTERN_FORMAT2);

    /**
     * 年月日 时分秒:  yyyyMMdd HHmmss
     */
    public static final DateTimeFormatter DATE_SECOND3 = DateTimeFormatter.ofPattern(DATE_SECOND_PATTERN_FORMAT3);

    /**
     * 中国时区(东八区)
     */
    public static final ZoneOffset CST_ZONE = ZoneOffset.ofHours(8);

    /**
     * Date转LocalDateTime(系统默认时区)
     *
     * @param date
     * @return
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转Date(系统默认时区)
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * LocalDate转LocalDateTime
     *
     * @param localDate
     * @return
     */
    public static LocalDateTime localDate2LocalDateTime(LocalDate localDate) {
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.MIN);
        return localDateTime;
    }

    /**
     * LocalDate转Date(系统默认时区)
     *
     * @param localDate
     * @return
     */
    public static Date localDate2Date(LocalDate localDate) {
        LocalDateTime localDateTime = localDate2LocalDateTime(localDate);
        return localDateTime2Date(localDateTime);
    }

    /**
     * Date转LocalTime(系统默认时区)
     *
     * @param date
     * @return
     */
    public static LocalTime date2LocalTime(Date date) {
        LocalDateTime localDateTime = date2LocalDateTime(date);
        return localDateTime.toLocalTime();
    }


    /**
     * 获取当天的最早时间
     *
     * @param date
     * @return
     */
    public static Date getMinimumOfDate(Date date) {
        LocalDateTime localDateTime = date2LocalDateTime(date);
        LocalDateTime endOfDate = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MIN);
        return localDateTime2Date(endOfDate);
    }

    /**
     * 获取当天的最晚时间
     *
     * @param date
     * @return
     */
    public static Date getMaximumOfDate(Date date) {
        LocalDateTime localDateTime = date2LocalDateTime(date);
        LocalDateTime endOfDate = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MAX);
        return localDateTime2Date(endOfDate);
    }


    /**
     * 时间转字符串 --> 年月日:  yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String date2NormalDateString(Date date) {
        LocalDateTime localDateTime = date2LocalDateTime(date);
        return localDateTime.format(YEAR_MONTH_DAY1);
    }

    /**
     * 时间转字符串 --> 年月日:  yyyy-MM-dd / yyyy/MM/dd / yyyyMMdd
     *
     * @param date
     * @param format
     * @return
     * @throws NullPointerException
     */
    public static String date2NormalDateString(Date date, String format) {
        if (!YEAR_MONTH_DAY_PATTERN_FORMAT1.equals(format)
                && !YEAR_MONTH_DAY_PATTERN_FORMAT2.equals(format)
                && !YEAR_MONTH_DAY_PATTERN_FORMAT3.equals(format)) {
            return null;
        }
        return date2String(date, format);
    }

    /**
     * 时间转字符串 --> 年月日 时分秒:  yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String date2NormalTimeString(Date date) {
        LocalDateTime localDateTime = date2LocalDateTime(date);
        return localDateTime.format(DATE_SECOND1);
    }

    /**
     * 时间转字符串 --> 年月日 时分秒:  yyyy-MM-dd HH:mm:ss /  yyyy-MM-dd HH:mm:ss  / yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @param format
     * @return
     * @throws NullPointerException
     */
    public static String date2NormalTimeString(Date date, String format) {
        if (!DATE_SECOND_PATTERN_FORMAT1.equals(format)
                && !DATE_SECOND_PATTERN_FORMAT2.equals(format)
                && !DATE_SECOND_PATTERN_FORMAT3.equals(format)) {
            return null;
        }
        return date2String(date, format);
    }

    /**
     * @param date
     * @param format
     * @return
     * @throws DateTimeException
     */
    public static String date2String(Date date, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime localDateTime = date2LocalDateTime(date);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 时间转字符串
     *
     * @param date
     * @param dateTimeFormatter
     * @return
     */
    public static String date2String(Date date, DateTimeFormatter dateTimeFormatter) {
        LocalDateTime localDateTime = date2LocalDateTime(date);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 字符串转时间 年月日  (yyyy-MM-dd  /  yyyy/MM/dd   /  yyyyMMdd )
     * 先转换成LocalDate再转换成LocalDateTime最后转Date
     *
     * @param dateString
     * @return
     * @throws NullPointerException
     */
    public static Date normalDateString2Date(String dateString) {
        Date date = null;
        if (isMatch(dateString, YEAR_MONTH_DAY_PATTERN1)) {
            LocalDateTime parse = LocalDateTime.of(LocalDate.parse(dateString, YEAR_MONTH_DAY1), LocalTime.MIN);
            date = localDateTime2Date(parse);
        }
        if (isMatch(dateString, YEAR_MONTH_DAY_PATTERN2)) {
            LocalDateTime parse = LocalDateTime.of(LocalDate.parse(dateString, YEAR_MONTH_DAY2), LocalTime.MIN);
            date = localDateTime2Date(parse);
        }
        if (isMatch(dateString, YEAR_MONTH_DAY_PATTERN3)) {
            LocalDateTime parse = LocalDateTime.of(LocalDate.parse(dateString, YEAR_MONTH_DAY3), LocalTime.MIN);
            date = localDateTime2Date(parse);
        }
        return date;
    }

    /**
     * 字符串转时间  年月日
     * 先转换成LocalDate再转换成LocalDateTime最后转Date
     *
     * @param dateString
     * @param format
     * @return
     * @throws DateTimeParseException
     * @throws NullPointerException
     */
    public static Date normalDateString2Date(String dateString, String format) {
        if (!YEAR_MONTH_DAY_PATTERN_FORMAT1.equals(format)
                && !YEAR_MONTH_DAY_PATTERN_FORMAT2.equals(format)
                && !YEAR_MONTH_DAY_PATTERN_FORMAT3.equals(format)) {
            return null;
        }
        if (!isMatch(dateString, format)) {
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime parse = LocalDateTime.of(LocalDate.parse(dateString, dateTimeFormatter), LocalTime.MIN);
        Date date = localDateTime2Date(parse);
        return date;
    }

    /**
     * 字符串转时间  年月日
     * 先转换成LocalDate再转换成LocalDateTime最后转Date
     *
     * @param dateString
     * @param dateTimeFormatter
     * @return
     * @throws DateTimeParseException
     * @throws NullPointerException
     */
    public static Date normalDateString2Date(String dateString, DateTimeFormatter dateTimeFormatter) {
        Date date = null;
        try {
            LocalDateTime parse = LocalDateTime.of(LocalDate.parse(dateString, dateTimeFormatter), LocalTime.MIN);
            date = localDateTime2Date(parse);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static void main(String[] args) {
//        System.out.println(normalDateString2Date("",YEAR_MONTH_DAY1));
        System.out.println("------------------------------------------------------------------------");
        System.out.println(String.format("yyyy-MM-dd : %b", isMatch("2019-12-01", "yyyy-MM-dd")));
        System.out.println(String.format("yyyy/MM/dd : %b", isMatch("2019/12/01", "yyyy/MM/dd")));
        System.out.println(String.format("yyyyMMdd : %b", isMatch("20191201", "yyyyMMdd")));
        System.out.println(String.format("yyyy-MM-dd HH:mm : %b", isMatch("2019-12-01 11:12", "yyyy-MM-dd HH:mm")));
        System.out.println(String.format("yyyy/MM/dd HH:mm : %b", isMatch("2019/12/01 11:12", "yyyy/MM/dd HH:mm")));
        System.out.println(String.format("yyyyMMdd HHmm : %b", isMatch("20191201 1112", "yyyyMMdd HHmm")));
        System.out.println(String.format("yyyy-MM-dd HH:mm:ss : %b", isMatch("2019-12-01 11:12:55", "yyyy-MM-dd HH:mm:ss")));
        System.out.println(String.format("yyyy/MM/dd HH:mm:ss : %b", isMatch("2019/12/01 11:12:55", "yyyy/MM/dd HH:mm:ss")));
        System.out.println(String.format("yyyyMMdd HHmmss : %b", isMatch("20191201 111255", "yyyyMMdd HHmmss")));
        System.out.println("------------------------------------------------------------------------");
        System.out.println(String.format("yyyy-MM-dd : %b", isMatch("2019-12-0", "yyyy-MM-dd")));
        System.out.println(String.format("yyyy/MM/dd : %b", isMatch("2019/12/0", "yyyy/MM/dd")));
        System.out.println(String.format("yyyyMMdd : %b", isMatch("2019120", "yyyyMMdd")));
        System.out.println(String.format("yyyy-MM-dd HH:mm : %b", isMatch("2019-12-01 11:1", "yyyy-MM-dd HH:mm")));
        System.out.println(String.format("yyyy/MM/dd HH:mm : %b", isMatch("2019/12/01 11:1", "yyyy/MM/dd HH:mm")));
        System.out.println(String.format("yyyyMMdd HHmm : %b", isMatch("20191201 111", "yyyyMMdd HHmm")));
        System.out.println(String.format("yyyy-MM-dd HH:mm:ss : %b", isMatch("2019-12-01 11:12:5", "yyyy-MM-dd HH:mm:ss")));
        System.out.println(String.format("yyyy/MM/dd HH:mm:ss : %b", isMatch("2019/12/01 11:12:5", "yyyy/MM/dd HH:mm:ss")));
        System.out.println(String.format("yyyyMMdd HHmmss : %b", isMatch("20191201 11155", "yyyyMMdd HHmmss")));
        System.out.println("------------------------------------------------------------------------");
        System.out.println(String.format("yyyy-MM-dd : %b", normalDateString2Date("2019-12-01", "yyyy-MM-dd")));
        System.out.println(String.format("yyyy/MM/dd : %b", normalDateString2Date("2019/12/01", "yyyy/MM/dd")));
        System.out.println(String.format("yyyyMMdd : %b", normalDateString2Date("20191201", "yyyyMMdd")));
        System.out.println(String.format("yyyy-MM-dd HH:mm : %b",normalDateString2Date("2019-12-01 11:12", "yyyy-MM-dd HH:mm")));
        System.out.println(String.format("yyyy/MM/dd HH:mm : %b",normalDateString2Date("2019/12/01 11:12", "yyyy/MM/dd HH:mm")));
        System.out.println(String.format("yyyyMMdd HHmm : %b",normalDateString2Date("20191201 1112", "yyyyMMdd HHmm")));
        System.out.println(String.format("yyyy-MM-dd HH:mm:ss : %b",normalDateString2Date("2019-12-01 11:12:55", "yyyy-MM-dd HH:mm:ss")));
        System.out.println(String.format("yyyy/MM/dd HH:mm:ss : %b",normalDateString2Date("2019/12/01 11:12:55", "yyyy/MM/dd HH:mm:ss")));
        System.out.println(String.format("yyyyMMdd HHmmss : %b",normalDateString2Date("20191201 111255", "yyyyMMdd HHmmss")));
        System.out.println("------------------------------------------------------------------------");
        System.out.println(String.format("yyyy-MM-dd : %b", normalTimeString2Date("2019-12-01", "yyyy-MM-dd")));
        System.out.println(String.format("yyyy/MM/dd : %b", normalTimeString2Date("2019/12/01", "yyyy/MM/dd")));
        System.out.println(String.format("yyyyMMdd : %b", normalTimeString2Date("20191201", "yyyyMMdd")));
        System.out.println(String.format("yyyy-MM-dd HH:mm : %b",normalTimeString2Date("2019-12-01 11:12", "yyyy-MM-dd HH:mm")));
        System.out.println(String.format("yyyy/MM/dd HH:mm : %b",normalTimeString2Date("2019/12/01 11:12", "yyyy/MM/dd HH:mm")));
        System.out.println(String.format("yyyyMMdd HHmm : %b",normalTimeString2Date("20191201 1112", "yyyyMMdd HHmm")));
        System.out.println(String.format("yyyy-MM-dd HH:mm:ss : %b",normalTimeString2Date("2019-12-01 11:12:55", "yyyy-MM-dd HH:mm:ss")));
        System.out.println(String.format("yyyy/MM/dd HH:mm:ss : %b",normalTimeString2Date("2019/12/01 11:12:55", "yyyy/MM/dd HH:mm:ss")));
        System.out.println(String.format("yyyyMMdd HHmmss : %b",normalTimeString2Date("20191201 111255", "yyyyMMdd HHmmss")));
    }

    /**
     * 字符串转时间
     * 年月日 时分 (yyyy-MM-dd HH:mm  /  yyyy/MM/dd HH:mm /  yyyyMMdd HHmm )
     * 年月日 时分秒 (yyyy-MM-dd HH:mm:ss  /  yyyy/MM/dd HH:mm:ss  /  yyyyMMdd HHmmss )
     *
     * @param dateString
     * @return
     * @throws NullPointerException
     */
    public static Date normalTimeString2Date(String dateString) {
        Date date = null;
        if (isMatch(dateString, DATE_MINUTE_PATTERN1)) {
            LocalDateTime parse = LocalDateTime.parse(dateString, DATE_MINUTE1);
            date = localDateTime2Date(parse);
        }
        if (isMatch(dateString, DATE_MINUTE_PATTERN2)) {
            LocalDateTime parse = LocalDateTime.parse(dateString, DATE_MINUTE2);
            date = localDateTime2Date(parse);
        }
        if (isMatch(dateString, DATE_MINUTE_PATTERN3)) {
            LocalDateTime parse = LocalDateTime.parse(dateString, DATE_MINUTE3);
            date = localDateTime2Date(parse);
        }
        if (isMatch(dateString, DATE_SECOND_PATTERN1)) {
            LocalDateTime parse = LocalDateTime.parse(dateString, DATE_SECOND1);
            date = localDateTime2Date(parse);
        }
        if (isMatch(dateString, DATE_SECOND_PATTERN2)) {
            LocalDateTime parse = LocalDateTime.parse(dateString, DATE_SECOND2);
            date = localDateTime2Date(parse);
        }
        if (isMatch(dateString, DATE_SECOND_PATTERN3)) {
            LocalDateTime parse = LocalDateTime.parse(dateString, DATE_SECOND3);
            date = localDateTime2Date(parse);
        }
        return date;
    }

    /**
     * 字符串转时间
     * 年月日 时分 (yyyy-MM-dd HH:mm  /  yyyy/MM/dd HH:mm /  yyyyMMdd HHmm )
     * 年月日 时分秒 (yyyy-MM-dd HH:mm:ss  /  yyyy/MM/dd HH:mm:ss  /  yyyyMMdd HHmmss )
     *
     * @param dateString
     * @param format
     * @return
     * @throws NullPointerException
     */
    public static Date normalTimeString2Date(String dateString, String format) {
        if (!DATE_MINUTE_PATTERN_FORMAT1.equals(format)
                && !DATE_MINUTE_PATTERN_FORMAT2.equals(format)
                && !DATE_MINUTE_PATTERN_FORMAT3.equals(format)
                && !DATE_SECOND_PATTERN_FORMAT1.equals(format)
                && !DATE_SECOND_PATTERN_FORMAT2.equals(format)
                && !DATE_SECOND_PATTERN_FORMAT3.equals(format)) {
            return null;
        }
        if (!isMatch(dateString, format)) {
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime parse = LocalDateTime.parse(dateString, dateTimeFormatter);
        Date date = localDateTime2Date(parse);
        return date;
    }

    /**
     * 字符串转时间
     * 年月日 时分 (yyyy-MM-dd HH:mm  /  yyyy/MM/dd HH:mm /  yyyyMMdd HHmm )
     * 年月日 时分秒 (yyyy-MM-dd HH:mm:ss  /  yyyy/MM/dd HH:mm:ss  /  yyyyMMdd HHmmss )
     *
     * @param dateString
     * @param dateTimeFormatter
     * @return
     * @throws NullPointerException
     */
    public static Date normalTimeString2Date(String dateString, DateTimeFormatter dateTimeFormatter) {
        Date date = null;
        try {
            LocalDateTime parse = LocalDateTime.parse(dateString, dateTimeFormatter);
            date = localDateTime2Date(parse);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @param dateString
     * @return
     * @throws NullPointerException
     */
    public static Date string2Date(String dateString) {
        Date date = null;
        if (isMatch(dateString, YEAR_MONTH_DAY_PATTERN1)
                || isMatch(dateString, YEAR_MONTH_DAY_PATTERN2)
                || isMatch(dateString, YEAR_MONTH_DAY_PATTERN3)) {
            date = normalDateString2Date(dateString);
        }
        if (isMatch(dateString, DATE_MINUTE_PATTERN1)
                || isMatch(dateString, DATE_MINUTE_PATTERN2)
                || isMatch(dateString, DATE_MINUTE_PATTERN3)
                || isMatch(dateString, DATE_SECOND_PATTERN1)
                || isMatch(dateString, DATE_SECOND_PATTERN2)
                || isMatch(dateString, DATE_SECOND_PATTERN3)) {
            date = normalTimeString2Date(dateString);
        }
        return date;
    }

    /**
     * 字符串转时间
     *
     * @param dateString
     * @param dateTimeFormatter
     * @return
     */
    public static Date string2Date(String dateString, DateTimeFormatter dateTimeFormatter) {
        Date date = null;
        if (isMatch(dateString, YEAR_MONTH_DAY_PATTERN1)
                || isMatch(dateString, YEAR_MONTH_DAY_PATTERN2)
                || isMatch(dateString, YEAR_MONTH_DAY_PATTERN3)) {
            date = normalDateString2Date(dateString, dateTimeFormatter);
        }
        if (isMatch(dateString, DATE_MINUTE_PATTERN1)
                || isMatch(dateString, DATE_MINUTE_PATTERN2)
                || isMatch(dateString, DATE_MINUTE_PATTERN3)
                || isMatch(dateString, DATE_SECOND_PATTERN1)
                || isMatch(dateString, DATE_SECOND_PATTERN2)
                || isMatch(dateString, DATE_SECOND_PATTERN3)) {
            date = normalTimeString2Date(dateString, dateTimeFormatter);
        }
        return date;
    }

    /**
     * 字符串转时间
     *
     * @param dateString
     * @return
     * @throws NullPointerException
     */
    public static Date parseGreen(String dateString) throws ParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(GREEN_FORMAT, Locale.ENGLISH);
        LocalDateTime parse = LocalDateTime.of(LocalDate.parse(dateString, dateTimeFormatter), LocalTime.MIN);
        return localDateTime2Date(parse);
    }

    /**
     * 返回当前日期时间
     */
    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 返回当前的日期
     */
    public static LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    /**
     * 返回当前时间
     */
    public static LocalTime getCurrentLocalTime() {
        return LocalTime.now();
    }

    /**
     * 返回日期  /  LocalDateTime转LocalDate
     *
     * @param localDateTime
     * @return
     */
    public static LocalDate getLocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

    /**
     * 返回日期  /  Date转LocalDate(系统默认时区)
     *
     * @param date
     * @return
     */
    public static LocalDate getLocalDate(Date date) {
        LocalDateTime localDateTime = date2LocalDateTime(date);
        return localDateTime.toLocalDate();
    }

    /**
     * 返回时间
     *
     * @param localDateTime
     * @return
     */
    public static LocalTime getLocalTime(LocalDateTime localDateTime) {
        return localDateTime.toLocalTime();
    }

    /**
     * 返回时间
     *
     * @param date
     * @return
     */
    public static LocalTime getLocalTime(Date date) {
        LocalDateTime localDateTime = date2LocalDateTime(date);
        return localDateTime.toLocalTime();
    }

    /**
     * 日期相隔秒
     */
    public static long periodHours(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return Duration.between(startDateTime, endDateTime).get(ChronoUnit.SECONDS);
    }

    /**
     * 日期相隔秒
     */
    public static long periodHours(Date startDateTime, Date endDateTime) {
        LocalDateTime startTime = date2LocalDateTime(startDateTime);
        LocalDateTime endTime = date2LocalDateTime(endDateTime);
        return Duration.between(startTime, endTime).get(ChronoUnit.SECONDS);
    }

    /**
     * 日期相隔天数
     */
    public static long periodDays(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.DAYS);
    }

    /**
     * 日期相隔周数
     */
    public static long periodWeeks(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.WEEKS);
    }

    /**
     * 日期相隔月数
     */
    public static long periodMonths(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.MONTHS);
    }

    /**
     * 日期相隔年数
     */
    public static long periodYears(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.YEARS);
    }

    /**
     * 是否当天
     */
    public static boolean isToday(LocalDate date) {
        return getCurrentLocalDate().equals(date);
    }

    /**
     * 获取当前毫秒数
     */
    public static Long toEpochMilli(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 判断是否为闰年
     */
    public static boolean isLeapYear(LocalDate localDate) {
        return localDate.isLeapYear();
    }

    /**
     * 判断是否符合对应格式
     *
     * @param dateString
     * @param pattern
     * @return
     */
    public static boolean isMatch(String dateString, Pattern pattern) {
        Matcher matcher = pattern.matcher(dateString);
        return matcher.matches();
    }

    /**
     * 判断是否符合对应格式
     *
     * @param dateString
     * @param format
     * @return
     */
    public static boolean isMatch(String dateString, String format) {
        String patternString = patternMap.get(format);
        if (AssertUtil.isEmpty(patternString)) {
            return false;
        }
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(dateString);
        return matcher.matches();
    }

}
