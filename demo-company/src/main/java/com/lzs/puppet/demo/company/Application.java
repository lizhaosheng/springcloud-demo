package com.lzs.puppet.demo.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * SpringBoot默认会扫描启动类同包以及子包下的注解,可以通过
 * @SpringBootApplication(scanBasePackages={"cn.kfit","org.kfit"})
 * 注解来指定
 * @author withf
 *
 */
@SpringBootApplication(scanBasePackages={"com.lzs.puppet.base","com.lzs.puppet.demo"})
@EnableDiscoveryClient
public class Application {

    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }
}