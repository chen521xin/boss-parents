/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.alibaba.fastjson.JSONObject;
import com.boss.api.utils.ApplicationConfigure;
import com.boss.api.utils.LoginFilterUtils;
import com.boss.core.db.User;
import com.boss.core.struct.ResultPage;
import com.netflix.ribbon.proxy.annotation.Http.HttpMethod;

/**
 * @description 登录验证过滤器
 * @data 2018年2月1日下午8:16:47
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	private final Logger logger = LoggerFactory.getLogger(LoginFilter.class);


	public LoginFilter(AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher("/login"));
		setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String body = IOUtils.toString(request.getInputStream(), "UTF-8");
		String sslCert = obtainSslCert(request);
		logger.info(String.format("----------sslCert:%s", sslCert));
		if (!HttpMethod.POST.equals(request.getMethod())) {
			if (logger.isDebugEnabled()) {
				logger.debug(
						String.format("Authentication method not support,Request method : %s", request.getMethod()));
			}
		}
		User user = JSONObject.parseObject(body, User.class);
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(user, null, Collections.<GrantedAuthority>emptyList()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		LoginFilterUtils.successfulAuthtication(response, auth);
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.setHeader("Content-type", "text/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.getWriter().write(JSONObject.toJSONString(ResultPage.error(failed.getMessage())));
	}
	/**
	 * 生成token的秘钥
	 * 
	 * @param request
	 * @return
	 */
	public String obtainSslCert(HttpServletRequest request) {
		String sslCert=request.getHeader(ApplicationConfigure.preAuthHeader);
		if(sslCert!=null){
			return sslCert.trim();
		}
		return null;
	}
}
