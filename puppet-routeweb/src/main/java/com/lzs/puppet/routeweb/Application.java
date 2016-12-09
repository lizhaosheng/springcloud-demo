package com.lzs.puppet.routeweb;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lzs.puppet.base.constant.Constant;
import com.lzs.puppet.base.redis.PuppetRedisTemplate;
import com.lzs.puppet.base.route.Route;

@SpringBootApplication(scanBasePackages = { "com.lzs.puppet.routeweb","com.lzs.puppet.base.redis"})
@EnableDiscoveryClient
@RestController
@RequestMapping(value = "/route")
public class Application {
	private Logger logger = LoggerFactory.getLogger(Application.class);
	
	@Autowired
	private PuppetRedisTemplate redisTemplate;
	
	@RequestMapping(value = "add")
	@ResponseBody
	public Object add(Route route) {
		try{
			checkAdd(route);
			route.setUuid(UUID.randomUUID().toString());
			redisTemplate.opsForList().leftPush(Constant.ROUTE.ROUTE_LIST, route);
			redisTemplate.opsForValue().set(Constant.ROUTE.LAST_UPDATE_TIMESTAMP, System.currentTimeMillis());
			return redisTemplate.opsForList().range(Constant.ROUTE.ROUTE_LIST, 0, redisTemplate.opsForList().size(Constant.ROUTE.ROUTE_LIST));
		}catch (Exception e){
			logger.error("添加配置异常",e);
			return e;
		}
	}

	private void checkAdd(Route route) throws Exception {
		if(route == null || route.empty()){
			throw new Exception("路由信息为空，添加失败"); 
		}
		// 检查这些表达式是否有误，使用空字符串进行校验，若没有异常即可
		route.isMatchSourceService("");
		route.isMatchSourceHost("");
		route.isMatchSourceIp("");
		
		route.isMatchTargetService("");
		route.isMatchTargetHost("");
		route.isMatchTargetIp("");
	}
	
	@RequestMapping(value = "get")
	@ResponseBody
	public Object get() {
		try{
			System.out.println(redisTemplate.opsForList().size(Constant.ROUTE.ROUTE_LIST));
			List<Object> list = redisTemplate.opsForList().range(Constant.ROUTE.ROUTE_LIST, 0, redisTemplate.opsForList().size(Constant.ROUTE.ROUTE_LIST));
			return list;
		}catch (Exception e){
			logger.error("添加配置异常",e);
			return e;
		}
	}
	
//	@RequestMapping(value = "delete")
//	@ResponseBody
//	public Object delet(int index) {
//		try{
//			if(index < 0){
//				return "索引取值必须为非负整数";
//			}
//			redisTemplate.leftDeleteAtT(Constant.ROUTE.ROUTE_LIST,index);
//			List<Object> list = redisTemplate.opsForList().range(Constant.ROUTE.ROUTE_LIST, 0, redisTemplate.opsForList().size(Constant.ROUTE.ROUTE_LIST));
//			return list;
//		}catch (Exception e){
//			logger.error("添加配置异常",e);
//			return e;
//		}
//	}
//
//	@RequestMapping("/delete")
//	@ResponseBody
//	public List<Route> delete(int index) {
//		try{
//			redisTemplate.opsForList().set(Constant.ROUTE.ROUTE_LIST, index, "__deleted__");
//			redisTemplate.opsForList().remove(Constant.ROUTE.ROUTE_LIST, 0, "__deleted__");
//			redisTemplate.opsForValue().set(Constant.ROUTE.LAST_UPDATE_TIMESTAMP, System.currentTimeMillis());
//			return redisTemplate.opsForList().range(Constant.ROUTE.ROUTE_LIST, -1, redisTemplate.opsForList().size(Constant.ROUTE.ROUTE_LIST));
//		}catch (Exception e){
//			return null;
//		}
//	}

	@RequestMapping("/clear")
	@ResponseBody
	public Object clear() {
		try{
		redisTemplate.delete(Constant.ROUTE.ROUTE_LIST);
		redisTemplate.opsForValue().set(Constant.ROUTE.LAST_UPDATE_TIMESTAMP, System.currentTimeMillis());
		return redisTemplate.opsForList().range(
			Constant.ROUTE.ROUTE_LIST, 0, redisTemplate.opsForList().size(Constant.ROUTE.ROUTE_LIST));
		}
		catch (Exception e){
			return e;
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}