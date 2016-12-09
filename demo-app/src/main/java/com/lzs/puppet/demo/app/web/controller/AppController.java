/**
 * Project Name: demo-app
 * File Name: AppController.java
 * Package Name: com.lzs.puppet.demo.app.web.controller
 * Describe: TODO
 * Date: 2016年8月26日下午12:32:23
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.app.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lzs.puppet.demo.app.service.AppService;
import com.lzs.puppet.demo.base.constant.Constant;
import com.lzs.puppet.demo.model.CommonResponse;
import com.lzs.puppet.demo.model.app.App;

/**
 * ClassName: AppController <br/>
 * Function: 应用内部服务. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月26日 下午12:32:23 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
@RestController
public class AppController {
	
	@Autowired
	private AppService appService;
	
	@RequestMapping(value = "/queryApp")
	@ResponseBody
	public CommonResponse<List<App>> queryApp(@RequestBody App app) {
		CommonResponse<List<App>> resp = new CommonResponse<List<App>>();
		try{
			List<App> list = appService.queryApp(app);
			resp.setResult(list);
			resp.setCode(Constant.RESPONSE_CODE_SUCCESS);
			resp.setMsg("success");
			return resp;
		}catch (Exception e){
			resp.setCode(Constant.RESPONSE_CODE_FAILED);
			resp.setMsg(e.getMessage());
			return resp;
		}
	}
	
	@RequestMapping(value = "/getAppById/{id}")
	@ResponseBody
	public CommonResponse<App> getAppById(@PathVariable("id") long id) {
		CommonResponse<App> resp = new CommonResponse<App>();
		try{
			App app = appService.getAppById(id);
			resp.setResult(app);
			resp.setCode(Constant.RESPONSE_CODE_SUCCESS);
			resp.setMsg("success");
			return resp;
		}catch (Exception e){
			resp.setCode(Constant.RESPONSE_CODE_FAILED);
			resp.setMsg(e.getMessage());
			return resp;
		}
	}
	
	@RequestMapping(value = "/addApp")
	@ResponseBody
	public CommonResponse<App> addApp(@RequestBody App app) {
		CommonResponse<App> resp = new CommonResponse<App>();
		try{
			int num = appService.addApp(app);
			if(num > 0){
				resp.setResult(app);
				resp.setCode(Constant.RESPONSE_CODE_SUCCESS);
				resp.setMsg("success");
				return resp;
			}
			else{
				resp.setCode(Constant.RESPONSE_CODE_FAILED);
				resp.setMsg("添加失败");
				return resp;
			}
		}catch (Exception e){
			resp.setCode(Constant.RESPONSE_CODE_FAILED);
			resp.setMsg(e.getMessage());
			return resp;
		}
	}
	
	@RequestMapping(value = "/updateApp")
	@ResponseBody
	public CommonResponse<App> updateApp(App app) {
		CommonResponse<App> resp = new CommonResponse<App>();
		try{
			int num = appService.updateApp(app);
			if(num > 0){
				app = appService.getAppById(app.getId());
				resp.setResult(app);
				resp.setCode(Constant.RESPONSE_CODE_SUCCESS);
				resp.setMsg("success");
				return resp;
			}
			else{
				resp.setCode(Constant.RESPONSE_CODE_FAILED);
				resp.setMsg("修改失败");
				return resp;
			}
		}catch (Exception e){
			resp.setCode(Constant.RESPONSE_CODE_FAILED);
			resp.setMsg(e.getMessage());
			return resp;
		}
	}
	
	@RequestMapping(value = "/deleteApp")
	@ResponseBody
	public CommonResponse<App> deleteApp(@RequestParam("id") long id) {
		CommonResponse<App> resp = new CommonResponse<App>();
		try{
			App app = appService.getAppById(id);
			appService.deleteApp(id);
			resp.setResult(app);
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

	