/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.boss.core.pojo.UserContextPojo;
import com.boss.utils.RequestIpUtils;
import com.boss.utils.StringUtil;
import com.boss.utils.cons.CommonUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @description
 * @data 2018年2月6日下午6:56:42
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Component
public class AuthHeaderFilter extends ZuulFilter{
	private static final Logger logger=LoggerFactory.getLogger(AuthHeaderFilter.class);


	@Autowired
	HttpServletRequest request;
	
	@Value("${redis.expire.time}")
	private Integer expireTime;
	@Override
	public Object run() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		RequestContext requestContext=RequestContext.getCurrentContext();
		String anonymousUser = "anonymousUser";
		if(!anonymousUser.equals(authentication.getPrincipal().toString())){
			UserContextPojo userContextPojo=JSONObject.parseObject(authentication.getPrincipal().toString(),UserContextPojo.class);
			
			try {
				requestContext.getRequest().setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.debug("Setting the character encoding format failed!",e);
			}
			requestContext.addZuulRequestHeader("username",StringUtil.convertString( userContextPojo.getUsername(), CommonUtils.CODE_UTF, CommonUtils.CODE_ISO));
			requestContext.addZuulRequestHeader("userRole", userContextPojo.getUserRole());
			requestContext.addZuulRequestHeader("fullName", StringUtil.convertString(userContextPojo.getFullName(), CommonUtils.CODE_UTF, CommonUtils.CODE_ISO));
			requestContext.addZuulRequestHeader("userId", userContextPojo.getId());
			requestContext.addZuulRequestHeader("remoteIp", RequestIpUtils.getIPAddr(request));
			requestContext.addZuulRequestHeader("insurcompId", userContextPojo.getInsurcompId());
			requestContext.addZuulRequestHeader("isSendMail", userContextPojo.getIsSendMail());
		}
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
