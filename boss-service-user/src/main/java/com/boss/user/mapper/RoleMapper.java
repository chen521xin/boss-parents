/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.user.mapper;

import java.util.List;

import com.boss.core.db.Role;
import com.boss.db.feature.orm.mybatis.Page;

/**
 * @description
 * @data 2018年2月3日下午11:39:32
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public interface RoleMapper {

	/**
	 * 根据用户名查找角色信息
	 * @param username
	 * @return
	 */
	Role findRoleByUsername(String username);
	/**
	 * 更新角色信息
	 * @param role
	 * @return
	 */
	int updateRoleById(Role role);
	
	/**
	 * 列表查询角色信息
	 * @param role
	 * @return
	 */
	List<Role> findRoleListByPage(Page<Role> page,Role role);
	/**
	 * 列表查询角色不分页
	 * @param role
	 * @return
	 */
	List<Role> findRoleListByPage(Role role);
	int validateDeleteRole(String id);
	
	String selectRoleCodeById(String id);
}
