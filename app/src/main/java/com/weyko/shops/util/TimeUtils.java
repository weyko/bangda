package com.weyko.shops.util;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间转换工具类
 * 
 * @author moxian-hhj
 * 
 */
public class TimeUtils {

	public static final long UNIT_MS_DAY = 24 * 60 * 60 * 1000;

	/**
	 * TimeFormatType 时间格式
	 */
	public enum TimeFormatType {
		/** yyyy-MM-dd HH:mm:ss */
		TIME_FOEMAT_STANDARD,
		/** yy-MM-dd HH:mm:ss */
		TIME_FOEMAT_NORMAL,
		/** yyyy-MM-dd */
		TIME_FOEMAT_Y_M_D,
		/** yyyy */
		TIME_FOEMAT_Y_Y,
		/** yy-MM */
		TIME_FOEMAT_Y_M,
		/** MM-dd */
		TIME_FOEMAT_M_D,
		/** HH:mm:ss */
		TIME_FOEMAT_H_M_S,
		/** HH:mm */
		TIME_FOEMAT_H_M,
		/** mm:ss */
		TIME_FOEMAT_M_S,
		/** yyyy/MM/dd HH:mm */
		TIME_FOEMAT_NOT_S,
		/** yyyy/MM/dd */
		TIME_FOEMAT_NOT_TIME,
		/** yyyy/MM/dd HH:mm:ss */
		TIME_FOEMAT,
		/** yyyy/MM */
		Y_M,
		/** dd */
		D,
		/** E */
		E
	}

	/**
	 * 获取时间格式
	 * 
	 * @param formatType
	 * @return
	 */
	private static String getTimeFormat(TimeFormatType formatType) {
		String formatStr = "";
		switch (formatType) {
		case TIME_FOEMAT_STANDARD:
			formatStr = "yyyy-MM-dd HH:mm:ss";
			break;
		case TIME_FOEMAT_NORMAL:
			formatStr = "yy-MM-dd HH:mm:ss";
			break;
		case TIME_FOEMAT_Y_M_D:
			formatStr = "yyyy-MM-dd";
			break;
		case TIME_FOEMAT_Y_Y :
			formatStr = "yyyy";
			break;
		case TIME_FOEMAT_Y_M:
			formatStr = "yy-MM";
			break;
		case TIME_FOEMAT_M_D:
			formatStr = "MM-dd";
			break;
		case TIME_FOEMAT_H_M_S:
			formatStr = "HH:mm:ss";
			break;
		case TIME_FOEMAT_H_M:
			formatStr = "HH:mm";
			break;
		case TIME_FOEMAT_M_S:
			formatStr = "mm:ss";
			break;
		case TIME_FOEMAT_NOT_S:
			formatStr = "yyyy/MM/dd HH:mm";
			break;
		case TIME_FOEMAT_NOT_TIME:
			formatStr = "yyyy/MM/dd";
			break;
		case TIME_FOEMAT:
			formatStr = "yyyy/MM/dd HH:mm:ss";
			break;
		case Y_M:
			formatStr = "yyyy/MM";
			break;
		case D:
			formatStr = "dd";
			break;
		case E:
			formatStr = "E";
			break;
		}
		return formatStr;
	}

	/**
	 * 时间戳转换指定格式的时间 需要HH:mm格式，需要将timestamp/1000
	 * 
	 * @param timestamp
	 *            单位秒
	 * @param formatType
	 *            时间格式类型
	 * @return 返回指定的时间格式
	 */
	public static String timeFormatStandardToSimple(long timestamp,
													TimeFormatType formatType) {
		SimpleDateFormat f = new SimpleDateFormat(getTimeFormat(formatType));

		String dateString = f.format(new Date(timestamp * 1000));
		return dateString;
	}

	public static String timeFormatStandardToSimple(long timestamp,
													String pattern) {
		SimpleDateFormat f = new SimpleDateFormat(pattern);
		String dateString = f.format(new Date(timestamp * 1000));
		return dateString;
	}

	/**
	 * 获取当前时间
	 * 
	 * @param formatType
	 *            时间格式类型
	 * @return 返回指定的时间格式
	 */
	public static String getCurrentTime(TimeFormatType formatType) {
		SimpleDateFormat f = new SimpleDateFormat(getTimeFormat(formatType));
		return f.format(new Date(System.currentTimeMillis()));
	}
	/**
	 * 获取当前时间
	 *
	 * @param formatType
	 *            时间格式类型
	 * @return 返回指定的时间格式
	 */
	public static String getTime(long data,TimeFormatType formatType) {
		SimpleDateFormat f = new SimpleDateFormat(getTimeFormat(formatType));
		return f.format(new Date(data));
	}

	/**
	 * 两个时间的月份是否一致
	 *
	 * @param Date1
	 * @param Date2
	 * @return
	 */
	public static boolean compareDateMonth(String Date1, String Date2){
        boolean result = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月
        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        try {
            date1.setTime(sdf.parse(Date1));
            date2.setTime(sdf.parse(Date2));
            if (date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH)){
                result = true;
                return result;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

		return result;
	}

    /**
     * 获取时间的月份
     * @param Date
     * @return
     * @throws ParseException
     */
    public static int getMonthDate(String Date){
        int month = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月
        Calendar date1 = Calendar.getInstance();
        try {
            date1.setTime(sdf.parse(Date));
            month = date1.get(Calendar.MONTH);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return month + 1;
    }

	/**
	 * 与当前系统时间对比
	 * 
	 * @param DATE1
	 * @param mFormatType
	 * @return -1 1 0
	 */
	@SuppressLint("SimpleDateFormat")
	public static int compareDate(String DATE1, TimeFormatType mFormatType) {//PswWrongLimitTimeCtrl.startLimitTime()按照这个方法改
		DateFormat df = new SimpleDateFormat(
				getTimeFormat(mFormatType));
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = new Date();
			// System.out.println("dt1.getTime()="+dt1.getTime() +
			// "dt2.getTime()=" + dt2.getTime());
			if (dt1.getTime() > dt2.getTime()) {
				return -1;

			} else if (dt1.getTime() < dt2.getTime()) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	@SuppressLint("SimpleDateFormat")
	public static long conversionDateToLong(String dateStr, TimeFormatType type) {
		SimpleDateFormat sdf = new SimpleDateFormat(getTimeFormat(type));
		Date date = null;
		try {
			date = sdf.parse(dateStr.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (date != null) {
			return date.getTime();
		} else {
			return 0;
		}
	}
	
	public static long conversionServiceTimeToLong(String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (date != null) {
			return date.getTime();
		} else {
			return 0;
		}
	}

	/**
	 * 计算与当前系统的时间差
	 * @return
     */
	public static long calculateWithCurrentTime(String time){
		long intervals = 0;
		if (android.text.TextUtils.isEmpty(time)){
			return intervals;
		}
		String cTime = timeFormatStandardToSimple(System.currentTimeMillis() / 1000,
				TimeFormatType.TIME_FOEMAT_STANDARD);
		long currentTimeMillisTime = conversionServiceTimeToLong(cTime);
		long cacheMillisTime = conversionServiceTimeToLong(time);
		intervals = currentTimeMillisTime - cacheMillisTime;
		return intervals;
	}

	/**
	 * yyyy-MM-dd 转换时间戳（秒级）
	 * @param time
	 * @return
     */
	public static long getTimeSecond(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long minutes = 0;
		try {
			Date dt2 = sdf.parse(time);
			minutes = dt2.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return minutes / 1000;
	}
	/**
	 * yyyy-MM-dd 转换时间戳（秒级）
	 * @param time
	 * @return
     */
	public static long getTime(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long minutes = 0;
		try {
			Date dt2 = sdf.parse(time);
			minutes = dt2.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return minutes;
	}
}