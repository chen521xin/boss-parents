/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.filter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @description
 * @data 2018年2月4日下午11:13:18
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public class SkipPathRequestMatcher implements RequestMatcher {
	private final OrRequestMatcher matcher;
	private final RequestMatcher requestMatcher;

	public SkipPathRequestMatcher(List<String> pathsToSkip) {
		List<RequestMatcher> matcherList = new ArrayList<>();
		for (String path : pathsToSkip) {
			matcherList.add(new AntPathRequestMatcher(path));
		}
		matcher = new OrRequestMatcher(matcherList);
		requestMatcher = new AntPathRequestMatcher("/**");
	}

	@Override
	public boolean matches(HttpServletRequest request) {
		return !matcher.matches(request) && requestMatcher.matches(request);
	}

}
