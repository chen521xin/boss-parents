/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @data 2018年2月26日下午9:18:58
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@RestController
@RequestMapping("/zuul")
public class TestController {

	@GetMapping("/test")
	public String test(){		
		System.out.println("===============================");
		String ss="";
		if(ss.equals("")){
			return "error";
		}
		return "sss";
	}
}
