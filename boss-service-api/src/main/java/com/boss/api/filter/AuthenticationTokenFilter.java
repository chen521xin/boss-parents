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

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.boss.api.handler.JWTAuthenticationFailureHandler;
import com.boss.api.token.JwtAuthenticationToken;

/**
 * @description 请求头中token验证
 * @data 2018年2月4日下午9:54:02
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/

public class AuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter{

	private final static Logger logger=LoggerFactory.getLogger(AuthenticationTokenFilter.class);
	@Autowired
	private JWTAuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	public AuthenticationTokenFilter(RequestMatcher matcher,AuthenticationManager authenticationManager) {
		super(matcher);
		setAuthenticationManager(authenticationManager);
	}


	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String authToKen=request.getHeader("token");
		String tokenHeader = "Authorization";
		String authHeader=request.getHeader(tokenHeader);
		String tokenHead="Bearer";
		if(!StringUtils.isEmpty(authHeader)&&authHeader.startsWith(tokenHead)){
			//如果header中存在token 则覆盖掉url中的token
			authToKen=authHeader.substring(tokenHead.length());
		}
		
		return getAuthenticationManager().authenticate(new JwtAuthenticationToken(authToKen));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		SecurityContext context=SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authResult);
		SecurityContextHolder.setContext(context);
		chain.doFilter(request, response);
	}
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
	  SecurityContextHolder.clearContext();
	  logger.debug(String.format("token invalid Message: %s", failed.getMessage()));
	  authenticationFailureHandler.onAuthenticationFailure(request, response, failed);
	}

}
