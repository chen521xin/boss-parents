/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.boss.utils.cons.CommonUtils;

/**
 * @description
 * @data 2018年2月3日下午9:19:11
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public class DateFormatUtils {

	public static String DateFormateToDay() {
		return DateFormatetoString(new Date(), CommonUtils.DATE_FORMATE_YYYY_MM_DD);
	}

	public static String DateFormate() {
		return DateFormatetoString(new Date(), CommonUtils.DATE_FORMATE_YYYY_MM_DD);
	}
	public static String DateFormates() {
		Calendar cla = Calendar.getInstance();
		cla.add(Calendar.MINUTE, 5);
		return DateFormatetoString(cla.getTime(), CommonUtils.DATE_FORMATE_YYYY_MM_DD);
	}

	public static Date DateFormate(Date date, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(DateFormatetoString(date, format));
	}

	public static String DateFormatetoString(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static Date DateFormat(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(CommonUtils.DATE_FORMATE_YYYY_MM_DD);
		Date parseDate = null;
		try {
			parseDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parseDate;
	}
	/**
	 * 格式化String类型的时间
	 */

	public static String DateFormatString(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(CommonUtils.DATE_FORMATE_YYYY_MM_DD_STRING);
		Date parseDate = null;
		try {
			parseDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdf.format(parseDate);
	}
	/**
	 * 得到当前时间的前一小时的具体时间
	 * 
	 * @throws ParseException
	 */
	public static Date BeforAnHour() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		SimpleDateFormat df = new SimpleDateFormat(CommonUtils.DATE_FORMATE_YYYY_MM_DD);
		Date parseDate = null;
		try {
			parseDate = df.parse(df.format(calendar.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parseDate;
	}

	public static String AfterTwentyMin() {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 20);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(now.getTimeInMillis());
	}

	public static String beforeDateNum(String strDate,int num) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 将字符串的日期转为Date类型，ParsePosition(0)表示从第一个字符开始解析
		Date date = sdf.parse(strDate, new ParsePosition(0));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日期的前n天
		calendar.add(Calendar.DATE, -10);
		Date date1 = calendar.getTime();
		return sdf.format(date1);
	}

}
