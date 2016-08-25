package com.lzs.puppet.demo.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lzs.puppet.demo.service.UserService;
import com.lzs.puppet.model.ApiResponse;
import com.lzs.puppet.model.User;

@RestController
public class RemoteDemoController {

	@Autowired
	private UserService userService;
	/**
	 * 远程调用内部模块
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/remote-hello")
	public ApiResponse<List<User>> remoteHello() {
		ApiResponse<List<User>> resp = new ApiResponse<List<User>>();
		List<User> list = new ArrayList<User>();
		User user = new User();
		user.setAuthCode("remote");
		list.add(user );
		resp.setResult(list);
		resp.setCode(200);
		resp.setMsg("success");
		return resp;
	}

	/**
	 * 查询所有数据库用户
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryUser")
	public ApiResponse<List<User>> queryUser(User user) {
		ApiResponse<List<User>> resp = new ApiResponse<List<User>>();
		List<User> list = userService.queryUser(user);
		resp.setResult(list);
		resp.setCode(200);
		resp.setMsg("success");
		return resp;
	}

}