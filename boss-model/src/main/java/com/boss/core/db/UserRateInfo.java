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
 * @data 2018年5月21日下午5:31:58
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public class UserRateInfo extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8006600902075987292L;

	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 用户费率
	 */

	private String userRate;
	/**
	 * 保险类型
	 */
	private String insuranceType;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserRate() {
		return userRate;
	}

	public void setUserRate(String userRate) {
		this.userRate = userRate;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

}
