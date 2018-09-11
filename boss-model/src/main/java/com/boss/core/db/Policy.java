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
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description
 * @data 2018年3月8日上午12:31:27
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
@ApiModel("保单")
public class Policy extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8256674272379254454L;
	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "userId", dataType = "java.lang.String", required = false)
	@NotNull(message = "用户不能为空！")
	@Size(max=32,message="用户姓名字段长度超出限制，不能超过32位！")
	private String userId;
	/**
	 * 用户姓名
	 */
	@ApiModelProperty(value = "userName", dataType = "java.lang.String", required = false)
	private String userName;
	/**
	 * 保险公司id
	 */
	@ApiModelProperty(value = "insurcompId", dataType = "java.lang.String", required = false)
	@NotNull(message = "保险公司不能为空！")
	@Size(max=32,message="保险公司名称字段长度超出限制，不能超过32位！")
	private String insurcompId;
	/**
	 * 保险公司全名
	 */
	@ApiModelProperty(value = "fullName", dataType = "java.lang.String", required = false)
	private String fullName;
	/**
	 * 保险产品id
	 */
	@ApiModelProperty(value = "productId", dataType = "java.lang.String", required = false)
	@NotNull(message = "保险产品不能为空！")
	@Size(max=32,message="保险产品id字段长度超出限制，不能超过32位！")
	private String productId;
	/**
	 * 保险产品名称
	 */
	@ApiModelProperty(value = "productId", dataType = "java.lang.String", required = false)
//	@NotNull(message = "保险产品名称不能为空！") TODO 确认是否必填校验
	@Size(max=32,message="保险产品id字段长度超出限制，不能超过32位！")
	private String productName;
	/**
	 * 保险单号（实际投保号）
	 */
	@ApiModelProperty(value = "policyNo", dataType = "java.lang.String", required = false)
	@NotNull(message = "保险单号不能为空！")
	@Size(max=30,message="保险单号字段长度超出限制，不能超过30位！")
	private String policyNo;
	/**
	 * 协议书编号
	 */
	@ApiModelProperty(value="transDealNo", dataType = "java.lang.String", required = false)
	private String transDealNo;
	/**
	 * 被保险人姓名
	 */
	@ApiModelProperty(value = "custName", dataType = "java.lang.String", required = false)
	@NotNull(message = "被保险人不能为空！")
	@Size(max=50,message="被保险人姓名字段长度超出限制，不能超过50位！")
	private String custName;
	/**
	 * 最高赔付
	 */
	@ApiModelProperty(value = "policyPayHeight", dataType = "java.lang.String", required = false)
	@Pattern(regexp = "^[+]?\\d+(\\.\\d+)?$", message = "最高赔付字段只能填入正数！")	
	private String policyPayHeight;
	/**
	 * 最高投保金额
	 */
	@ApiModelProperty(value = "amount", dataType = "java.lang.String", required = false)
	@Pattern(regexp = "^[+]?\\d+(\\.\\d+)?$", message = "最高投保金额字段只能填入正数！")	
	private String amount;
	/**
	 * 保单开始时间
	 */
	@ApiModelProperty(value = "policyStartDate", dataType = "java.lang.String", required = false)
	@NotNull(message = "保单开始时间不能为空！")
	private String policyStartDate;
	/**
	 * 保单结束时间
	 */
	@ApiModelProperty(value = "policyEndDate", dataType = "java.lang.String", required = false)
	@NotNull(message = "保单结束时间不能为空！")
	private String policyEndDate;
	/**
	 * 总车次
	 */
	@ApiModelProperty(value = "totalCarNo", dataType = "java.lang.Double", required = false)
	@Pattern(regexp = "^[+]?\\d+(\\.\\d+)?$", message = "总车次字段只能填入正数！")	
	private String totalCarNo;
	/**
	 * 已用车次
	 */
	@ApiModelProperty(value = "alreadyStartTime", dataType = "java.lang.String", required = false)
	@Pattern(regexp = "^[+]?\\d+(\\.\\d+)?$", message = "已用车次字段只能填入正数！")	
	private String alreadyStartTime;
	/**
	 * 剩余车次
	 */
	@ApiModelProperty(value = "surplusCarNo", dataType = "java.lang.Double", required = false)
	@Pattern(regexp = "^[+]?\\d+(\\.\\d+)?$", message = "剩余车次字段只能填入正数！")	
	private String surplusCarNo;
	/**
	 * 投保总额
	 */
	@ApiModelProperty(value = "policyTotalMoney", dataType = "java.lang.Doubel", required = false)
	@Pattern(regexp = "^[+]?\\d+(\\.\\d+)?$", message = "投保总额字段只能填入正数！")	
	@NotNull(message = "投保总金额不能为空！")
	private String policyTotalMoney;
	/**
	 * 已用保额
	 */
	@ApiModelProperty(value = "alreadySendMoney", dataType = "java.lang.Doubel", required = false)
	private String alreadySendMoney;
	/**
	 * 投保剩余金额
	 */
	@ApiModelProperty(value = "surplusCarNoMoney", dataType = "java.lang.Double", required = false)
	@Pattern(regexp = "^[+]?\\d+(\\.\\d+)?$", message = "投保剩余金额字段只能填入正数！")	
	@NotNull(message = "投保剩余金额不能为空！")
	private String surplusCarNoMoney;
	/**
	 * 保单状态
	 */
	@ApiModelProperty(value = "state", dataType = "java.lang.String", required = false)
	@Size(max=1,message="保单状态字段超出限制，不能超过1位！")
	private String state;
	/**
	 * 保单状态
	 */
	@ApiModelProperty(value = "state", dataType = "java.lang.String", required = false)
	private String stateName;
	/**
	 * 最后填单时间
	 */
	@ApiModelProperty(value = "lastTdate", dataType = "java.lang.String", required = false)
	private String lastTdate;
	/**
	 * 保单剩余提醒车次标准
	 */
	@ApiModelProperty(value = "remindNormCar", dataType = "java.lang.String", required = false)
	@Pattern(regexp = "^[+]?\\d+(\\.\\d+)?$", message = "保单剩余提醒车次标准字段只能填入正数！")	
	private String remindNormCar;
	/**
	 * 保单剩余提醒额度标准
	 */
	@ApiModelProperty(value = "remindNormMoney", dataType = "java.lang.String", required = false)
	@Pattern(regexp = "^[+]?\\d+(\\.\\d+)?$", message = "保单剩余提醒额度标准标准字段只能填入正数！")	
	private String remindNormMoney;
	/**
	 * 保单到期提前提醒标准
	 */
	@ApiModelProperty(value = "remindNormExpireDay", dataType = "java.lang.String", required = false)
	@Pattern(regexp = "^\\+?[1-9][0-9]*$", message = "到期提醒(天)字段只能填入非零的正整数！")
	private String remindNormExpireDay;
	/**
	 * 一级代理
	 */
	@ApiModelProperty(value = "primaryAgency", dataType = "java.lang.String", required = false)
	private String primaryAgency;
	/**
	 * 二级代理
	 */
	@ApiModelProperty(value = "secondaryAgent", dataType = "java.lang.String", required = false)
	private String secondaryAgent;
	/**
	 * 保费金额
	 */
	@ApiModelProperty(value = "premiums", dataType = "java.lang.String", required = false)
	@Pattern(regexp = "^[+]?\\d+(\\.\\d+)?$", message = "保费金额字段只能填入正数！")	
	private String premiums;
	/**
	 * 缴费金额
	 */
	@ApiModelProperty(value = "paymentAmount", dataType = "java.lang.String", required = false)
	@Pattern(regexp = "^[+]?\\d+(\\.\\d+)?$", message = "缴费金额字段只能填入正数！")	
	private String paymentAmount;
	/**
	 * 保险类型值
	 */
	@ApiModelProperty(value = "productTypeValue", dataType = "java.lang.String", required = false)
	private String productTypeValue;
	/**
	 * 保险类型值
	 */
	@ApiModelProperty(value = "productTypeValue", dataType = "java.lang.String", required = false)
	private String productTypeName;
	/**
	 * 人保系数
	 */
	@ApiModelProperty(value = "renbaoXishu", dataType = "java.lang.String", required = false)
	@Pattern(regexp = "^[+]?\\d+(\\.\\d+)?$", message = "缴费金额字段只能填入正数！")
	private String renBaoXiShu;
	
	private String isSendMail;
	private String userMail;

	/**
	 * 到期提醒日
	 */
	private String remindNormExpireDate;
	
	public String getRemindNormExpireDate() {
		return remindNormExpireDate;
	}


	public void setRemindNormExpireDate(String remindNormExpireDate) {
		this.remindNormExpireDate = remindNormExpireDate;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getInsurcompId() {
		return insurcompId;
	}


	public void setInsurcompId(String insurcompId) {
		this.insurcompId = insurcompId;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getPolicyNo() {
		return policyNo;
	}


	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}


	public String getTransDealNo() {
		return transDealNo;
	}


	public void setTransDealNo(String transDealNo) {
		this.transDealNo = transDealNo;
	}


	public String getCustName() {
		return custName;
	}


	public void setCustName(String custName) {
		this.custName = custName;
	}


	public String getPolicyPayHeight() {
		return policyPayHeight;
	}


	public void setPolicyPayHeight(String policyPayHeight) {
		this.policyPayHeight = policyPayHeight;
	}


	public String getAmount() {
		return amount;
	}


	public void setAmount(String amount) {
		this.amount = amount;
	}


	public String getPolicyStartDate() {
		return policyStartDate;
	}


	public void setPolicyStartDate(String policyStartDate) {
		this.policyStartDate = policyStartDate;
	}


	public String getPolicyEndDate() {
		return policyEndDate;
	}


	public void setPolicyEndDate(String policyEndDate) {
		this.policyEndDate = policyEndDate;
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


	public String getRemindNormCar() {
		return remindNormCar;
	}


	public void setRemindNormCar(String remindNormCar) {
		this.remindNormCar = remindNormCar;
	}


	public String getRemindNormMoney() {
		return remindNormMoney;
	}


	public void setRemindNormMoney(String remindNormMoney) {
		this.remindNormMoney = remindNormMoney;
	}


	public String getRemindNormExpireDay() {
		return remindNormExpireDay;
	}


	public void setRemindNormExpireDay(String remindNormExpireDay) {
		this.remindNormExpireDay = remindNormExpireDay;
	}


	public String getPrimaryAgency() {
		return primaryAgency;
	}


	public void setPrimaryAgency(String primaryAgency) {
		this.primaryAgency = primaryAgency;
	}


	public String getSecondaryAgent() {
		return secondaryAgent;
	}


	public void setSecondaryAgent(String secondaryAgent) {
		this.secondaryAgent = secondaryAgent;
	}


	public String getPremiums() {
		return premiums;
	}


	public void setPremiums(String premiums) {
		this.premiums = premiums;
	}


	public String getPaymentAmount() {
		return paymentAmount;
	}


	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}


	public String getProductTypeValue() {
		return productTypeValue;
	}


	public void setProductTypeValue(String productTypeValue) {
		this.productTypeValue = productTypeValue;
	}

	public String getRenBaoXiShu() {
		return renBaoXiShu;
	}


	public void setRenBaoXiShu(String renBaoXiShu) {
		this.renBaoXiShu = renBaoXiShu;
	}


	public String getIsSendMail() {
		return isSendMail;
	}


	public void setIsSendMail(String isSendMail) {
		this.isSendMail = isSendMail;
	}


	public String getUserMail() {
		return userMail;
	}


	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getAlreadyStartTime() {
		return alreadyStartTime;
	}


	public void setAlreadyStartTime(String alreadyStartTime) {
		this.alreadyStartTime = alreadyStartTime;
	}


	public String getAlreadySendMoney() {
		return alreadySendMoney;
	}


	public void setAlreadySendMoney(String alreadySendMoney) {
		this.alreadySendMoney = alreadySendMoney;
	}

	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductTypeName() {
		return productTypeName;
	}


	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}


	public String getStateName() {
		return stateName;
	}


	public void setStateName(String stateName) {
		this.stateName = stateName;
	}


	@Override
	public String toString() {
		return "Policy [userId=" + userId + ", userName=" + userName + ", insurcompId=" + insurcompId + ", fullName="
				+ fullName + ", productId=" + productId + ", policyNo=" + policyNo + ", transDealNo=" + transDealNo
				+ ", custName=" + custName + ", policyPayHeight=" + policyPayHeight + ", amount=" + amount
				+ ", policyStartDate=" + policyStartDate + ", policyEndDate=" + policyEndDate + ", totalCarNo="
				+ totalCarNo + ", alreadyStartTime=" + alreadyStartTime + ", surplusCarNo=" + surplusCarNo
				+ ", policyTotalMoney=" + policyTotalMoney + ", alreadySendMoney=" + alreadySendMoney
				+ ", surplusCarNoMoney=" + surplusCarNoMoney + ", state=" + state + ", lastTdate=" + lastTdate
				+ ", remindNormCar=" + remindNormCar + ", remindNormMoney=" + remindNormMoney + ", remindNormExpireDay="
				+ remindNormExpireDay + ", primaryAgency=" + primaryAgency + ", secondaryAgent=" + secondaryAgent
				+ ", premiums=" + premiums + ", paymentAmount=" + paymentAmount + ", productTypeValue="
				+ productTypeValue + ", renBaoXiShu=" + renBaoXiShu + ", isSendMail=" + isSendMail + ", userMail="
				+ userMail + "]";
	}
}
