/**
 * Project Name: demo-company
 * File Name: LeaveDao.java
 * Package Name: com.lzs.puppet.demo.company.dao
 * Describe: TODO
 * Date: 2016年8月26日下午3:32:28
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.leave.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lzs.puppet.demo.model.leave.Leave;

/**
 * ClassName: LeaveDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月26日 下午3:32:28 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
public interface LeaveDao {

	List<Leave> queryLeave(Leave leave);
	
	Leave getLeaveById(@Param("id")long id);
	
	int addLeave(Leave leave);

	int updateLeave(Leave leave);
	
	int deleteLeave(@Param("id")long id);

}

	