/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.ecargo.service;

import com.boss.core.db.Jlog;

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
	boolean addJlog(Jlog jlog);
	/**
	 * 添加日志（api调用）
	 * @param jlog
	 * @return
	 */
	boolean insertJlog(Jlog jlog);
}
