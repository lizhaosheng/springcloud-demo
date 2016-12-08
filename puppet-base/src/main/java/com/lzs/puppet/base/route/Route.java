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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	private List<Integer> sourcePortList;
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
	private List<Integer> targetPortList;
	/** 目标服务服务名，正则表达式*/
	private Map<String,String> targetMetaData;
	

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
		if(port == 0){
			return false;
		}
		if(sourcePortList == null || sourcePortList.isEmpty()){
			return true;
		}
		return sourcePortList.contains(port);
	}
	
	public boolean isMatchTargetPort(int port){
		if(port == 0){
			return false;
		}
		if(targetPortList == null || targetPortList.isEmpty()){
			return true;
		}
		return targetPortList.contains(port);
	}
	
	public boolean isMatchSourceMetaData(Map<String,String> map){//sourceMetaData
		if(sourceMetaData == null || sourceMetaData.isEmpty()){
			return true;
		}
		if(map == null || map.isEmpty()){
			return false;
		}
		String key = null;
		Iterator<String> it = sourceMetaData.keySet().iterator();
		while(it.hasNext()){
			key = it.next();
			String value = sourceMetaData.get(key);
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
	
	public boolean isMatchTargetMetaData(Map<String,String> map){//sourceMetaData
		if(targetMetaData == null || targetMetaData.isEmpty()){
			return true;
		}
		if(map == null || map.isEmpty()){
			return false;
		}
		String key = null;
		Iterator<String> it = targetMetaData.keySet().iterator();
		while(it.hasNext()){
			key = it.next();
			String value = targetMetaData.get(key);
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
	}

	public List<Integer> getSourcePortList() {
		return sourcePortList;
	}

	public void setSourcePortList(List<Integer> sourcePortList) {
		this.sourcePortList = sourcePortList;
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
	}

	public List<Integer> getTargetPortList() {
		return targetPortList;
	}

	public void setTargetPortList(List<Integer> targetPortList) {
		this.targetPortList = targetPortList;
	}

	public Map<String, String> getTargetMetaData() {
		return targetMetaData;
	}

	public void setTargetMetaData(Map<String, String> targetMetaData) {
		this.targetMetaData = targetMetaData;
	}
}

	