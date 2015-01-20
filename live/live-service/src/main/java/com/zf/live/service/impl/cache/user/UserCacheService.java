package com.zf.live.service.impl.cache.user;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import com.alibaba.fastjson.JSON;
import com.zf.live.client.exception.LiveException;
import com.zf.live.client.vo.user.CacheUser;
import com.zf.live.common.validate.Notnull;
import com.zf.live.dao.pojo.Lvuser;

/**
 * 关于User的缓存
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月24日 上午12:33:47
 */
@Component("userCacheService")
public class UserCacheService {
	
	static final Logger log = LoggerFactory.getLogger(UserCacheService.class);


	@Autowired
	private JedisPool jedisPool ;

	/**
	 * 缓存用户信息的Prefix
	 * key：userid
	 * value：CacheUser
	 */
	private final String USER_CACHE_KEY_PREFIX = "uckp_";

	/**
	 * 缓存token信息的的Prefix
	 * key:token
	 * value：userid
	 */
	private final String TOEKN_USERID_KEY_PREFIX = "tukp_";

	/**
	 * 缓存token信息的的Prefix
	 * key：userid
	 * value：token
	 */
	private final String USER_TOKEN_KEY_PREFIX = "utkp_";

	/**
	 * 用户缓存时间(1小时不使用，自动失效)
	 */
	private final int USER_CACHE_TIME = 3600 ;
	
	/**
	 * token缓存时间（7天）
	 */
	private final int TOKEN_CACHE_TIME = 60 * 60 * 24 * 7 ;
	
	
	/**
	 * 用户登录后（缓存用户信息）
	 * @param token
	 * @param cacheUser
	 */
	public void putLoginUserInfo(@Notnull String token , 
			@Notnull("id")
	Lvuser lvuser){
		Jedis jedis = null ;
		try {

			CacheUser cacheUser = new CacheUser() ;
			BeanUtils.copyProperties(lvuser, cacheUser); 
			cacheUser.setCacheTime(System.currentTimeMillis()); 

			jedis = jedisPool.getResource() ;
			Pipeline pipeline = jedis.pipelined();

			//缓存userid-token信息
			String useridKey = USER_TOKEN_KEY_PREFIX + cacheUser.getId() ;
			String useridValue = token ;

			//缓存token-userid信息
			String tokenKey = TOEKN_USERID_KEY_PREFIX + token ;
			String tokenValue = String.valueOf(cacheUser.getId());

			//缓存用户信息
			String userKey = USER_CACHE_KEY_PREFIX + cacheUser.getId();
			String userJson = JSON.toJSONString(cacheUser);

			//不能重复登录，移除之前的缓存信息
			String oldTokenValue = jedis.get(useridKey) ;
			if(StringUtils.isNotBlank(oldTokenValue)){
				pipeline.del(useridKey) ; //删除userid对应的token
				pipeline.del(TOEKN_USERID_KEY_PREFIX + oldTokenValue); //删除token对应的userid
			}
			
			pipeline.set(useridKey, useridValue);
			pipeline.expire(useridKey, TOKEN_CACHE_TIME) ;
			pipeline.set(tokenKey, tokenValue); 
			pipeline.expire(tokenKey, TOKEN_CACHE_TIME) ;
			pipeline.set(userKey, userJson);
			pipeline.expire(userKey, USER_CACHE_TIME) ;
			
			jedis.sync();
		} catch (Exception e) {
			throw new LiveException(e.getMessage());
		}finally{
			jedisPool.returnResource(jedis);
		}
	}
	
	
	/**
	 * 根据userid移除登录用户的登录信息
	 * @param userid
	 */
	public void removeLoginUserInfo(@Notnull Long userid){
		Jedis jedis = null ;
		try {
			jedis = jedisPool.getResource() ;
			String useridKey = USER_TOKEN_KEY_PREFIX + userid ;
			String userToken = jedis.get(useridKey);
			Pipeline pipeline = jedis.pipelined();
			if(StringUtils.isNoneBlank(userToken)){
				String tokenKey = TOEKN_USERID_KEY_PREFIX + userToken ;
				pipeline.del(tokenKey);
			}
			pipeline.del(useridKey);
			String userKey = USER_CACHE_KEY_PREFIX + userid;
			pipeline.del(userKey); 
			pipeline.sync();
		} catch (Exception e) {
			throw new LiveException(e.getMessage());
		}finally{
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 根据token移除用户的登录信息
	 * @param token
	 */
	public void removeLoginUserInfo(@Notnull String token){
		Jedis jedis = null ;
		try {
			jedis = jedisPool.getResource() ;
			String tokenKey = TOEKN_USERID_KEY_PREFIX + token ;
			String useridValue = jedis.get(tokenKey);
			Pipeline pipeline = jedis.pipelined();
			if(StringUtils.isNoneBlank(useridValue)){
				String useridKey = USER_TOKEN_KEY_PREFIX + useridValue ;
				pipeline.del(useridKey);
			}
			pipeline.del(tokenKey);
			String userKey = USER_CACHE_KEY_PREFIX + useridValue;
			pipeline.del(userKey); 
			pipeline.sync();
		} catch (Exception e) {
			throw new LiveException(e.getMessage());
		}finally{
			jedisPool.returnResource(jedis);
		}
	}
	
	/**
	 * 缓存用户信息
	 * @param cacheUser
	 */
	public void putUserInfo(@Notnull("id") Lvuser lvuser){
		Jedis jedis = null ;
		try {
			CacheUser cacheUser = new CacheUser() ;
			BeanUtils.copyProperties(lvuser, cacheUser); 
			cacheUser.setCacheTime(System.currentTimeMillis()); 

			jedis = jedisPool.getResource() ;
			Pipeline pipeline = jedis.pipelined();
			//缓存用户信息
			String userKey = USER_CACHE_KEY_PREFIX + cacheUser.getId();
			String userJson = JSON.toJSONString(cacheUser);
			pipeline.set(userKey, userJson);
			pipeline.expire(userKey, USER_CACHE_TIME) ; 
			jedis.sync();
		} catch (Exception e) {
			throw new LiveException(e.getMessage());
		}finally{
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 根据userid获取缓存的用户信息
	 * @param userid
	 * @return 缓存中能找到则返回CacheUser、否则返回null
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
			jedis.expire(key, USER_CACHE_TIME) ;
			return JSON.parseObject(userJson, CacheUser.class); 
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new LiveException(e.getMessage());
		}finally{
			jedisPool.returnResource(jedis);
		}
	}
	
	/**
	 * 判断指定的userid是否被缓存了
	 * @param userid
	 * @return
	 */
	public boolean existCachedUser(@Notnull Long userid){
		Jedis jedis = null ;
		try {
			jedis = jedisPool.getResource();
			String key = USER_CACHE_KEY_PREFIX + userid ;
			return jedis.exists(key); 
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new LiveException(e.getMessage());
		}finally{
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 根据token查找userid
	 * @param token
	 * @return 缓存中能找到则返回userid ，否则返回null
	 */
	public Long getUserIdByToken(@Notnull String token){
		Jedis jedis = null ;
		try {
			String tokenKey = TOEKN_USERID_KEY_PREFIX + token ;
			jedis = jedisPool.getResource();
			String userId = jedis.get(tokenKey);
			if(StringUtils.isNotBlank(userId)){
				Pipeline pipeline = jedis.pipelined();
				pipeline.expire(tokenKey, TOKEN_CACHE_TIME) ;
				pipeline.expire(USER_TOKEN_KEY_PREFIX + userId, TOKEN_CACHE_TIME) ;
				pipeline.sync();
				return Long.valueOf(userId);
			}
			return null ;
		} catch (Exception e) {
			throw new LiveException(e.getMessage());
		}finally{
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 根据userid查找该用户的token
	 * @param userid
	 * @return
	 */
	public String getTokenByUserId(@Notnull Long userid){
		Jedis jedis = null ;
		try {
			String userTokenKey = USER_TOKEN_KEY_PREFIX + userid ;
			jedis = jedisPool.getResource();
			String token = jedis.get(userTokenKey) ;
			if(StringUtils.isNotBlank(token)){
				Pipeline pipeline = jedis.pipelined();
				pipeline.expire(userTokenKey, TOKEN_CACHE_TIME) ;
				pipeline.expire(TOEKN_USERID_KEY_PREFIX + token, TOKEN_CACHE_TIME) ;
				pipeline.sync();
				return token ;
			}
			return null;
		} catch (Exception e) {
			throw new LiveException(e.getMessage());
		}finally{
			jedisPool.returnResource(jedis);
		}
	}

}
