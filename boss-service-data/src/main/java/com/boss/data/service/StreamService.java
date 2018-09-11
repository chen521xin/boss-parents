/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.service;

import com.boss.core.db.Stream;

/**
 * @description 
 * @data 2018年4月8日下午8:25:40
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
public interface StreamService {

	/**
	 * 添加流水
	 * @param stream
	 * @return
	 */
	void insertStream(Stream st);
}
