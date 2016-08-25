package com.lzs.puppet.demo.manage.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lzs.puppet.demo.manage.base.Constant;
import com.lzs.puppet.demo.manage.service.feign.LeaveService;
import com.lzs.puppet.model.CommonResponse;
import com.lzs.puppet.model.app.App;
import com.lzs.puppet.model.leave.Leave;

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
@RequestMapping(value="/leave")
public class LeaveController {

	@Autowired
	private LeaveService leaveService;

	/**
	 * 远程调用内部模块。
	 * http://服务名    即可调用远程模块
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getList")
	public CommonResponse<List<Leave>> getList(Leave leave) {
		return leaveService.getList(leave);
	}
	@ResponseBody
	@RequestMapping(value = "/getDetail")
	public CommonResponse<List<Leave>> getDetail(@RequestParam("id")long id) {
		return leaveService.getDetail(id);
	}
}