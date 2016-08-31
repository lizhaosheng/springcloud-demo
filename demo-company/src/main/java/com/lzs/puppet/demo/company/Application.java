package com.lzs.puppet.demo.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * SpringBoot默认会扫描启动类同包以及子包下的注解,可以通过
 * @SpringBootApplication(scanBasePackages={"cn.kfit","org.kfit"})
 * 注解来指定
 * @author withf
 *
 */
@SpringBootApplication(scanBasePackages={"com.lzs.puppet.demo.company","com.lzs.puppet.demo.base"})
@EnableDiscoveryClient
//@RibbonClient(name = "demo-manage", configuration = DemoRibbonConfiguration.class)
@EnableFeignClients
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