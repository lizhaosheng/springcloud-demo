/**
 * Project Name:demo
 * File Name:PuppetRule.java
 * Package Name:com.lzs.puppet.demo
 * Describe: TODO
 * Date:2016年8月23日下午3:56:27
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.leave;

import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

/**
 * ClassName:PuppetRule <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月23日 下午3:56:27 <br/>
 * @author hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
public class PuppetRule implements IRule{
	private IRule rule = new AvailabilityFilteringRule();
//	ILoadBalancer paramILoadBalancer;
//	@Override
	public Server choose(Object paramObject) {
		System.out.println(paramObject);
		return rule.choose(paramObject);
	}

	@Override
	public void setLoadBalancer(ILoadBalancer paramILoadBalancer) {
		rule.setLoadBalancer(paramILoadBalancer);	
	}

	@Override
	public ILoadBalancer getLoadBalancer() {
		return rule.getLoadBalancer();
			
	}
}

	