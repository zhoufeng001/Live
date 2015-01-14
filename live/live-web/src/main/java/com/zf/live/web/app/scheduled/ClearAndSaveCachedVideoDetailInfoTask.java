package com.zf.live.web.app.scheduled;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zf.live.client.video.local.LocalVideoService;
import com.zf.live.common.util.cache.EhCacheUtil;
import com.zf.live.dao.pojo.Video;
import com.zf.live.web.WebConst;
import com.zf.live.web.WebConst.EhCacheNames;
import com.zf.live.web.app.service.video.WebVideoService;
import com.zf.live.web.vo.video.CachedVideoDetailVo;

/**
 * 清除并保存缓存的视频详情
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月15日 上午2:30:30
 */
@Component("clearAndSaveCachedVideoDetailInfoTask")
public class ClearAndSaveCachedVideoDetailInfoTask {
	
	static final Logger log = LoggerFactory.getLogger(ClearAndSaveCachedVideoDetailInfoTask.class);
	
	@Resource(name="localVideoService")
	private LocalVideoService localVideoService ;
	
	@Autowired
	private WebVideoService webVideoService ;
	
	private static final EhCacheUtil ehCacheUtil = EhCacheUtil.getInstance(WebConst.WebEhcacheFileName);
	
	public void clearAndSave(){
		List<?> allVideoKeys =  ehCacheUtil.getAllKeys(EhCacheNames.videoInfoCache);
		if(allVideoKeys == null || allVideoKeys.size() == 0){
			return ;
		}
		for (Object videoId : allVideoKeys) { 
			CachedVideoDetailVo videoDetailInfo = ehCacheUtil.get(EhCacheNames.videoInfoCache, videoId, CachedVideoDetailVo.class);
			ehCacheUtil.remove(EhCacheNames.videoInfoCache, videoId); 
			if(videoDetailInfo == null){
				continue ;
			}
			
			Video video = localVideoService.selectVideoById((Long)videoId) ;
			if(video == null){
				continue ;
			}
			Video updateVideo = new Video();
			updateVideo.setId(video.getId());
			updateVideo.setViewCount(video.getViewCount() + videoDetailInfo.getIncrementViewCount()); 
			updateVideo.setPraise(video.getPraise() + videoDetailInfo.getIncrementPraiseCount());
			boolean updateResult = localVideoService.updateVideoBySelective(updateVideo) ;
			if(updateResult){
				log.info("更新缓存视频{}到数据库成功",videoId);
			}else{
				log.error("更新缓存视频{}到数据库失败",videoId);
			}
		}
	}
	
}
