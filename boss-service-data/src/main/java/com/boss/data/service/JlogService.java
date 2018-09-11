/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.service;

import com.boss.core.db.Jlog;
import com.boss.core.pojo.JlogPojo;
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
	
	/**
	 * 添加日志
	 * @param jlog
	 * @return
	 */
	void addJlog(Jlog jlog);
	/**
	 * 日志分页查询
	 * @param page
	 * @param jLog
	 * @return
	 */
	Page<Jlog> findAllByPage(Page<Jlog> page,JlogPojo jLog);
	/**
	 * 添加日志（api调用）
	 * @param jlog
	 * @return
	 */
	void insertJlog(Jlog jlog);
}
