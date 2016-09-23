package com.lzs.puppet.demo.manage.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.lzs.puppet.demo.base.constant.Constant;
import com.lzs.puppet.demo.manage.service.feign.CompanyService;
import com.lzs.puppet.demo.model.CommonResponse;
import com.lzs.puppet.demo.model.app.App;
import com.lzs.puppet.demo.model.company.Company;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller
@RequestMapping(value="/hello")
public class HelloController {
	
	/**
	 * ribbon 远程调用方式,开启@Bean @LoadBalanced后可直接注入使用
	 */
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private CompanyService companyService;

	/**
	 * 普通请求
	 * 
	 * @return
	 */
	@RequestMapping(value = "")
	 public String hello(Model model, 
			 @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "hello";
    }
	

	/**
	 * hystrix & ribbon 使用例子
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/queryApp")
	@HystrixCommand(fallbackMethod = "defaultAppShow")
	public CommonResponse<List<App>> queryApp(App app) {
		Map<String,App> map = new HashMap<String,App>();
		// 发生断路后，调用defaultAppShow方法，其中参数就是当前方法参数的引用，所以这里的改动会反应到defaultAppShow方法中
		app.setName("the name!");
		map.put("app", app);
		return restTemplate.getForObject("http://" + Constant.SERVICE.DEMO_APP +"/queryApp", CommonResponse.class,map);
	}
	public CommonResponse<List<App>> defaultAppShow(App app) {
		CommonResponse<List<App>> cr = new CommonResponse<List<App>>();
		cr.setCode(10000);
		cr.setMsg("hystrix fallback!!!");
		cr.setData(app);
        return cr;
    }
	
	
	/**
	 * hystrix & feign 例子
	 *
	 * @author hzlizhaosheng
	 * @param company
	 * @return
	 */
	@RequestMapping(value = "/queryCompany")
	@ResponseBody
	@HystrixCommand(fallbackMethod = "defaultCompanyShow")
	public CommonResponse<List<Company>> queryCompany(Company company) {
		return companyService.queryCompany(company);
	}
	public CommonResponse<List<App>> defaultCompanyShow(Company company) {
		CommonResponse<List<App>> cr = new CommonResponse<List<App>>();
		cr.setCode(10000);
		cr.setMsg("hystrix fallback!!!");
		cr.setData(company);
        return cr;
    }
	
}