/**
 * Project Name: puppet-route
 * File Name: Route.java
 * Package Name: com.lzs.puppet.route
 * Describe: TODO
 * Date: 2016年10月26日下午4:48:04
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.route;

import java.io.Serializable;
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
	/** 服务发起方服务名，支持通配符*/
	private List<String> sourceServiceName;
	/** 服务发起方主机名，支持通配符*/
	private List<String> sourceHost;
	/** 服务发起方ip，支持通配符*/
	private List<String> sourceIp;
	/** 服务发起方端口号表达式，类似svn的版本表达式，多个版本使用逗号分隔，减号表示连续端口号*/
	private String sourcePort;
	/** 上面表达式的解析结果*/
	private List<int[]> sourcePortList;
	/** 服务发起方标签，支持通配符*/
	private List<String> sourceTag;
	/** 服务发起方服务名，支持通配符*/
	private Map<String,Object> sourceMetaData;
	
	/** 目标服务服务名，支持通配符*/
	private List<String> targetServiceName;
	/** 目标服务服务名，支持通配符*/
	private List<String> targetHost;
	/** 目标服务服务名，支持通配符*/
	private List<String> targetIp;
	/** 目标服务服务名表达式，类似svn的版本表达式，多个版本使用逗号分隔，减号表示连续端口号*/
	private String targetPort;
	/** 上面表达式的解析结果*/
	private List<int[]> targetPortList;
	/** 目标服务服务名，支持通配符*/
	private List<String> targetTag;
	/** 目标服务服务名，支持通配符*/
	private Map<String,Object> targetMetaData;
	
	
}

	