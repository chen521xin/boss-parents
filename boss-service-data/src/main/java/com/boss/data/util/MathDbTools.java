/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.boss.utils.enums.DateFormatModel;

/**
 * 数学数据（转换）工具类
 * 
 * @author wot_chenlin
 * 
 */
public class MathDbTools
{
	
	/**
	 * 
	 * @param msg 需要转换字符集的信息
	 * @param charset 转换成哪种字符集信息，如GBK,UTF-8
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String strConvertEncode(String msg,String charset) throws UnsupportedEncodingException
	{
		return new String( msg.getBytes(),charset);
	}

	//	public static Date convertTimestamp2Date(Timestamp time)
	//	{
	//		Date dt = new Date();
	//
	//		//		dt.setYear(time.getYear());
	//		//		dt.setMonth(time.getMonth());
	//		dt = time;
	//		//		dt.setDay(time.getDay())
	//
	//		return dt;
	//	}

	public static String convertDate2String(Date date, DateFormatModel dfm)
	{
		if (date == null)
		{
			throw new NullPointerException("传入的Date类型为null");
		}
		String pattern = dfm.getDateFormatModel();
		SimpleDateFormat sdm = new SimpleDateFormat(pattern);
		return sdm.format(date);
	}
	
	/**
	 * 字符转时间
	 * @param dateStr 传入一个String类型的时间
	 * @param dfm 传入一个com.boss.data.email.DateFormatModel类弄的时间格式模型
	 * @return
	 * @throws ParseException
	 */
	public static Date convertString2Date(String dateStr, DateFormatModel dfm) throws ParseException
	{
		if (dateStr == null || "".equals(dateStr))
		{
			throw new NullPointerException("传入的Date类型为null");
		}
		String pattern = dfm.getDateFormatModel();
		SimpleDateFormat sdm = new SimpleDateFormat(pattern);
		return sdm.parse(dateStr);
	}
	
	
	

	public static boolean strIsEmail(String emailAdd)
	{
		Pattern patern = Pattern
				.compile("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
		Matcher isEmail = patern.matcher(emailAdd);
		return isEmail.matches();
	}

	/**
	 * 验证数据精度，如果errorText为null就没有表示该数据符合验证规则，否则就有错误
	 * 
	 * @param db
	 *            需要验证的数据
	 * @param intMax
	 *            整数位的最大位数
	 * @param floatMax
	 *            小数位的最大位数
	 * @param maxNum
	 *            最大值，如果为0，就不验证，最大值必须小于等于验证值整数部分的位数才有效
	 * @return
	 */
	public static String checkDataPosion(double db, int intMax, int floatMax,
			double maxNum)
	{
		String errorText = null;
		String dbs = new Double(db).toString();
		Pattern patern = Pattern.compile("[-+]?[0-" + intMax + "]+[.]?[0-"
				+ floatMax + "]*");
		Matcher isNum = patern.matcher(dbs);
		if (isNum.matches())
		{
			errorText = "输入的数据不合法：整数位最大应为" + intMax + "位；小数最大应为：" + floatMax
					+ "位。";
		}
		if (db > maxNum && maxNum != 0.0)
		{
			errorText = "输入的数据不合法：最大值应小于或等于：" + maxNum;
		}
		return errorText;
	}

	/**
	 * 对字符串类型数据进行精度截取，根据传入指定精度
	 * 
	 * @param db
	 *            String 需要转换的数值类型的数据
	 * @param precision
	 *            in t需要获取的精度
	 * @param roundingMode
	 *            int 要应用的舍入模式,若为null默认为BigDecimal.ROUND_HALF_UP, 详情参见JAVA API
	 *            BigDecimal中的字段
	 * @return BigDecimal
	 */
	public static BigDecimal convertToDouble(String db, int precision,
			int roundingMode) throws NumberFormatException
	{
		if (!MathDbTools.strIsNumber(db))
		{
			throw new NumberFormatException("请传入正确的数值！");

		}
		return new BigDecimal(db).setScale(precision, roundingMode);
	}

	/**
	 * 对数据进行精度截取，根据传入指定精度
	 * 
	 * @param db
	 *            Double 需要转换的数值类型的数据
	 * @param precision
	 *            int 需要获取的精度
	 * @param roundingMode
	 *            int 要应用的舍入模式,建议为BigDecimal.ROUND_HALF_UP, 详情参见JAVA API
	 *            BigDecimal中的字段
	 * @return Double
	 */
	public static BigDecimal convertToDouble(Double db, int precision,
			int roundingMode)
	{
		
		BigDecimal dbr = new BigDecimal(db).setScale(precision, roundingMode);
		return dbr;
	}

	/**
	 * 末尾带%的字符串转换成double,并且自动验证%之前的数字是否为数字,并且可以为正负+-
	 * 
	 * @param baifenbiString
	 *            末尾带%的字符串
	 * @return
	 */
	public static Double converBaifenbiStringToDouble(String baifenbiString)
	{
		baifenbiString = baifenbiString.trim();
		double db = 0;

		if (MathDbTools.strIsBaifenbiNumber(baifenbiString))
		{
			char c = baifenbiString.charAt(baifenbiString.length() - 1);
			int idx = baifenbiString.indexOf(c);
			String ts = baifenbiString.substring(0, idx);

			double tp = Double.parseDouble(ts);
			db = tp / 100.0;

		} else
		{
			throw new java.lang.NumberFormatException(
					"传入的字符不是数字类型的百分比数值，或包含多个%，正确的例如：25.5%");
		}

		return db;
	}

	/**
	 * 判断输入的字符串是否为数字,支持int、double以及正负值
	 * 
	 * @param strNumber
	 *            字符串
	 * @return
	 */
	public static boolean strIsNumber(String strNumber)
	{
		// Pattern patern=Pattern.compile("[-+]?[0-9]+[.]?[0-9]*");
		Pattern patern = Pattern.compile("[-+]?[0-9]+([.]{1}[0-9]+)*");
		Matcher isNum = patern.matcher(strNumber);
		// return isNum.matches()?true:false;
		return isNum.matches();
	}

	/**
	 * 判断是否百分比数字字符串，例：25.5%，-25.5%，+25.5%；
	 * 
	 * @param strNumber
	 *            带百分比%字符数字
	 * @return
	 */
	public static boolean strIsBaifenbiNumber(String strNumber)
	{
		// Pattern patern=Pattern.compile("[-+]?[0-9]+[.]?[0-9]*[%]$");
		Pattern patern = Pattern.compile("[-+]?[0-9]+([.]{1}[0-9]+)*[%]$");
		Matcher isNum = patern.matcher(strNumber);
		// return isNum.matches()?true:false;
		return isNum.matches();
	}

	/**
	 * 获取序列号，yyyyMMddhhmmss+随机数,date为传入，或NULL为当前系统
	 * 
	 * @param dt
	 *            Date日期类型，若获取流水号建议传入NULL
	 * @param random
	 *            获取多少为随机数
	 * @return
	 */
	public static String getDateAndRandomSerilasNum(Date dt, int random)
	{
		Date d = new Date();
		long num = 1;
		StringBuffer sf = new StringBuffer();

		// 用于判断0123之类的数据,数据值为99.9。。。。
		Double db1 = new Double("0.1");
		if (dt != null)
		{
			d = dt;
		}
		for (int i = 0; i < random; i++)
		{
			num *= 10;
			sf.append("9");
		}

		Double db2 = new Double(sf.toString());
		db1 = db1 * db2;

		SimpleDateFormat sdf = new SimpleDateFormat(DateFormatModel.md5_24
				.getDateFormatModel());
		String strdt = sdf.format(d);
		Double d1 = new Double(num * Math.random());

		while (d1 < db1)
		{
			d1 = new Double(num * Math.random());
		}

		Long l1 = d1.longValue();
		return strdt + l1.toString();
	}
	
	/**
	 * 在年月日时分秒等字估上加或者减去指定时间，时间可以为正，也可以为负
	 * @param date 需要添加的时候
	 * @param minute 需要加的量
	 * @param field 要加的地方（年、月、日、时、分、秒、）参见Calendar类的常量  （分钟 Calendar.MINUTE）
	 * @return
	 */
	public static Date timePlusAmount(Date date,int  amount,int field )
	{
		Calendar cnd = Calendar.getInstance();
		cnd.setTime(date);
		
		cnd.add(field, amount);
		
		return cnd.getTime();
	}

	/**
	 * 获取指定日期期限的天数，如果不输入日期，默认取当前系统日期和时间天，如果两个日期都为空直接返回0天；
	 * dfm为DateFormatModel枚举类型的日期格式--枚举，输入null默认为model1=yyyy-MM-dd；
	 * 起始日期比终止日期小返回天数为正数，反之为负数；
	 * 
	 * @param date1
	 *            输入日期
	 * @param date2
	 *            输入日期
	 * @param dfm
	 *            DateFormatModel 日期格式类型
	 * @return
	 * @throws ParseException
	 */
	public static long getDaysForSpecifiedPeriod(String date1, String date2,
			DateFormatModel dfm) throws ParseException
	{
		// 起始日期比终止日期小返回天数为正数，反之为负数
		int flag = 1;
		long days = 0;

		// 如果两个日期都为空，则返回0天
		if ((date1 == null || date1.equals(""))
				&& (date2 == null || date2.equals("")))
		{
			return 0;
		}

		// DateFormatModel dm=dfm==null?DateFormatModel.md1:dfm;
		DateFormatModel dm = DateFormatModel.md1;
		if (dfm != null)
		{
			dm = dfm;
		}

		// date1为空则date1为当前系统日期
		if (date1 == null || date1.equals(""))
		{
			date1 = new SimpleDateFormat(dm.getDateFormatModel())
					.format(new Date());
		}

		// date2为空则date2为当前系统日期
		if (date2 == null || date2.equals(""))
		{
			date2 = new SimpleDateFormat(dm.getDateFormatModel())
					.format(new Date());
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dm.getDateFormatModel());

		Date d1 = sdf.parse(date1);
		Date d2 = sdf.parse(date2);

		// 获取日历日期
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);

		// 用于保存年份小的日期
		Calendar can = null;

		// 拿出两个年份
		int year1 = c1.get(Calendar.YEAR);
		int year2 = c2.get(Calendar.YEAR);

		// 日期1年份小于日期2
		if (c1.before(c2))
		{
			flag = +1;
			// 天数减去已过去的天数
			days -= c1.get(Calendar.DAY_OF_YEAR);
			// 天数加上没有过去的天数
			days += c2.get(Calendar.DAY_OF_YEAR);

			can = c1;
		} else
		{
			flag = -1;
			// 天数加上没有过去的天数
			days += c1.get(Calendar.DAY_OF_YEAR);
			// 天数减去已过去的天数
			days -= c2.get(Calendar.DAY_OF_YEAR);

			can = c2;
		}
		for (int i = 0; i < Math.abs(year1 - year2); i++)
		{
			// 天数加上年份中的最大天数
			days += can.getActualMaximum(Calendar.DAY_OF_YEAR);
			// 年份加1
			can.add(Calendar.YEAR, 1);
		}

		return days * flag;
	}

	/**
	 * 获取指定日期期限的天数，如果不输入日期，默认取当前系统日期和时间天，如果两个日期都为空直接返回0天；
	 * dfm为DateFormatModel枚举类型的日期格式--枚举，输入null默认为model1=yyyy-MM-dd；
	 * 起始日期比终止日期小返回天数为正数，反之为负数；
	 * 
	 * @param date1
	 *            输入日期
	 * @param date2
	 *            输入日期
	 * @param dfm
	 *            DateFormatModel 日期格式类型
	 * @return
	 * @throws ParseException
	 */
	public static long getDaysForSpecifiedPeriod(Date date1, Date date2)
			throws ParseException
	{
		// 起始日期比终止日期小返回天数为正数，反之为负数
		int flag = 1;
		long days = 0;

		// 如果两个日期都为空，则返回0天
		if ((date1 == null || date1.equals(""))
				&& (date2 == null || date2.equals("")))
		{
			return 0;
		}

		// DateFormatModel dm=dfm==null?DateFormatModel.md1:dfm;

		// date1为空则date1为当前系统日期
		if (date1 == null)
		{
			date1 = new Date();
		}

		// date2为空则date2为当前系统日期
		if (date2 == null)
		{
			date2 = new Date();
		}

		Date d1 = date1;
		Date d2 = date2;

		// 获取日历日期
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);

		// 用于保存年份小的日期
		Calendar can = null;

		// 拿出两个年份
		int year1 = c1.get(Calendar.YEAR);
		int year2 = c2.get(Calendar.YEAR);

		// 日期1年份小于日期2
		if (c1.before(c2))
		{
			flag = +1;
			// 天数减去已过去的天数
			days -= c1.get(Calendar.DAY_OF_YEAR);
			// 天数加上没有过去的天数
			days += c2.get(Calendar.DAY_OF_YEAR);

			can = c1;
		} else
		{
			flag = -1;
			// 天数加上没有过去的天数
			days += c1.get(Calendar.DAY_OF_YEAR);
			// 天数减去已过去的天数
			days -= c2.get(Calendar.DAY_OF_YEAR);

			can = c2;
		}
		for (int i = 0; i < Math.abs(year1 - year2); i++)
		{
			// 天数加上年份中的最大天数
			days += can.getActualMaximum(Calendar.DAY_OF_YEAR);
			// 年份加1
			can.add(Calendar.YEAR, 1);
		}

		return days * flag;
	}

	/**
	 * 为空判断，可以判断字符串，和对象，为空返回true,反之false,对象为null，或者String 为“”都返回true
	 * 
	 * @param obj
	 *            需要判断的对象
	 * @return
	 */
	public static boolean objIsNull(Object obj)
	{
		if (obj == null || "".equals(obj))
		{
			return true;
		} else
		{
			return false;
		}
	}

	/**
	 * 验证一个对象数组是否为，只要有一个为空，则返回为空。 例如：if((a==null || "".equals(a))||(b==null ||
	 * "".equals(b))) return true; else return false;
	 * 
	 * @param objs
	 *            需要验证的对象
	 * @return
	 */
	public static boolean objsIsNull(Object... objs)
	{
		for (Object o : objs)
		{
			if (o == null || "".equals(o))
			{
				return true;
			}
		}
		return false;
	}

}
