package com.cn.jedis.demo;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class RedisConverter implements RedisSerializer<Object>{
	
	private Converter<Object,byte[]> serilizer = new SerializingConverter();
	private Converter<byte[],Object> deserializer = new DeserializingConverter();
	
	@Override
	public byte[] serialize(Object t) throws SerializationException {
		if(null == t) {
			return new byte[0];
		}
		try {
			return serilizer.convert(t);
		} catch (Exception e) {
			return new byte[0];
		}
	}

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		if(bytes == null || bytes.length == 0) {
			return null;
		}
		try {
			return deserializer.convert(bytes);
		} catch (Exception e) {
			return null;
		}
	}
}
