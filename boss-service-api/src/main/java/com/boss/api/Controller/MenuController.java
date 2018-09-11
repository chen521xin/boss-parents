/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boss.api.client.ResourceServiceClient;

/**
 * @description 
 * @data 2018年4月26日下午1:36:39
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@RestController
public class MenuController extends BaseController{

	@Autowired
	private ResourceServiceClient resourceClient;
	
	@GetMapping("/menu")
	public String menu(){
		return resourceClient.findMenuByRole();
	}
}
