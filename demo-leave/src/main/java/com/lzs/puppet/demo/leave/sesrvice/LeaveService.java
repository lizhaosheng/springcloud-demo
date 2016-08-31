/**
 * Project Name: demo-Leave
 * File Name: LeaveService.java
 * Package Name: com.lzs.puppet.demo.Leave.service
 * Describe: TODO
 * Date: 2016年8月26日下午2:11:42
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.leave.sesrvice;

import java.util.List;

import com.lzs.puppet.demo.model.leave.Leave;

/**
 * ClassName: LeaveService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月26日 下午2:11:42 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
public interface LeaveService {

	List<Leave> queryLeave(Leave Leave);
	
	Leave getLeaveById(long id);
	
	int addLeave(Leave Leave);

	int updateLeave(Leave Leave);
	
	int deleteLeave(long id);
	
}

	