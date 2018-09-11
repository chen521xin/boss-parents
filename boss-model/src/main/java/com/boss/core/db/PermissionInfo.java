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

/**
 * @description
 * @data 2018年3月21日下午11:49:41
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public class PermissionInfo extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 576824171868694032L;
	private String roleId;
	private String resourceId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	@Override
	public String toString() {
		return "PermissionInfo [roleId=" + roleId + ", resourceId=" + resourceId + "]";
	}

}
