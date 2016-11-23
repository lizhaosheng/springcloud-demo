/**
 * Project Name: demo-base
 * File Name: PuppetServerListFilter.java
 * Package Name: com.lzs.puppet.demo.base.ribbon
 * Describe: TODO
 * Date: 2016年11月9日上午1:25:41
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.base.ribbon;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.ZonePreferenceServerListFilter;
import org.springframework.data.redis.core.RedisTemplate;

import com.netflix.loadbalancer.Server;

/**
 * ClassName: PuppetServerListFilter <br/>
 * Function: 服务过滤. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年11月9日 上午1:25:41 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
public class PuppetServerListFilter extends ZonePreferenceServerListFilter {
	
	private RedisTemplate redisTemplate;
	
	public PuppetServerListFilter(RedisTemplate redisTemplate){
		super();
		if(redisTemplate == null){
			throw new RuntimeException("can't find RedisTemplate bean!");
		}
		this.redisTemplate = redisTemplate;
	}
	@Override
	public List<Server> getFilteredListOfServers(List<Server> servers) {
		List<Server> output = super.getFilteredListOfServers(servers);
		// 获取配置是否有变化标志（redis）
		redisTemplate.opsForValue().get("");
		// 若有变化则更新本地配置
		// 更根据配置过滤服务
		
		return output;
	}
}

	