/**
* Project Name:demo
* File Name:Constant.java
* Package Name:com.lzs.puppet.demo.base
* Describe: TODO
* Date:2016年8月20日下午12:39:58
* Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
*
*/

package com.lzs.puppet.base.constant;

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
	private Constant(){
		
	}
	public static final int RESPONSE_CODE_SUCCESS = 200;
	public static final int RESPONSE_CODE_FAILED = 400;
	public static final String MANAGE_LOGIN_USER = "MANAGE_LOGIN_USER_";
//
//	public static final URI REMOTE_HELLO_URL;
//	public static final URI REMOTE_QUERY_USER;
//	
//	static{
//		URI uri = null;
//		URI uri1 = null;
//		try {
//			uri = new URI("http://demo-service/remote-hello");
//			uri1 = new URI("http://demo-service/queryUser");
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		REMOTE_HELLO_URL = uri;
//		REMOTE_QUERY_USER = uri1;
//	}
//	
//	public static enum SERVICE{
//		DEMO_APP("demo-app"),
//		DEMO_LEAVE("demo-leave");
//		
//		// 成员变量
//        private String name;
//
//        // 构造方法
//        private SERVICE(String name) {
//            this.name = name;
//        }
//        @Override
//        public String toString() {
//            return this.name;
//        }
//	}
	public class SERVICE{
		public static final String DEMO_APP = "demo-app";
		public static final String DEMO_LEAVE = "demo-leave";
		public static final String DEMO_COMPANY = "demo-company";
		public static final String DEMO_IM = "demo-im";
	}
	
	public class ROUTE{
		/**
		 * 路由信息最近一次更新的时间戳
		 */
		public static final String LAST_UPDATE_TIMESTAMP = "LAST_UPDATE_TIMESTAMP";
		/**
		 * 缓存锁标示
		 */
		public static final String ROUTE_LIST_LOCK = "ROUTE_LIST_LOCK";
		/**
		 * 路由配置信息key
		 */
		public static final String ROUTE_LIST = "ROUTE_LIST";
	}
}

	