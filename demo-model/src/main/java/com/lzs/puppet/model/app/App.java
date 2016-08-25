/**
 * Project Name:demo-model
 * File Name:App.java
 * Package Name:com.lzs.puppet.model.app
 * Describe: TODO
 * Date:2016年8月25日下午7:25:36
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.model.app;

import java.io.Serializable;

/**
 * ClassName:App <br/>
 * Function: 应用描述对象. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月25日 下午7:25:36 <br/>
 * @author hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
public class App implements Serializable{

	/**
	 * serialVersionUID:TODO.
	 */
		
	private static final long serialVersionUID = 1L;
	
	/**
	 * 自增长id
	 */
	private long id;
	/**
	 * 创建时间
	 */
	private long createTime;
	/**
	 * 应用标示
	 */
	private String identity;
	/**
	 * 应用类型
	 */
	private int type;
	/**
	 * 应用名
	 */
	private String name;
	/**
	 * 应用图标
	 */
	private String logo;
	/**
	 * 应用描述
	 */
	private String remark;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}

	