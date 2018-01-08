/**
 *
 */
package com.example.jpa.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author huxm
 */
public class DateUtil {
    public static final String FULL = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String YEAR_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String YEAR_MINUTE = "yyyy-MM-dd HH:mm";
    public static final String YEAR_HOUR = "yyyy-MM-dd HH";
    public static final String YEAR_DAY = "yyyy-MM-dd";
    public static final String S_YEAR_DAY = "yyyyMMdd";
    public static final String HOUR_SECOND = "HH:mm:ss";
    public static final String DIR_YEAR_DAY = "yyyy/MM/dd";
    private static SimpleDateFormat sdf_FULL;
    private static SimpleDateFormat sdf_YEAR_SECOND;
    private static SimpleDateFormat sdf_YEAR_MINUTE;
    private static SimpleDateFormat sdf_YEAR_HOUR;
    private static SimpleDateFormat sdf_YEAR_DAY;
    private static SimpleDateFormat sdf_HOUR_SECOND;

    public static Date parser(String format, String value) throws ParseException {

        if (format == null || "".equals(format) || value == null || "".equals(value))
            return null;
        SimpleDateFormat sdf = getFormator(format);
        return sdf.parse(value);
    }

    public static Date tryParser(String format, String value) {

        if (format == null || "".equals(format) || value == null || "".equals(value))
            return null;
        SimpleDateFormat sdf = getFormator(format);
        try {
            return sdf.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式化日期
     *
     * @param format
     * @param date
     * @return
     */
    public static String format(String format, Date date) {

        if (format == null || "".equals(format) || date == null)
            return "";
        SimpleDateFormat sdf = getFormator(format);
        return sdf.format(date);
    }

    private static SimpleDateFormat getFormator(String format) {

        SimpleDateFormat sdf;
        if (FULL.equals(format)) {
            if (sdf_FULL == null)
                sdf_FULL = new SimpleDateFormat(FULL);
            sdf = sdf_FULL;
        } else if (YEAR_SECOND.equals(format)) {
            if (sdf_YEAR_SECOND == null)
                sdf_YEAR_SECOND = new SimpleDateFormat(YEAR_SECOND);
            sdf = sdf_YEAR_SECOND;
        } else if (YEAR_MINUTE.equals(format)) {
            if (sdf_YEAR_MINUTE == null)
                sdf_YEAR_MINUTE = new SimpleDateFormat(YEAR_MINUTE);
            sdf = sdf_YEAR_MINUTE;
        } else if (YEAR_HOUR.equals(format)) {
            if (sdf_YEAR_HOUR == null)
                sdf_YEAR_HOUR = new SimpleDateFormat(YEAR_HOUR);
            sdf = sdf_YEAR_HOUR;
        } else if (YEAR_DAY.equals(format)) {
            if (sdf_YEAR_DAY == null)
                sdf_YEAR_DAY = new SimpleDateFormat(YEAR_DAY);
            sdf = sdf_YEAR_DAY;
        } else if (HOUR_SECOND.equals(format)) {
            if (sdf_HOUR_SECOND == null)
                sdf_HOUR_SECOND = new SimpleDateFormat(HOUR_SECOND);
            sdf = sdf_HOUR_SECOND;
        } else {
            sdf = (new SimpleDateFormat(format));
        }
        return sdf;
    }

    public static Timestamp toTimestamp(Date date) {
        String str = format(FULL, date);
        return toTimestamp(str);
    }

    public static Timestamp toTimestamp(String date) {
        return Timestamp.valueOf(date);
    }

    public static int toUnixTimestamp(Date date) {
        Date origin = new Date(0);
        Long value = (date.getTime() - origin.getTime()) / 1000;
        return value.intValue();
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     * @author zzw
     * @date 2015-9-7
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 字符串的日期格式的计算
     *
     * @author zzw
     * @date 2015-9-7
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    public static Date addDateEndfix(Date date) {
        String strDate = addDateEndfix(DateUtil.format(YEAR_DAY, date));
        return DateUtil.tryParser(YEAR_SECOND, strDate);
    }

    /**
     * 时间查询时,结束时间的 23:59:59
     */
    public static String addDateEndfix(String datestring) {
        if ((datestring == null) || datestring.equals("")) {
            return null;
        }
        return datestring + " 23:59:59";
    }

    /**
     * zzw
     *
     * @param dateStr
     * @return
     */
    public static synchronized String subDay(String dateStr) {

        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date parse;
        try {
            parse = dft.parse(dateStr);

            Calendar date = Calendar.getInstance();
            date.setTime(parse);
            date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);

            Date endDate = dft.parse(dft.format(date.getTime()));
            return dft.format(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ClassName: DateUtil
     *
     * @Description: 日期减去天数
     * @author zzw
     * @date 2016-9-21
     */
    public static synchronized Date subDay(Date da, int days) {

        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse;
        try {
            parse = da;

            Calendar date = Calendar.getInstance();
            date.setTime(parse);
            date.set(Calendar.DATE, date.get(Calendar.DATE) - days);

            Date endDate = dft.parse(dft.format(date.getTime()));
            return endDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* zzw
     * 毫秒转化时分秒毫秒 
     */
    public static String formatTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "天");
        }
        if (hour > 0) {
            sb.append(hour + "小时");
        }
        if (minute > 0) {
            sb.append(minute + "分");
        }
        if (second > 0) {
            sb.append(second + "秒");
        }
        if (milliSecond > 0) {
            sb.append(milliSecond + "毫秒");
        }
        return sb.toString();
    }

    public static Date formatDate(long time) {
        return new Date(time * 1000);
    }
}
