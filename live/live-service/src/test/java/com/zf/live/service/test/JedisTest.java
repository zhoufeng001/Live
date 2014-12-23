package com.zf.live.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.JedisPool;

public class JedisTest extends ServiceBaseTester{

	@Autowired
	private JedisPool jedisPool ;
	
	@Test
	public void test01(){
		System.out.println(jedisPool.getResource());
	}
	
}
