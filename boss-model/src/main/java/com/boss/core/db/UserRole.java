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

@ApiModel("用户角色信息")
public class UserRole extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 28876890860605148L;

	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id", dataType = "java.lang.String", required = false)
	private String userId;
	/**
	 * 角色id
	 */
	@ApiModelProperty(value = "角色id", dataType = "java.lang.String", required = false)
	private String roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserRole [userId=" + userId + ", roleId=" + roleId + "]";
	}

}
