package com.lzs.puppet.demo.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages={"com.lzs.puppet.demo.manage","com.lzs.puppet.demo.base"})
@EnableDiscoveryClient
//@RibbonClient(name = "demo-manage", configuration = DemoRibbonConfiguration.class)
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableRedisHttpSession
public class Application {
	@Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }
}