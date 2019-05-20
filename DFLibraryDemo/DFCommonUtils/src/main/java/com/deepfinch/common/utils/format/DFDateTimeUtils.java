package com.deepfinch.common.utils.format;

import com.deepfinch.common.utils.log.DFLog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by linkface on 2017/2/28.
 */

public class DFDateTimeUtils {
    public static final String TAG = "DFDateTimeUtils";

    public static final String TIME_FORMAT_DAY_24 = "HH:mm";
    public static final String TIME_FORMAT_DAY_24_SS = "HH:mm:ss:S";
    public static final String TIME_FORMAT_YEAR_MONTH_DAY = "yyyy.MM.dd";
    public static final String TIME_FORMAT_MAIN_YEAR_MONTH_DAY = "yyyy/MM/dd";
    public static final String TIME_FORMAT_YEAR = "yyyy";
    public static final String TIME_FORMAT_YEAR_MONTH = "yyyy年M月";
    public static final String TIME_FORMAT_YEAR_MONTH_CONCAT_DOT = "yyyy.MM";
    public static final String TIME_FORMAT_SEND_SERVER_YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static final String TIME_FORMAT_YMD_HMS = "yyyy-MM-dd-HH:mm:ss";
    public static final String TIME_FORMAT_RECORD_SAVE = "yyyyMMddHHmmss";
    public static final String TIME_FORMAT_HOUR_MINUTE = "HH小时mm分钟";
    public static final String TIME_FORMAT_MINUTE_SECOND = "mm分钟ss秒";
    public static final String TIME_FORMAT_HOUR_MINUTE_SECOND = "HH:mm:ss";

    public static final int ONE_DAY_TIME_MILLIS = 24 * 60 * 60 * 1000;

    public static String[] mWeekUpperCase = {
            "星期日",
            "星期一",
            "星期二",
            "星期三",
            "星期四",
            "星期五",
            "星期六"
    };

    public static SimpleDateFormat sSimpleFormat;

    static {
        sSimpleFormat = new SimpleDateFormat();
    }

    public static String getTimeFor24(Date date) {
        sSimpleFormat.applyPattern(TIME_FORMAT_DAY_24);
        String format = "";
        if (date != null) {
            format = sSimpleFormat.format(date);
        }
        return format;
    }

    public static String getTimeForSS(Date date) {
        sSimpleFormat.applyPattern(TIME_FORMAT_DAY_24_SS);
        String format = sSimpleFormat.format(date);
        return format;
    }

    public static String getTimeFormatYearMonthDay(Date date) {
        sSimpleFormat.applyPattern(TIME_FORMAT_YEAR_MONTH_DAY);
        String format = sSimpleFormat.format(date);
        return format;
    }

    public static String getTimeFormatMainYearMonthDay(Date date) {
        sSimpleFormat.applyPattern(TIME_FORMAT_MAIN_YEAR_MONTH_DAY);
        String format = sSimpleFormat.format(date);
        return format;
    }

    public static String getTimeYear(Date date) {
        sSimpleFormat.applyPattern(TIME_FORMAT_YEAR);
        String format = sSimpleFormat.format(date);
        return format;
    }

    public static String getTimeYearMonth(Date date) {
        sSimpleFormat.applyPattern(TIME_FORMAT_YEAR_MONTH);
        String format = sSimpleFormat.format(date);
        return format;
    }

    public static String getTimeYearMonthConcatDot(Date date) {
        sSimpleFormat.applyPattern(TIME_FORMAT_YEAR_MONTH_CONCAT_DOT);
        String format = sSimpleFormat.format(date);
        return format;
    }

    public static String getSendServerYearMonthDay(Date date) {
        sSimpleFormat.applyPattern(TIME_FORMAT_SEND_SERVER_YEAR_MONTH_DAY);
        String format = sSimpleFormat.format(date);
        return format;
    }

    public static String getWeekUpperCase(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        DFLog.i(TAG, "refreshDayOfWeek", dayOfWeek);
        return mWeekUpperCase[dayOfWeek - 1];
    }

    /**
     * 获取时间 12小时28分钟，不够一小时显示10分钟39秒
     *
     * @param timeLength 时间段，以s为单位
     * @return
     */
    public static String getTimeFormatHourMinuteOrMinuteSecond(long timeLength) {
        DFLog.i(TAG, "getTimeFormatHourMinuteOrMinuteSecond", "timeLength", timeLength);

        long hours = timeLength / (60 * 60);
        long minutes = (timeLength - hours * (60 * 60)) / 60;
        long second = (timeLength - hours * (60 * 60) - minutes * 60);
        String resultStr = null;
        DFLog.i(TAG, "getTimeFormatHourMinuteOrMinuteSecond", "second", second);
        if (hours > 0) {
            if (second > 0) {
                minutes++;
            }
            resultStr = getTimeFormatHourMinute(hours, minutes);
        } else {
            resultStr = getTimeFormatMinuteSecond(minutes, second);
        }
        return resultStr;
    }

    /**
     * 获取时间 12小时28分钟
     *
     * @param hour
     * @param minute
     * @return
     */
    public static String getTimeFormatHourMinute(long hour, long minute) {
        StringBuffer format = new StringBuffer();
        format.append(hour);
        format.append("小时");
        format.append(minute);
        format.append("分钟");
        return format.toString();
    }

    /**
     * 获取时间 28分钟29秒
     *
     * @param minute
     * @param second
     * @return
     */
    public static String getTimeFormatMinuteSecond(long minute, long second) {
        StringBuffer format = new StringBuffer();
        format.append(minute);
        format.append("分钟");
        format.append(second);
        format.append("秒");
        return format.toString();
    }

    /**
     * 日期格式：2017.04.17 星期一
     *
     * @param date
     * @return
     */
    public static String getFormatYearMonthDayWeek(Date date) {
        StringBuffer sb = new StringBuffer();
        sb.append(getTimeFormatYearMonthDay(date));
        sb.append(" ");
        sb.append(getWeekUpperCase(date.getTime()));
        return sb.toString();
    }

    /**
     * 日期格式：18：22：26
     *
     * @param date
     * @return
     */
    public static String getFormatHourMinuteSecond(Date date) {
        String format = null;
        if (date != null) {
            sSimpleFormat.applyPattern(TIME_FORMAT_HOUR_MINUTE_SECOND);
            format = sSimpleFormat.format(date);
        }
        return format;
    }

    /**
     * 日期格式：2017年第十五周
     *
     * @param calendar
     * @return
     */
    public static String getFormatYearWeek(Calendar calendar) {
        StringBuffer sb = new StringBuffer();
        sb.append(getTimeYear(calendar.getTime()));
        sb.append("年");
        sb.append("第");
        sb.append(calendar.get(Calendar.WEEK_OF_YEAR));
        sb.append("周");
        return sb.toString();
    }

    /**
     * 时间格式  2017-03-19-16:16:36
     *
     * @param date
     * @return
     */
    public static String getTimeYMDAndHMS(Date date) {
        String format = null;
        if (date != null) {
            sSimpleFormat.applyPattern(TIME_FORMAT_YMD_HMS);
            format = sSimpleFormat.format(date);
        }
        return format;
    }

    /**
     * 时间格式  2017-03-19 16:16:36
     *
     * @param timeMillis
     * @return
     */
    public static String getTimeYMDAndHMS(long timeMillis) {
        String format = null;
        Date date = new Date(timeMillis);
        sSimpleFormat.applyPattern(TIME_FORMAT_YMD_HMS);
        format = sSimpleFormat.format(date);
        return format;
    }

    /**
     * 时间格式  20170319161636
     *
     * @param timeMillis
     * @return
     */
    public static String getTimeRecordSave(long timeMillis) {
        String format = null;
        Date date = new Date(timeMillis);
        sSimpleFormat.applyPattern(TIME_FORMAT_RECORD_SAVE);
        format = sSimpleFormat.format(date);
        return format;
    }

    public static Date parseTimeFor24(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_FORMAT_DAY_24);
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getDistanceTime(String s1, String s2) {
        Date d1, d2;
        long hour = 0, min = 0;
        DateFormat df = new SimpleDateFormat(TIME_FORMAT_DAY_24);

        try {
            d1 = df.parse(s1);
            d2 = df.parse(s2);

            long t1 = d1.getTime();
            long t2 = d2.getTime();
            long diff = (t1 < t2) ? (t2 - t1) : (t1 - t2);

            hour = diff / (60 * 60 * 1000);
            min = diff / (60 * 1000) - hour * 60;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (min == 0) {
            return hour + "小时";
        }
        return hour + "小时" + min + "分钟";
    }

    public static void resetDayTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public static void resetDayOfWeek(Calendar calendar) {
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public static void resetMonth(Calendar calendar) {
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}
