/**
 * Project Name:demo-mange
 * File Name:ManageUserService.java
 * Package Name:com.lzs.puppet.demo.manage.service
 * Describe: TODO
 * Date:2016年8月25日下午5:59:08
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.manage.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lzs.puppet.demo.manage.dao.ManageUserDao;
import com.lzs.puppet.demo.manage.service.ManageUserService;
import com.lzs.puppet.model.exception.ServiceException;
import com.lzs.puppet.model.manage.ManageUser;

/**
 * ClassName:ManageUserService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月25日 下午5:59:08 <br/>
 * @author hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
public class ManageUserServiceImpl implements ManageUserService{

	@Autowired
	private ManageUserDao manageUserDao;
	
	public void checkLogin(ManageUser user) throws ServiceException{
		if(user == null || StringUtils.isBlank(user.getName())){
			throw new ServiceException("参数有误");
		}
		ManageUser u = manageUserDao.getManageUserByName(user);
		if(u == null){
			throw new ServiceException("用户不存在");
		}
		if(!u.getPassword().equals(user.getPassword())){
			throw new ServiceException("密码错误");
		}
	}

	@Override
	public ManageUser addManageUser(ManageUser user) throws ServiceException {
		if(user == null || StringUtils.isBlank(user.getName()) || StringUtils.isBlank(user.getPassword())){
			throw new ServiceException("参数有误");
		}
		ManageUser u = manageUserDao.getManageUserByName(user);
		if(u != null){
			throw new ServiceException("用户已存在");
		}
		return manageUserDao.addManageUser(user);
	}
}

	