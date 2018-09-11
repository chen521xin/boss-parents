/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.boss.api.filter.AuthenticationTokenFilter;
import com.boss.api.filter.LoginFilter;
import com.boss.api.filter.SkipPathRequestMatcher;
import com.boss.api.handler.CustomLogoutHandler;
import com.boss.api.provider.JwtAuthenticationProvider;
import com.boss.api.provider.LoginAuthenticationProvider;
import com.boss.utils.cons.CommonUtils;

/**
 * @description
 * @data 2018年2月1日下午8:14:44
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Configurable
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	
	@Autowired
	private LoginAuthenticationProvider loginAuthenticationProvider;

	@Autowired
	private BossAuthenticationEntiryPoint bossAuthenticationEntryPoint;

	@Autowired
	private JwtAuthenticationProvider authenticationProvider;
	
	@Autowired
	private CustomLogoutHandler customLogoutHandler;

	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth){
		auth.authenticationProvider(loginAuthenticationProvider);
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(loginAuthenticationProvider);
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().exceptionHandling()
		.authenticationEntryPoint(bossAuthenticationEntryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy((SessionCreationPolicy.STATELESS))
		.and().authorizeRequests()
		.antMatchers(CommonUtils.SKIP_URL).permitAll()
		.anyRequest().authenticated()
		.and().logout().logoutUrl("/logout").logoutSuccessHandler(customLogoutHandler)
		.and().rememberMe();
		http.headers().cacheControl();
		http.addFilterBefore(new LoginFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(authenticationTokenFilteBean(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public AuthenticationTokenFilter authenticationTokenFilteBean() throws Exception{
		List<String> pathToSkip=Arrays.asList(CommonUtils.SKIP_URL);
		SkipPathRequestMatcher matcher=new SkipPathRequestMatcher(pathToSkip);
		return new AuthenticationTokenFilter(matcher,authenticationManager());
	}
}
