package com.zf.live.service.impl.cache.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import com.alibaba.fastjson.JSON;
import com.zf.live.client.exception.LiveException;
import com.zf.live.client.vo.user.CacheUser;
import com.zf.live.common.validate.Notnull;

/**
 * 关于User的缓存
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月24日 上午12:33:47
 */
@Component("userCacheService")
public class UserCacheService {

	@Autowired
	private JedisPool jedisPool ;

	/**
	 * 缓存用户信息的Prefix
	 */
	private final String USER_CACHE_KEY_PREFIX = "user_";

	/**
	 * 用户缓存时间
	 */
	private final int USER_CACHE_TIME = 3600 ;

	/**
	 * 缓存token信息的的Prefix
	 */
	private final String TOEKN_CACHE_KEY_PREFIX = "token_";


	/**
	 * 缓存用户登录信息
	 * @param token
	 * @param cacheUser
	 */
	public void putLoginUserInfo(@Notnull String token , 
			@Notnull @Notnull("id")
	CacheUser cacheUser){
		//缓存用户信息
		Jedis jedis = null ;
		try {
			jedis = jedisPool.getResource() ;
			Pipeline pipeline = jedis.pipelined(); 
			String userKey = USER_CACHE_KEY_PREFIX + cacheUser.getId();
			String userJson = JSON.toJSONString(cacheUser);
			pipeline.setex(userKey, USER_CACHE_TIME, userJson);
			//缓存token信息
			String tokenKey = TOEKN_CACHE_KEY_PREFIX + token ;
			String tokenValue = String.valueOf(cacheUser.getId());
			pipeline.set(tokenKey, tokenValue) ;
			jedis.sync();
		} catch (Exception e) {
			e.printStackTrace();
			throw new LiveException(e.getMessage());
		}finally{
			jedisPool.returnResource(jedis);
		}
	}
	
	/**
	 * 根据userid获取缓存的用户信息
	 * @param userid
	 * @return
	 */
	public CacheUser getCacheUserById(@Notnull Long userid){
		Jedis jedis = null ;
		try {
			jedis = jedisPool.getResource();
			String key = USER_CACHE_KEY_PREFIX + userid ;
			String userJson = jedis.get(key);
			if(userJson == null){
				return null ;
			}
			return JSON.parseObject(userJson, CacheUser.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new LiveException(e.getMessage());
		}finally{
			jedisPool.returnBrokenResource(jedis);
		}
	}
	
}
