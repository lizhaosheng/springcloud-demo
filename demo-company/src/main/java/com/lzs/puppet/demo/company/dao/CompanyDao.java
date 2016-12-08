/**
 * Project Name: demo-company
 * File Name: CompanyDao.java
 * Package Name: com.lzs.puppet.demo.company.dao
 * Describe: TODO
 * Date: 2016年8月26日下午2:22:06
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.company.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lzs.puppet.demo.model.company.Company;

/**
 * ClassName: CompanyDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月26日 下午2:22:06 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
@Mapper
public interface CompanyDao {

	List<Company> queryCompany(Company company);
	
	Company getCompanyById(@Param("id")long id);
	
	int addCompany(Company company);

	int updateCompany(Company company);
	
	int deleteCompany(@Param("id")long id);
}

	