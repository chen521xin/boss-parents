/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * @description
 * @data 2018年2月5日下午12:02:15
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2833131922205512624L;
	private final String token;

	public JwtAuthenticationToken(String token) {
		super(null);
		this.token = token;
		this.setAuthenticated(false);
	}

	@Override
	public void setAuthenticated(boolean authenticated) {
		if (authenticated) {
			throw new IllegalArgumentException(
					"Cannot set this token to trusted - use constructor which takes a GrantedAuthority list insted");

		}
		super.setAuthenticated(false);
	}

	@Override
	public Object getCredentials() {
		return this.token;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

}
