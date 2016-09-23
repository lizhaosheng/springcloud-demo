package com.lzs.puppet.demo.manage.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lzs.puppet.demo.base.constant.Constant;
import com.lzs.puppet.demo.manage.service.ManageUserService;
import com.lzs.puppet.demo.model.CommonResponse;
import com.lzs.puppet.demo.model.manage.ManageUser;

@RestController
public class LoginController {

	@Autowired
	private ManageUserService manageUserService;
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	/**
	 * 登录接口
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/doLogin")
	@ResponseBody
	public CommonResponse doLogin(ManageUser user, HttpSession sesison) {
		CommonResponse resp = new CommonResponse();
		try{
			manageUserService.checkLogin(user);
			sesison.setAttribute(Constant.MANAGE_LOGIN_USER, user);
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