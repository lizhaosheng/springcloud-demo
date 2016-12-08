/**
 * Project Name: puppet-route
 * File Name: Route.java
 * Package Name: com.lzs.puppet.route
 * Describe: TODO
 * Date: 2016年10月26日下午4:48:04
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.base.route;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * ClassName: Route <br/>
 * Function: 路由配置项. 多个字段之间是且的关系，同一个字段多个值是或的关系<br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年10月26日 下午4:48:04 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
public class Route implements Serializable{

	/**
	 * serialVersionUID:TODO.
	 */
		
	private static final long serialVersionUID = 1L;
	/** */
	private static final String sep_lisan = ",";
	/** */
	private static final String sep_lianxu = "-";
	
	private String uuid;
	
	private long id;
	
	/** 服务发起方服务名，正则表达式*/
	private String sourceServiceName;
	/** 服务发起方主机名，正则表达式*/
	private String sourceHost;
	/** 服务发起方ip，正则表达式*/
	private String sourceIp;
	/** 服务发起方端口号表达式，类似svn的版本表达式，多个版本使用逗号分隔，减号表示连续端口号*/
	private String sourcePort;
	/** 上面表达式的解析结果*/
	private List<int[]> sourcePortList;
	/** 服务发起方服务名，正则表达式*/
	private Map<String,String> sourceMetaData;
	
	/** 目标服务服务名，正则表达式*/
	private String targetServiceName;
	/** 目标服务服务名，正则表达式*/
	private String targetHost;
	/** 目标服务服务名，正则表达式*/
	private String targetIp;
	/** 目标服务服务名表达式，类似svn的版本表达式，多个版本使用逗号分隔，减号表示连续端口号*/
	private String targetPort;
	/** 上面表达式的解析结果*/
	private List<int[]> targetPortList;
	/** 目标服务服务名，正则表达式*/
	private Map<String,String> targetMetaData;
	
	/**
	 * 
	 * 判断路由配置是否是无效的空配置 
	 *
	 * @author hzlizhaosheng
	 * @return
	 */
	public boolean isEmpty() {
		if(sourceServiceName != null && !"".equals(sourceServiceName)){
			return false;
		}
		if(sourceHost != null && !"".equals(sourceHost)){
			return false;
		}
		if(sourceIp != null && !"".equals(sourceIp)){
			return false;
		}
		if(sourcePort != null && !"".equals(sourcePort)){
			return false;
		}
		if(sourceMetaData != null && !sourceMetaData.isEmpty()){
			return false;
		}
		
		if(targetServiceName != null && !"".equals(targetServiceName)){
			return false;
		}
		if(targetHost != null && !"".equals(targetHost)){
			return false;
		}
		if(targetIp != null && !"".equals(targetIp)){
			return false;
		}
		if(targetPort != null && !"".equals(targetPort)){
			return false;
		}
		if(targetMetaData != null && !targetMetaData.isEmpty()){
			return false;
		}
		return true;
	}

	/**
	 * 
	 * 检查给定的端口号是否在已有的端口号集合中
	 *
	 * @author hzlizhaosheng
	 * @param port - 给定端口号
	 * @param portList - 端口集合，每一项是一个端口号或者一个端口号端的起止值，比如int[0]=1,int[1]=10 表示从1到10连续的端口号，int[0]=4,int[1]=-1 表示只有4端口号
	 * @return
	 */
	private boolean isMatchPort(int port, List<int[]> portList){
		if(port == 0){
			return false;
		}
		if(portList == null || portList.isEmpty()){
			return true;
		}
		for(int[] item:portList){
			if(item == null || item.length == 0){
				continue;
			}
			int start = item[0];
			int end = item.length>1?item[item.length-1]:-1;
			if(end == -1){
				if(start == port){
					return true;
				}
			}
			else if(start <= port &&
					end >= port){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * 将字符串表示的端口号表达式处理为 List<int[]>数据格式，字符串表达式和svn的版本号类似，比如：1,4,6-12,20,30-60  处理后将变成数组
	 * [
	 * {1,-1},
	 * {4,-1},
	 * {6,12},
	 * {20,-1},
	 * {30,60}
	 * ]
	 *
	 * @author hzlizhaosheng
	 * @param portstr
	 * @return
	 */
	public List<int[]> dealPortList(String portstr) {
		if(StringUtils.isNotBlank(portstr)){
			List<int[]> portList = new ArrayList<int[]>();
			String[] array1 = portstr.split(sep_lisan);
			for(String s:array1){
				String[] array2 = s.split(sep_lianxu);
				int[] a = new int[2];
				int start = Integer.parseInt(array2[0]);
				a[0] = start;
				if(array2.length > 1){
					int end = Integer.parseInt(array2[array2.length-1]);
					a[1] = end;
				}
				else{
					a[1] = -1;
				}
				portList.add(a);
			}
			return portList;
		}
		return null;
	}
	
	/**
	 * 
	 * 判断map的key-value是否满足regexMap中所有key对应的正则表达式，也就是说regexMap的所有key都存在map中，并且这些key在map中的value必须符合regexMap中对应key的value代表的正则表达式
	 *
	 * @author hzlizhaosheng
	 * @param map
	 * @param regexMap
	 * @return
	 */
	public boolean regexContainMap(Map<String,String> map, Map<String,String> regexMap){//sourceMetaData
		if(regexMap == null || regexMap.isEmpty()){
			return true;
		}
		if(map == null || map.isEmpty()){
			return false;
		}
		String key = null;
		Iterator<String> it = regexMap.keySet().iterator();
		while(it.hasNext()){
			key = it.next();
			String value = regexMap.get(key);
			if(value == null){
				continue;
			}
			if(!map.containsKey(key)){
				return false;
			}
			if(!map.get(key).matches(value)){
				return false;
			}
		}
		return true;
	}
	
	public boolean isMatchSourceService(String serviceName){
		if(serviceName == null){
			return false;
		}
		if(sourceServiceName == null){
			return true;
		}
		return serviceName.matches(sourceServiceName);
	}
	
	public boolean isMatchTargetService(String serviceName){
		if(serviceName == null){
			return false;
		}
		if(targetServiceName == null){
			return true;
		}
		return serviceName.matches(targetServiceName);
	}
	
	public boolean isMatchSourceHost(String host){
		if(host == null){
			return false;
		}
		if(sourceHost == null){
			return true;
		}
		return host.matches(sourceHost);
	}
	
	public boolean isMatchTargetHost(String host){
		if(host == null){
			return false;
		}
		if(targetHost == null){
			return true;
		}
		return host.matches(targetHost);
	}
	
	public boolean isMatchSourceIp(String ip){
		if(ip == null){
			return false;
		}
		if(sourceIp == null){
			return true;
		}
		return ip.matches(sourceIp);
	}
	
	public boolean isMatchTargetIp(String ip){
		if(ip == null){
			return false;
		}
		if(targetIp == null){
			return true;
		}
		return ip.matches(targetIp);
	}
	
	public boolean isMatchSourcePort(int port){
		return isMatchPort(port,sourcePortList);
	}
	
	public boolean isMatchTargetPort(int port){
		return isMatchPort(port,targetPortList);
	}
	
	public boolean isMatchSourceMetaData(Map<String,String> map){//sourceMetaData
		return regexContainMap(map,sourceMetaData);
	}
	
	public boolean isMatchTargetMetaData(Map<String,String> map){//sourceMetaData
		return regexContainMap(map,targetMetaData);
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSourceServiceName() {
		return sourceServiceName;
	}

	public void setSourceServiceName(String sourceServiceName) {
		this.sourceServiceName = sourceServiceName;
	}

	public String getSourceHost() {
		return sourceHost;
	}

	public void setSourceHost(String sourceHost) {
		this.sourceHost = sourceHost;
	}

	public String getSourceIp() {
		return sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

	public String getSourcePort() {
		return sourcePort;
	}

	public void setSourcePort(String sourcePort) {
		this.sourcePort = sourcePort;
		this.sourcePortList = dealPortList(sourcePort);
	}

	public Map<String, String> getSourceMetaData() {
		return sourceMetaData;
	}

	public void setSourceMetaData(Map<String, String> sourceMetaData) {
		this.sourceMetaData = sourceMetaData;
	}

	public String getTargetServiceName() {
		return targetServiceName;
	}

	public void setTargetServiceName(String targetServiceName) {
		this.targetServiceName = targetServiceName;
	}

	public String getTargetHost() {
		return targetHost;
	}

	public void setTargetHost(String targetHost) {
		this.targetHost = targetHost;
	}

	public String getTargetIp() {
		return targetIp;
	}

	public void setTargetIp(String targetIp) {
		this.targetIp = targetIp;
	}

	public String getTargetPort() {
		return targetPort;
	}

	public void setTargetPort(String targetPort) {
		this.targetPort = targetPort;
		this.targetPortList = dealPortList(targetPort);
	}

	public Map<String, String> getTargetMetaData() {
		return targetMetaData;
	}

	public void setTargetMetaData(Map<String, String> targetMetaData) {
		this.targetMetaData = targetMetaData;
	}

	public List<int[]> getSourcePortList() {
		return sourcePortList;
	}

	public List<int[]> getTargetPortList() {
		return targetPortList;
	}
}

	