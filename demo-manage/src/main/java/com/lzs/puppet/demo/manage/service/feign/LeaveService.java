/**
 * Project Name:demo-mange
 * File Name:LeaveService.java
 * Package Name:com.lzs.puppet.demo.manage.service.feign
 * Describe: TODO
 * Date:2016年8月25日下午8:08:50
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.manage.service.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lzs.puppet.demo.manage.base.Constant;
import com.lzs.puppet.demo.model.CommonResponse;
import com.lzs.puppet.demo.model.leave.Leave;

/**
 * ClassName:LeaveService <br/>
 * Function: 请假相关远程接口，使用feign方式进行远程调用. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月25日 下午8:08:50 <br/>
 * @author hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
// @FeignClient(url = "https://api.github.com")  url方式
@FeignClient(Constant.SERVICE.DEMO_LEAVE)  // serviceid方式
public interface LeaveService {
	/**
	 * 格式
	 * @RequestMapping(method = RequestMethod.POST, value = "/stores/{storeId}", consumes = "application/json")
       Store update(@PathVariable("storeId") Long storeId, Store store);
	 * storeId参数将设置到{storeId} ，store作为json格式参数放置到body中传递
	 * @author hzlizhaosheng
	 * @param leave
	 * @return
	 */
	@RequestMapping(value = "/getList", method = RequestMethod.POST, consumes = "application/json")
	CommonResponse<List<Leave>> getList(Leave leave);
	
	@RequestMapping(value = "/getDetail/{id}", method = RequestMethod.POST)
	CommonResponse<List<Leave>> getDetail(@PathVariable("id")long id);
}

	