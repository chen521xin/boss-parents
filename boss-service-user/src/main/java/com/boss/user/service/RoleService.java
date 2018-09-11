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

import com.boss.core.db.Role;
import com.boss.db.feature.orm.mybatis.Page;

/**
 * @description
 * @data 2018年2月3日下午11:42:54
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public interface RoleService {

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
	void updateRoleById(Role role);
	/**
	 * 删除角色信息
	 * @param role
	 * @return
	 */
	void delteRoleById(String id);
	
	/**
	 * 列表查询角色信息
	 * @param role
	 * @return
	 */
	List<Role> findRoleListByPage(Page<Role> page,Role role);
	/**
	 *  查看所有不分页
	 * @param role
	 * @return
	 */
	List<Role> findRoleListByPage();
	String selectRoleCodeById(String id);
}
