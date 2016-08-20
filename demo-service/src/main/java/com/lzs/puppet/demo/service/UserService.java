/**
 * Project Name:demo-service
 * File Name:UserService.java
 * Package Name:com.lzs.puppet.demo.service
 * Describe: TODO
 * Date:2016年8月20日下午3:56:56
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.service;

import java.util.List;

import com.lzs.puppet.model.User;

/**
 * ClassName:UserService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月20日 下午3:56:56 <br/>
 * @author hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
public interface UserService {

	/**
	 * 根据条件查询用户
	 * @author hzlizhaosheng
	 * @param user
	 * @return
	 * @since JDK 1.6
	 */
	List<User> queryUser(User user);

}

	