/**
 * Project Name:demo
 * File Name:DemoRibbonConfiguration.java
 * Package Name:com.lzs.puppet.demo
 * Describe: TODO
 * Date:2016年8月23日下午2:31:46
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.ribbonfff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerListFilter;

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
public class HiRibbonConfiguration {
	private Logger logger = LoggerFactory.getLogger(HiRibbonConfiguration.class);
//
//	@Autowired
//	IClientConfig ribbonClientConfig;
//	  
//	@Bean
//	public IPing ribbonPing(IClientConfig config) {
//		logger.info("create ribbon iping!");
//		return new PingUrl();
//	}
//
//	@Bean
//	public IRule ribbonRule(IClientConfig config) {
//		logger.info("create ribbon irule!");
//		return new PuppetRule();
//	}
//
//	@Bean
//	public ILoadBalancer ribbonILoadBalancer(IClientConfig config){
//		logger.info("create ribbon ribbonILoadBalancer!");
//		return new PuppetZoneAwareLoadBalancer();
//	}
//
//	@Bean
//	public ILoadBalancer ribbonILoadBalancer(){
//		logger.info("create ribbon ribbonILoadBalancer!");
//		return new PuppetZoneAwareLoadBalancer();
//	}
//	
//	@Bean
//	public IClientConfig ribbonClientConfig() {
//		DefaultClientConfigImpl config = new DefaultClientConfigImpl();
//		config.loadProperties("say-hello");
//		return config;
//	}
	@Bean
	public ServerListFilter<Server> ribbonServerListFilter() {
		PuppetServerListFilter filter = new PuppetServerListFilter();
//		filter.initWithNiwsConfig(config);
		return filter;
	}
}
