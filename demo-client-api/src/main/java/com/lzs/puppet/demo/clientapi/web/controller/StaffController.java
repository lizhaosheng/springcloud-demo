package com.lzs.puppet.demo.clientapi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lzs.puppet.demo.clientapi.service.feign.StaffService;
import com.lzs.puppet.demo.model.CommonResponse;
import com.lzs.puppet.demo.model.company.Staff;

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
@RequestMapping(value="/staff")
public class StaffController {

	@Autowired
	private StaffService staffService;


	@RequestMapping(value = "/queryStaff")
	@ResponseBody
	public CommonResponse<List<Staff>> queryStaff(Staff staff) {
		return staffService.queryStaff(staff);
	}
	
	@RequestMapping(value = "/getStaffById")
	@ResponseBody
	public CommonResponse<Staff> getStaffById(@RequestParam("id") long id) {
		return staffService.getStaffById(id);
	}
	
	@RequestMapping(value = "/updatMe")
	@ResponseBody
	public CommonResponse<Staff> updatMe(Staff staff) {
		Staff s = new Staff();
		s.setId(staff.getId());
		s.setMobile(staff.getMobile());
		s.setRemark(staff.getRemark());
		return staffService.updateStaff(s);	
	}
	@RequestMapping(value = "/updatLoginPwd")
	@ResponseBody
	public CommonResponse<Staff> updatLoginPwd(@RequestHeader("uid")long uid,
			@RequestParam("oldpwd")String oldpwd,@RequestParam("newpwd")String newpwd) {
		return staffService.updateStaffPwd(uid,oldpwd,newpwd);	
	}
}