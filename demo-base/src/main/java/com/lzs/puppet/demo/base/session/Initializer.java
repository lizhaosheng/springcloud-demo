/**
 * Project Name: demo-base
 * File Name: Initializer.java
 * Package Name: com.lzs.puppet.demo.base.session
 * Describe: TODO
 * Date: 2016年9月12日下午3:58:55
 * Copyright (c) 2016, withfeelings@163.com All Rights Reserved.
 *
 */

package com.lzs.puppet.demo.base.session;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

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
public class Initializer extends AbstractHttpSessionApplicationInitializer { 

    public Initializer() {
            super(SpringSessionConfig.class); 
    }
}
	