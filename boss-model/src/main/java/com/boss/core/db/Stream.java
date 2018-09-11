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

import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description
 * @data 2018年4月8日下午3:35:02
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
@ApiModel("流水实体")
public class Stream extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2125761636774971051L;
	/**
	 * 发生前余额
	 */
	@ApiModelProperty(value = "发生前余额", dataType = "java.lang.Double", required = false)
	private String beforeBalance;
	/**
	 * 发生额
	 */
	@ApiModelProperty(value = "发生额", dataType = "java.lang.Double", required = false)
	private String accrual;
	/**
	 * 发生后余额
	 */
	@ApiModelProperty(value = "发生后余额", dataType = "java.lang.Double", required = false)
	private String afterBalance;
	/**
	 * 发生方向
	 */
	@ApiModelProperty(value = "发生方向", dataType = "java.lang.Integer", required = false)
	@Size(max = 1, min = 0, message = "请按标准填写")
	private String directionOf;
	/**
	 * 保单号
	 */
	@ApiModelProperty(value = "保单号", dataType = "java.lang.String", required = false)
	private String policyNo;
	/**
	 * 发生前总车次
	 */
	private String totalCarNo;
	/**
	 * 发生前剩余车次
	 */
	private String surplusCarNo;
	/**
	 * 发生车次
	 */
	private String inTrain;
	/**
	 * 发生后剩余车次
	 */
	private String afterOccurrence;

	public String getAfterOccurrence() {
		return afterOccurrence;
	}

	public void setAfterOccurrence(String afterOccurrence) {
		this.afterOccurrence = afterOccurrence;
	}

	public String getTotalCarNo() {
		return totalCarNo;
	}

	public void setTotalCarNo(String totalCarNo) {
		this.totalCarNo = totalCarNo;
	}

	public String getSurplusCarNo() {
		return surplusCarNo;
	}

	public void setSurplusCarNo(String surplusCarNo) {
		this.surplusCarNo = surplusCarNo;
	}

	public String getInTrain() {
		return inTrain;
	}

	public void setInTrain(String inTrain) {
		this.inTrain = inTrain;
	}

	public String getBeforeBalance() {
		return beforeBalance;
	}

	public void setBeforeBalance(String beforeBalance) {
		this.beforeBalance = beforeBalance;
	}

	public String getAccrual() {
		return accrual;
	}

	public void setAccrual(String accrual) {
		this.accrual = accrual;
	}

	public String getAfterBalance() {
		return afterBalance;
	}

	public void setAfterBalance(String afterBalance) {
		this.afterBalance = afterBalance;
	}

	public String getDirectionOf() {
		return directionOf;
	}

	public void setDirectionOf(String directionOf) {
		this.directionOf = directionOf;
	}
	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
}
