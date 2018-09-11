/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.mapper;

import com.boss.core.db.Stream;

/**
 * @description 
 * @data 2018年4月8日下午8:23:24
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
public interface StreamMapper {

	/**
	 * 添加流水
	 * @param stream
	 * @return
	 */
	int insertStream(Stream stream);
}
