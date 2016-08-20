/**
* Project Name:model
* File Name:User.java
* Package Name:com.lzs.puppet.model
* Describe: TODO
* Date:2016年8月20日下午12:46:27
* Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
*
*/

package com.lzs.puppet.model;

import java.io.Serializable;
import java.util.Date;

/**
* ClassName:User <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2016年8月20日 下午12:46:27 <br/>
* @author hzlizhaosheng
* @version
* @since JDK 1.6
* @see
*/
public class User implements Serializable {

	/** 序列化. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户状态，正常的
	 */
	public static final int STATE_NORMAL = 1;
	/**
	 * 用户状态，未激活
	 */
	public static final int STATE_NOT_ACTIVETED = 2;
	
	/** ID. */
	private Integer id;

	/** 创建时间. */
	private Date createDate;

	/** 更新时间. */
	private Date updateDate;

	/** 所属公司ID. */
	private Integer companyId;
	
	/**
	 * 所属公司名称
	 */
	private String companyName;
	
	/** 用户名. */
	private String userName;

	/** 真实姓名. */
	private String realName;

	/** 昵称. */
	private String nickName;
	
	/** 邮件. */
	private String email;
	
	/** 手机号.*/
	private String mobile;

	/** 用户类型，1为系统创建的用户 **/
	private Integer type;
	
	/** 密码. */
	private String password;

	/** 密码有效期. */
	private Integer passwordPeriod;
	
	/** 角色id. */
	private Integer roleId;

	/** 角色名称. */
	private String roleName;
	
	private String jobNumber;
	/** 最后登录时间. */
	private Date lastLoginTime;

	/** 用户状态，1正常，2未激活 **/
	private Integer state;
	
	/** 验证码. */
	private String authCode;

	/** 排序字段. */
	private String sort;
	
	private int fromType;
	
	private String yixinNick;
	private String col_yx_account;
	private Long col_yx_id;
	private String col_yx_phone;
	private Long col_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPasswordPeriod() {
		return passwordPeriod;
	}

	public void setPasswordPeriod(Integer passwordPeriod) {
		this.passwordPeriod = passwordPeriod;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public int getFromType() {
		return fromType;
	}

	public void setFromType(int fromType) {
		this.fromType = fromType;
	}

	public String getYixinNick() {
		return yixinNick;
	}

	public void setYixinNick(String yixinNick) {
		this.yixinNick = yixinNick;
	}

	public String getCol_yx_account() {
		return col_yx_account;
	}

	public void setCol_yx_account(String col_yx_account) {
		this.col_yx_account = col_yx_account;
	}

	public Long getCol_yx_id() {
		return col_yx_id;
	}

	public void setCol_yx_id(Long col_yx_id) {
		this.col_yx_id = col_yx_id;
	}

	public String getCol_yx_phone() {
		return col_yx_phone;
	}

	public void setCol_yx_phone(String col_yx_phone) {
		this.col_yx_phone = col_yx_phone;
	}

	public Long getCol_id() {
		return col_id;
	}

	public void setCol_id(Long col_id) {
		this.col_id = col_id;
	}
	
}

	