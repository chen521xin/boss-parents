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

import io.swagger.annotations.ApiModelProperty;

/**
 * @description
 * @data 2018年2月4日下午4:13:29
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
public class UserContextPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -124783380222388422L;
	/**
	 * 过期时长
	 */
	private Integer expiration;
	/**
	 * 用户id
	 */
	private String id;
	/**
	 * 用户账号
	 */
	@ApiModelProperty(value = "用户账号", dataType = "java.lang.String", required = false)
	private String fullName;
	/**
	 * 用户姓名
	 */
	@ApiModelProperty(value = "用户姓名", dataType = "java.lang.String", required = false)
	private String username;

	/**
	 * 用户邮箱
	 */
	@ApiModelProperty(value = "用户邮箱", dataType = "java.lang.String", required = false)
	private String userMail;
	/**
	 * 用户联系方式
	 */
	@ApiModelProperty(value = "用户联系方式", dataType = "java.lang.String", required = false)
	private String userMoidle;
	private String userRole;
	/**
	 * 住址
	 */
	@ApiModelProperty(value = "住址", dataType = "java.lang.String", required = false)
	private String userAddress;

	/**
	 * 代理级别:一级代理、二级代理
	 */
	@ApiModelProperty(value = "代理级别", dataType = "java.lang.String", required = false)
	private String agentId;

	/**
	 * 所属保险公司
	 */
	@ApiModelProperty(value = "所属保险公司", dataType = "java.lang.String", required = false)
	private String insurcompId;
	/**
	 * 被保险人
	 */
	private String insurant;
	/**
	 * 上级代理userId
	 */
	@ApiModelProperty(value = "上级代理userId", dataType = "java.lang.String", required = false)
	private String parentId;
	/**
	 * 为标记是否发送邮件0不发送，1为发送
	 */
	@ApiModelProperty(value = "为标记是否发送邮件", dataType = "java.lang.String", required = false)
	private String isSendMail;
	private String secert;
	/**
	 * 综合险
	 */
	private String synthesizeRate;

	/**
	 * 一般险
	 */
	private String commonlyRate;
	/**
	 * 费率
	 */
	private String userRate;

	public String getSynthesizeRate() {
		return synthesizeRate;
	}

	public void setSynthesizeRate(String synthesizeRate) {
		this.synthesizeRate = synthesizeRate;
	}

	public String getCommonlyRate() {
		return commonlyRate;
	}

	public void setCommonlyRate(String commonlyRate) {
		this.commonlyRate = commonlyRate;
	}

	public String getUserRate() {
		return userRate;
	}

	public void setUserRate(String userRate) {
		this.userRate = userRate;
	}

	public String getSecert() {
		return secert;
	}

	public void setSecert(String secert) {
		this.secert = secert;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserMoidle() {
		return userMoidle;
	}

	public void setUserMoidle(String userMoidle) {
		this.userMoidle = userMoidle;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getInsurcompId() {
		return insurcompId;
	}

	public void setInsurcompId(String insurcompId) {
		this.insurcompId = insurcompId;
	}

	public String getInsurant() {
		return insurant;
	}

	public void setInsurant(String insurant) {
		this.insurant = insurant;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIsSendMail() {
		return isSendMail;
	}

	public void setIsSendMail(String isSendMail) {
		this.isSendMail = isSendMail;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getExpiration() {
		return expiration;
	}

	public void setExpiration(Integer expiration) {
		this.expiration = expiration;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
