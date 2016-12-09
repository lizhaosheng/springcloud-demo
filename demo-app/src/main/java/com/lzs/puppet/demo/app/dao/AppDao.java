/**
 * Project Name: demo-company
 * File Name: AppDao.java
 * Package Name: com.lzs.puppet.demo.company.dao
 * Describe: TODO
 * Date: 2016年8月26日下午3:32:28
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lzs.puppet.demo.model.app.App;

/**
 * ClassName: AppDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月26日 下午3:32:28 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
@Mapper
public interface AppDao {

	List<App> queryApp(App app);
	
	App getAppById(@Param("id")long id);
	
	int addApp(App app);

	int updateApp(App app);
	
	int deleteApp(@Param("id")long id);

	int deleteByCompanyId(long id);

}

	