/**
 * Project Name:demo-model
 * File Name:ServiceException.java
 * Package Name:com.lzs.puppet.model.exception
 * Describe: TODO
 * Date:2016年8月25日下午6:16:05
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.model.exception;
/**
 * ClassName:ServiceException <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年8月25日 下午6:16:05 <br/>
 * @author hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
public class ServiceException extends Exception{

	/**
	 * serialVersionUID:TODO.
	 */
		
	private static final long serialVersionUID = 1L;

	public ServiceException(){
		super();
	}
	public ServiceException(String msg){
		super(msg);
	}
	public ServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ServiceException(Throwable arg0) {
		super(arg0);
	}

	protected ServiceException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
}

	