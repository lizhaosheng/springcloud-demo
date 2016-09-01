/**
 * Project Name: demo-Staff
 * File Name: StaffService.java
 * Package Name: com.lzs.puppet.demo.Staff.service
 * Describe: TODO
 * Date: 2016年8月26日下午2:11:42
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.company.service;

import java.util.List;

import com.lzs.puppet.demo.model.company.Staff;

/**
 * ClassName: StaffService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月26日 下午2:11:42 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
public interface StaffService {

	List<Staff> queryStaff(Staff Staff);
	
	Staff getStaffById(long id);

	Staff getStaffByJobnumber(String jobnumber);
	
	int addStaff(Staff Staff);

	int updateStaff(Staff Staff);
	
	int updateStaffPwd(long id, String pwd, String newpwd);
	
	int deleteStaff(long id);
	
}

	