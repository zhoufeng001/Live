package com.zf.live.service.impl.room.audience;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zf.live.client.room.audience.AudienceCountService;
import com.zf.live.client.video.local.LocalVideoServiceV2;
import com.zf.live.client.vo.room.Audience;
import com.zf.live.common.validate.Notnull;
import com.zf.live.dao.mapper.AudienceCountMapperExt;
import com.zf.live.dao.pojo.AudienceCount;
import com.zf.live.dao.pojo.LocalVideo;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年2月8日 下午7:08:52
 */
@Component("audienceCountService")
public class AudienceCountServiceImpl implements AudienceCountService{
	
	static final Logger log = LoggerFactory.getLogger(AudienceCountServiceImpl.class);
	
	@Autowired
	private AudienceCountMapperExt audienceCountMapper ;
	
	@Autowired
	private LocalVideoServiceV2 localVideoServiceV2 ;

	@Override
	public void addAudience(@Notnull("videoId") Audience audience) {
		AudienceCount audienceCount = audienceCountMapper.selectByPrimaryKey(audience.getVideoId());
		if(audienceCount == null){
			LocalVideo localVideo = localVideoServiceV2.selectVideoById(audience.getVideoId());
			if(localVideo == null){
				log.warn("视频[{}]不存在" , audience.getVideoId());
				return ;
			}
			audienceCount = new AudienceCount() ;
			audienceCount.setVid(audience.getVideoId());
			audienceCount.setAudienceCount(1);
			if(audience.isTourist()){
				audienceCount.setTouristCount(1);
				audienceCount.setUserCount(0);
			}else{
				audienceCount.setUserCount(1);
				audienceCount.setTouristCount(0); 
			}
			audienceCount.setVideoCategory(localVideo.getCategory()); 
			int insertResult = audienceCountMapper.insertSelective(audienceCount) ; 
			if(insertResult <= 0){
				log.warn("插入观众信息失败！"); 
			}
		}else{
			int updateResult = 0 ;
			if(audience.isTourist()){
				updateResult = audienceCountMapper.incrementTouristCount(audience.getVideoId(),1);
			}else{
				updateResult = audienceCountMapper.incrementUserCount((audience.getVideoId()),1);
			}
			if(updateResult <= 0){
				log.warn("更新观众信息失败！");
			} 
		}
	}

	@Override
	public void removeAudience(@Notnull("videoId") Audience audience) {
		AudienceCount audienceCount = audienceCountMapper.selectByPrimaryKey(audience.getVideoId());
		if(audienceCount == null){
			log.warn("移除观众失败，该视频[{}]对应的观众信息不存在!",audience.getVideoId());
			return ;
		}
		//如果最后一个用户退出房间，就删除记录
		if(audienceCount.getAudienceCount().equals(1)){ 
			audienceCountMapper.deleteByPrimaryKey(audience.getVideoId());
			return ;
		}
		int updateResult = 0 ;
		if(audience.isTourist()){
			updateResult = audienceCountMapper.incrementTouristCount(audience.getVideoId(), -1) ;
		}else{
			updateResult = audienceCountMapper.incrementUserCount((audience.getVideoId()),-1);
		}
		if(updateResult <= 0){
			log.warn("更新观众信息失败！");
		} 
	}

	@Override
	public AudienceCount getByVideoId(@Notnull String videoId) {
		AudienceCount audienceCount = audienceCountMapper.selectByPrimaryKey(videoId);
		if(audienceCount == null){
			audienceCount = new AudienceCount();
			audienceCount.setVid(videoId);
			audienceCount.setAudienceCount(0);
			audienceCount.setTouristCount(0);
			audienceCount.setUserCount(0);
		}
		return audienceCount; 
	}

	@Override
	public int getAudienceCount(String videoId) {
		Integer audienceCount = audienceCountMapper.selectAudienceCount(videoId);
		if(audienceCount == null){
			audienceCount = 0 ;
		}
		return audienceCount.intValue();
	}

	@Override
	public int getUserCount(String videoId) {
		Integer userCount = audienceCountMapper.selectUserCount(videoId); 
		if(userCount == null){
			userCount = 0 ;
		}
		return userCount.intValue();
	}

	@Override
	public int getTouristCount(String videoId) {
		Integer touristCount = audienceCountMapper.selectTouristCount(videoId);
		if(touristCount == null){
			touristCount = 0 ;
		}
		return touristCount.intValue();
	}
	
	
}
