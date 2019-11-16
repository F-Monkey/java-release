package com.cn.jedis.demo;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.redis.connection.ReturnType;

@SpringBootApplication
@RestController
public class JedisStarter {
	public static void main(String[] args) {
		SpringApplication.run(JedisStarter.class, args);
	}
	
	@Resource
	RedisTemplate<String, Object> redisTemplate;
	
	@Resource
	StringRedisTemplate stringRedisTemplate;
	
	@RequestMapping("simple")
	public void multipartTask() {
		final String key = "lockKey";
		try {
			Boolean ifAbsent = stringRedisTemplate.opsForValue().setIfAbsent(key, "tom");
			if(!ifAbsent) {
				System.out.println("error");
			}
			int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
			Thread.yield();// 尝试线程转出，模拟线程切换
			if(stock > 0) {
				int realStock = stock - 1;
				stringRedisTemplate.opsForValue().set("stock", realStock+"");
				System.out.println("扣减成功，剩余："+realStock);
			}else {
				System.out.println("库存不足");
			}
		}finally {
			stringRedisTemplate.delete(key);
		}
	}
	
	@Resource
	DefaultRedisScript<String> redisScript;
	
	@RequestMapping("lua")
	public void multipartLuaTask() {
//		init();
		final String key = "stock";
//		 String script = "return redis.call('get','stock')";
		 String script = "local numStr = redis.call('get','"+key+"')\n"
		 		+ "\t local num = tonumber(numStr) "
		 		+ "\t if num > 0 then\n"
		 		+ "\t num = num -1 \n"
		 		+ "\t redis.call('set','"+key+"',num) \n"
		 		+ "\t return num \n"
		 		+ "\t else\n"
		 		+ "\t return 0 \n"
		 		+ "\tend";
		Long stock = redisTemplate.execute((RedisConnection connection) -> 
			connection.eval(script.getBytes(), ReturnType.INTEGER,0));
		if(stock > 0) {
			System.out.println("扣减成功，剩余："+stock);
		}else {
			System.out.println("库存不足");
		}
	}
	
	
	public void init() {
		stringRedisTemplate.opsForValue().set("stock", "1000");
	}
	
	@RequestMapping("multipartLua")
	public void multipartLuaRequest() {
		init();
		for(int i = 0;i<=1000;i++) {
			Thread thread = new Thread(this::multipartLuaTask);
			thread.start();
		}
		System.out.println("result:"+stringRedisTemplate.opsForValue().get("stock"));
	}
	
	
	@RequestMapping("multipart")
	public void multipartRequest() {
		init();
		for(int i = 0;i<=1000;i++) {
			Thread thread = new Thread(this::multipartTask);
			thread.start();
		}
		System.out.println("result:"+stringRedisTemplate.opsForValue().get("stock"));
	}
}
