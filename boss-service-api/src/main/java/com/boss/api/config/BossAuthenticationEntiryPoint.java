/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @description 用户访问资源时，发生授权异常（AuthenticationException）或认证异（AccessDeniedException），
 * ExceptionTranslationFilter通过调用AuthenticationEntryPoint的commence方法发起认证过程 
 * 提供凭证入口，真正的认证信息filter完成
 * @data 2018年2月4日下午7:59:39
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
@Component
public class BossAuthenticationEntiryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException auth)
			throws IOException, ServletException {
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getOutputStream().println(auth.getMessage());
	}

}
