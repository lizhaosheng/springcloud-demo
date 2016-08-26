/**
 * Project Name: demo-model
 * File Name: Company.java
 * Package Name: com.lzs.puppet.demo.model.company
 * Describe: TODO
 * Date: 2016年8月26日下午2:02:22
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.model.company;

import java.io.Serializable;

/**
 * ClassName: Company <br/>
 * Function: 公司信息. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月26日 下午2:02:22 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
public class Company implements Serializable{

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
	 * 创建时间
	 */
	private long updateTime;
	/**
	 * 企业名
	 */
	private String name;
	/**
	 * 企业描述
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
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}

	