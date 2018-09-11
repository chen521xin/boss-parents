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
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description
 * @data 2018年2月2日下午11:20:35
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
@ApiModel("用户信息")
public class User extends BaseEntity implements Serializable {


	@ApiModelProperty
	private static final long serialVersionUID = -1754611404004642354L;

	private String insuranceType;
	private Role role;

	@NotNull(message = "用户所属角色不能为空!")
	@Size(max=32, message = "用户所属角色字段长度超出限制，不能超过32位!")
	private String roleId;
	/**
	 * 用户登录名
	 */
	@ApiModelProperty(value = "用户登录名", dataType = "java.lang.String", required = false)
	@NotNull(message = "用户账号不能为空!")
	@Size(min=1,max=256, message = "用户账号字段长度超出限制，不能超过256位!")
	private String username;
	/**
	 * 用户全名
	 */
	@ApiModelProperty(value = "用户全名", dataType = "java.lang.String", required = false)
	@NotNull(message = "用户全名不能为空!")
	@Size(min=1,max=256, message = "用户全名字段长度超出限制，不能超过256位!")
	private String fullName;
	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码", dataType = "java.lang.String", required = false)
	@Size(max=100,message="密码字段长度超出限制，不能超过20位!")
	private String password;
	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码", dataType = "java.lang.String", required = false)
	@Size(max=20,message="密码字段长度超出限制，不能超过20位!")
	private String againPassword;
	


	/**
	 * 用户邮箱
	 */
	@ApiModelProperty(value = "用户邮箱", dataType = "java.lang.String", required = false)
	@NotNull(message="用户邮箱不能为空")
	@Size(max=100, message = "用户邮箱字段长度超出限制，不能超过256位!")
	@Pattern(regexp = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$", message = "邮箱格式错误!")
	private String userMail;
	/**
	 * 用户联系方式
	 */
	@ApiModelProperty(value = "用户联系方式", dataType = "java.lang.String", required = false)
	@Pattern(regexp = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$", message = "用户联系方式格式错误!")
	private String userMoidle;
	/**
	 * 住址
	 */
	@ApiModelProperty(value = "住址", dataType = "java.lang.String", required = false)
	@Size(max=200, message = "住址字段长度超出限制，不能超过200位!")
	private String userAddress;
	/**
	 * 身份证号
	 */
	@ApiModelProperty(value = "身份证号", dataType = "java.lang.String", required = false)
	@Size(max=18, message = "住址字段长度超出限制，不能超过18位!")
	@Pattern(regexp = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$", message = "身份证号格式错误!")
	private String idcardNo;
	/**
	 * 代理级别:一级代理、二级代理
	 */
	@ApiModelProperty(value = "代理级别:一级代理、二级代理", dataType = "java.lang.String", required = false)
	@Size(max=1, message = "代理级别长度超出限制，不能超过1位!")
	private String agentLevel;
	/**
	 * 代理级别:一级代理、二级代理
	 */
	@ApiModelProperty(value = "代理级别:一级代理、二级代理", dataType = "java.lang.String", required = false)
	private String agentLevelName;
	/**
	 * 上级代理userId
	 */
	@ApiModelProperty(value = "上级代理userId", dataType = "java.lang.String", required = false)
	@Size(max=32, message = "上级代理字段长度超出限制，不能超过32位!")
	private String parentId;
	/**
	 * 上级代理名称
	 */
	private String parentName;
	/**
	 * 所属保险公司
	 */
	@ApiModelProperty(value = "所属保险公司", dataType = "java.lang.String", required = false)
	@Size(max=32, message = "所属保险公司字段长度超出限制，不能超过32位!")
	private String insurcompId;
	/**
	 * 被保险人
	 */
	@ApiModelProperty(value = "被保险人", dataType = "java.lang.String", required = false)
	@Size(max=256, message = "被保险人字段长度超出限制，不能超过256位!")
	private String insurant;
	/**
	 * 为标记是否发送邮件0不发送，1为发送
	 */
	@ApiModelProperty(value = "为标记是否发送邮件0不发送，1为发送", dataType = "java.lang.String", required = false)
	@Size(max=1, message = "是否发送邮件长度超出限制，不能超过过1位!")
	@NotNull(message="是否发送邮件不能为空")
	private String isSendMail;
	/**
	 * 为标记是否发送邮件0不发送，1为发送
	 */
	@ApiModelProperty(value = "为标记是否发送邮件0不发送，1为发送", dataType = "java.lang.String", required = false)
	@Size(max=1, message = "是否发送邮件长度超出限制，不能超过过1位!")
	private String isSendMailName;
	/**
	 * 密码错误次数
	 */
	@ApiModelProperty(value = "密码错误次数!", dataType = "java.lang.String", required = false)
	private Integer countPwdErrors;
	/**
	 * 锁定开始时间。约定1小时
	 * 
	 */
	@ApiModelProperty(value = "锁定开始时间。约定1小时", dataType = "java.lang.String", required = false)
	private Date userLockTime;
	/**
	 * 用户状态
	 */
	@ApiModelProperty(value = "用户状态", dataType = "java.lang.String", required = false)
	@NotNull(message="用户状态字段不能为空!")
	@Size(max=1, message = "用户状态字段长度超出限制，不能超过1位!")
	private String status;
	/**
	 * 用户状态
	 */
	@ApiModelProperty(value = "用户状态", dataType = "java.lang.String", required = false)
	private String statusName;

	/**
	 * 是否第一次登陆
	 */
	@ApiModelProperty(value = "是否第一次登陆", dataType = "java.lang.String", required = false)
	private String isFirstLogin;

	/**
	 * 用户所属角色id
	 */
	@ApiModelProperty(value = "用户所属角色id", dataType = "java.lang.String", required = false)
	private String roleCode;

	/**
	 * 综合险
	 */
	@Pattern(regexp = "^([1-9]([0-9]{1,2})?|0)([.][0-9]{1,10})?$", message = "综合险费率字段格式错误，必须输入数字，且整数最多输入3位，小数最多输入10位")
	
	private String synthesizeRate;

	/**
	 * 一般险
	 */
	@Pattern(regexp = "^([1-9]([0-9]{1,2})?|0)([.][0-9]{1,10})?$", message = "一般险字段格式错误，必须输入数字，且整数最多输入3位，小数最多输入10位")
	private String commonlyRate;

	private List<UserRateInfo> userRate;

	private String rate;
	/**
	 * 角色名称
	 */
	private String roleName;

	private String currentUserRate;

	/**
	 * 一级代理
	 */
	private String agentName1;
	/**
	 * 二级代理
	 */
	private String agentName2;
	
	/**
	 * 是否允许ecargo投保
	 */
	private String isAllowEcargo;
	
	/**
	 * 是否允许ecargo投保
	 */
	private String isAllowEcargoName;
	
	public String getIsAllowEcargoName() {
		return isAllowEcargoName;
	}

	public void setIsAllowEcargoName(String isAllowEcargoName) {
		this.isAllowEcargoName = isAllowEcargoName;
	}

	public String getIsAllowEcargo() {
		return isAllowEcargo;
	}

	public void setIsAllowEcargo(String isAllowEcargo) {
		this.isAllowEcargo = isAllowEcargo;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getIdcardNo() {
		return idcardNo;
	}

	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}

	public String getAgentLevel() {
		return agentLevel;
	}

	public void setAgentLevel(String agentLevel) {
		this.agentLevel = agentLevel;
	}

	public String getParentId() {
		return parentId;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public String getIsSendMail() {
		return isSendMail;
	}

	public void setIsSendMail(String isSendMail) {
		this.isSendMail = isSendMail;
	}

	public Integer getCountPwdErrors() {
		return countPwdErrors;
	}

	public void setCountPwdErrors(Integer countPwdErrors) {
		this.countPwdErrors = countPwdErrors;
	}

	public Date getUserLockTime() {
		return userLockTime;
	}

	public void setUserLockTime(Date userLockTime) {
		this.userLockTime = userLockTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsFirstLogin() {
		return isFirstLogin;
	}

	public void setIsFirstLogin(String isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

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

	public List<UserRateInfo> getUserRate() {
		return userRate;
	}

	public void setUserRate(List<UserRateInfo> userRate) {
		
		this.userRate = userRate;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCurrentUserRate() {
		return currentUserRate;
	}

	public void setCurrentUserRate(String currentUserRate) {
		this.currentUserRate = currentUserRate;
	}

	public String getAgentName1() {
		return agentName1;
	}

	public void setAgentName1(String agentName1) {
		this.agentName1 = agentName1;
	}

	public String getAgentName2() {
		return agentName2;
	}

	public void setAgentName2(String agentName2) {
		this.agentName2 = agentName2;
	}

	public String getAgentLevelName() {
		return agentLevelName;
	}

	public void setAgentLevelName(String agentLevelName) {
		this.agentLevelName = agentLevelName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getAgainPassword() {
		return againPassword;
	}

	public void setAgainPassword(String againPassword) {
		this.againPassword = againPassword;
	}
	
	
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getIsSendMailName() {
		return isSendMailName;
	}

	public void setIsSendMailName(String isSendMailName) {
		this.isSendMailName = isSendMailName;
	}

	@Override
	public String toString() {
		return "User [role=" + role + ", roleId=" + roleId + ", username=" + username + ", fullName=" + fullName
				+ ", password=" + password + ", userMail=" + userMail + ", userMoidle=" + userMoidle + ", userAddress="
				+ userAddress + ", idcardNo=" + idcardNo + ", agentLevel=" + agentLevel + ", parentId=" + parentId
				+ ", insurcompId=" + insurcompId + ", insurant=" + insurant + ", isSendMail=" + isSendMail
				+ ", countPwdErrors=" + countPwdErrors + ", userLockTime=" + userLockTime + ", status=" + status
				+ ", isFirstLogin=" + isFirstLogin + ", roleCode=" + roleCode + ", synthesizeRate=" + synthesizeRate
				+ ", commonlyRate=" + commonlyRate + ", userRate=" + userRate + ", roleName=" + roleName
				+ ", currentUserRate=" + currentUserRate + "]";
	}
	


}
