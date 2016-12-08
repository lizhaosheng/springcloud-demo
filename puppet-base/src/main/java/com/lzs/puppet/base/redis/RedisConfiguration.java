/**
 * Project Name: puppet-base
 * File Name: RedisConfig.java
 * Package Name: com.lzs.puppet.base.redis
 * Describe: TODO
 * Date: 2016年9月12日下午3:56:49
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.base.redis;

import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ClassName: RedisConfig <br/>
 * Function: redis 配置信息. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年9月12日 下午3:56:49 <br/>
 * 
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see  CachingConfigurerSupport
 * @see org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
 */
@Configuration
@EnableCaching
public class RedisConfiguration {
	@Bean(name = "puppetRedisProperties")
	public PuppetRedisProperties redisProperties() {
		return new PuppetRedisProperties();
	}
	
//	@Bean
//	public JedisConnectionFactory connectionFactory() {
//		JedisConnectionFactory factory = new JedisConnectionFactory();
//		factory.setHostName(getHost());
//		factory.setPort(getPort());
//		factory.setTimeout(getTimeout()); // 设置连接超时时间
//		factory.setPassword(getPassword());
//		return factory;
//	}

	@Bean(name = "puppetRedisTemplate")
	public PuppetRedisTemplate redisTemplate(RedisConnectionFactory factory) {
		PuppetRedisTemplate template = new PuppetRedisTemplate();
		template.setConnectionFactory(factory);
		setSerializer(template); // 设置序列化工具
		template.afterPropertiesSet();
		return template;
	}
	
//	@Bean
//	public StringRedisTemplate stringRedisTemplate(
//			RedisConnectionFactory redisConnectionFactory)
//					throws UnknownHostException {
//		StringRedisTemplate template = new StringRedisTemplate();
//		template.setConnectionFactory(redisConnectionFactory);
//	    setSerializer(template); // 设置序列化工具，这样ReportBean不需要实现Serializable接口
//	    template.afterPropertiesSet();
//		return template;
//	}
	
	private void setSerializer(PuppetRedisTemplate template) {
		// string serializer
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		
		// value serializer
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		
		// key serializer
		template.setKeySerializer(stringSerializer);
		// value serializer
		template.setValueSerializer(jackson2JsonRedisSerializer);
		// hash key serializer
		template.setHashKeySerializer(stringSerializer);
		// hash value serializer
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
	}

	@Bean
	public KeyGenerator wiselyKeyGenerator() {
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
		// Number of seconds before expiration. Defaults to unlimited (0)
		cacheManager.setDefaultExpiration(100); // 设置key-value超时时间
		return cacheManager;
	}

}
