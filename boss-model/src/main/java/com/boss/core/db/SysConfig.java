/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.core.db;

import java.io.Serializable;

/**
 * @description
 * @data 2018年5月22日下午3:06:23
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public class SysConfig extends BaseEntity implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = -976589454832168219L;
	/**
	 * 参数说明
	 */
	private String pamCodeInfo;
	/**
	 * 业务类型
	 */
	private String pamBuzType;
	/**
	 * '参数代码
	 */
	private String pamCode;
	/**
	 * 参数名称
	 */
	private String pamCodeName;
	/**
	 * 参数值
	 */
	private String pamCodeValue;

	public String getPamCodeInfo() {
		return pamCodeInfo;
	}

	public void setPamCodeInfo(String pamCodeInfo) {
		this.pamCodeInfo = pamCodeInfo;
	}

	public String getPamBuzType() {
		return pamBuzType;
	}

	public void setPamBuzType(String pamBuzType) {
		this.pamBuzType = pamBuzType;
	}

	public String getPamCode() {
		return pamCode;
	}

	public void setPamCode(String pamCode) {
		this.pamCode = pamCode;
	}

	public String getPamCodeName() {
		return pamCodeName;
	}

	public void setPamCodeName(String pamCodeName) {
		this.pamCodeName = pamCodeName;
	}

	public String getPamCodeValue() {
		return pamCodeValue;
	}

	public void setPamCodeValue(String pamCodeValue) {
		this.pamCodeValue = pamCodeValue;
	}

}
