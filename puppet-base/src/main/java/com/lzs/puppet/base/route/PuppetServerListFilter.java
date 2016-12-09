/**
 * Project Name: demo-base
 * File Name: PuppetServerListFilter.java
 * Package Name: com.lzs.puppet.demo.base.ribbon
 * Describe: TODO
 * Date: 2016年11月9日上午1:25:41
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.base.route;

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

import com.lzs.puppet.base.constant.Constant;
import com.lzs.puppet.base.redis.PuppetRedisTemplate;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.Server.MetaInfo;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;

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
	private PuppetRedisTemplate redisTemplate;

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
		logger.info("进入路由信息同步方法");
		if(!sync){// 没有其他线程在处理
			sync = true;
			try{
				// 获取配置是否有变化标志（redis）
				Long timestamp = (Long) redisTemplate.opsForValue().get(Constant.ROUTE.LAST_UPDATE_TIMESTAMP);
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
			
			// 传进来的参数servers中的每一个列表项server的serverName都是一样的！！
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
				DiscoveryEnabledServer des = null;
				for(Server server:servers){
					des = (DiscoveryEnabledServer) server;
//					metaInfo = server.getMetaInfo();
					// service name
					if(!serviceInstanceIdMap.containsKey(serviceName)){
						serviceInstanceIdMap.put(serviceName, new HashSet<String>(3));
					}
					// 当前服务对应的服务实例列表，本地缓存
					instanceSet = serviceInstanceIdMap.get(serviceName);
					instanceId = des.getId();
					if(instanceSet.contains(instanceId)){
						// servers和serviceInstanceIdMap中都存在该服务实例，不再进行重复过滤判断，直接放入输出结果集中
						output.add(server);
					}else if(isMatchTargetInstance(des,serviceName,usedRoute)){ // 对当前实例根据过滤配置进行过滤
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
		logger.info("开始刷新路由配置");
		localRouteList = new ArrayList<Route>();
		serviceInstanceIdMap = new HashMap<String,Set<String>>(3);
		// 获取所有的远程配置
		List<Object> allRouteList = redisTemplate.opsForList().range(
				Constant.ROUTE.ROUTE_LIST, 0, redisTemplate.opsForList().size(Constant.ROUTE.ROUTE_LIST));
		if(allRouteList != null && !allRouteList.isEmpty()){
			// 本地服务实例信息
			ServiceInstance localInstance = discoveryClient.getLocalServiceInstance();
			for(Object route:allRouteList){
				try{
					if(isMatchSourceInstance(localInstance,(Route)route)){
						// 是本地服务实例的路由配置
						localRouteList.add((Route)route);
					}
				}catch (Exception e){
					logger.error("路由配置错误:" + route.toString(), e);
					continue;
				}
			}
		}
		localTimestamp = timestamp;
	}
	
	/**
	 * 给定规则route是否是给定服务实例localInstance可用的规则。
	 * @param localInstance
	 * @param route
	 * @return
	 */
	private boolean isMatchSourceInstance(ServiceInstance localInstance, Route route) {
		/**
		 * String host = localInstance.getHost();
		 * URI url = localInstance.getUri();
		 * String ser = localInstance.getServiceId();
		 * int p = localInstance.getPort();
		 * Map<String, String> map = localInstance.getMetadata();
		 * 
		 * 注意，比较郁闷的是ServiceInstance实例并没有过多的信息，而且host和ip是不能同时获取的！
		 * 若在配置文件中（application.yml）设置preferIpAddress: true ，则ServiceInstance.getHost() 返回ip；
		 * 反之返回配置文件中的host。
		 * 其中若配置文件中没有设置eureka.instance.hostname 则使用真实主机名；
		 * 若没有设置eureka.instance.ip-address ，则使用真实ip地址。
		 * 
		 * ServiceInstance.getUri() = ServiceInstance.getHost() + ":" + ServiceInstance.getPort()
		 * 
		 * 因此，需要判断host是ip还是域名，然后做不同的处理
		 * 实际使用要求配置eureka.instance.preferIpAddress: true 且不能设置eureka.instance.ip-address
		 */
//		// service name
//		String serviceName = localInstance.getServiceId();
//		if(!route.isMatchSourceService(serviceName)){
//			return false;
//		}
//		// host
//		String host = localInstance.getHost();
//		// is ip?
//		if(isIpAddress(host)){
//			if(!route.isMatchSourceIp(host)){
//				return false;
//			}
//			// 使用空白主机名进行校验
//			if(!route.isMatchSourceHost("")){
//				return false;
//			}
//		}
//		else {
//			// 使用空白ip地址进行校验
//			if(!route.isMatchSourceIp("")){
//				return false;
//			}
//			if(!route.isMatchSourceHost(host)){
//				return false;
//			}
//		}
		
		// 仅做ip判断，要求配置eureka.instance.preferIpAddress: true 且不能设置eureka.instance.ip-address
		String ip = localInstance.getHost();
		if(!route.isMatchSourceIp(ip)){
			return false;
		}
		// port
		int port = localInstance.getPort();
		if(!route.isMatchSourcePort(port)){
			return false;
		}
		// metadata
		Map<String, String> map = localInstance.getMetadata();
		if(!route.isMatchSourceMetaData(map)){
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param serviceName
	 * @param route
	 * @return
	 */
	private boolean isMatchTargetService(String serviceName, Route route) {
		return route.isMatchTargetService(serviceName);
	}
	/**
	 * 根据路由配置过滤服务实例
	 * @param server
	 * @param serviceName2 
	 * @param route
	 * @return
	 */
	private boolean isMatchTargetInstance(DiscoveryEnabledServer server, String serviceName, Route route) {
//		// service name  前面已经做过检查
//		if(!route.isMatchTargetService(serviceName)){
//			return false;
//		}
		InstanceInfo instance = server.getInstanceInfo();
//		// host
//		String host = instance.getHostName();
//		if(!route.isMatchTargetHost(host)){
//			return false;
//		}
		// ip
		String ip = instance.getIPAddr();
		if(!route.isMatchTargetIp(ip)){
			return false;
		}
		// port
		int port = instance.getPort();
		if(!route.isMatchTargetPort(port)){
			return false;
		}
		// metadata
		Map<String, String> map = instance.getMetadata();
		if(!route.isMatchTargetMetaData(map)){
			return false;
		}
		
		return true;
	}

	/**
	 * 
	 * 简单判断是否ip地址，仅做形式检验，不进行精确判断 
	 *
	 * @author hzlizhaosheng
	 * @param IP
	 * @return
	 */
	public boolean isIpAddress(String IP){
        IP = IP.trim();  
        if(IP.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")){  
            return true;
        }  
        return false;  
    }  
}

	