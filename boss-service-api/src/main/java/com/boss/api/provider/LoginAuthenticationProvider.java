/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.boss.api.utils.UserOperationType;
import com.boss.core.db.User;

/**
 * @description Authentication用来传递验证信息
 *              provider类为具体验证逻辑，与filter一一对应
 * @data 2018年2月3日下午12:27:23
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Component
public class LoginAuthenticationProvider extends BaseAuthenticationProvider implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		User user=(User) auth.getPrincipal();
		auth=authentica(user, UserOperationType.LOGIN);
		return auth;
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}
	

}
