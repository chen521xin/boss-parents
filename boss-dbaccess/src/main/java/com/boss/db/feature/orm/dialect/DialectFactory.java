/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.db.feature.orm.dialect;

import org.apache.ibatis.session.Configuration;

/**
 * @description 数据库方言工厂，产生方言对象
 * @data 2017年10月24日下午7:21:03
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
public class DialectFactory {

	public static String dialectClass = null;

	public static AbstractDialect buildDialect(Configuration configuration) {
		if (dialectClass == null) {
			synchronized (DialectFactory.class) {
				if (dialectClass == null) {
					dialectClass = configuration.getVariables().getProperty("dialectClass");
				}
			}
		}
		AbstractDialect dislect = null;
		try {
			dislect = (AbstractDialect) Class.forName(dialectClass).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dislect;
	}
}
