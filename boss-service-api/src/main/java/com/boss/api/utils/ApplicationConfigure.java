/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description
 * @data 2018年2月5日下午7:41:44
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Component("applicationConfigure")
public class ApplicationConfigure implements InitializingBean{
	
	@Value("${pre.auth.header}")
	private String authHeader;
	
	public static String preAuthHeader;

	@Override
	public void afterPropertiesSet() throws Exception {
		preAuthHeader=authHeader;
		
	}

}
