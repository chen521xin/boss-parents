/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#              Shanghai stock Exchange(SSE),shanghai,China        #
 *#                          All rights reserved.                   #
 *#                                                                 #
 *###################################################################
 */
package com.boss.core.pojo;

import java.io.Serializable;

import com.boss.core.db.BaseEntity;

/**
 * @description 
 * @data 2018年7月6日下午8:27:38
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
public class InsurcompAndPolicyType extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 选择的保险公司的标识
	 */
	private String isCompany;
	/**
	 * 选择的保单类型
	 */
	private String isInsured;
	/**
	 * 登陆用户id
	 */
	private String userId;
	/**
	 * 选择保险公司id
	 */
	private String insurcompId;
	
	public String getInsurcompId() {
		return insurcompId;
	}
	public void setInsurcompId(String insurcompId) {
		this.insurcompId = insurcompId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIsCompany() {
		return isCompany;
	}
	public void setIsCompany(String isCompany) {
		this.isCompany = isCompany;
	}
	public String getIsInsured() {
		return isInsured;
	}
	public void setIsInsured(String isInsured) {
		this.isInsured = isInsured;
	}
	
}
