/**
 * Project Name:demo-service
 * File Name:UserDao.java
 * Package Name:com.lzs.puppet.demo.dao
 * Describe: TODO
 * Date:2016年8月20日下午4:01:50
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.dao;

import java.util.List;

import com.lzs.puppet.model.User;

/**
 * ClassName:UserDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月20日 下午4:01:50 <br/>
 * @author hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
public interface UserDao {

	List<User> queryUser(User user);

}

	