/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2017 by                   #
 *#              Shanghai stock Exchange(SSE),shanghai,China        #
 *#                          All rights reserved.                   #
 *#                                                                 #
 *###################################################################
 */
package com.boss.utils.feature.orm.dialect;
/**
 * @description
 * @data 2017年10月24日下午7:19:21
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
public abstract class AbstractDialect {

	/**
	 * 得到分页SQL
	 * @param sql
	 * @param offset
	 * @param limit
	 * @return
	 */
	public abstract String getLimitSring(String sql,int offset,int limit);
	/**
	 * 得到总数量SQL
	 * @param sql
	 * @return
	 */
	public abstract String getCountString(String sql);
}
