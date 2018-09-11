package com.boss.ecargo.email;

/**
 * 鏃ユ湡鏍煎紡鍖栫被鍨嬫灇涓緂etgetDateFormatModel()鍙幏鍙栧瓧绗︿覆锛�
 * 鏀寔鈥測yyy-MM-dd鈥濇牸寮忔棩鏈熴�佲�測yyy/MM/dd鈥濇牸寮忔棩鏈熴��
 * yyyy/MM/dd HH:mm:ss鏍煎紡鏃ユ湡銆亂yyy-MM-dd HH:mm:ss鏍煎紡鏃ユ湡锛�
 * 
 * @author wot_chenlin
 *
 */
public enum DateFormatModel 
{
	/**
	 * 鈥測yyy-MM-dd鈥濇牸寮忔棩鏈�
	 */
	md1{public String getDateFormatModel(){return "yyyy-MM-dd";}},
	/**
	 * 鈥測yyy/MM/dd鈥濇牸寮忔棩鏈�
	 */
	md2{public String getDateFormatModel(){return "yyyy/MM/dd";}},
	/**
	 * yyyy/MM/dd hh:mm:ss鏍煎紡鏃ユ湡+12灏忔椂鍒舵椂闂�
	 */
	md3_12{public String getDateFormatModel(){return "yyyy/MM/dd hh:mm:ss";}},
	/**
	 * yyyy/MM/dd HH:mm:ss鏍煎紡鏃ユ湡+24灏忔椂鍒舵椂闂�
	 */
	md3_24{public String getDateFormatModel(){return "yyyy/MM/dd HH:mm:ss";}},
	/**
	 * yyyy-MM-dd hh:mm:ss鏍煎紡鏃ユ湡+12灏忔椂鍒舵椂闂�
	 */
	md4_12{public String getDateFormatModel(){return "yyyy-MM-dd hh:mm:ss";}},
	/**
	 * yyyy-MM-dd HH:mm:ss鏍煎紡鏃ユ湡+24灏忔椂鍒舵椂闂�
	 */
	md4_24{public String getDateFormatModel(){return "yyyy-MM-dd HH:mm:ss";}},
	/**
	 * yyyyMMddhhmmss鏍煎紡鏃ユ湡+12灏忔椂鍒舵椂闂�
	 */
	md5_12{public String getDateFormatModel(){return "yyyyMMddhhmmss";}},
	/**
	 * yyyyMMddHHmmss鏍煎紡鏃ユ湡+24灏忔椂鍒舵椂闂�
	 */
	md5_24{public String getDateFormatModel(){return "yyyyMMddHHmmss";}};
	
	public abstract String getDateFormatModel();
}
