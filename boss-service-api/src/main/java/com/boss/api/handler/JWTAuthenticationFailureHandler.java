/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alibaba.fastjson.JSONObject;
import com.boss.core.struct.ResultPage;

/**
 * @description
 * @data 2018年2月4日下午10:07:57
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Component
public class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler{

	@Override
	@ResponseBody
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException auth)
			throws IOException, ServletException {
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.getWriter().println(JSONObject.toJSONString(ResultPage.error(auth.getMessage(),HttpStatus.FORBIDDEN.value())));
		response.getWriter().close();
		
	}

}
