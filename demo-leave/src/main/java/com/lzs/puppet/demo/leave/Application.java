package com.lzs.puppet.demo.leave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class Application {
	@Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }
    
	/**
	 * ribbon 远程调用方式,开启@Bean @LoadBalanced后可直接注入使用
	 */
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 远程调用内部模块。
	 * http://服务名    即可调用远程模块
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/configa")
	public String queryApp() {
		String r = restTemplate.getForObject("http://demo-im/hello", String.class);
		return r;
	}
}