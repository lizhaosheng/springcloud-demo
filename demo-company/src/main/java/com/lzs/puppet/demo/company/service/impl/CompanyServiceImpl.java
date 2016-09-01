/**
 * Project Name: demo-company
 * File Name: CompanyServiceImpl.java
 * Package Name: com.lzs.puppet.demo.company.service.impl
 * Describe: TODO
 * Date: 2016年8月26日下午2:21:21
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.company.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lzs.puppet.demo.company.dao.CompanyDao;
import com.lzs.puppet.demo.company.dao.StaffDao;
import com.lzs.puppet.demo.company.service.CompanyService;
import com.lzs.puppet.demo.model.company.Company;
import com.lzs.puppet.demo.base.exception.ServiceException;

/**
 * ClassName: CompanyServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月26日 下午2:21:21 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private StaffDao staffDao;
	
	@Override
	public List<Company> queryCompany(Company company) {
		return companyDao.queryCompany(company);
	}

	@Override
	public Company getCompanyById(long id) {
		return companyDao.getCompanyById(id);
	}

	@Override
	public int addCompany(Company company) {
		checkAdd(company);
		return companyDao.addCompany(company);
	}

	private void checkAdd(Company company) {
		if(company == null || StringUtils.isBlank(company.getName())){
			throw new ServiceException("公司名不能为空");
		}
		if(StringUtils.isBlank(company.getRemark())){
			throw new ServiceException("备注不能为空");
		}
		Company query = new Company();
		query.setName(company.getName());
		List<Company> list = companyDao.queryCompany(query );
		if(!list.isEmpty()){
			throw new ServiceException("已存在同名公司");
		}
	}

	@Override
	public int updateCompany(Company company) {
		checkUpdate(company);
		return companyDao.updateCompany(company);
	}
	private void checkUpdate(Company company) {
		if(company == null || company.getId() <= 0){
			throw new ServiceException("未指定需要更新的公司");
		}
		if(StringUtils.isBlank(company.getName())){
			Company query = new Company();
			query.setName(company.getName());
			List<Company> list = companyDao.queryCompany(query );
			if(!list.isEmpty() && list.get(0).getId() != company.getId()){
				throw new ServiceException("已存在同名公司");
			}
		}
	}
	
	@Override
	public int deleteCompany(long id) {
		int num = companyDao.deleteCompany(id);
		// 删除企业下的所有员工
		staffDao.deleteByCompanyId(id);
		return num;
	}

}

	