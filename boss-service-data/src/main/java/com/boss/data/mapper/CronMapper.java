/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.mapper;

import com.boss.core.db.Cron;

/**
 * @description 
 * @data 2018年4月6日下午5:38:37
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public interface CronMapper {
	  int deleteCorn(Cron cron);
	  int updateCron(Cron cron); 
	  int insertCron(Cron cron);
	  Cron selectCron();
}
