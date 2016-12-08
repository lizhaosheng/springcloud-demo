/**
 * Project Name: demo-company
 * File Name: StaffDao.java
 * Package Name: com.lzs.puppet.demo.company.dao
 * Describe: TODO
 * Date: 2016年8月26日下午3:32:28
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.company.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lzs.puppet.demo.model.company.Staff;

/**
 * ClassName: StaffDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月26日 下午3:32:28 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
@Mapper
public interface StaffDao {

	List<Staff> queryStaff(Staff staff);
	
	Staff getStaffById(@Param("id")long id);
	
	Staff getStaffByJobnumber(@Param("jobnumber")String jobnumber);
	
	int addStaff(Staff staff);

	int updateStaff(Staff staff);
	
	int updateStaffPwd(@Param("id")long id, @Param("pwd") String pwd);
	
	int deleteStaff(@Param("id")long id);

	int deleteByCompanyId(@Param("companyId")long companyId);

}

	