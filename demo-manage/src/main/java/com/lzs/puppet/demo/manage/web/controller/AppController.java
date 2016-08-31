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

import com.lzs.puppet.demo.base.constant.Constant;
import com.lzs.puppet.demo.model.CommonResponse;
import com.lzs.puppet.demo.model.app.App;

/**
 * 
 * ClassName: AppController <br/>
 * Function: 调用远程app模块服务，通过ribbon方式调用 <br/>
 * Reason: TODO ADD REASON(optional). <br/>
 * date: 2016年8月25日 下午8:01:47 <br/>
 *
 * @author lizhaosheng-黎昭声
 * @version 
 * @since JDK 1.6
 */
@RestController
@RequestMapping(value="/app")
public class AppController {
	/**
	 * ribbon 远程调用方式,开启@Bean @LoadBalanced后可直接注入使用
	 */
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 远程调用内部模块。
	 * http://服务名    即可调用远程模块
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/queryApp")
	public CommonResponse<List<App>> queryApp(App app) {
		Map<String,App> map = new HashMap<String,App>();
		map.put("app", app);
		return restTemplate.getForObject("http://" + Constant.SERVICE.DEMO_APP +"/queryApp", CommonResponse.class,map);
	}

	/**
	 * 远程调用内部模块
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/getAppById")
	public CommonResponse<App> getAppById(@RequestParam("id")long id) {
		return restTemplate.getForObject("http://" + Constant.SERVICE.DEMO_APP +"/getAppById/" + id, CommonResponse.class);
	}
}