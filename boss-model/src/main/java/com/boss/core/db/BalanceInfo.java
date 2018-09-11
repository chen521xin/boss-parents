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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description
 * @data 2018年4月20日下午11:25:56
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@ApiModel("余额")
public class BalanceInfo extends BalanceLsInfo implements Serializable {

	private static final long serialVersionUID = 3037104746770237006L;
	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id", dataType = "java.lang.String", required = false)
	private String userId;
	/**
	 * 总额
	 */
	@ApiModelProperty(value = "账户号", dataType = "java.lang.String", required = false)
	private String accountNum;
	/**
	 * 总额
	 */
	@ApiModelProperty(value = "总额", dataType = "java.lang.String", required = false)	
	private String totalBalance;
	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名", dataType = "java.lang.String", required = false)
	private String username;
	/**
	 * 开始金额
	 */
	@ApiModelProperty(value = "开始金额", dataType = "java.lang.String", required = false)
	private String startBalance;
	/**
	 * 结束金额
	 */
	@ApiModelProperty(value = "结束金额", dataType = "java.lang.String", required = false)
	private String endBalance;

	/**
	 * ecargo保单id
	 */
	@ApiModelProperty(value = "ecargo保单id", dataType = "java.lang.String", required = false)
	private String ecargoPolicyNo;
	
	/**
	 * 发生前金额
	 */
	private String fsqBalance;
	
	private String fullName;
	
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFsqBalance() {
		return fsqBalance;
	}

	public void setFsqBalance(String fsqBalance) {
		this.fsqBalance = fsqBalance;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(String totalBalance) {
		this.totalBalance = totalBalance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	public String getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(String startBalance) {
		this.startBalance = startBalance;
	}

	public String getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(String endBalance) {
		this.endBalance = endBalance;
	}

	public String getEcargoPolicyNo() {
		return ecargoPolicyNo;
	}

	public void setEcargoPolicyNo(String ecargoPolicyNo) {
		this.ecargoPolicyNo = ecargoPolicyNo;
	}

	@Override
	public String toString() {
		return "BalanceInfo [userId=" + userId + ", accountNum=" + accountNum + ", totalBalance=" + totalBalance
				+ ", username=" + username + ", startBalance=" + startBalance + ", endBalance=" + endBalance
				+ "]";
	}

	
}
