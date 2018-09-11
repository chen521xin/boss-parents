/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2017 by                   #
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
 * @data 2018年7月1日下午10:44:17
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public class EcargoPojo extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3763707979998513913L;
	/**
	 * 投保人
	 */
	private String holderNameQuery;
	/**
	 * 投保人地址
	 */
	private String holderAddrQuery;
	/**
	 * 被保险人
	 */

	private String recognizeeNameQuery;
	/**
	 * 被保险人地址
	 */
	private String recognizeeAddrQuery;
	/**
	 * 被保险人证件号码（身份证）
	 */
	private String recognizeeIdentyQuery;
	/**
	 * 被保险人电话
	 */
	private String recognizeePhoneQuery;
	/**
	 * 组织机构
	 */
	private String recognizeeOrgQuery;
	/**
	 * 发票号/运单号
	 */
	private String invoiceNumberQuery;
	/**
	 * 货物名称
	 */
	private String itemdetailQuery;
	/**
	 * 货物重量
	 */
	private String weightQuery;
	/**
	 * 货物大类
	 */
	private String superTypeQuery;
	/**
	 * 货物小类
	 */
	private String typeQuery;
	/**
	 * 货物数量
	 */
	private String quantityQuery;
	/**
	 * 包装词
	 */
	private String packagesQuery;
	/**
	 * 车牌号
	 */
	private String licenseNoQuery;
	/**
	 * 起运地
	 */
	private String startsiteNameQuery;
	/**
	 * 中转地
	 */
	private String viasiteNameQuery;
	/**
	 * 目的地
	 */
	private String endsiteNameQuery;
	/**
	 * 起运时间
	 */
	private String startTimeQuery;
	/**
	 * 解决争议方式
	 */
	private String disputeFunctionQuery;
	/**
	 * 保额（元）
	 */
	private String sumamountQuery;

	private String policyNoQuery;
	
	/**申请单号*/
	private String applyPolicyNo;
	
	/**投保状态*/
	private String applyStatus;

	/**投保状态*/
	public String getApplyStatus() {
		return applyStatus;
	}

	/**
	 * 
	 * @param applyStatus 投保状态
	 */
	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	/**申请单号*/
	public String getApplyPolicyNo() {
		return applyPolicyNo;
	}

	/***
	 * 
	 * @param applyNoQuery 申请单号
	 */
	public void setApplyPolicyNo(String applyPolicyNo) {
		this.applyPolicyNo = applyPolicyNo;
	}

	private String premiumQuery;

	private String isFaildQuery;
	private String insuranceTypeQuery;
	private String faildMessageQuery;

	private String userIdQuery;
	private String userId;
	 
	private String ecargoPolicyNoQuery;
	
	private String fullNameQuery;
	
	
	public String getFullNameQuery() {
		return fullNameQuery;
	}

	public void setFullNameQuery(String fullNameQuery) {
		this.fullNameQuery = fullNameQuery;
	}

	public String getEcargoPolicyNoQuery() {
		return ecargoPolicyNoQuery;
	}

	public void setEcargoPolicyNoQuery(String ecargoPolicyNoQuery) {
		this.ecargoPolicyNoQuery = ecargoPolicyNoQuery;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserIdQuery() {
		return userIdQuery;
	}

	public void setUserIdQuery(String userIdQuery) {
		this.userIdQuery = userIdQuery;
	}

	public String getFaildMessageQuery() {
		return faildMessageQuery;
	}

	public void setFaildMessageQuery(String faildMessageQuery) {
		this.faildMessageQuery = faildMessageQuery;
	}

	public String getInsuranceTypeQuery() {
		return insuranceTypeQuery;
	}

	public void setInsuranceTypeQuery(String insuranceTypeQuery) {
		this.insuranceTypeQuery = insuranceTypeQuery;
	}

	public String getHolderNameQuery() {
		return holderNameQuery;
	}

	public void setHolderNameQuery(String holderNameQuery) {
		this.holderNameQuery = holderNameQuery;
	}

	public String getHolderAddrQuery() {
		return holderAddrQuery;
	}

	public void setHolderAddrQuery(String holderAddrQuery) {
		this.holderAddrQuery = holderAddrQuery;
	}

	public String getRecognizeeNameQuery() {
		return recognizeeNameQuery;
	}

	public void setRecognizeeNameQuery(String recognizeeNameQuery) {
		this.recognizeeNameQuery = recognizeeNameQuery;
	}

	public String getRecognizeeAddrQuery() {
		return recognizeeAddrQuery;
	}

	public void setRecognizeeAddrQuery(String recognizeeAddrQuery) {
		this.recognizeeAddrQuery = recognizeeAddrQuery;
	}

	public String getRecognizeeIdentyQuery() {
		return recognizeeIdentyQuery;
	}

	public void setRecognizeeIdentyQuery(String recognizeeIdentyQuery) {
		this.recognizeeIdentyQuery = recognizeeIdentyQuery;
	}

	public String getRecognizeePhoneQuery() {
		return recognizeePhoneQuery;
	}

	public void setRecognizeePhoneQuery(String recognizeePhoneQuery) {
		this.recognizeePhoneQuery = recognizeePhoneQuery;
	}

	public String getRecognizeeOrgQuery() {
		return recognizeeOrgQuery;
	}

	public void setRecognizeeOrgQuery(String recognizeeOrgQuery) {
		this.recognizeeOrgQuery = recognizeeOrgQuery;
	}

	public String getInvoiceNumberQuery() {
		return invoiceNumberQuery;
	}

	public void setInvoiceNumberQuery(String invoiceNumberQuery) {
		this.invoiceNumberQuery = invoiceNumberQuery;
	}

	public String getItemdetailQuery() {
		return itemdetailQuery;
	}

	public void setItemdetailQuery(String itemdetailQuery) {
		this.itemdetailQuery = itemdetailQuery;
	}

	public String getWeightQuery() {
		return weightQuery;
	}

	public void setWeightQuery(String weightQuery) {
		this.weightQuery = weightQuery;
	}

	public String getSuperTypeQuery() {
		return superTypeQuery;
	}

	public void setSuperTypeQuery(String superTypeQuery) {
		this.superTypeQuery = superTypeQuery;
	}

	public String getTypeQuery() {
		return typeQuery;
	}

	public void setTypeQuery(String typeQuery) {
		this.typeQuery = typeQuery;
	}

	public String getQuantityQuery() {
		return quantityQuery;
	}

	public void setQuantityQuery(String quantityQuery) {
		this.quantityQuery = quantityQuery;
	}

	public String getPackagesQuery() {
		return packagesQuery;
	}

	public void setPackagesQuery(String packagesQuery) {
		this.packagesQuery = packagesQuery;
	}

	public String getLicenseNoQuery() {
		return licenseNoQuery;
	}

	public void setLicenseNoQuery(String licenseNoQuery) {
		this.licenseNoQuery = licenseNoQuery;
	}

	public String getStartsiteNameQuery() {
		return startsiteNameQuery;
	}

	public void setStartsiteNameQuery(String startsiteNameQuery) {
		this.startsiteNameQuery = startsiteNameQuery;
	}

	public String getViasiteNameQuery() {
		return viasiteNameQuery;
	}

	public void setViasiteNameQuery(String viasiteNameQuery) {
		this.viasiteNameQuery = viasiteNameQuery;
	}

	public String getEndsiteNameQuery() {
		return endsiteNameQuery;
	}

	public void setEndsiteNameQuery(String endsiteNameQuery) {
		this.endsiteNameQuery = endsiteNameQuery;
	}

	public String getStartTimeQuery() {
		return startTimeQuery;
	}

	public void setStartTimeQuery(String startTimeQuery) {
		this.startTimeQuery = startTimeQuery;
	}

	public String getDisputeFunctionQuery() {
		return disputeFunctionQuery;
	}

	public void setDisputeFunctionQuery(String disputeFunctionQuery) {
		this.disputeFunctionQuery = disputeFunctionQuery;
	}

	public String getSumamountQuery() {
		return sumamountQuery;
	}

	public void setSumamountQuery(String sumamountQuery) {
		this.sumamountQuery = sumamountQuery;
	}

	public String getPolicyNoQuery() {
		return policyNoQuery;
	}

	public void setPolicyNoQuery(String policyNoQuery) {
		this.policyNoQuery = policyNoQuery;
	}

	public String getPremiumQuery() {
		return premiumQuery;
	}

	public void setPremiumQuery(String premiumQuery) {
		this.premiumQuery = premiumQuery;
	}

	public String getIsFaildQuery() {
		return isFaildQuery;
	}

	public void setIsFaildQuery(String isFaildQuery) {
		this.isFaildQuery = isFaildQuery;
	}

}
