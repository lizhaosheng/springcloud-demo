/**
 * Project Name: demo-base
 * File Name: Initializer.java
 * Package Name: com.lzs.puppet.demo.base.session
 * Describe: TODO
 * Date: 2016年9月12日下午3:58:55
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.base.session;

import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

import com.lzs.puppet.base.redis.RedisConfiguration;

/**
 * ClassName: Initializer <br/>
 * Function: AbstractHttpSessionApplicationInitializer（顺序是100，基本上是最靠前的）
 * 会在系统启动时会加载SpringSessionConfig，SpringSessionConfig会创建一个过滤器springSessionRepositoryFilter，
 * 该过滤器保证在每个请求进来之前，将容器的HttpSession 换成Spring Session定制的session. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年9月12日 下午3:58:55 <br/>
 * @author: hzlizhaosheng
 * @version
 * @since JDK 1.6
 * @see
 */
@EnableRedisHttpSession
public class SpringSessionInitializer extends AbstractHttpSessionApplicationInitializer { 

	public SpringSessionInitializer() {
    	super(RedisConfiguration.class); 
    }
	/**
	 * 
	 * SessionDeletedEvent and SessionExpiredEvent are both types of SessionDestroyedEvent.
		RedisOperationsSessionRepository supports firing a SessionDeletedEvent whenever a Session is deleted or a SessionExpiredEvent when it expires. This is necessary to ensure resources associated with the Session are properly cleaned up.
		For example, when integrating with WebSockets the SessionDestroyedEvent is in charge of closing any active WebSocket connections.
		Firing SessionDeletedEvent or SessionExpiredEvent is made available through the SessionMessageListener which listens to Redis Keyspace events. In order for this to work, Redis Keyspace events for Generic commands and Expired events needs to be enabled. For example:
		redis-cli config set notify-keyspace-events Egx
		If you are using @EnableRedisHttpSession the SessionMessageListener and enabling the necessary Redis Keyspace events is done automatically. However, in a secured Redis enviornment the config command is disabled. This means that Spring Session cannot configure Redis Keyspace events for you. To disable the automatic configuration add ConfigureRedisAction.NO_OP as a bean.
		For example, Java Configuration can use the following:
		@Bean
		public static ConfigureRedisAction configureRedisAction() {
		    return ConfigureRedisAction.NO_OP;
		}
		XML Configuraiton can use the following:
		<util:constant
    		static-field="org.springframework.session.data.redis.config.ConfigureRedisAction.NO_OP"/>
	 * spring session需要通过redis config命令去设置一些变量，若是无法开启redis的config命令，则需要配置下面的bean
	 * 
	 * @author hzlizhaosheng
	 * @return
	 */
	@Bean
	public static ConfigureRedisAction configureRedisAction() {
	    return ConfigureRedisAction.NO_OP;
	}
}
	