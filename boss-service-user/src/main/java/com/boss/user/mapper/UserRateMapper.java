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

import com.boss.core.db.UserRateInfo;

/**
 * @description
 * @data 2018年2月3日下午2:16:08
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public interface UserRateMapper {
	
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
	int insertUserRate(UserRateInfo userRate);

	/**
	 * 修改费率
	 * 
	 * @param userRate
	 * @return
	 */
	int updateUserRate(UserRateInfo userRate);
	
	int deleteUserRate(String userRate);
	
	List<UserRateInfo> findUserRateByUserId(String userId);
	

}
