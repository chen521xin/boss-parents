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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boss.api.client.impl.UserServiceClientImpl;
import com.boss.core.db.User;
import com.boss.core.struct.ResultInfo;
import com.boss.core.struct.ResultPage;

/**
 * @description feign调用方法，feign已实现负载均衡。
 * @data 2017年11月28日下午5:16:28
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/

@FeignClient(name = "boss-service-user"+"${PRODUCER_TAG:}", fallback = UserServiceClientImpl.class)
public interface UserServiceClient {

	@RequestMapping(method = RequestMethod.POST, value = "/user/validate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResultPage validateUser(@RequestBody User user);

	@RequestMapping(method = RequestMethod.PUT, value = "/user/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResultInfo updateUser(@RequestBody User user);

	@RequestMapping(method = RequestMethod.GET, value = "/user/username", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	User getUserbyUsername(@RequestParam("username") String username);
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/updStatus", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	ResultInfo updateStatus(@RequestBody User user);
	
}
