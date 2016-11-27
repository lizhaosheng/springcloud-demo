/**
 * Project Name: demo-base
 * File Name: PuppetServerListFilter.java
 * Package Name: com.lzs.puppet.demo.base.ribbon
 * Describe: TODO
 * Date: 2016年11月9日上午1:25:41
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.ZonePreferenceServerListFilter;
import org.springframework.data.redis.core.RedisTemplate;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.Server.MetaInfo;

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
	private Logger logger = LoggerFactory.getLogger(PuppetServerListFilter.class);
	
	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private DiscoveryClient discoveryClient;
	
	/**
	 * 因为PuppetServerListFilter通常被配置为通用过滤器，在spring的根上下文中，
	 * 因此同一个进程中的所有服务（被本地调用过的所有服务）都会生成一个定时同步任务并调用PuppetServerListFilter进行过滤（除非指定了其他特定过滤器），
	 * 所以配置同步需要加锁，这里使用一个sync的boolean变量控制同时只有一个线程任务可以进行配置同步。
	 */
	private boolean sync = false;
	
	/**
	 * 本地最近一次同步时间戳
	 */
	private long localTimestamp = 0;
	
	/**
	 * 本地服务的路由配置缓存,初始化没有配置信息
	 */
	private List<Route> localRouteList = new ArrayList<Route>(0);
	
	/**
	 * 服务名和对应的服务实例集合映射。
	 * 主要用于减少过滤次数，遍历getFilteredListOfServers(List<Server> servers)中servers，
	 * 若有出现在serviceInstanceIdMap中，则不需要进行再次过滤判断。
	 * 当规则有变动serviceInstanceIdMap置空，通过过滤的server加入到serviceInstanceIdMap中
	 */
	private Map<String,Set<String>> serviceInstanceIdMap = new HashMap<String,Set<String>>(0);
	
	@Override
	public List<Server> getFilteredListOfServers(List<Server> servers) {
		if(!sync){// 没有其他线程在处理
			sync = true;
			try{
				// 获取配置是否有变化标志（redis）
				Long timestamp = (Long) redisTemplate.opsForValue().get(Constants.PUPPUET_ROUTE_TIMESTAMP);
				if(timestamp != null && localTimestamp < timestamp){
					// 若有变化则更新本地配置
					refreshLocalRoute(timestamp);
				}
			}catch (Exception e){
				logger.error("puppet filte server exception",e);
			}finally{
				sync = false;
			}
		}
		if(!localRouteList.isEmpty() && !servers.isEmpty()){
			MetaInfo metaInfo = null;
			String serviceName = null;
			
			// servers中的server的serverName都是一样的！！
			metaInfo = servers.get(0).getMetaInfo();
			// service name
			serviceName = metaInfo.getServiceIdForDiscovery();
			// 获取第一条符合serviceName的规则
			Route usedRoute = null;
			for(Route route:localRouteList){
				if(isMatchTargetService(serviceName,route)){
					usedRoute = route;
					break;
				}
			}
			if(usedRoute == null){
				// 没有可用规则，不做任何处理
			}
			else{
				String instanceId = null;
				Set<String> instanceSet = null;
				List<Server> output = new ArrayList<Server>();
				
				for(Server server:servers){
					metaInfo = server.getMetaInfo();
					// service name
					serviceName = metaInfo.getServiceIdForDiscovery();
					if(!serviceInstanceIdMap.containsKey(serviceName)){
						serviceInstanceIdMap.put(serviceName, new HashSet<String>(3));
					}
					// 当前服务对应的服务实例列表，本地缓存
					instanceSet = serviceInstanceIdMap.get(serviceName);
					instanceId = metaInfo.getInstanceId();
					if(instanceSet.contains(instanceId)){
						// servers和serviceInstanceIdMap中都存在该服务实例，不再进行重复过滤判断，直接放入输出结果集中
						output.add(server);
					}else if(isMatchTargetInstance(server,usedRoute)){ // 对当前实例根据过滤配置进行过滤
						instanceSet.add(instanceId);
						output.add(server);
					}
				}
				servers = output;
			}
		}
		return super.getFilteredListOfServers(servers);
	}
	
	/**
	 * 刷新本地路由配置
	 * @param timestamp
	 */
	private void refreshLocalRoute(Long timestamp) {
		localRouteList = new ArrayList<Route>();
		serviceInstanceIdMap = new HashMap<String,Set<String>>(3);
		// 获取所有的远程配置
		List<Route> allRouteList = (List<Route>) redisTemplate.opsForValue().get(Constants.PUPPUET_ROUTE);
		if(allRouteList != null && !allRouteList.isEmpty()){
			for(Route route:allRouteList){
				// 本地服务实例信息
				ServiceInstance localInstance = discoveryClient.getLocalServiceInstance();
				if(isMatchSourceInstance(localInstance,route)){
					// 是本地服务实例的路由配置
					localRouteList.add(route);
				}
			}
		}
	}
	
	/**
	 * 给定规则route是否是给定服务实例localInstance可用的规则。
	 * @param localInstance
	 * @param route
	 * @return
	 */
	private boolean isMatchSourceInstance(ServiceInstance localInstance, Route route) {
		// TODO Auto-generated method stub
		return true;
	}
	/**
	 * 
	 * @param serviceName
	 * @param route
	 * @return
	 */
	private boolean isMatchTargetService(String serviceName, Route route) {
		// TODO Auto-generated method stub
		return true;
	}
	/**
	 * 根据路由配置过滤服务实例
	 * @param server
	 * @param route
	 * @return
	 */
	private boolean isMatchTargetInstance(Server server, Route route) {
		// TODO Auto-generated method stub
		return true;
	}

}

	