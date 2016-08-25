/**
* Project Name:demo
* File Name:Constant.java
* Package Name:com.lzs.puppet.demo.base
* Describe: TODO
* Date:2016年8月20日下午12:39:58
* Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
*
*/

package com.lzs.puppet.demo.leave.base;

import java.net.URI;
import java.net.URISyntaxException;

/**
* ClassName:Constant <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2016年8月20日 下午12:39:58 <br/>
* @author hzlizhaosheng
* @version
* @since JDK 1.6
* @see
*/
public class Constant {

	public static final URI REMOTE_HELLO_URL;
	public static final URI REMOTE_QUERY_USER;
	static{
		URI uri = null;
		URI uri1 = null;
		try {
			uri = new URI("http://demo-service/remote-hello");
			uri1 = new URI("http://demo-service/queryUser");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		REMOTE_HELLO_URL = uri;
		REMOTE_QUERY_USER = uri1;
	}
}

	