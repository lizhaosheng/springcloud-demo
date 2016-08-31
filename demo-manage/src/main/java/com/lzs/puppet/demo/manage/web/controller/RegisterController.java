package com.lzs.puppet.demo.manage.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lzs.puppet.demo.base.constant.Constant;
import com.lzs.puppet.demo.manage.service.ManageUserService;
import com.lzs.puppet.demo.model.CommonResponse;
import com.lzs.puppet.demo.model.manage.ManageUser;

@RestController
public class RegisterController {

	@Autowired
	private ManageUserService manageUserService;
	
	/**
	 * 登录接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/register")
	@ResponseBody
	public CommonResponse<ManageUser> register(ManageUser user) {
		CommonResponse<ManageUser> resp = new CommonResponse<ManageUser>();
		try{
			int num = manageUserService.addManageUser(user);
			if(num <= 0){
				resp.setCode(Constant.RESPONSE_CODE_FAILED);
				resp.setMsg("failed");
				return resp;
			}
			resp.setCode(Constant.RESPONSE_CODE_SUCCESS);
			resp.setMsg("success");
			resp.setData(user);
			return resp;
		}catch (Exception e){
			resp.setCode(Constant.RESPONSE_CODE_FAILED);
			resp.setMsg(e.getMessage());
			return resp;
		}
	}

}