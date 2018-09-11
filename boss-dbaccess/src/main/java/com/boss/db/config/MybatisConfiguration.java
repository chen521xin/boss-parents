/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.db.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.boss.db.config.DbcontextHolder.DbType;

/**
 * @description 将多数据源注入到sqlSessionFactory中
 * @data 2017年11月2日下午4:50:11
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
@Configuration
@ConditionalOnClass({ EnableTransactionManagement.class, EntityManager.class })
@AutoConfigureAfter({ DataBaseConfiguration.class })
@EntityScan("com.boss.core.db")
@MapperScan(basePackages = { "com.boss.*.mapper" })
public class MybatisConfiguration implements EnvironmentAware {

	private static Logger logger = LoggerFactory.getLogger(MybatisConfiguration.class);

	@Resource(name = "writerDataResource")
	private DataSource writerDataSource;

	@Resource(name = "readDataResources")
	private List<DataSource> readDataSource;

	private MyAbsctractRoutingDataSource dataSource;

	@Bean
	public MyAbsctractRoutingDataSource roundRobinDataSourceProxy() {
		if (dataSource == null) {
			MyAbsctractRoutingDataSource proxy = new MyAbsctractRoutingDataSource();
			Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
			proxy.setDefaultTargetDataSource(writerDataSource);
			targetDataSources.put(DbType.READ, readDataSource.get(0));
			targetDataSources.put(DbType.WRITE, writerDataSource);
			proxy.setTargetDataSources(targetDataSources);
			dataSource = proxy;
		}
		return dataSource;
	}

	@Bean
	@ConditionalOnMissingBean
	public SqlSessionFactory sqlSessionFactory(MyAbsctractRoutingDataSource dataSource) {
		try {
			SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
			sessionFactory.setDataSource(dataSource);
			// 加载全局的配置文件
			sessionFactory.setConfigLocation(new DefaultResourceLoader()
					.getResource(String.format("classpath:mybatis-config%s.xml", getProfile())));

			logger.debug("Configuring sql session factory");
			return sessionFactory.getObject();

		} catch (Exception e) {
			logger.warn("could not configure session factory", e);
			return null;
		}
	}

	@Bean
	@ConditionalOnMissingBean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(roundRobinDataSourceProxy());
	}

	public String getProfile() {
		String profile = System.getProperty("PROFILE");
		if (profile==null||profile.trim().equals("")) {
			profile = "";
		} else {
			profile = "-" + profile;
		}
		return profile;
	}

	@Override
	public void setEnvironment(Environment environment) {

	}

}
