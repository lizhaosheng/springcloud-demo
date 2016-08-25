package com.lzs.puppet.demo.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lzs.puppet.demo.base.Constant;
import com.lzs.puppet.model.ApiResponse;
import com.lzs.puppet.model.User;

@RestController
@RequestMapping(value="/hello")
public class HelloController {
	/**
	 * ribbon 远程调用方式
	 */
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 普通请求
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "")
	public String home() {
		return "Hello World!";
	}

	/**
	 * 远程调用内部模块
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/remoteHello")
	public ApiResponse<List<User>> remoteHello() {
//		return restTemplate.getForObject(Constant.REMOTE_HELLO_URL, ApiResponse.class);
		Map<String,String> map = new HashMap<String,String>();
		map.put("header", "head");
		return restTemplate.getForObject("http://demo-service/queryUser", ApiResponse.class,map);
	}

	/**
	 * 远程调用内部模块 
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/queryUser")
	public ApiResponse<List<User>> queryUser() {
		return restTemplate.getForObject(Constant.REMOTE_QUERY_USER, ApiResponse.class);
	}
	
}