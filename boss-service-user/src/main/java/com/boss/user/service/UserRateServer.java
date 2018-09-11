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

import com.boss.core.db.UserRateInfo;

/**
 * @description 
 * @data 2018年5月21日下午7:31:58
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public interface UserRateServer {
	/**
	 * 查找用户费率
	 * 
	 * @param userRate
	 * @return
	 */
	String findUserRate(UserRateInfo userRate);

	/**
	 * 新增费率
	 * 
	 * @param userRate
	 * @return
	 */
	void insertUserRate(UserRateInfo userRate);

	/**
	 * 修改费率
	 * 
	 * @param userRate
	 * @return
	 */
	void updateCodeUserRate(UserRateInfo userRate);
	
	/**
	 * 删除
	 * @param userId
	 */
	void deleteUserRate(String userId);
	List<UserRateInfo> findUserRateByUserId(String userId);
}
