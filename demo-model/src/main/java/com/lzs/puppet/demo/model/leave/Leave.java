/**
 * Project Name:demo-model
 * File Name:Leave.java
 * Package Name:com.lzs.puppet.model.leave
 * Describe: TODO
 * Date:2016年8月25日下午8:11:13
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.model.leave;

import java.io.Serializable;

/**
 * ClassName:Leave <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月25日 下午8:11:13 <br/>
 * @author hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
public class Leave implements Serializable{

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
	 * 更新时间
	 */
	private long updateTime;
	/**
	 * 请假开始时间
	 */
	private long startTime;
	/**
	 * 请假结束时间
	 */
	private long endTime;
	/**
	 * 请假原因
	 */
	private String reason;
	/**
	 * 员工id
	 */
	private long staffId;
	/**
	 * 审批人id
	 */
	private long approver;
	/**
	 * 状态
	 */
	private long state;
	
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
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public long getStaffId() {
		return staffId;
	}
	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}
	public long getApprover() {
		return approver;
	}
	public void setApprover(long approver) {
		this.approver = approver;
	}
	public long getState() {
		return state;
	}
	public void setState(long state) {
		this.state = state;
	}
	
}

	