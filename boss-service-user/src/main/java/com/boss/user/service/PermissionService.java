/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.user.service;

import java.util.List;

import com.boss.core.db.PermissionInfo;

/**
 * @description 
 * @data 2018年3月25日下午8:20:47
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public interface PermissionService {

	/**
	 * 为角色增加权限
	 * @param permission
	 * @return
	 */
	int addPermission(PermissionInfo permission);
	/**
	 * 根据id删除权限
	 * @param id
	 * @return
	 */
	int deletePermissionInfo(String id);
	/**
	 * 根据角色查询其拥有的所有权限
	 * @param roleId
	 * @return
	 */
	List<String> selectPermissionIdByRoleId(String roleId);
}


