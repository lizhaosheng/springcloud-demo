/**
 * Project Name: demo-client-api
 * File Name: MyController.java
 * Package Name: com.lzs.puppet.demo.clientapi.web.controller
 * Describe: TODO
 * Date: 2016年8月26日下午5:31:42
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.clientapi.web.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lzs.puppet.demo.base.constant.Constant;
import com.lzs.puppet.demo.clientapi.service.feign.StaffService;
import com.lzs.puppet.demo.model.CommonResponse;
import com.lzs.puppet.demo.model.company.Staff;

/**
 * ClassName: MyController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月26日 下午5:31:42 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
@RestController
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private StaffService staffService;
	
	/**
	 * 登录接口
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/login")
	@ResponseBody
	public CommonResponse login(@RequestParam("jobnumber") String jobnumber, @RequestParam("pwd") String pwd) {
		CommonResponse resp = new CommonResponse();
		try{
			if(StringUtils.isBlank(jobnumber)){
				resp.setCode(Constant.RESPONSE_CODE_FAILED);
				resp.setMsg("登录名不能为空");
				return resp;
			}else if(StringUtils.isBlank(pwd)){
				resp.setCode(Constant.RESPONSE_CODE_FAILED);
				resp.setMsg("密码不能为空");
				return resp;
			}
			Staff staff = new Staff();
			staff.setJobnumber(jobnumber);
			staff.setPwd(pwd);
			staff.setState(Staff.STATE_OK);
			staff.setWorkState(Staff.WORK_STATE_ONBOARD);
			CommonResponse<List<Staff>> cr = staffService.queryStaff(staff);
			if(cr.getCode() == Constant.RESPONSE_CODE_SUCCESS){
				if(cr.getResult() != null && cr.getResult().size() == 1){
					Staff s = cr.getResult().get(0);
					if(pwd.equals(s.getPwd())){
						resp.setCode(Constant.RESPONSE_CODE_SUCCESS);
						resp.setMsg("success");
						return resp;
					}else{
						resp.setCode(Constant.RESPONSE_CODE_FAILED);
						resp.setMsg("密码错误");
						return resp;
					}
				}
				resp.setCode(Constant.RESPONSE_CODE_FAILED);
				resp.setMsg("用户不存在");
				return resp;
			}
			logger.error("请求staff服务失败，msg=" + cr.getMsg());
			resp.setCode(Constant.RESPONSE_CODE_FAILED);
			resp.setMsg("系统繁忙，请稍候");
			return resp;
		}catch (Exception e){
			resp.setCode(Constant.RESPONSE_CODE_FAILED);
			resp.setMsg(e.getMessage());
			return resp;
		}
	}

}

	