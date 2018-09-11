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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alibaba.fastjson.JSONObject;
import com.boss.core.struct.ResultPage;

/**
 * @description
 * @data 2018年2月9日下午7:26:26
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Component
public class BossAccessDeniedHandler implements AccessDeniedHandler{

	@Override
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public void handle(HttpServletRequest arg0, HttpServletResponse response, AccessDeniedException exception)
			throws IOException, ServletException {
		response.getOutputStream().println(JSONObject.toJSONString(ResultPage.error(exception.getMessage())));
		
	}

}
