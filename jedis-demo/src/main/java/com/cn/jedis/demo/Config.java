package com.cn.jedis.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;

@Configuration
public class Config {
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(factory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new RedisConverter());
		return redisTemplate;
	}
	
	@Bean
	public DefaultRedisScript<String> redisScript(){
		DefaultRedisScript<String> redisScript = new DefaultRedisScript<String>();
		redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("decrease.lua")));
		redisScript.setResultType(String.class);
		return redisScript;
	}
}
