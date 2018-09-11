/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.alibaba.fastjson.JSONObject;
import com.boss.api.client.UserServiceClient;
import com.boss.core.db.User;
import com.boss.core.pojo.UserContextPojo;

/**
 * @description
 * @data 2018年2月9日下午6:48:16
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
public class BaseController {

	@Autowired
	private UserServiceClient userServiceClient;
	
	
	@Autowired
	private HttpServletRequest request;
	public User getUser(){
		return userServiceClient.getUserbyUsername(getUserContext().getUsername());
	}
	
	public Authentication getAuthentication(){
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public UserContextPojo getUserContext(){
		return JSONObject.parseObject(getAuthentication().getPrincipal().toString(),UserContextPojo.class);
	}
	
	/**
	 * 获取用户名
	 * @return
	 */
	public String getUserName(){
		return request.getHeader("username");
	}
}
