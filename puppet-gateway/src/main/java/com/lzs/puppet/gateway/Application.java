package com.lzs.puppet.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

/**
 * @EnableSidecar annotation includes @EnableCircuitBreaker, @EnableDiscoveryClient, and @EnableZuulProxy
 * ClassName: Application <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(optional). <br/>
 * date: 2016年8月20日 下午2:47:49 <br/>
 *
 * @author lizhaosheng-黎昭声
 * @version 
 * @since JDK 1.6
 */
@SpringBootApplication
@EnableSidecar  // include @EnableZuulProxy
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
