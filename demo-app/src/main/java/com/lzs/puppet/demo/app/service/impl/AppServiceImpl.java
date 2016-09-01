/**
 * Project Name: demo-app
 * File Name: AppServiceImpl.java
 * Package Name: com.lzs.puppet.demo.app.service.impl
 * Describe: TODO
 * Date: 2016年8月26日下午2:21:21
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.app.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lzs.puppet.demo.app.dao.AppDao;
import com.lzs.puppet.demo.app.service.AppService;
import com.lzs.puppet.demo.model.app.App;
import com.lzs.puppet.demo.base.exception.ServiceException;

/**
 * ClassName: AppServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月26日 下午2:21:21 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
@Service("appService")
public class AppServiceImpl implements AppService{
	
	@Autowired
	private AppDao appDao;

	@Override
	public List<App> queryApp(App app) {
		return appDao.queryApp(app);
	}

	@Override
	public App getAppById(long id) {
		return appDao.getAppById(id);
	}

	@Override
	public int addApp(App app) {
		checkAdd(app);
		return appDao.addApp(app);
	}

	private void checkAdd(App app) {
		if(app == null || StringUtils.isBlank(app.getName())){
			throw new ServiceException("应用名不能为空");
		}
		if(StringUtils.isBlank(app.getIdentity())){
			throw new ServiceException("应用标示不能为空");
		}
		if(app.getType() <= 0){
			throw new ServiceException("未指定应用类型");
		}
		App query = new App();
		query.setIdentity(app.getIdentity());
		List<App> list = appDao.queryApp(query );
		if(!list.isEmpty()){
			throw new ServiceException("已存在相同标示的应用");
		}
	}

	@Override
	public int updateApp(App app) {
		checkUpdate(app);
		return appDao.updateApp(app);
	}
	private void checkUpdate(App app) {
		if(app == null || app.getId() <= 0){
			throw new ServiceException("未指定需要修改的应用");
		}
	}
	
	@Override
	public int deleteApp(long id) {
		return appDao.deleteApp(id);
	}

}

	