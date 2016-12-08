package com.lzs.puppet.base.ds;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PuppetDataSourceProperties.class)
public class DataSourceConfiguration {
	@Autowired
	private PuppetDataSourceProperties properties;
	
	private org.apache.tomcat.jdbc.pool.DataSource dataSource;
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {	
		this.dataSource = new org.apache.tomcat.jdbc.pool.DataSource();		
		this.dataSource.setDriverClassName(properties.getDriverClassName());
		this.dataSource.setUrl(properties.getUrl());
		if (properties.getUsername() != null) {
			this.dataSource.setUsername(properties.getUsername());
		}
		if (properties.getPassword() != null) {
			this.dataSource.setPassword(properties.getPassword());
		}
		this.dataSource.setInitialSize(properties.getInitialSize());
		this.dataSource.setMaxActive(properties.getMaxActive());
		this.dataSource.setMaxIdle(properties.getMaxIdle());
		this.dataSource.setMinIdle(properties.getMinIdle());
		this.dataSource.setTestOnBorrow(properties.isTestOnBorrow());
		this.dataSource.setTestOnReturn(properties.isTestOnReturn());
		this.dataSource.setValidationQuery(properties.getValidationQuery());
		return this.dataSource;
	}

	@PreDestroy
	public void close() {
		if (this.dataSource != null) {
			this.dataSource.close();
		}
	}
	
//	spring boot的方式，参考spring-boot-autoconfigure 包 @see org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.NonEmbeddedConfiguration
//	@Bean
//	public DataSource dataSource() {
//		DataSourceBuilder factory = DataSourceBuilder
//				.create(this.properties.getClassLoader())
//				.driverClassName(this.properties.getDriverClassName())
//				.url(this.properties.getUrl()).username(this.properties.getUsername())
//				.password(this.properties.getPassword());
//		if (this.properties.getType() != null) {
//			factory.type(this.properties.getType());
//		}
//		return factory.build();
//	}
}
