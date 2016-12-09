/**
 * Project Name:demo-mange
 * File Name:ManageUserDao.java
 * Package Name:com.lzs.puppet.demo.manage.dao
 * Describe: TODO
 * Date:2016年8月25日下午6:23:12
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.manage.dao;

import org.apache.ibatis.annotations.Mapper;

import com.lzs.puppet.demo.model.manage.ManageUser;

/**
 * ClassName:ManageUserDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月25日 下午6:23:12 <br/>
 * @author hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
@Mapper
public interface ManageUserDao {

	ManageUser getManageUserByName(ManageUser user);

	int addManageUser(ManageUser user);
}

	