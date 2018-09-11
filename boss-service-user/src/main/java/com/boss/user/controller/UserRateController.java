/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boss.core.db.User;
import com.boss.core.struct.ResultInfo;
import com.boss.user.service.imple.UserRateServerImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @description
 * @data 2018年2月3日下午2:03:56
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@RestController
@RequestMapping("/userRate")
public class UserRateController extends BaseController {

	@Autowired
	private UserRateServerImpl UserRateServer;
	
	/**
	 * 删除用户
	 * 
	 * @param user
	 * @return
	 */
	@GetMapping("/{userId}")
	@ApiOperation(value = "删除用户", httpMethod = "DELETE", response = User.class, notes = "删除用户")
	public ResultInfo getUserRate(@ApiParam(required = true, name = "id", value = "用户id") @PathVariable String userId) {		
		return ResultInfo.success(UserRateServer.findUserRateByUserId(userId));
	}

}
