/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.user.service;

import java.text.ParseException;
import java.util.List;

import com.boss.core.db.User;
import com.boss.core.struct.ResultPage;
import com.boss.db.feature.orm.mybatis.Page;

/**
 * @description
 * @data 2018年2月3日下午2:06:18
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public interface UserService {

	/**
	 * 验证登录用户是否删除或冻结
	 * @param userName
	 * @return
	 */
	User findUserByUserName(String username);
	/**
	 * 验证用户名和密码是否一致
	 * @param user
	 * @return
	 */
	boolean validateUserNameAndPassword(User user);
	
	/**
	 * 登录验证逻辑
	 * @return
	 * @throws ParseException 
	 */
	ResultPage validateUser(User user);
	
	/**
	 * 更新密码错误次数
	 * @param user
	 * @return
	 */
	User recordLoginInFailErros(User user);

	/**
	 * 更改用户状态
	 * @param user
	 * @return
	 */
	void updateStatus(User user);
	/**
	 * 查询用户分页
	 * @param user
	 * @return
	 */
	Page<User> findUserByPage(Page<User> page,User user);
	/**
	 * 查询所有用户不分页
	 * @param user
	 * @return
	 */
	List<User> findUserByPage(User user);
	
	/**
	 * 插入用户
	 * @param user
	 * @return
	 */
	void insertUser(User user);
	/**
	 * 更新用户
	 */
	void deleteUser(String id);
	
	void updateUser(User user);
	
	List<User> getAllUser(User user);
	
	void updPwd(User user);
	String selectUserIsAllowEcargo(String userId);
}
