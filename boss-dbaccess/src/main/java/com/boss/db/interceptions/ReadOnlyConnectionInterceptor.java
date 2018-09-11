/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.db.interceptions;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.boss.db.config.DbcontextHolder;
import com.boss.db.config.DbcontextHolder.DbType;

/**
 * @description Spring AOP对注解的拦截
 * @data 2017年11月2日下午5:33:32
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
@Aspect
@Component
public class ReadOnlyConnectionInterceptor implements Ordered {
	private static Logger logger = LoggerFactory.getLogger(ReadOnlyConnectionInterceptor.class);

	@Around("@annotation(readOnlyConnection)")
	public Object proceed(ProceedingJoinPoint proceedingJoinPoint, ReadOnlyConnectionInterceptor readOnlyConnection)
			throws Throwable {
		DbType currentDbType = DbcontextHolder.getDbType();
		try {
			logger.info("Setting dataBase connection to readOnly");
			DbcontextHolder.setDbType(DbcontextHolder.DbType.READ);
			return proceedingJoinPoint.proceed();
		} finally {
			DbcontextHolder.setDbType(currentDbType);
			logger.info("Setting dataBase connection to readOnly");
		}
	}

	@Override
	public int getOrder() { 
		return 0;
	}
}
