/**
 * Project Name:demo-service
 * File Name:UserServiceImpl.java
 * Package Name:com.lzs.puppet.demo.service.impl
 * Describe: TODO
 * Date:2016年8月20日下午4:00:26
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lzs.puppet.demo.dao.UserDao;
import com.lzs.puppet.demo.service.UserService;
import com.lzs.puppet.model.User;

/**
 * ClassName:UserServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月20日 下午4:00:26 <br/>
 * @author hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> queryUser(User user) {
		return userDao.queryUser(user);	
	}

}

	