/**
 * Project Name:demo-mange
 * File Name:ManageUserService.java
 * Package Name:com.lzs.puppet.demo.manage.service
 * Describe: TODO
 * Date:2016年8月25日下午5:59:08
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.manage.service;

import com.lzs.puppet.demo.base.exception.ServiceException;
import com.lzs.puppet.demo.model.manage.ManageUser;

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
public interface ManageUserService {

	void checkLogin(ManageUser user) throws ServiceException;

	int addManageUser(ManageUser user) throws ServiceException;

}

	