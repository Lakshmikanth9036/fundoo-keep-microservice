package com.bridgelabz.fundookeep.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
	
	private RedisTemplate<String, Long> redisTemplet;

	private ValueOperations<String, Long> valueOperation;
	
	@Autowired
	public RedisService(RedisTemplate<String, Long> redisTemplet) {
		this.redisTemplet = redisTemplet;
		valueOperation = redisTemplet.opsForValue();
	}
	
	public void putToken(String token,Long id) {
		valueOperation.set(token, id);
	}
	
	public Long getToken(String token) {
		return valueOperation.get(token);
	}
	
}
