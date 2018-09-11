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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;

/**
 * @description
 * @data 2018年4月20日下午11:32:03
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public class BalanceLsInfo extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4165293994085773497L;
	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id", dataType = "java.lang.String", required = false)
	private String balanceId;
	/**
	 * 来源
	 */
	@ApiModelProperty(value = "来源", dataType = "java.lang.String", required = false)
	private String module;
	/**
	 * 发生前余额
	 */
	@ApiModelProperty(value = "发生前余额", dataType = "java.lang.String", required = false)
	private String beforeBalance;
	/**
	 * 发生额
	 */
	@ApiModelProperty(value = "发生额", dataType = "java.lang.String", required = false)
	@NotNull(message="发生额不能为空")
	@Pattern(regexp="^[+]?\\d+(\\.\\d+)?$" ,message="发生额必须填入正数")	
	private String fse;
	/**
	 * 发生后金额
	 */
	@ApiModelProperty(value = "发生后金额", dataType = "java.lang.String", required = false)
	private String afterBalance;
	/**
	 * 发生方向
	 */
	@ApiModelProperty(value = "发生方向", dataType = "java.lang.String", required = false)
	@NotNull(message="操作不能为空")
	private String directionOf;
	/**
	 * 起始时间
	 */
	@ApiModelProperty(value = "起始时间", dataType = "java.lang.String", required = false)
	private String startDate;
	/**
	 * 结束时间
	 */
	@ApiModelProperty(value = "结束时间", dataType = "java.lang.String", required = false)
	private String endDate;
	/**
	 * ecargo保单id
	 */
	@ApiModelProperty(value = "ecargo保单id", dataType = "java.lang.String", required = false)
	private String ecargoPolicyNo;
	
	
	private String updateDate;
	
	
	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getBeforeBalance() {
		return beforeBalance;
	}

	public void setBeforeBalance(String beforeBalance) {
		this.beforeBalance = beforeBalance;
	}

	public String getFse() {
		return fse;
	}

	public void setFse(String fse) {
		this.fse = fse;
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

	public String getBalanceId() {
		return balanceId;
	}

	public void setBalanceId(String balanceId) {
		this.balanceId = balanceId;
	}

	public String getEcargoPolicyNo() {
		return ecargoPolicyNo;
	}

	public void setEcargoPolicyNo(String ecargoPolicyNo) {
		this.ecargoPolicyNo = ecargoPolicyNo;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "BalanceLsInfo [balanceId=" + balanceId + ", module=" + module + ", beforeBalance=" + beforeBalance
				+ ", fse=" + fse + ", afterBalance=" + afterBalance + ", directionOf=" + directionOf + ", startDate="
				+ startDate + ", endDate=" + endDate + ", ecargoPolicyNo=" + ecargoPolicyNo + ", updateDate="
				+ updateDate + "]";
	}


}
