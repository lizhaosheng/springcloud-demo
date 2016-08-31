/**
 * Project Name: demo-company
 * File Name: CompanyController.java
 * Package Name: com.lzs.puppet.demo.company.web.controller
 * Describe: TODO
 * Date: 2016年8月26日下午12:32:23
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.company.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lzs.puppet.demo.base.constant.Constant;
import com.lzs.puppet.demo.company.service.CompanyService;
import com.lzs.puppet.demo.model.CommonResponse;
import com.lzs.puppet.demo.model.company.Company;

/**
 * ClassName: CompanyController <br/>
 * Function: 公司（子公司）内部服务. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月26日 下午12:32:23 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
@RestController
@RequestMapping(value = "/company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value = "/queryCompany")
	@ResponseBody
	public CommonResponse<List<Company>> queryCompany(Company company) {
		CommonResponse<List<Company>> resp = new CommonResponse<List<Company>>();
		try{
			List<Company> list = companyService.queryCompany(company);
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
	
	@RequestMapping(value = "/getCompanyById")
	@ResponseBody
	public CommonResponse<Company> getCompanyById(@RequestParam("id") long id) {
		CommonResponse<Company> resp = new CommonResponse<Company>();
		try{
			Company company = companyService.getCompanyById(id);
			resp.setResult(company);
			resp.setCode(Constant.RESPONSE_CODE_SUCCESS);
			resp.setMsg("success");
			return resp;
		}catch (Exception e){
			resp.setCode(Constant.RESPONSE_CODE_FAILED);
			resp.setMsg(e.getMessage());
			return resp;
		}
	}
	
	@RequestMapping(value = "/addCompany")
	@ResponseBody
	public CommonResponse<Company> addCompany(Company company) {
		CommonResponse<Company> resp = new CommonResponse<Company>();
		try{
			int num = companyService.addCompany(company);
			if(num > 0){
				resp.setResult(company);
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
	
	@RequestMapping(value = "/updateCompany")
	@ResponseBody
	public CommonResponse<Company> updateCompany(Company company) {
		CommonResponse<Company> resp = new CommonResponse<Company>();
		try{
			int num = companyService.updateCompany(company);
			if(num > 0){
				company = companyService.getCompanyById(company.getId());
				resp.setResult(company);
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
	
	@RequestMapping(value = "/deleteCompany")
	@ResponseBody
	public CommonResponse<Company> deleteCompany(@RequestParam("id") long id) {
		CommonResponse<Company> resp = new CommonResponse<Company>();
		try{
			Company company = companyService.getCompanyById(id);
			companyService.deleteCompany(id);
			resp.setResult(company);
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

	