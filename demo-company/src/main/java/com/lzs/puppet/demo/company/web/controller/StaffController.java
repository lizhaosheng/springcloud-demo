/**
 * Project Name: demo-staff
 * File Name: StaffController.java
 * Package Name: com.lzs.puppet.demo.staff.web.controller
 * Describe: TODO
 * Date: 2016年8月26日下午12:32:23
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.company.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lzs.puppet.demo.base.constant.Constant;
import com.lzs.puppet.demo.company.service.StaffService;
import com.lzs.puppet.demo.model.CommonResponse;
import com.lzs.puppet.demo.model.company.Staff;

/**
 * ClassName: StaffController <br/>
 * Function: 公司（子公司）内部服务. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月26日 下午12:32:23 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
@RestController
@RequestMapping(value = "/staff")
public class StaffController {
	
	@Autowired
	private StaffService staffService;
	
	@RequestMapping(value = "/queryStaff")
	@ResponseBody
	public CommonResponse<List<Staff>> queryStaff(@RequestBody Staff staff) {
		CommonResponse<List<Staff>> resp = new CommonResponse<List<Staff>>();
		try{
			List<Staff> list = staffService.queryStaff(staff);
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
	
	@RequestMapping(value = "/getStaffById")
	@ResponseBody
	public CommonResponse<Staff> getStaffById(@RequestParam("id") long id) {
		CommonResponse<Staff> resp = new CommonResponse<Staff>();
		try{
			Staff staff = staffService.getStaffById(id);
			resp.setResult(staff);
			resp.setCode(Constant.RESPONSE_CODE_SUCCESS);
			resp.setMsg("success");
			return resp;
		}catch (Exception e){
			resp.setCode(Constant.RESPONSE_CODE_FAILED);
			resp.setMsg(e.getMessage());
			return resp;
		}
	}
	
	@RequestMapping(value = "/addStaff")
	@ResponseBody
	public CommonResponse<Staff> addStaff(@RequestBody Staff staff) {
		CommonResponse<Staff> resp = new CommonResponse<Staff>();
		try{
			int num = staffService.addStaff(staff);
			if(num > 0){
				resp.setResult(staff);
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
	
	@RequestMapping(value = "/updateStaff")
	@ResponseBody
	public CommonResponse<Staff> updateStaff(@RequestBody Staff staff) {
		CommonResponse<Staff> resp = new CommonResponse<Staff>();
		try{
			int num = staffService.updateStaff(staff);
			if(num > 0){
				staff = staffService.getStaffById(staff.getId());
				resp.setResult(staff);
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
	
	@RequestMapping(value = "/updateStaffPwd")
	@ResponseBody
	public CommonResponse<Staff> updateStaffPwd(@RequestParam("id")long id,
			@RequestParam("oldpwd")String oldpwd,@RequestParam("newpwd")String newpwd) {
		CommonResponse<Staff> resp = new CommonResponse<Staff>();
		try{
			int num = staffService.updateStaffPwd(id, oldpwd, newpwd);
			if(num > 0){
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
	
	@RequestMapping(value = "/deleteStaff")
	@ResponseBody
	public CommonResponse<Staff> deleteStaff(@RequestParam("id") long id) {
		CommonResponse<Staff> resp = new CommonResponse<Staff>();
		try{
			Staff staff = staffService.getStaffById(id);
			staffService.deleteStaff(id);
			resp.setResult(staff);
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

	