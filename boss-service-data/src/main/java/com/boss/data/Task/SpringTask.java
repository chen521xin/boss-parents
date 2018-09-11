/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#              Shanghai stock Exchange(SSE),shanghai,China        #
 *#                          All rights reserved.                   #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.boss.data.mapper.PolicyMapper;

/**
 * @description 
 * @data 2018年7月26日下午8:31:15
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
@Component
public class SpringTask {

	@Autowired
	private PolicyMapper policyMapper; 
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void thePolicyExpiration(){
		policyMapper.thePolicyExpiration();
	}
}
