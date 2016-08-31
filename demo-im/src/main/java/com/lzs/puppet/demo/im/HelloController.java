package com.lzs.puppet.demo.im;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	/**
	 * 普通请求
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/hello")
	public String home() {
		return "Hello World!";
	}
}