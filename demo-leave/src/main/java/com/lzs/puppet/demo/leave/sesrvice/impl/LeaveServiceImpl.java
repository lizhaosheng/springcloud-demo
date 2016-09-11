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
import com.lzs.puppet.demo.leave.sesrvice.feign.StaffService;
import com.lzs.puppet.demo.model.CommonResponse;
import com.lzs.puppet.demo.model.company.Staff;
import com.lzs.puppet.demo.model.leave.Leave;
import com.lzs.puppet.demo.base.constant.Constant;
import com.lzs.puppet.demo.base.exception.ServiceException;

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

	@Autowired
	private StaffService staffService;
	
	@Override
	public List<Leave> queryLeave(Leave leave) {
		return leaveDao.queryLeave(leave);
	}

	@Override
	public Leave getLeaveById(long id) {
		return leaveDao.getLeaveById(id);
	}

	@Override
	public Leave addLeave(Leave leave) {
		checkAdd(leave);
		int num = leaveDao.addLeave(leave);
		if(num == 1){
			return leave;
		}
		return null;
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
		if(leave.getApprover() == leave.getStaffId()){
			throw new ServiceException("审批人不能是请假人");
		}
		// 检查请假人和审批人是否存在
		CommonResponse<Staff> crstaff = staffService.getStaffById(leave.getStaffId());
		if(crstaff.getCode() == Constant.RESPONSE_CODE_SUCCESS && crstaff.getResult() != null){
			CommonResponse<Staff> crapprover = staffService.getStaffById(leave.getApprover());
			if(crapprover.getCode() == Constant.RESPONSE_CODE_SUCCESS && crapprover.getResult() != null){
				
			}else{
				throw new ServiceException("审批人不存在");
			}
		}else{
			throw new ServiceException("请假人不存在");
		}
	}

	@Override
	public Leave getLeaveByIdUid(long id,long uid) {
		return leaveDao.getLeaveByIdUid(id, uid);
	}

	@Override
	public Leave updateMyLeave(Leave leave) {
		if(leave == null || leave.getId() <= 0){
			throw new ServiceException("参数有误");
		}
		Leave l = getLeaveByIdUid(leave.getId(),leave.getStaffId());
		if(l == null){
			throw new ServiceException("指定请假单不存在");
		}
		if(l.getState() == 2){
			// 已经审批，不能修改
			throw new ServiceException("该请假单已经审批通过，不能修改");
		}
		if(l.getState() == 3){
			// 已经审批，不能修改
			throw new ServiceException("该请假单已经审批不通过，不能修改");
		}
		if(l.getState() == 4){
			// 已经审批，不能修改
			throw new ServiceException("该请假单已经撤销，不能修改");
		}
		if(leave.getApprover() > 0){
			if(l.getStaffId() == leave.getApprover()){
				throw new ServiceException("审批人不能是自己");
			}
			l.setApprover(leave.getApprover());
		}
		if(StringUtils.isNotBlank(leave.getReason())){
			l.setReason(leave.getReason());
		}
		if(leave.getStartTime() > 0){
			l.setStartTime(leave.getStartTime());
		}
		if(leave.getEndTime() > 0){
			l.setEndTime(leave.getEndTime());
		}
		int num = updateLeave(l);
		if(num != 1){
			throw new ServiceException("更新失败");
		}
		return l;
	}

	@Override
	public Leave rollbackMyLeave(long id, long uid) {
		if(id <= 0 || uid <= 0){
			throw new ServiceException("参数有误");
		}
		Leave l = getLeaveByIdUid(id,uid);
		if(l == null){
			throw new ServiceException("指定请假单不存在");
		}
		if(l.getState() == 2){
			// 已经审批，不能修改
			throw new ServiceException("该请假单已经审批通过，不能撤销");
		}
		if(l.getState() == 3){
			// 已经审批，不能修改
			throw new ServiceException("该请假单已经审批不通过，不能撤销");
		}
		if(l.getState() == 4){
			// 已经审批，不能修改
			throw new ServiceException("该请假单已经撤销，不能重复撤销");
		}
		l.setState(4);
		int num = updateLeave(l);
		if(num != 1){
			throw new ServiceException("更新失败");
		}
		return l;
	}

	@Override
	public Leave updateApproval(Leave leave) {
		if(leave == null || leave.getId() <= 0 || 
				(leave.getState() != 2 && leave.getState() != 3)){
			throw new ServiceException("参数有误");
		}
		Leave l = getLeaveById(leave.getId());
		if(l == null || l.getApprover() != leave.getApprover()){
			throw new ServiceException("指定请假单不存在");
		}
		if(l.getState() == 2){
			// 已经审批，不能修改
			throw new ServiceException("该请假单已经审批通过，不能重复审批");
		}
		if(l.getState() == 3){
			// 已经审批，不能修改
			throw new ServiceException("该请假单已经审批不通过，不能重复审批");
		}
		if(l.getState() == 4){
			// 已经审批，不能修改
			throw new ServiceException("该请假单已经撤销，不能审批");
		}
		l.setState(leave.getState());
		int num = updateLeave(l);
		if(num != 1){
			throw new ServiceException("更新失败");
		}
		return l;
	}

	private int updateLeave(Leave leave) {
		return leaveDao.updateLeave(leave);
	}

}

	