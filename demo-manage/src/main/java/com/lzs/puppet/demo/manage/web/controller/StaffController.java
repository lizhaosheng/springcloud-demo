package com.lzs.puppet.demo.manage.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lzs.puppet.demo.manage.service.feign.StaffService;
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
	
	@RequestMapping(value = "/addStaff")
	@ResponseBody
	public CommonResponse<Staff> addStaff(Staff staff) {
		return staffService.addStaff(staff);
	}
	
	@RequestMapping(value = "/updateStaff")
	@ResponseBody
	public CommonResponse<Staff> updateStaff(Staff staff) {
		return staffService.updateStaff(staff);
		
	}
	
	@RequestMapping(value = "/deleteStaff")
	@ResponseBody
	public CommonResponse<Staff> deleteStaff(@RequestParam("id") long id) {
		return staffService.deleteStaff(id);
	}
}