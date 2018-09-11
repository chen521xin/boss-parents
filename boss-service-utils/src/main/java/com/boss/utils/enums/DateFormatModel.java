/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.utils.enums;

/**
 * 日期格式化类型枚举getgetDateFormatModel()可获取字符串；
 * 支持“yyyy-MM-dd”格式日期、“yyyy/MM/dd”格式日期、
 * yyyy/MM/dd HH:mm:ss格式日期、yyyy-MM-dd HH:mm:ss格式日期；
 * 
 * @author wot_chenlin
 *
 */
public enum DateFormatModel 
{
	/**
	 * “yyyy-MM-dd”格式日期
	 */
	md1{public String getDateFormatModel(){return "yyyy-MM-dd";}},
	/**
	 * “yyyy/MM/dd”格式日期
	 */
	md2{public String getDateFormatModel(){return "yyyy/MM/dd";}},
	/**
	 * yyyy/MM/dd hh:mm:ss格式日期+12小时制时间
	 */
	md3_12{public String getDateFormatModel(){return "yyyy/MM/dd hh:mm:ss";}},
	/**
	 * yyyy/MM/dd HH:mm:ss格式日期+24小时制时间
	 */
	md3_24{public String getDateFormatModel(){return "yyyy/MM/dd HH:mm:ss";}},
	/**
	 * yyyy-MM-dd hh:mm:ss格式日期+12小时制时间
	 */
	md4_12{public String getDateFormatModel(){return "yyyy-MM-dd hh:mm:ss";}},
	/**
	 * yyyy-MM-dd HH:mm:ss格式日期+24小时制时间
	 */
	md4_24{public String getDateFormatModel(){return "yyyy-MM-dd HH:mm:ss";}},
	/**
	 * yyyyMMddhhmmss格式日期+12小时制时间
	 */
	md5_12{public String getDateFormatModel(){return "yyyyMMddhhmmss";}},
	/**
	 * yyyyMMddHHmmss格式日期+24小时制时间
	 */
	md5_24{public String getDateFormatModel(){return "yyyyMMddHHmmss";}};
	
	public abstract String getDateFormatModel();
}
