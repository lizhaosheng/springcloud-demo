package com.lzs.puppet.base.ds;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceFactory {

	private org.apache.tomcat.jdbc.pool.DataSource pool;
	
	@Bean(name = "jdbcProperties")
	public JdbcProperties jdbcProperties() {
		return new JdbcProperties();
	}
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {	
		JdbcProperties jdbcProperties = jdbcProperties();
		this.pool = new org.apache.tomcat.jdbc.pool.DataSource();		
		this.pool.setDriverClassName(jdbcProperties.getDriverClassName());
		this.pool.setUrl(jdbcProperties.getUrl());
		if (jdbcProperties.getUsername() != null) {
			this.pool.setUsername(jdbcProperties.getUsername());
		}
		if (jdbcProperties.getPassword() != null) {
			this.pool.setPassword(jdbcProperties.getPassword());
		}
		this.pool.setInitialSize(jdbcProperties.getInitialSize());
		this.pool.setMaxActive(jdbcProperties.getMaxActive());
		this.pool.setMaxIdle(jdbcProperties.getMaxIdle());
		this.pool.setMinIdle(jdbcProperties.getMinIdle());
		this.pool.setTestOnBorrow(jdbcProperties.isTestOnBorrow());
		this.pool.setTestOnReturn(jdbcProperties.isTestOnReturn());
		this.pool.setValidationQuery(jdbcProperties.getValidationQuery());
		return this.pool;
	}

	@PreDestroy
	public void close() {
		if (this.pool != null) {
			this.pool.close();
		}
	}

}
