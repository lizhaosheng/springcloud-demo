package com.lzs.puppet.ribbon.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lzs.puppet.ribbonfff.PuppetRibbonConfiguration;

@SpringBootApplication(scanBasePackages = { "com.lzs.puppet.ribbon.client","com.lzs.puppet.ribbonfff"})
@EnableDiscoveryClient
@RestController
//@RibbonClient(name = "ribbon-server", configuration = PuppetRibbonConfiguration.class)
// 下面的注解会自动配置freemarker，导致自定义的spring.freemarker 相关配置不生效
// @EnableHystrixDashboard
public class UserApplication {

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/hi")
	public String hi(@RequestParam(value = "name", defaultValue = "Artaban") String name) {
		String greeting = this.restTemplate.getForObject("http://say-hi/greeting", String.class);
		return String.format("%s, %s!", greeting, name);
	}

	@RequestMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "Artaban") String name) {
		String greeting = this.restTemplate.getForObject("http://say-hello/greeting", String.class);
		return String.format("%s, %s!", greeting, name);
	}

	@RequestMapping("/foo")
	public String foo(@RequestParam(value = "name", defaultValue = "Artaban") String name) {
		String greeting = this.restTemplate.getForObject("http://foo/greeting", String.class);
		return String.format("%s, %s!", greeting, name);
	}

	@RequestMapping("/serv")
	public String serv(@RequestParam(value = "name", defaultValue = "Artaban") String name) {
		String greeting = this.restTemplate.getForObject("http://ribbon-server/greeting", String.class);
		return String.format("%s, %s!", greeting, name);
	}

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
}