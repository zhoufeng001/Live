package com.zf.live.comet.service.room;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.eclipse.jetty.util.ConcurrentHashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zf.live.client.room.RoomService;
import com.zf.live.client.vo.room.Audience;
import com.zf.live.client.vo.room.VideoSessionPair;

/**
 * 观众列表容器，存储本机器上的观众列表信息
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月24日 下午1:27:21
 */
@Component("audienceContainerManager")
public class AudienceContainerManager {
	
	static final Logger log = LoggerFactory.getLogger(AudienceContainerManager.class);
	
	@Resource(name="roomService")
	private RoomService roomService ;
	
	/**
	 * 保存观众列表
	 * key:videoId
	 * value:sessionId集合
	 */
	private ConcurrentHashMap<Long, ConcurrentHashSet<String> > roomAudienceMap = new ConcurrentHashMap<Long, ConcurrentHashSet<String>>() ;
	
	/**
	 * 添加观众
	 * @param sessionId  cometd sessionId
	 * @param videoId	  视频Id
	 */
	public void addAudience(Long videoId,Audience audience){
		ConcurrentHashSet<String> sessionSet = roomAudienceMap.get(videoId);
		if(sessionSet == null){
			sessionSet = new ConcurrentHashSet<String>();
			roomAudienceMap.put(videoId, sessionSet);
		}
		sessionSet.add(audience.getSessionId());
		roomService.comeInRoom(videoId, audience); 
	}
	
	/**
	 * 移除观众
	 * @param sessionId  cometd sessionId
	 */
	public void removeAudience(Long videoId,String sessionId){
		ConcurrentHashSet<String> sessionSet = roomAudienceMap.get(videoId);
		if(sessionSet == null){
			log.warn("移除观众不存在，不存在roomSet[{}]" , videoId);
			return ;
		}
		sessionSet.remove(sessionId);
		roomService.outRoom(videoId, sessionId); 
	}
	
	/**
	 * 清除本地所有所有观众，以及Redis中存在的观众
	 */
	public void clearAllAudience(){
		log.info("正在移除观众列表...");
		List<VideoSessionPair> vsps = new ArrayList<VideoSessionPair>();
		Set<Entry<Long, ConcurrentHashSet<String>>> roomUserMap = roomAudienceMap.entrySet();
		Iterator<Entry<Long, ConcurrentHashSet<String>>> roomUserIter = roomUserMap.iterator();
		while(roomUserIter.hasNext()){
			Entry<Long, ConcurrentHashSet<String>> roomUserSetEntry = roomUserIter.next();
			Long videoId = roomUserSetEntry.getKey() ;
			ConcurrentHashSet<String> sessionSet = roomUserSetEntry.getValue();
			for (String sessionId : sessionSet) {
				vsps.add(new VideoSessionPair(videoId, sessionId)); 
			}
		}
		if(vsps.size() <= 0){
			log.info("没有在线观众可移除...");
		}else{
			roomService.batchOutRoom(vsps); 
			log.info("移除观众列表完成，成功移除[{}]个观众" ,vsps.size()); 
		}
	}
	
}
