/**
 * Project Name:demo
 * File Name:DemoRibbonConfiguration.java
 * Package Name:com.lzs.puppet.demo
 * Describe: TODO
 * Date:2016年8月23日下午2:31:46
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.clientapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.Server;

/**
 * ClassName:DemoRibbonConfiguration <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月23日 下午2:31:46 <br/>
 * 
 * @author hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
public class DemoRibbonConfiguration {
	private Logger logger = LoggerFactory.getLogger(DemoRibbonConfiguration.class);
	
	@Autowired
	IClientConfig ribbonClientConfig;

	@Bean
	public IPing ribbonPing(IClientConfig config) {
		logger.info("create ribbon iping!");
		return new PingUrl();
	}

	@Bean
	public IRule ribbonRule(IClientConfig config) {
		logger.info("create ribbon irule!");
//		return new AvailabilityFilteringRule();
		return new PuppetRule();
	}
}
