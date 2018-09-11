/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @description
 * @data 2018年2月9日下午7:10:36
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Component
public class ExceptionFilter extends ZuulFilter{
	private final static Logger logger=LoggerFactory.getLogger(ExceptionFilter.class);
	@Override
	public Object run() {
		try {
			RequestContext ctx = RequestContext.getCurrentContext();
			Object e = ctx.get("error.exception");
			if (e != null && e instanceof ZuulException) {
				ZuulException zuulException = (ZuulException) e;
				logger.error(String.format("Zuul failure detected: %s", zuulException.getMessage()));
				ctx.remove("error.status_code");
				ctx.setResponseBody("Overriding Zuul Exception Body");
				ctx.getResponse().setContentLength(HttpStatus.INTERNAL_SERVER_ERROR.value());
			} 
		} catch (Exception e) {
			ReflectionUtils.rethrowRuntimeException(e);
		}
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return RequestContext.getCurrentContext().containsKey("error.status_code");
	}

	@Override
	public int filterOrder() {
		return -1;
	}

	@Override
	public String filterType() {
		return "post";
	}

}
