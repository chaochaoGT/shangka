package com.geek.shengka.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * getMonthSpace
 *
 * @author gdl
 */
public class DateUtils {

    private static Logger log = LoggerFactory.getLogger(DateUtils.class);

    public static String DEFAULT_FORMAT = "yyyy-MM-dd";

    public static String FORMAT_STR1 = "yyyy.MM.dd";

    private static String timePattern = "HH:mm";

    public static String timePattern2 = "yyyyMMddHHmmss";

    public static String timePattern3 = "yyyyMMdd";

    public static String dateTimePattern = "yyyy-MM-dd HH:mm:ss";

    public static DateTimeFormatter YEAR_MONTH_DAY_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    /**
     * 按照日期格式，将字符串解析为日期对象
     *
     * @param aMask   输入字符串的格式
     * @param strDate 一个按aMask格式排列的日期的字符串描述
     * @return Date 对象
     * @throws ParseException
     * @see SimpleDateFormat
     */
    public static final Date convertStringToDate(String aMask, String strDate)
            throws ParseException {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);

        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '"
                    + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            // log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }


    public static final String getFormatNow() {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT);
        return df.format(new Date());
    }


    /**
     * 获取当前时间前 或后几天的时间
     * @param amount 前几天 /后几天
     * @return       2019-06-18 15:35:17
     */
    public static final String getCurrentBeforeOrAfterTime(int amount) {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT);
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        date = calendar.getTime();
        return df.format(date);
    }

}
