/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import org.springframework.util.StringUtils;

/**
 * @description 
 * @data 2018年4月18日下午6:03:23
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public class StringUtil {

	public static String convertString(String str,String code,String another){
		if(StringUtils.isEmpty(str)){
			return "";
		}
		String result="";
		try {
			result=new String(str.getBytes(code),another);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 将0.00200 转换成0.002
	 * @param str
	 * @return
	 */
	public static String convertDecimalZero(String str){
		if(str==null||"".equals(str)){
			return "";
		}
		 return new BigDecimal(str).stripTrailingZeros().toString();
	}
	/**
	 * 进行千分单位处理
	 * 
	 * @param rate
	 * @return
	 */
	public static BigDecimal getDecimalMillesimal(String rate) {
		return new BigDecimal(rate).divide(new BigDecimal("1000"));
	}
}
