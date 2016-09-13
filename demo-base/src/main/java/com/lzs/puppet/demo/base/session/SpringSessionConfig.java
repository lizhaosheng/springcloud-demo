/**
 * Project Name: demo-base
 * File Name: SpringSessionConfig.java
 * Package Name: com.lzs.puppet.demo.base.session
 * Describe: TODO
 * Date: 2016年9月12日下午3:56:49
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.base.session;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * ClassName: SpringSessionConfig <br/>
 * Function: spring session 配置信息. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年9月12日 下午3:56:49 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
@EnableRedisHttpSession
public class SpringSessionConfig {
	@Bean
    public JedisConnectionFactory connectionFactory() {
            return new JedisConnectionFactory(); 
    }
}

	