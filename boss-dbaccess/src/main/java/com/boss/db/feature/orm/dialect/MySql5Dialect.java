/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.db.feature.orm.dialect;

/**
 * @description
 * @data 2017年11月14日下午1:39:35
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
public class MySql5Dialect extends AbstractDialect {

	protected static final String SQL_END_DELIMITER = ";";

	@Override
	public String getLimitSring(String sql, int offset, int limit) {
		return MySql5PageHepler.getLimitString(sql, offset, limit);
	}

	@Override
	public String getCountString(String sql) {
		return MySql5PageHepler.getCountString(sql);
	}

}
