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

/**
 * @description
 * @data 2018年5月11日下午2:04:22
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public class EcargoInfo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -7840188310890220477L;
	
	/**投保单号码*/
	private String applyPolicyNo;
	
	/**投保单状态*/
	private String applyStatus;
	
	
	

	/**投到ecargo的费率 */
	private  String ecRatio;
	
	/**投到ecargo的费率 */
	public  String getEcRatio() {
		return ecRatio;
	}

	/** 投到ecargo的费率 */
	public void setEcRatio(String ecRatio) {
		this.ecRatio = ecRatio;
	}

	/**
	 * 投保人
	 */
	@NotNull(message = "投保人不能为空！")
	@Size(max = 50, message = "投保人字段长度超出限制，不能超过50位！")
	private String holderName;
	private String userId;
	/**
	 * 投保人地址
	 */
	@Size(max = 200, message = "投保人地址字段长度超出限制，不能超过50位！")
	private String holderAddr;
	/**
	 * 被保险人
	 */
	@Size(max = 50, message = "被保险人字段长度超出限制，不能超过50位！")
	@NotNull(message = "被保险人不能为空！")
	private String recognizeeName;
	/**
	 * 被保险人地址
	 */
	@Size(max = 200, message = "被保险人地址字段长度超出限制，不能超过200位！")
	private String recognizeeAddr;
	
	//@Pattern(regexp = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$", message = "身份证号格式错误！")
	//@NotNull(message="被保险人证件号码不能为空！")
	/**
	 * 被保险人证件号码（身份证）
	 * 
	 */
	private String recognizeeIdenty;
	/**
	 * 被保险人电话
	 */
	@Pattern(regexp = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$", message = "用户联系方式格式错误！")
	private String recognizeePhone;
	/**
	 * 组织机构
	 */
	@Size(max = 50, message = "组织机构地址字段长度超出限制，不能超过50位！")
	private String recognizeeOrg;
	/**
	 * 发票号/运单号
	 */
	@Size(max = 50, message = "发票号/运单号字段长度超出限制，不能超过50位！")
	private String invoiceNumber;
	/**
	 * 货物名称
	 */
	@Size(max = 50, message = "货物名称字段长度超出限制，不能超过50位！")
	@NotNull(message = "货物名称不能为空！")
	private String itemdetail;
	/**
	 * 货物重量
	 */
	@Size(max = 50, message = "货物重量字段长度超出限制，不能超过50位！")
	@NotNull(message = "重量不能为空！")
	private String weight;
	/**
	 * 货物大类
	 */
	@NotNull(message = "货物大类不能为空！")
	private String superType;
	/**
	 * 货物小类
	 */
	@NotNull(message = "货物小类不能为空！")
	private String type;
	/**
	 * 货物数量
	 */
	@Pattern(regexp = "^[1-9][0-9]{0,10}$", message = "货物数量必须为1~11位的正整数！")
	@NotNull(message = "货物数量不能为空！")
	private String quantity;
	/**
	 * 包装词
	 */
	private String packages;
	/**
	 * 车牌号
	 */
	@NotNull(message = "车牌号不能为空！")
	@Size(max = 50, message = "车牌号字段长度超出限制，不能超过50位！")
	private String licenseNo;
	/**
	 * 起运地
	 */
	@Size(max = 50, message = "起运地字段长度超出限制，不能超过50位！")
	@NotNull(message = "起运地不能为空！")
	private String startsiteName;
	/**
	 * 中转地
	 */
	@Size(max = 50, message = "中转地字段长度超出限制，不能超过50位！")
	@NotNull(message = "中转地不能为空！")
	private String viasiteName;
	/**
	 * 目的地
	 */
	@Size(max = 50, message = "目的地字段长度超出限制，不能超过50位！")
	@NotNull(message = "目的地不能为空！")
	private String endsiteName;
	/**
	 * 起运时间
	 */

	@NotNull(message = "起运时间不能为空！")
	private String startTime;
	/**
	 * 解决争议方式
	 */
	private String disputeFunction;
	/**
	 * 保额（元）
	 */
	@NotNull(message = "保额（元）不能为空！")
	@Pattern(regexp = "^([1-9]([0-9]{1,11})?|0)([.][0-9]{1,2})?$", message = "保额格式字段错误！")
	private String sumamount;

	private String policyNo;

	@NotNull(message = "投保类型字段不能为空！")
	private String insuranceType;

	private String premium;
	private String isFaild;

	private String faildMessage;

	/**
	 * 费率
	 */
	private double ratio;

	/**
	 * 费率
	 */
	public double getRatio() {
		return ratio;
	}

	/**
	 * 费率
	 */
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	public String getFaildMessage() {
		return faildMessage;
	}

	public void setFaildMessage(String faildMessage) {
		this.faildMessage = faildMessage;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getHolderAddr() {
		return holderAddr;
	}

	public void setHolderAddr(String holderAddr) {
		this.holderAddr = holderAddr;
	}

	public String getRecognizeeName() {
		return recognizeeName;
	}

	public void setRecognizeeName(String recognizeeName) {
		this.recognizeeName = recognizeeName;
	}

	public String getRecognizeeAddr() {
		return recognizeeAddr;
	}

	public void setRecognizeeAddr(String recognizeeAddr) {
		this.recognizeeAddr = recognizeeAddr;
	}

	public String getRecognizeeIdenty() {
		return recognizeeIdenty;
	}

	public void setRecognizeeIdenty(String recognizeeIdenty) {
		this.recognizeeIdenty = recognizeeIdenty;
	}

	public String getRecognizeePhone() {
		return recognizeePhone;
	}

	public void setRecognizeePhone(String recognizeePhone) {
		this.recognizeePhone = recognizeePhone;
	}

	public String getRecognizeeOrg() {
		return recognizeeOrg;
	}

	public void setRecognizeeOrg(String recognizeeOrg) {
		this.recognizeeOrg = recognizeeOrg;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getItemdetail() {
		return itemdetail;
	}

	public void setItemdetail(String itemdetail) {
		this.itemdetail = itemdetail;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getSuperType() {
		return superType;
	}

	public void setSuperType(String superType) {
		this.superType = superType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getStartsiteName() {
		return startsiteName;
	}

	public void setStartsiteName(String startsiteName) {
		this.startsiteName = startsiteName;
	}

	public String getViasiteName() {
		return viasiteName;
	}

	public void setViasiteName(String viasiteName) {
		this.viasiteName = viasiteName;
	}

	public String getEndsiteName() {
		return endsiteName;
	}

	public void setEndsiteName(String endsiteName) {
		this.endsiteName = endsiteName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getDisputeFunction() {
		return disputeFunction;
	}

	public void setDisputeFunction(String disputeFunction) {
		this.disputeFunction = disputeFunction;
	}

	public String getSumamount() {
		return sumamount;
	}

	public void setSumamount(String sumamount) {
		this.sumamount = sumamount;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPremium() {
		return premium;
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}

	public String getIsFaild() {
		return isFaild;
	}

	public void setIsFaild(String isFaild) {
		this.isFaild = isFaild;
	}

	/**
	 * 
	 * @param applyStatus 投保单状态
	 */
	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	/**投保单号码*/
	public String getApplyPolicyNo() {
		return applyPolicyNo;
	}
	/***
	 * 
	 * @param applyPolicyNo 投保单号码
	 */
	public void setApplyPolicyNo(String applyPolicyNo) {
		this.applyPolicyNo = applyPolicyNo;
	}
	
	/**投保单状态*/
	public String getApplyStatus() {
		return applyStatus;
	}
}
