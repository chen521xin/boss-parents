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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description
 * @data 2018年4月20日下午6:59:34
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public class Md5Util {

	protected static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	protected static MessageDigest messagedigest = null;

	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取字符串MD5
	 * 
	 * @param s
	 * @return
	 */
	public static synchronized String getMD5String(String s) {
		try {
			return getMD5String(s.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
/**
 * 验证MD5密码是否正确
 * @param password
 * @param md5PwdStr
 * @return
 */
	public static boolean checkPassword(String password,String md5PwdStr){
		String s=getMD5String(password);
		return s.equals(md5PwdStr);
	}
	public static synchronized String getMD5String(byte[] bytes) {
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest());
	}

	private static String bufferToHex(byte[] bytes) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte[] bytes, int m, int n) {
		StringBuffer sb = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], sb);
		}
		return sb.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer sb) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		sb.append(c0);
		sb.append(c1);
	}
}
