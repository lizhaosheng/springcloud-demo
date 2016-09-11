package com.lzs.puppet.demo.clientapi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lzs.puppet.demo.base.constant.Constant;
import com.lzs.puppet.demo.clientapi.service.feign.LeaveService;
import com.lzs.puppet.demo.model.CommonResponse;
import com.lzs.puppet.demo.model.leave.Leave;

/**
 * 
 * ClassName: AppController <br/>
 * Function: 调用远程app模块服务，通过feign方式调用 <br/>
 * Reason: TODO ADD REASON(optional). <br/>
 * date: 2016年8月25日 下午8:01:47 <br/>
 *
 * @author lizhaosheng-黎昭声
 * @version 
 * @since JDK 1.6
 */
@RestController
@RequestMapping(value = "/leave")
public class LeaveController {

	@Autowired
	private LeaveService leaveService;

	@ResponseBody
	@RequestMapping(value = "/query")
	public CommonResponse<List<Leave>> query(@RequestHeader("uid")long uid, Leave leave) {
		leave.setStaffId(uid);
		return leaveService.queryMyLeave(leave);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getById/{id}")
	public CommonResponse<Leave> getLeaveByIdUid(@RequestHeader("uid")long uid, @PathVariable("id")long id) {
		 return leaveService.getLeaveByIdUid(id,uid);
	}
	

	@ResponseBody
	@RequestMapping(value = "/add")
	public CommonResponse<Leave> add(@RequestHeader("uid")long uid, Leave leave) {
		if(uid <= 0){
			CommonResponse<Leave> r = new CommonResponse<Leave>();
			r.setCode(Constant.RESPONSE_CODE_FAILED);
			r.setMsg("参数有误");
			return r;
		}
		leave.setStaffId(uid);
		return leaveService.addLeave(leave);
	}
	

	@ResponseBody
	@RequestMapping(value = "/update")
	public CommonResponse<Leave> update(@RequestHeader("uid")long uid, Leave leave) {
		leave.setStaffId(uid);
		return leaveService.updateMyLeave(leave);
	}
	
	@ResponseBody
	@RequestMapping(value = "/rollback/{id}")
	public CommonResponse<Leave> rollback(@RequestHeader("uid")long uid, @PathVariable("id")long id) {
		return leaveService.rollbackMyLeave(uid,id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/approval")
	public CommonResponse<Leave> approval(@RequestHeader("uid")long uid, Leave leave) {
		leave.setApprover(uid);
		return leaveService.approval(leave);
	}
}