/**
 * Project Name: demo-company
 * File Name: CompanyService.java
 * Package Name: com.lzs.puppet.demo.company.service
 * Describe: TODO
 * Date: 2016年8月26日下午2:11:42
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.company.service;

import java.util.List;

import com.lzs.puppet.demo.model.company.Company;

/**
 * ClassName: CompanyService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月26日 下午2:11:42 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
public interface CompanyService {

	List<Company> queryCompany(Company company);
	
	Company getCompanyById(long id);
	
	int addCompany(Company company);

	int updateCompany(Company company);
	
	int deleteCompany(long id);
}

	