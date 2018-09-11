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
 * @data 2018年2月4日下午5:10:00
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
@ApiModel("菜单借口api")
public class Resource extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9079737558814649008L;

	/**
	 * 资源代码
	 */
	@ApiModelProperty(value = "资源代码", dataType = "java.lang.String", required = false)
	private String code;
	/**
	 * 资源名称
	 */
	@ApiModelProperty(value = "资源名称", dataType = "java.lang.String", required = false)
	private String name;
	/**
	 * 统一资源索引
	 */
	@ApiModelProperty(value = "统一资源索引", dataType = "java.lang.String", required = false)
	private String url;
	/**
	 * 菜单状态
	 */
	@ApiModelProperty(value = "菜单状态", dataType = "java.lang.String", required = false)
	private Integer status;
	/**
	 * 父资源ID
	 */
	@ApiModelProperty(value = "父资源ID", dataType = "java.lang.String", required = false)
	private String parentId;
	/**
	 * 序号
	 */
	@ApiModelProperty(value = "序号", dataType = "java.lang.String", required = false)
	private Integer index;
	/**
	 * 资源类别：1：菜单，2：接口
	 */
	@ApiModelProperty(value = "资源类别：1：菜单，2：接口", dataType = "java.lang.String", required = false)
	private String resourceType;
	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述", dataType = "java.lang.String", required = false)
	private String desc;
	
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序", dataType = "java.lang.String", required = false)
	private String sort;
	
	private String icon;
	private String roleId;
	private String roleCode;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
 
	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	 
}
