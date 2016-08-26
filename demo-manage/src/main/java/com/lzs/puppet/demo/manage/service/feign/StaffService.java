/**
 * Project Name: demo-mange
 * File Name: StaffService.java
 * Package Name: com.lzs.puppet.demo.manage.service.feign
 * Describe: TODO
 * Date: 2016年8月26日下午4:20:59
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.manage.service.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lzs.puppet.demo.manage.base.MyConstant;
import com.lzs.puppet.demo.model.CommonResponse;
import com.lzs.puppet.demo.model.company.Staff;

/**
 * ClassName: StaffService <br/>
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
@FeignClient(MyConstant.SERVICE.DEMO_LEAVE)  // serviceid方式
public interface StaffService {

	@RequestMapping(value = "/staff/queryStaff", method = RequestMethod.POST, consumes = "application/json")
	CommonResponse<List<Staff>> queryStaff(Staff staff);
	
	@RequestMapping(value = "/staff/getStaffById", method = RequestMethod.GET)
	CommonResponse<Staff> getStaffById(long id);
	
	@RequestMapping(value = "/staff/addStaff", method = RequestMethod.POST, consumes = "application/json")
	CommonResponse<Staff> addStaff(Staff staff);
	
	@RequestMapping(value = "/staff/updateStaff", method = RequestMethod.POST, consumes = "application/json")
	CommonResponse<Staff> updateStaff(Staff staff);
	
	@RequestMapping(value = "/staff/deleteStaff", method = RequestMethod.GET)
	CommonResponse<Staff> deleteStaff(long id);
}

	