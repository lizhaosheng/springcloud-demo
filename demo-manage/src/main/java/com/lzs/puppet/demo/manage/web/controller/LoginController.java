package com.lzs.puppet.demo.manage.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lzs.puppet.demo.manage.service.ManageUserService;
import com.lzs.puppet.demo.model.CommonResponse;
import com.lzs.puppet.demo.model.Constant;
import com.lzs.puppet.demo.model.manage.ManageUser;

@RestController
public class LoginController {

	@Autowired
	private ManageUserService manageUserService;
	
	/**
	 * 登录接口
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/login")
	@ResponseBody
	public CommonResponse remoteHello(ManageUser user) {
		CommonResponse resp = new CommonResponse();
		try{
			manageUserService.checkLogin(user);
			resp.setCode(Constant.RESPONSE_CODE_SUCCESS);
			resp.setMsg("success");
			return resp;
		}catch (Exception e){
			resp.setCode(Constant.RESPONSE_CODE_FAILED);
			resp.setMsg("failed");
			return resp;
		}
	}

}