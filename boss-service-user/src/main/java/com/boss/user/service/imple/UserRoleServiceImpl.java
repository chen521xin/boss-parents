/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.user.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.UserRole;
import com.boss.user.mapper.UserRoleMapper;
import com.boss.user.service.UserRoleService;
import com.boss.utils.cons.CommonUtils;

/**
 * @description 
 * @data 2018年3月18日上午11:01:37
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Service
@Transactional(rollbackFor=Exception.class)
public class UserRoleServiceImpl extends BaseServiceImpl implements UserRoleService{

	@Autowired
	private UserRoleMapper userRoleMapper;
	/**
	 * 更新用户角色表
	 */
	@Override
	public void insertUserRole(UserRole userRole) {		
		userRole.changeStatus(CommonUtils.METHOD_ADD, getUserName());
		 userRoleMapper.insertUserRole(userRole);
	}

	/**
	 * 根据用户id更新角色id
	 */
	@Override
	public int updateUserRoleByUserId(UserRole userRole) {
		return userRoleMapper.updateUserRoleByUserId(userRole);
	}

	@Override
	public void deleteUserRole(String userId) {
		 userRoleMapper.deleteUserRole(userId);
	}

}


