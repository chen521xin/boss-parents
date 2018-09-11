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
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @description 根据标识获取不同源 这里我们通过扩展AbstractRoutingDataSource来获取不同的源。
 *              它是Spring提供的一个可以根据用户发起的不同请求去转换不同的数据源，比如根据用户的不同地区语言选择不同的数据库。
 *              通过查看源码可以发现，它是通过determineCurrentLookupKey（）
 *              返回的不同key到sqlSessionFactory中获取不同源
 *              （前面已经展示了如何在sqlSessionFactory中注入多个源）
 * 
 * @data 2017年11月2日下午5:06:30
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
public class MyAbsctractRoutingDataSource extends AbstractRoutingDataSource {

	private static Logger logger = LoggerFactory.getLogger(MyAbsctractRoutingDataSource.class);

	public MyAbsctractRoutingDataSource() {

	}

	@Override
	protected Object determineCurrentLookupKey() {
		logger.info("current database context is " + DbcontextHolder.getDbType());
		return DbcontextHolder.getDbType();
	}

}
