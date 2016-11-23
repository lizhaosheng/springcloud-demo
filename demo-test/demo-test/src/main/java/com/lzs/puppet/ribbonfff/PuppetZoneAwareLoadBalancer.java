/**
 * Project Name: demo-base
 * File Name: PuppetZoneAwareLoadBalancer.java
 * Package Name: com.lzs.puppet.demo.base.ribbon
 * Describe: TODO
 * Date: 2016年11月8日下午2:14:40
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.ribbonfff;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;

/**
 * ClassName: PuppetZoneAwareLoadBalancer <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年11月8日 下午2:14:40 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
public class PuppetZoneAwareLoadBalancer<T extends Server> extends ZoneAwareLoadBalancer<T> {

	public PuppetZoneAwareLoadBalancer() {
		super();
	}
	
	public PuppetZoneAwareLoadBalancer(IClientConfig config) {
		super(config);
	}

}

	