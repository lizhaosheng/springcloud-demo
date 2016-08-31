/**
 * Project Name: demo-leave
 * File Name: LeaveController.java
 * Package Name: com.lzs.puppet.demo.leave.web.controller
 * Describe: TODO
 * Date: 2016年8月26日下午12:32:23
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.leave.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lzs.puppet.demo.base.constant.Constant;
import com.lzs.puppet.demo.leave.sesrvice.LeaveService;
import com.lzs.puppet.demo.model.CommonResponse;
import com.lzs.puppet.demo.model.leave.Leave;

/**
 * ClassName: LeaveController <br/>
 * Function: 应用内部服务. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月26日 下午12:32:23 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
@RestController
@RequestMapping(value = "/leave")
public class LeaveController {
	
	@Autowired
	private LeaveService leaveService;
	
	@RequestMapping(value = "/queryLeave")
	@ResponseBody
	public CommonResponse<List<Leave>> queryLeave(Leave leave) {
		CommonResponse<List<Leave>> resp = new CommonResponse<List<Leave>>();
		try{
			List<Leave> list = leaveService.queryLeave(leave);
			resp.setResult(list);
			resp.setCode(Constant.RESPONSE_CODE_SUCCESS);
			resp.setMsg("success");
			return resp;
		}catch (Exception e){
			resp.setCode(Constant.RESPONSE_CODE_FAILED);
			resp.setMsg(e.getMessage());
			return resp;
		}
	}
	
	@RequestMapping(value = "/getLeaveById")
	@ResponseBody
	public CommonResponse<Leave> getLeaveById(@RequestParam("id") long id) {
		CommonResponse<Leave> resp = new CommonResponse<Leave>();
		try{
			Leave leave = leaveService.getLeaveById(id);
			resp.setResult(leave);
			resp.setCode(Constant.RESPONSE_CODE_SUCCESS);
			resp.setMsg("success");
			return resp;
		}catch (Exception e){
			resp.setCode(Constant.RESPONSE_CODE_FAILED);
			resp.setMsg(e.getMessage());
			return resp;
		}
	}
	
	@RequestMapping(value = "/addLeave")
	@ResponseBody
	public CommonResponse<Leave> addLeave(Leave leave) {
		CommonResponse<Leave> resp = new CommonResponse<Leave>();
		try{
			int num = leaveService.addLeave(leave);
			if(num > 0){
				resp.setResult(leave);
				resp.setCode(Constant.RESPONSE_CODE_SUCCESS);
				resp.setMsg("success");
				return resp;
			}
			else{
				resp.setCode(Constant.RESPONSE_CODE_FAILED);
				resp.setMsg("添加失败");
				return resp;
			}
		}catch (Exception e){
			resp.setCode(Constant.RESPONSE_CODE_FAILED);
			resp.setMsg(e.getMessage());
			return resp;
		}
	}
	
	@RequestMapping(value = "/updateLeave")
	@ResponseBody
	public CommonResponse<Leave> updateLeave(Leave leave) {
		CommonResponse<Leave> resp = new CommonResponse<Leave>();
		try{
			int num = leaveService.updateLeave(leave);
			if(num > 0){
				leave = leaveService.getLeaveById(leave.getId());
				resp.setResult(leave);
				resp.setCode(Constant.RESPONSE_CODE_SUCCESS);
				resp.setMsg("success");
				return resp;
			}
			else{
				resp.setCode(Constant.RESPONSE_CODE_FAILED);
				resp.setMsg("修改失败");
				return resp;
			}
		}catch (Exception e){
			resp.setCode(Constant.RESPONSE_CODE_FAILED);
			resp.setMsg(e.getMessage());
			return resp;
		}
	}
	
	@RequestMapping(value = "/deleteLeave")
	@ResponseBody
	public CommonResponse<Leave> deleteLeave(@RequestParam("id") long id) {
		CommonResponse<Leave> resp = new CommonResponse<Leave>();
		try{
			Leave leave = leaveService.getLeaveById(id);
			leaveService.deleteLeave(id);
			resp.setResult(leave);
			resp.setCode(Constant.RESPONSE_CODE_SUCCESS);
			resp.setMsg("success");
			return resp;
		}catch (Exception e){
			resp.setCode(Constant.RESPONSE_CODE_FAILED);
			resp.setMsg(e.getMessage());
			return resp;
		}
	}
}

	