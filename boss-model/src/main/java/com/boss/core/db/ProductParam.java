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
@ApiModel("产品参数")
public class ProductParam extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1461542866216225221L;
	@ApiModelProperty(value = "产品名称", dataType = "java.lang.String", required = false)
	@NotNull(message = "产品名称不能为空！")
	@Size(max=32,message="产品名称超过限制，不能超过32位！")
	private String productId;
	/**
	 * 产品名称
	 */
	@ApiModelProperty(value = "产品名称", dataType = "java.lang.String", required = false)
	private String productName;
	/**
	 * 参数名称
	 */
	@ApiModelProperty(value = "参数名称", dataType = "java.lang.String", required = false)
	@NotNull(message = "参数名称不能为空！")
	@Size(max=500,message="参数名称超过限制，不能超过32位！")
	private String paramName;
	/**
	 * 发车系数
	 */
	@ApiModelProperty(value = "发车系数", dataType = "java.lang.String", required = false)
	@Pattern(regexp="^[+]?\\d+(\\.\\d+)?$",message="发车系统字段只能输入正数！")
	private String paramValue1;
	/**
	 * 保额额度
	 */
	@ApiModelProperty(value = "保额额度", dataType = "java.lang.String", required = false)
	@NotNull(message = "保额额度不能为空！")
	@Pattern(regexp="^[+]?\\d+(\\.\\d+)?$",message="保额额度字段只能输入正数！")	
	private String paramValue2;
	/**
	 * 类型描述
	 */
	@ApiModelProperty(value = "类型描述", dataType = "java.lang.String", required = false)
	private String typeInfo;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue1() {
		return paramValue1;
	}

	public void setParamValue1(String paramValue1) {
		this.paramValue1 = paramValue1;
	}

	public String getParamValue2() {
		return paramValue2;
	}

	public void setParamValue2(String paramValue2) {
		this.paramValue2 = paramValue2;
	}

	public String getTypeInfo() {
		return typeInfo;
	}

	public void setTypeInfo(String typeInfo) {
		this.typeInfo = typeInfo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "ProductParam [productId=" + productId + ", productName=" + productName + ", paramName=" + paramName
				+ ", paramValue1=" + paramValue1 + ", paramValue2=" + paramValue2 + ", typeInfo=" + typeInfo + "]";
	}

}
