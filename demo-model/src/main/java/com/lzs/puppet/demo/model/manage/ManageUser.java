/**
 * Project Name:demo-model
 * File Name:ManageUser.java
 * Package Name:com.lzs.puppet.model.manage
 * Describe: TODO
 * Date:2016年8月25日下午5:28:06
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.model.manage;

import java.io.Serializable;

/**
 * ClassName:ManageUser <br/>
 * Function: 管理后台用户信息. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月25日 下午5:28:06 <br/>
 * @author hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
public class ManageUser implements Serializable{

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
	 * 用户登录名
	 */
	private String name;
	/**
	 * 登录密码
	 */
	private String password;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

	