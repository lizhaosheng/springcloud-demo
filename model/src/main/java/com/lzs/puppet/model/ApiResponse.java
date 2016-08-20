package com.lzs.puppet.model;

import java.io.Serializable;

/**
 * 内部api调用返回结果
 * @author hzlizhaosheng
 *
 */
public class ApiResponse<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 返回码，默认200成功
	 */
	private int code;
	/**
	 * 提示语
	 */
	private String msg;
	/**
	 * 不可预知类型数据
	 */
	private Object data;
	/**
	 * 可预知类型数据
	 */
	private T result;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	
}
