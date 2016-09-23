/**
 * Project Name: demo-base
 * File Name: SpringSessionConfig.java
 * Package Name: com.lzs.puppet.demo.base.session
 * Describe: TODO
 * Date: 2016年9月12日下午3:56:49
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.base.redis;


import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@Configuration
@EnableCaching
public class RedisConfig {
	@Bean
    public JedisConnectionFactory connectionFactory() {
            return new JedisConnectionFactory(); 
    }

    @Bean
    public KeyGenerator wiselyKeyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    @Bean
    public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
    	 RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
         cacheManager.setDefaultExpiration(300);
         return cacheManager;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}

	