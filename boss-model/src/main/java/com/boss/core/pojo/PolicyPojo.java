/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.core.pojo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.boss.core.db.BaseEntity;

/**
 * @description
 * @data 2018年4月8日下午8:56:02
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
public class PolicyPojo extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8596851502224312486L;
	/**
	 * 识别标识
	 */
	@NotNull(message = "标识不能为空")
	private Integer flag;
	/**
	 * 保单号
	 */
	private String policyNo;
	/**
	 * 发生额
	 */
	@NotNull(message = "发生额不能为空")
	// @Size(min = 0,message="发生额不能小于0")
	private String accrual;
	/**
	 * 发生方向
	 */
	@NotNull(message = "发生方向不能为空")
	// @Size(min = 0,max = 1,message="发生方向值错误")
	private String directionOf;
	/**
	 * 总金额
	 */
	private String policyTotalMoney;
	/**
	 * 发生前余额
	 */
	private String surplusCarNoMoney;
	/**
	 * 最后填单时间
	 */
	private String lastTdate;
	/**
	 * 发生前总车次
	 */
	private String totalCarNo;
	/**
	 * 发生前剩余车次
	 */
	private String surplusCarNo;
	/**
	 * 保单状态
	 */
	private String state;
	/**
	 * 发生车次
	 */
	private String inTrain;
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLastTdate() {
		return lastTdate;
	}

	public void setLastTdate(String lastTdate) {
		this.lastTdate = lastTdate;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getAccrual() {
		return accrual;
	}

	public void setAccrual(String accrual) {
		this.accrual = accrual;
	}

	public String getDirectionOf() {
		return directionOf;
	}

	public void setDirectionOf(String directionOf) {
		this.directionOf = directionOf;
	}

	public String getPolicyTotalMoney() {
		return policyTotalMoney;
	}

	public void setPolicyTotalMoney(String policyTotalMoney) {
		this.policyTotalMoney = policyTotalMoney;
	}

	public String getSurplusCarNoMoney() {
		return surplusCarNoMoney;
	}

	public void setSurplusCarNoMoney(String surplusCarNoMoney) {
		this.surplusCarNoMoney = surplusCarNoMoney;
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

}
