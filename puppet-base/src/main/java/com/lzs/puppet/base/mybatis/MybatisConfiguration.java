/**
 * Project Name: puppet-base
 * File Name: MybatisConfiguration.java
 * Package Name: com.lzs.puppet.base.mybatis
 * Describe: TODO
 * Date: 2016年12月6日下午5:46:02
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.base.mybatis;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * ClassName: MybatisConfiguration <br/>
 * Function: mybatis配置. <br/>
 * Reason: 建议使用spring boot提供的集成方式，下面只是作为学习参考编写自定义mybatis配置. 
 * 如果要使用，请将下面的注解@Configuration和@EnableTransactionManagement 打开<br/>
 * Date: 2016年12月6日 下午5:46:02 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
//@Configuration
//@EnableTransactionManagement
public class MybatisConfiguration implements TransactionManagementConfigurer {

    @Autowired
    DataSource dataSource;
    
    /**
     * 使用spring boot内置的MybatisProperties，当然也可以自己定义
     */
    @Autowired
    MybatisProperties mybatisProperties;
    
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSource ds) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

//      //添加插件
//        bean.setPlugins(new Interceptor[]{pageHelper});
        
        // XML文件中的model类的基包路径
        bean.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());
        //添加XML目录
        bean.setMapperLocations(mybatisProperties.resolveMapperLocations());
        return bean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}

	