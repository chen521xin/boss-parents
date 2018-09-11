/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.user.service;

import com.boss.core.db.UserRole;

/**
 * @description 
 * @data 2018年3月18日上午11:01:31
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public interface UserRoleService {

	/**
	 * 插入用户角色关系表
	 * @param userRole
	 * @return
	 */
	void insertUserRole(UserRole userRole );
	
	/**
	 * 更新用户角色关系表
	 * @param userRole
	 * @return
	 */
	int updateUserRoleByUserId(UserRole userRole);
	
	/**
	 * 删除用户id
	 * @param userId
	 * @return
	 */
	void deleteUserRole(String userId);
}


