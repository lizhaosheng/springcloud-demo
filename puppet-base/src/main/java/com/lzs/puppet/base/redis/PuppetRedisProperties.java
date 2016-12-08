/**
 * Project Name: puppet-base
 * File Name: PuppetRedisProperties.java
 * Package Name: com.lzs.puppet.base.redis
 * Describe: TODO
 * Date: 2016年12月7日下午12:24:19
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.base.redis;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ClassName: PuppetRedisProperties <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年12月7日 下午12:24:19 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
@ConfigurationProperties(prefix = PuppetRedisProperties.PREFIX)
public class PuppetRedisProperties extends RedisProperties{
	public final static String PREFIX = "redis";
	/** 是否支持事务，默认支持*/
	private boolean transactionSupport = true;

	public boolean isTransactionSupport() {
		return transactionSupport;
	}

	public void setTransactionSupport(boolean transactionSupport) {
		this.transactionSupport = transactionSupport;
	}

}

	