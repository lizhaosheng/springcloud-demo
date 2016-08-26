/**
 * Project Name: demo-staff
 * File Name: StaffServiceImpl.java
 * Package Name: com.lzs.puppet.demo.staff.service.impl
 * Describe: TODO
 * Date: 2016年8月26日下午2:21:21
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.company.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lzs.puppet.demo.company.dao.CompanyDao;
import com.lzs.puppet.demo.company.dao.StaffDao;
import com.lzs.puppet.demo.company.service.StaffService;
import com.lzs.puppet.demo.model.company.Company;
import com.lzs.puppet.demo.model.company.Staff;
import com.lzs.puppet.demo.model.exception.ServiceException;

/**
 * ClassName: StaffServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月26日 下午2:21:21 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
public class StaffServiceImpl implements StaffService{
	
	@Autowired
	private StaffDao staffDao;
	@Autowired
	private CompanyDao companyDao;
	
	@Override
	public List<Staff> queryStaff(Staff staff) {
		return staffDao.queryStaff(staff);
	}

	@Override
	public Staff getStaffById(long id) {
		return staffDao.getStaffById(id);
	}

	@Override
	public int addStaff(Staff staff) {
		checkAdd(staff);
		return staffDao.addStaff(staff);
	}

	private void checkAdd(Staff staff) {
		if(staff == null || StringUtils.isBlank(staff.getName())){
			throw new ServiceException("员工名不能为空");
		}
		if(StringUtils.isBlank(staff.getJobnumber())){
			throw new ServiceException("工号不能为空");
		}
		if(StringUtils.isBlank(staff.getMobile())){
			throw new ServiceException("手机号不能为空");
		}
		Staff query = new Staff();
		query.setJobnumber(staff.getJobnumber());
		List<Staff> list = staffDao.queryStaff(query );
		if(!list.isEmpty()){
			throw new ServiceException("已存在相同工号的员工");
		}
	}

	@Override
	public int updateStaff(Staff staff) {
		checkUpdate(staff);
		return staffDao.updateStaff(staff);
	}
	private void checkUpdate(Staff staff) {
		if(staff == null || staff.getId() <= 0){
			throw new ServiceException("未指定需要修改的员工");
		}
		if(StringUtils.isNotBlank(staff.getJobnumber())){
			Staff query = new Staff();
			query.setJobnumber(staff.getJobnumber());
			List<Staff> list = staffDao.queryStaff(query );
			if(!list.isEmpty() && staff.getId() != list.get(0).getId()){
				throw new ServiceException("已存在相同工号的员工");
			}
		}
		if(staff.getCompanyId() > 0){
			Company company = companyDao.getCompanyById(staff.getCompanyId());
			if(company == null){
				throw new ServiceException("员工所属子公司不存在");
			}
		}
	}
	
	@Override
	public int updateStaffPwd(long id, String oldpwd, String newpwd) {
		if(id <= 0 || StringUtils.isBlank(newpwd)){
			throw new ServiceException("参数有误");
		}
		Staff staff = staffDao.getStaffById(id);
		if(staff == null || !oldpwd.equals(staff.getPwd())){
			throw new ServiceException("原密码错误");
		}
		return staffDao.updateStaffPwd(id, newpwd);
	}

	@Override
	public int deleteStaff(long id) {
		return staffDao.deleteStaff(id);
	}

}

	