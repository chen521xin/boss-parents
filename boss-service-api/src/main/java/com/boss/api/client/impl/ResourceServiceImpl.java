/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.client.impl;

import com.boss.api.client.ResourceServiceClient;
import com.boss.core.struct.ResultPage;

/**
 * @description
 * @data 2018年2月4日下午5:36:37
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
public class ResourceServiceImpl implements ResourceServiceClient{

	@Override
	public ResultPage findResourceByRole(String role) {
		return  ResultPage.error("获取角色权限错误");
	}

	@Override
	public String findMenuByRole() {
	
		return ResultPage.successToJsonString("获取角色菜单错误");
	}

}
