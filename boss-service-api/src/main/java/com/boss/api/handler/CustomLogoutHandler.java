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

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.boss.core.struct.ResultPage;
import com.boss.core.value.BizCode;

/**
 * @description
 * @data 2018年2月4日下午8:44:15
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Component
public class CustomLogoutHandler implements LogoutSuccessHandler{

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
	   response.setHeader("Content-type", "text/json;charset=UTF-8");
	   response.setCharacterEncoding("UTF-8");
	   response.getWriter().write(ResultPage.successToJsonString(BizCode.SUCCESS_LOGOUT));
		
	}

}
