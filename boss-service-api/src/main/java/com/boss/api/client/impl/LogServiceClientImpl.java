/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.client.impl;

import com.boss.api.client.LogServiceClient;
import com.boss.core.db.Jlog;
import com.boss.core.struct.ResultPage;

/**
 * @description 
 * @data 2018年4月6日下午10:07:13
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public class LogServiceClientImpl implements LogServiceClient{

	@Override
	public ResultPage creatUserLog(Jlog log) {
		return ResultPage.error("插入日志失败");
	}

}
