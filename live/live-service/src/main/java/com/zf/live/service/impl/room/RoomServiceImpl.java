package com.zf.live.service.impl.room;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.alibaba.fastjson.JSON;
import com.zf.live.client.room.RoomService;
import com.zf.live.client.vo.room.Audience;
import com.zf.live.common.assertx.ZFAssert;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月20日 下午10:34:19
 */
@Component("roomService")
public class RoomServiceImpl implements RoomService{
	
	static final Logger log = LoggerFactory.getLogger(RoomServiceImpl.class);
	
	private static final String roomUserCountKey = "_r_uc_k_";
	
	private static final String roomTouristCountKey = "_r_tc_k_";
	
	private static final String roomUserMapKey = "_r_ul_k_";
	
	@Autowired
	private JedisPool jedisPool ;
	
	@Override
	public void comeInRoom(Long videoId, Audience audience) {
		ZFAssert.notNull(videoId, "videoId不能为空"); 
		ZFAssert.notNull(audience, "audience不能为空"); 
		Jedis jedis = null ;
		try{
			jedis = jedisPool.getResource();
			if(audience.isTourist()){
				jedis.incr(roomTouristCountKey + videoId); 
			}else{
				jedis.incr(roomUserCountKey + videoId); 
			}
			String audienceJson = JSON.toJSONString(audience);
			jedis.hset(roomUserMapKey + videoId, audience.getSessionId() , audienceJson);
			log.info("用户[{}]，进入房间[{}]成功" , audience.getSessionId() , videoId);
		}finally{  
			jedisPool.returnResource(jedis);
		}
	}

	@Override
	public void outRoom(Long videoId, String sessionId) {
		ZFAssert.notNull(videoId, "videoId不能为空"); 
		ZFAssert.notBlank(sessionId, "sessionId不能为空"); 
		Jedis jedis = null ;
		try{
			jedis = jedisPool.getResource();
			String rumk = roomUserMapKey + videoId;
			String audienceJson = jedis.hget(rumk, sessionId);
			if(StringUtils.isBlank(audienceJson)){
				log.warn("视频房间[{}]中未找到sessionId[{}]对应的用户" , videoId ,sessionId); 
				return ;
			} 
			jedis.hdel(rumk,sessionId );
			Audience audience = JSON.parseObject(audienceJson, Audience.class);
			if(audience.isTourist()){
				jedis.incrBy(roomTouristCountKey + videoId, -1);
			}else{
				jedis.incrBy(roomUserCountKey + videoId , -1); 
			}
			log.info("用户[{}]退出房间" , sessionId);
		}finally{
			jedisPool.returnResource(jedis);
		}
		
	}

	@Override
	public Integer getRoomUserCount(Long videoId) {
		Jedis jedis = null ;
		try{
			jedis = jedisPool.getResource();
			String value = jedis.get(roomUserCountKey + videoId);
			if(StringUtils.isBlank(value)){
				return 0;
			}else{
				return Integer.valueOf(value);
			}
		}finally{
			jedisPool.returnResource(jedis);
		}
	}

	@Override
	public Integer getRoomTouristCount(Long videoId) {
		Jedis jedis = null ;
		try{
			jedis = jedisPool.getResource();
			String value = jedis.get(roomTouristCountKey + videoId);
			if(StringUtils.isBlank(value)){
				return 0;
			}else{
				return Integer.valueOf(value);
			}
		}finally{
			jedisPool.returnResource(jedis);
		}
	}

	@Override
	public List<Audience> getRoomUserList(Long videoId) {
		Jedis jedis = null ;
		try{
			jedis = jedisPool.getResource();
			Map<String, String> usersMap = jedis.hgetAll(roomUserMapKey + videoId);
			if(usersMap == null || usersMap.size() == 0	){
				return null ;
			}
			List<Audience> audienceList = new ArrayList<Audience>();
			for (String audienceJson : usersMap.values()) {
				audienceList.add(JSON.parseObject(audienceJson, Audience.class));  
			}
			return audienceList ;
		}finally{
			jedisPool.returnResource(jedis);
		}
	}

}
