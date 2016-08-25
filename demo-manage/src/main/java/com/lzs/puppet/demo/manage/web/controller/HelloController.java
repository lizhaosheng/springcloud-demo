package com.lzs.puppet.demo.manage.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/hello")
public class HelloController {

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
}