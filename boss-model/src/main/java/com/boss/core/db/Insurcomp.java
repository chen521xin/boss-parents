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
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description
 * @data 2018年3月3日下午10:06:42
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/

@ApiModel("保险公司实体")
public class Insurcomp extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6024554145927888617L;
	/**
	 * 保险公司全名
	 */
	@ApiModelProperty(value = "保险公司全名", dataType = "java.lang.String", required = false)
	@NotNull(message = "保险公司名称不能为空")
	@Size(max=100,message="保险公司全名字段长度超出限制，不能超过100位")
	private String fullName;
	/**
	 * 保险公司简称
	 */
	@ApiModelProperty(value = "保险公司简称", dataType = "java.lang.String", required = false)
	@Size(max=50,message="保险公司简称字段长度超出限制，不能超过50位")
	private String simpleName;
	/**
	 * 合同起始期
	 */
	@ApiModelProperty(value = "合同起始期", dataType = "java.lang.String", required = false)
	@Size(max=10,message="保险公司全名字段长度超出限制，不能超过10位")
	private String startTime;
	/**
	 * 合同结束期
	 */
	@ApiModelProperty(value = "合同结束期", dataType = "java.lang.String", required = false)
	@Size(max=10,message="合同结束期字段长度超出限制，不能超过10位")
	private String endTime;
	/**
	 * 保险公司联系人
	 */
	@ApiModelProperty(value = "保险公司联系人", dataType = "java.lang.String", required = false)
	@Size(max=50,message="保险公司联系人字段长度超出限制，不能超过50位")
	private String linkMan;
	/**
	 * 联系人电话
	 */
	@ApiModelProperty(value = "联系人电话", dataType = "java.lang.String", required = false)
	@Size(max=50,message="保险公司联系人电话字段长度超出限制，不能超过50位")
	private String linkPhone;
	/**
	 * 保险公司地址
	 */
	@ApiModelProperty(value = "保险公司地址", dataType = "java.lang.String", required = false)
	@Size(max=100,message="保险公司地址字段长度超出限制，不能超过100位")
	private String insurcompAddress;
	/**
	 * 保险公司电话
	 */
	@ApiModelProperty(value = "保险公司电话", dataType = "java.lang.String", required = false)
	@Size(max=50,message="保险公司电话字段长度超出限制，不能超过50位")
	private String insurcompPhone;
	/**
	 * 保险公司email
	 */
	@ApiModelProperty(value = "保险公司email", dataType = "java.lang.String", required = false)
	@Size(max=100,message="保险公司邮箱字段长度超出限制，不能超过100位")
	private String insurcompEmail;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注", dataType = "java.lang.String", required = false)
	private String remark;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getInsurcompAddress() {
		return insurcompAddress;
	}

	public void setInsurcompAddress(String insurcompAddress) {
		this.insurcompAddress = insurcompAddress;
	}

	public String getInsurcompPhone() {
		return insurcompPhone;
	}

	public void setInsurcompPhone(String insurcompPhone) {
		this.insurcompPhone = insurcompPhone;
	}

	public String getInsurcompEmail() {
		return insurcompEmail;
	}

	public void setInsurcompEmail(String insurcompEmail) {
		this.insurcompEmail = insurcompEmail;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Insurcomp [fullName=" + fullName + ", simpleName=" + simpleName + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", linkMan=" + linkMan + ", linkPhone=" + linkPhone + ", insurcompAddress="
				+ insurcompAddress + ", insurcompPhone=" + insurcompPhone + ", insurcompEmail=" + insurcompEmail
				+ ", remark=" + remark + "]";
	}

}
