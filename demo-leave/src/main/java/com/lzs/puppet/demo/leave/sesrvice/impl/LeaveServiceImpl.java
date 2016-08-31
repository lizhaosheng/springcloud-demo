/**
 * Project Name: demo-leave
 * File Name: LeaveServiceImpl.java
 * Package Name: com.lzs.puppet.demo.leave.service.impl
 * Describe: TODO
 * Date: 2016年8月26日下午2:21:21
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.leave.sesrvice.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lzs.puppet.demo.leave.dao.LeaveDao;
import com.lzs.puppet.demo.leave.sesrvice.LeaveService;
import com.lzs.puppet.demo.model.leave.Leave;
import com.lzs.puppet.demo.model.exception.ServiceException;

/**
 * ClassName: LeaveServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月26日 下午2:21:21 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
@Service("leaveService")
public class LeaveServiceImpl implements LeaveService{
	
	@Autowired
	private LeaveDao leaveDao;

	@Override
	public List<Leave> queryLeave(Leave leave) {
		return leaveDao.queryLeave(leave);
	}

	@Override
	public Leave getLeaveById(long id) {
		return leaveDao.getLeaveById(id);
	}

	@Override
	public int addLeave(Leave leave) {
		checkAdd(leave);
		return leaveDao.addLeave(leave);
	}

	private void checkAdd(Leave leave) {
		if(leave == null){
			throw new ServiceException("参数有误");
		}
		if(leave.getStartTime() <= 0){
			throw new ServiceException("请选择开始时间");
		}
		if(leave.getEndTime() <= 0){
			throw new ServiceException("请选择结束时间");
		}
		if(leave.getEndTime() <= leave.getStartTime()){
			throw new ServiceException("结束时间不能早于开始时间");
		}
		if(StringUtils.isBlank(leave.getReason())){
			throw new ServiceException("请假缘由不能为空");
		}
		if(leave.getStaffId() <= 0){
			throw new ServiceException("未指定请假人");
		}
		if(leave.getApprover() <= 0){
			throw new ServiceException("未指定审批人");
		}
		// 检查请假人和审批人是否存在
	}

	@Override
	public int updateLeave(Leave leave) {
		checkUpdate(leave);
		return leaveDao.updateLeave(leave);
	}
	private void checkUpdate(Leave leave) {
		if(leave == null || leave.getId() <= 0){
			throw new ServiceException("未指定需要修改的应用");
		}
	}
	
	@Override
	public int deleteLeave(long id) {
		return leaveDao.deleteLeave(id);
	}

}

	