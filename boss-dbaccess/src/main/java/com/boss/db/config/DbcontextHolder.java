/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.db.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description //标识存放ThreadLocal的实现 这里主要思路如下： 1-将不同的数据源标识记录在ThreadLocal中
 *              2-通过注解标识出当前的service方法使用哪个库 3-通过Spring
 *              AOP实现拦截注解并注入不同的标识到threadlocal中
 *              4-获取源的时候通过threadlocal中不同的标识给出不同的sqlSession * @data
 *              2017年11月2日下午5:07:43
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
public class DbcontextHolder {
	private static Logger logger = LoggerFactory.getLogger(DbcontextHolder.class);

	public enum DbType {
		READ, WRITE
	}

	private static final ThreadLocal<DbType> contextHolder = new ThreadLocal<DbType>();

	public static void setDbType(DbType dbType) {
		if (dbType == null) {
			throw new NullPointerException();
		}
		logger.debug("settign dataBase context to " + dbType);
		contextHolder.set(dbType);
	}

	public static DbType getDbType() {
		contextHolder.set(contextHolder.get() == null ? DbType.WRITE : contextHolder.get());
		return contextHolder.get();
	}

	public static void clearDbType() {
		logger.debug("clearing database context.");
		contextHolder.remove();
	}
}
