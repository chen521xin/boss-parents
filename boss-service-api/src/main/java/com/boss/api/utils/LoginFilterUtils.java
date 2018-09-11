/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.boss.core.pojo.UserContextPojo;
import com.boss.core.struct.ResultPage;

/**
 * @description 登录成功生成token
 * @data 2018年2月4日下午7:08:27
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Component
public class LoginFilterUtils {

	public static void successfulAuthtication(HttpServletResponse response,Authentication auth) throws IOException{
		UserContextPojo userContext=JSONObject.parseObject(auth.getPrincipal().toString(),UserContextPojo.class);
		JwtTokenUtils.secret=userContext.getSecert();
		JwtTokenUtils.expiration=userContext.getExpiration();
		response.setHeader("Content-type", "text/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader(JwtTokenUtils.HEADER_STRING, String.format("%s %s", JwtTokenUtils.TOKEN_PERFIX,JwtTokenUtils.generateToken(auth.getName(), auth.getAuthorities())));
		// 登录成功把用户信息返回前端，secert 和expiration不可返回
		userContext.setSecert(null);
		userContext.setExpiration(null);
		userContext.setParentId(null);
		userContext.setAgentId(null);
		userContext.setInsurcompId(null);
		response.getWriter().write(ResultPage.successToJsonString(userContext));
	}
}
