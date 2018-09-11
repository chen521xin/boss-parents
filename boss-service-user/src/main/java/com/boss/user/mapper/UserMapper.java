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

import com.boss.core.db.User;
import com.boss.db.feature.orm.mybatis.Page;

/**
 * @description
 * @data 2018年2月3日下午2:16:08
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public interface UserMapper {
	/**
	 * 验证登录用户是否删除或冻结
	 * 
	 * @param userName
	 * @return
	 */

	User findUserByUserNameAndStatus(User user);

	/**
	 * 验证用户名和密码是否一致
	 * 
	 * @param user
	 * @return
	 */

	int validateUserNameAndPassword(User user);

	/**
	 * 更新用户
	 * 
	 * @param user
	 * @return
	 */
	int updateUser(User user);

	/**
	 * 更改用户状态
	 * 
	 * @param user
	 * @return
	 */
	int updateStatus(User user);

	/**
	 * 查询用户分页
	 * 
	 * @param user
	 * @return
	 */
	List<User> findUserByPage(Page<User> page, User user);

	/**
	 * 查询用户分页
	 * 
	 * @param user
	 * @return
	 */
	List<User> findUserByPage(User user);

	/**
	 * 查询用户分页
	 * 
	 * @param user
	 * @return
	 */
	List<User> findUserByPageAndProxy(Page<User> page, User user);

	/**
	 * 插入用户
	 * 
	 * @param user
	 * @return
	 */
	int insertUser(User user);

	/**
	 * 校验用户名是否在系统存在
	 * 
	 * @param username
	 * @return
	 */
	int selectCountByUsername(String username);

	/**
	 * 验证删除人员下面是否有有效保单
	 * 
	 * @param id
	 * @return
	 */
	int validatePolicyByUserId(String id);

	List<User> findUser(User user);

	User findParentUser(String parent);

	int updateUserByAdmin(User user);

	String selectUserIsAllowEcargo(String userId);
}
