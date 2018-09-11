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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boss.api.client.impl.ResourceServiceImpl;
import com.boss.core.db.Jlog;
import com.boss.core.struct.ResultPage;

/**
 * @description 
 * @data 2018年4月6日下午10:06:42
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@FeignClient(name = "boss-service-data"+"${PRODUCER_TAG:}", fallback = ResourceServiceImpl.class)
public interface LogServiceClient {

	@RequestMapping(method = RequestMethod.POST, value = "/log/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResultPage creatUserLog(@RequestBody Jlog log);
}
