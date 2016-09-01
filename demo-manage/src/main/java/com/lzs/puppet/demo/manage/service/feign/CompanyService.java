/**
 * Project Name: demo-mange
 * File Name: CompanyService.java
 * Package Name: com.lzs.puppet.demo.manage.service.feign
 * Describe: TODO
 * Date: 2016年8月26日下午4:20:59
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.manage.service.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lzs.puppet.demo.base.constant.Constant;
import com.lzs.puppet.demo.model.CommonResponse;
import com.lzs.puppet.demo.model.company.Company;

/**
 * ClassName: CompanyService <br/>
 * Function: 
 * 格式 <br/>
	@RequestMapping(method = RequestMethod.POST, value = "/stores/{storeId}", consumes = "application/json")
    Store update(@PathVariable("storeId") Long storeId, Store store);
	storeId参数将设置到{storeId} ，store作为json格式参数放置到body中传递
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月26日 下午4:20:59 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
//@FeignClient(url = "https://api.github.com")  url方式
@FeignClient(Constant.SERVICE.DEMO_COMPANY)  // serviceid方式
public interface CompanyService {

	@RequestMapping(value = "/company/queryCompany", method = RequestMethod.POST)
	CommonResponse<List<Company>> queryCompany(Company company);
	
	@RequestMapping(value = "/company/getCompanyById/{id}", method = RequestMethod.GET)
	CommonResponse<Company> getCompanyById(@PathVariable("id")long id);
	
	@RequestMapping(value = "/company/addCompany", method = RequestMethod.POST)
	CommonResponse<Company> addCompany(Company company);
	
	@RequestMapping(value = "/company/updateCompany/{id}", method = RequestMethod.POST)
	CommonResponse<Company> updateCompany(@PathVariable("id")long id, Company company);
	
	@RequestMapping(value = "/company/deleteCompany/{id}", method = RequestMethod.GET)
	CommonResponse<Company> deleteCompany(@PathVariable("id")long id);
}

	