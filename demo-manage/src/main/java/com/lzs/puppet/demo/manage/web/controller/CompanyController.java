package com.lzs.puppet.demo.manage.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lzs.puppet.demo.manage.service.feign.CompanyService;
import com.lzs.puppet.demo.model.CommonResponse;
import com.lzs.puppet.demo.model.company.Company;

/**
 * 
 * ClassName: AppController <br/>
 * Function: 调用远程app模块服务，通过feign方式调用 <br/>
 * Reason: TODO ADD REASON(optional). <br/>
 * date: 2016年8月25日 下午8:01:47 <br/>
 *
 * @author lizhaosheng-黎昭声
 * @version 
 * @since JDK 1.6
 */
@RestController
@RequestMapping(value="/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;


	@RequestMapping(value = "/queryCompany")
	@ResponseBody
	public CommonResponse<List<Company>> queryCompany(Company company) {
		return companyService.queryCompany(company);
	}
	
	@RequestMapping(value = "/getCompanyById")
	@ResponseBody
	public CommonResponse<Company> getCompanyById(@RequestParam("id") long id) {
		return companyService.getCompanyById(id);
	}
	
	@RequestMapping(value = "/addCompany")
	@ResponseBody
	public CommonResponse<Company> addCompany(Company company) {
		return companyService.addCompany(company);
	}
	
	@RequestMapping(value = "/updateCompany")
	@ResponseBody
	public CommonResponse<Company> updateCompany(Company company) {
		return companyService.updateCompany(company);
		
	}
	
	@RequestMapping(value = "/deleteCompany")
	@ResponseBody
	public CommonResponse<Company> deleteCompany(@RequestParam("id") long id) {
		return companyService.deleteCompany(id);
	}
}