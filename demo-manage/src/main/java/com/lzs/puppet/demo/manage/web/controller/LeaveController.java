package com.lzs.puppet.demo.manage.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lzs.puppet.demo.manage.service.feign.LeaveService;
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
	@RequestMapping(value = "/queryLeave")
	public CommonResponse<List<Leave>> queryList(Leave leave) {
		return leaveService.queryLeave(leave);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getLeaveById/{id}")
	public CommonResponse<Leave> getLeaveById(@PathVariable("id")long id) {
		return leaveService.getLeaveById(id);
	}
}