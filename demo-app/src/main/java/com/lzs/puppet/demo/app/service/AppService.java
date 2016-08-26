/**
 * Project Name: demo-App
 * File Name: AppService.java
 * Package Name: com.lzs.puppet.demo.App.service
 * Describe: TODO
 * Date: 2016年8月26日下午2:11:42
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.app.service;

import java.util.List;

import com.lzs.puppet.demo.model.app.App;

/**
 * ClassName: AppService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月26日 下午2:11:42 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
public interface AppService {

	List<App> queryApp(App App);
	
	App getAppById(long id);
	
	int addApp(App App);

	int updateApp(App App);
	
	int deleteApp(long id);
	
}

	