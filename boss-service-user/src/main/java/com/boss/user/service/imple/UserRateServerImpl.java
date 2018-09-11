/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.user.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.UserRateInfo;
import com.boss.user.mapper.UserRateMapper;
import com.boss.user.service.UserRateServer;
import com.boss.utils.cons.CommonUtils;

/**
 * @description 
 * @data 2018年5月21日下午7:32:12
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Service
@Transactional(rollbackFor=Exception.class)
public class UserRateServerImpl  extends BaseServiceImpl implements UserRateServer{

	@Autowired
	private UserRateMapper userRateMapper;
	
	@Override
	public String findUserRate(UserRateInfo userRate) {
		return userRateMapper.findUserRate(userRate);
	}

	@Override
	public void insertUserRate(UserRateInfo userRate) {
		userRate.changeStatus(CommonUtils.METHOD_ADD, getUserName());
		userRateMapper.insertUserRate(userRate);
	}

	@Override
	public void updateCodeUserRate(UserRateInfo userRate) {
		userRate.changeStatus(CommonUtils.METHOD_UPDATE, getUserName());
		userRateMapper.insertUserRate(userRate);
	}

	@Override
	public void deleteUserRate(String userId) {
		userRateMapper.deleteUserRate(userId);
		
	}

	@Override
	public List<UserRateInfo> findUserRateByUserId(String userId) {
		return userRateMapper.findUserRateByUserId(userId);
	}

	
}
