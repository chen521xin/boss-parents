/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.client.impl;

import com.boss.api.client.UserServiceClient;
import com.boss.core.db.User;
import com.boss.core.struct.ResultInfo;
import com.boss.core.struct.ResultPage;

/**
 * @description
 * @data 2017年11月28日下午5:18:23
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
public class UserServiceClientImpl implements UserServiceClient{



	@Override
	public ResultPage validateUser(User user) {
		return ResultPage.error("验证错误");
	}

	@Override
	public ResultInfo updateUser(User user) {
		return ResultInfo.error("修改失败");
	}

	@Override
	public User getUserbyUsername(String username) {
		return new User();
	}

	@Override
	public ResultInfo updateStatus(User user) {
		return ResultInfo.error("删除失败");
	}



}
