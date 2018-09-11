/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boss.api.client.impl.ResourceServiceImpl;
import com.boss.core.struct.ResultPage;

/**
 * @description
 * @data 2018年2月4日下午5:36:06
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
@FeignClient(name = "boss-service-user"+"${PRODUCER_TAG:}", fallback = ResourceServiceImpl.class)
public interface ResourceServiceClient {

	@RequestMapping(value = "/resource/{roleId}", method = RequestMethod.GET)
	@ResponseBody
	ResultPage findResourceByRole(@PathVariable("roleId") String roleCode);

	@RequestMapping(value = "/resource/menu", method = RequestMethod.GET)
	@ResponseBody
	String findMenuByRole();
}
