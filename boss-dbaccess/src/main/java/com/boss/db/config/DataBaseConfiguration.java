/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.db.config;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @description 配置数据源
 * @data 2017年11月2日下午4:03:41
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Configuration
@AutoConfigureBefore({ MybatisConfiguration.class })
@EnableTransactionManagement
public class DataBaseConfiguration implements EnvironmentAware {

	private static Logger logger = LoggerFactory.getLogger(DataBaseConfiguration.class);
	private RelaxedPropertyResolver propertyresolver;

	@Override
	public void setEnvironment(Environment env) {
		this.propertyresolver = new RelaxedPropertyResolver(env, "jdbc.");
	}

	@Bean(value = "writerDataResource", destroyMethod = "close", initMethod = "init")
	// 优选选择该数据源
	@Primary
	public DataSource writerDataSource() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(propertyresolver.getProperty("url"));
		dataSource.setDriverClassName(propertyresolver.getProperty("driverClassName"));
		dataSource.setUsername(propertyresolver.getProperty("userName"));
		dataSource.setPassword(propertyresolver.getProperty("password"));
		dataSource.setMaxWait(Long.parseLong(propertyresolver.getProperty("maxWait")));
		dataSource.setInitialSize(Integer.parseInt(propertyresolver.getProperty("initialSize")));
		dataSource.setMinIdle(Integer.parseInt(propertyresolver.getProperty("minIdle")));
		dataSource.setMaxActive(Integer.parseInt(propertyresolver.getProperty("maxActive")));
		dataSource.setLogAbandoned(Boolean.parseBoolean(propertyresolver.getProperty("logAbandoned")));
		dataSource.setRemoveAbandoned(Boolean.parseBoolean(propertyresolver.getProperty("removeAbandoned")));
		dataSource.setRemoveAbandonedTimeout(Integer.parseInt(propertyresolver.getProperty("removeAbandonedTimeout")));
		dataSource.setTimeBetweenEvictionRunsMillis(
				Integer.parseInt(propertyresolver.getProperty("timeBetweenEvictionRunMillis")));
		dataSource.setMinEvictableIdleTimeMillis(
				Integer.parseInt(propertyresolver.getProperty("minEvictableIdleTimeMillis")));
		dataSource.setValidationQuery(propertyresolver.getProperty("validationQuery"));
		dataSource.setTestWhileIdle(Boolean.parseBoolean(propertyresolver.getProperty("testWhileIdle")));
		dataSource.setTestOnBorrow(Boolean.parseBoolean(propertyresolver.getProperty("testOnBorrow")));
		dataSource.setTestOnBorrow(Boolean.parseBoolean(propertyresolver.getProperty("testOnReturn")));
		dataSource.setPoolPreparedStatements(
				Boolean.parseBoolean(propertyresolver.getProperty("poolPreparedStatements")));
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(
				Integer.parseInt(propertyresolver.getProperty("maxPoolPreparedStatementPerConnectionSize")));
		dataSource.setFilters(propertyresolver.getProperty("FILTERS"));
		return dataSource;
	}

	@Bean(value = "readDataResource", destroyMethod = "close", initMethod = "init")
	public DataSource readDataSource() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(propertyresolver.getProperty("slave.url"));
		dataSource.setDriverClassName(propertyresolver.getProperty("slave.driverClassName"));
		dataSource.setUsername(propertyresolver.getProperty("slave.userName"));
		dataSource.setPassword(propertyresolver.getProperty("slave.password"));
		dataSource.setMaxWait(Long.parseLong(propertyresolver.getProperty("slave.maxWait")));
		dataSource.setInitialSize(Integer.parseInt(propertyresolver.getProperty("slave.initialSize")));
		dataSource.setMinIdle(Integer.parseInt(propertyresolver.getProperty("slave.minIdle")));
		dataSource.setMaxActive(Integer.parseInt(propertyresolver.getProperty("slave.maxActive")));
		dataSource.setLogAbandoned(Boolean.parseBoolean(propertyresolver.getProperty("slave.logAbandoned")));
		dataSource.setRemoveAbandoned(Boolean.parseBoolean(propertyresolver.getProperty("slave.removeAbandoned")));
		dataSource.setRemoveAbandonedTimeout(
				Integer.parseInt(propertyresolver.getProperty("slave.removeAbandonedTimeout")));
		dataSource.setTimeBetweenEvictionRunsMillis(
				Integer.parseInt(propertyresolver.getProperty("slave.timeBetweenEvictionRunMillis")));
		dataSource.setMinEvictableIdleTimeMillis(
				Integer.parseInt(propertyresolver.getProperty("slave.minEvictableIdleTimeMillis")));
		dataSource.setValidationQuery(propertyresolver.getProperty("slave.validationQuery"));
		dataSource.setTestWhileIdle(Boolean.parseBoolean(propertyresolver.getProperty("slave.testWhileIdle")));
		dataSource.setTestOnBorrow(Boolean.parseBoolean(propertyresolver.getProperty("slave.testOnBorrow")));
		dataSource.setTestOnBorrow(Boolean.parseBoolean(propertyresolver.getProperty("slave.testOnReturn")));
		dataSource.setPoolPreparedStatements(
				Boolean.parseBoolean(propertyresolver.getProperty("slave.poolPreparedStatements")));
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(
				Integer.parseInt(propertyresolver.getProperty("slave.maxPoolPreparedStatementPerConnectionSize")));
		dataSource.setFilters(propertyresolver.getProperty("slave.FILTERS"));
		return dataSource;
	}

	@Bean(value = "readDataResources")
	public List<DataSource> getReadDataSource() throws SQLException {
		List<DataSource> dataSources = new ArrayList<DataSource>();
		dataSources.add(readDataSource());

		logger.info("ReadDataSource count:" + dataSources.size());
		return dataSources;
	}
}
