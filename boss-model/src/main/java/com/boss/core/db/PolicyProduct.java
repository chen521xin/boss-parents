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
 * @data 2018年3月3日下午10:06:42
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/

@ApiModel("保险产品实体")
public class PolicyProduct extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 4460215873239762213L;
	/**
	 * 保险公司Id
	 */
	@ApiModelProperty(value = "保险公司Id", dataType = "java.lang.String", required = false)
	@Size(max=32,message="保险公司名称字段长度超出限制，不能超过32位！")
	@NotNull(message="保险公司名称不能为空！")
	private String compId;
	/**
	 * 保险公司名称
	 */
	@ApiModelProperty(value = "保险公司名称", dataType = "java.lang.String", required = false)
	private String fullName;
	/**
	 * 产品名称
	 */
	@ApiModelProperty(value = "产品名称", dataType = "java.lang.String", required = false)
	@NotNull(message = "产品名称不能为空！")
	@Size(max=100,message="产品名称字段长度超出限制，不能超过100位！")
	private String productName;
	/**
	 * 产品类型值
	 */
	@ApiModelProperty(value = "产品类型值", dataType = "java.lang.String", required = false)
	@NotNull(message = "产品类型不能为空！")
	@Size(max=1,message="产品类型值长度超出限制，不能超过1位！")
	private String productTypeValue;
	/**
	 * 产品类型名称
	 */
	@ApiModelProperty(value = "产品类型名称", dataType = "java.lang.String", required = false)
	private String productTypeName;
	/**
	 * 保额，万元
	 */
	@ApiModelProperty(value = "保额，万元", dataType = "java.lang.String", required = false)
	@NotNull(message = "保额(万元)不能为空！")
	@Pattern(regexp="^[+]?\\d+(\\.\\d+)?$",message="保额字段只能输入正数！")
	private String amount;
	/**
	 * 保险条款
	 */
	@ApiModelProperty(value = "保险条款", dataType = "java.lang.String", required = false)
	private String policyInfos;

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductTypeValue() {
		return productTypeValue;
	}

	public void setProductTypeValue(String productTypeValue) {
		this.productTypeValue = productTypeValue;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPolicyInfos() {
		return policyInfos;
	}

	public void setPolicyInfos(String policyInfos) {
		this.policyInfos = policyInfos;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	@Override
	public String toString() {
		return "PolicyProduct [compId=" + compId + ", fullName=" + fullName + ", productName=" + productName
				+ ", productTypeValue=" + productTypeValue + ", productTypeName=" + productTypeName + ", amount="
				+ amount + ", policyInfos=" + policyInfos + "]";
	}

}
