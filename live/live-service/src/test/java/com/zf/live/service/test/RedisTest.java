package com.zf.live.service.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月17日 下午11:14:03
 */

public class RedisTest extends ServiceBaseTester {

	@Autowired
	private RedisTemplate<String, String> stringTemplate ;

	@Resource(name="redisTemplate")
	private ListOperations<String, String> listOps ;

	@Resource(name="redisTemplate")
	private ValueOperations<String, String> valueOps ;

	@Test
	public void testString(){


		String result = stringTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection)
					throws DataAccessException { 
				connection.set(stringTemplate.getStringSerializer().serialize("name"), 
						stringTemplate.getStringSerializer().serialize("is_zhoufeng")); 
				return "ok";
			}
		});
		System.out.println("OK");
	}

	@Test
	public void testSet(){
		long result = listOps.leftPush("listx", "b");
		System.out.println(result);
	}

	@Test
	public void testGet(){
		List<String> ls = listOps.range("listx", 0, -1); 
		if(ls != null){
			for (String string : ls) {
				System.out.println(string);
			} 
		}
	}

	@Test
	public void testSetValueOpt(){
		valueOps.set("a1", "is_zhoufengx"); 
	}

	@Test
	public void testGetValueOpt(){
		System.out.println(valueOps.get("a1"));
	}

	@Test
	public void testJdis(){
		String host = "127.0.0.1" ;
		int port = 6379 ;
		int timeout = 1000 ;
		String password = "is_zhoufeng" ;
		JedisPoolConfig config = new JedisPoolConfig() ;
		JedisPool jedisPool = new JedisPool(config, host, port, timeout, password) ;
		Jedis jedis = null ;
		try {
			jedis = jedisPool.getResource() ;
			String result = jedis.set("abc", "def");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(jedis != null){
				jedis.close(); 
			}
		}
	}


}
