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
import java.util.List;

import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description
 * @data 2018年2月3日下午11:35:04
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
@ApiModel("角色信息api")
public class Role extends BaseEntity implements Serializable {

	@ApiModelProperty(value = "角色描述", dataType = "java.lang.String", required = false)
	private String roleInfo;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8922088024415398467L;
	/**
	 * 角色名称
	 */
	@ApiModelProperty(value = "角色名称", dataType = "java.lang.String", required = false)
	@Size(max=50,message="角色名称字段不能超过50位")
	private String roleName;
	/**
	 * 角色代码
	 */
	@ApiModelProperty(value = "角色代码", dataType = "java.lang.String", required = false)
	private String roleCode;

	private List<PermissionInfo> permission;

	public String getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(String roleInfo) {
		this.roleInfo = roleInfo;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public List<PermissionInfo> getPermission() {
		return permission;
	}

	public void setPermission(List<PermissionInfo> permission) {
		this.permission = permission;
	}


}
