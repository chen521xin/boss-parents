/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.user.service;

import com.boss.core.db.Jlog;
import com.boss.db.feature.orm.mybatis.Page;
/**
 * @description
 * @data 2018年3月3日下午10:06:42
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/

/**
 * 日志记录
 * @author Administrator
 *
 */
public interface JlogService {
	
	//记录日志
	int addJlog(Jlog jlog);
	//日志分页查询
	Page<Jlog> findAllByPage(Page<Jlog> page,Jlog jLog);
	
}
