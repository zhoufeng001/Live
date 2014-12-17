package com.zf.live.service.redis;

import com.zf.live.client.exception.RedisException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis操作基础服务类
 * 已在service-beans.xml中进行了配置，id=redisService
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月17日 下午10:54:04
 */
public class RedisService {

	private String host ;
	private int port ;
	private String password ;

	private JedisPool jedisPool ;
	
	public RedisService(String host , int port , String password){
		this.host = host ;
		this.port = port ;
		this.password = password ;
		initJedisPool() ;
	}

	private void initJedisPool(){
		JedisPoolConfig config = new JedisPoolConfig() ;
		config.setMaxIdle(30);
		config.setMinIdle(10); 
		config.setMaxWaitMillis(300);
		jedisPool = new JedisPool(config, host, port, 3000 , password ) ;
	}
	
	public Jedis getConnection(){
		if(jedisPool == null){
			throw new RedisException("jdisPool not Initialization...");
		}
		return jedisPool.getResource() ;
	}
	
}
