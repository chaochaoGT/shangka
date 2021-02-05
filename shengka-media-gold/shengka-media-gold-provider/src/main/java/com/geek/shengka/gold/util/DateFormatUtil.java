package com.geek.shengka.gold.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtil {
	
	private DateFormatUtil() {
		
	}
	
	public static final String DATE_FORMAT_PATTERN="yyyy-MM-dd HH:mm:ss";
	
	public static String day(Date date) {
		SimpleDateFormat YYYYMMDD=new SimpleDateFormat("yyyyMMdd");
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(date);
		String dateStr = YYYYMMDD.format(calendar.getTime());
		return dateStr;
	}
	
	public static String day(long timeInMilliseconds) {
		Calendar calendar =Calendar.getInstance();
		calendar.setTimeInMillis(timeInMilliseconds);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateStr = sdf.format(calendar.getTime());
		return dateStr;
	}

	public static String startDate(Date date) {
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN);
		String dateStr = sdf.format(calendar.getTime());
		return dateStr;
	}
	
	public static String startDate(long timeInMilliseconds) {
		Calendar calendar =Calendar.getInstance();
		calendar.setTimeInMillis(timeInMilliseconds);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN);
		String dateStr = sdf.format(calendar.getTime());
		return dateStr;
	}

	public static String endDate(Date date) {
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN);
		String dateStr = sdf.format(calendar.getTime());
		return dateStr;
	}
	
	public static String endDate(long timeInMilliseconds) {
		Calendar calendar =Calendar.getInstance();
		calendar.setTimeInMillis(timeInMilliseconds);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN);
		String dateStr = sdf.format(calendar.getTime());
		return dateStr;
	}

}
