package com.boss.data.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

	/**
	 * 将源字符串使用MD5加密为字节数组
	 * 
	 * @param source
	 * @return
	 */
	public static byte[] encode2bytes(String source,String charset) {
		byte[] result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			if(null==charset || "".equals(charset))
			{
				md.update(source.getBytes());
			}
			else
			{
				md.update(source.getBytes(charset));
			}
			md.update(source.getBytes(charset));
			result = md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return result;
	}

	
	/**
	 * 将源字符串使用MD5加密为32位16进制数
	 * @param source
	 * @param charset
	 * @return
	 */
	public static String encode2hex(String source,String charset) {
		byte[] data = encode2bytes(source,charset);

		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			String hex = Integer.toHexString(0xff & data[i]);

			if (hex.length() == 1) {
				hexString.append('0');
			}

			hexString.append(hex);
		}

		return hexString.toString();
	}

	/**
	 * 验证字符串是否匹配
	 * 
	 * @param unknown
	 *            待验证的字符串
	 * @param okHex
	 *            使用MD5加密过的16进制字符串
	 * @return 匹配返回true，不匹配返回false
	 */
	public static boolean validate(String unknown, String okHex,String charset) {
		return okHex.equals(encode2hex(unknown,charset));
	}
	 
	/**
	 * 使用md5的算法进行加密
	 * @throws UnsupportedEncodingException 
	 */
	public static String md5(String plainText,String charset) throws UnsupportedEncodingException
	{
		byte[] secretBytes = null;
		try
		{
			if(null==charset || "".equals(charset))
			{
				secretBytes = MessageDigest.getInstance("MD5").digest(
						plainText.getBytes());
			}
			else
			{
				secretBytes = MessageDigest.getInstance("MD5").digest(
						plainText.getBytes(charset));
			}
			
		} catch (NoSuchAlgorithmException e)
		{
			throw new RuntimeException("没有md5这个算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++)
		{
			md5code = "0" + md5code;
		}
		return md5code;
	}
	
	
	/*public static String newMd5(String s,String charset) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] bytes = md.digest(s.getBytes(charset));
	        return toHex(bytes);
	    }
	    catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

	private static String toHex(byte[] bytes) {

	    final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
	    StringBuilder ret = new StringBuilder(bytes.length * 2);
	    for (int i=0; i<bytes.length; i++) {
	        ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
	        ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
	    }
	    return ret.toString();
	}*/
	
	/**
	 * 人保用加密
	 */
	public static String getMd5Value(String md5String,String charset) {
		String result = "";
	    try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        //String md5Key = "";
			//md5Key = "PICCTEST1234";
			if(null==charset || "".equals(charset))
			{
				md.update( (md5String).getBytes());
			}
			else
			{
				md.update( (md5String).getBytes(charset));
			}
	        byte b[] = md.digest();
	        int i;
	        StringBuffer buf = new StringBuffer("");
	        for (int offset = 0; offset < b.length; offset++) {
	            i = b[offset];
	            if (i < 0)
	                i += 256;
	            if (i < 16)
	                buf.append("0");
	            buf.append(Integer.toHexString(i));
	        }
	        result = buf.toString();
	        //	        System.out.println("MD5(" + sourceStr + ",32) = " + result);
	        //          System.out.println("MD5(" + sourceStr + ",16) = " + buf.toString().substring(8, 24));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
}
