package com.zf.live.web.app.service.video;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.zf.live.client.video.local.LocalVideoService;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.client.vo.paging.PagedVo;
import com.zf.live.client.vo.video.local.LocalVideoSearchCondition;
import com.zf.live.client.vo.video.local.VideoDetailVo;
import com.zf.live.common.util.cache.EhCacheUtil;
import com.zf.live.dao.pojo.Video;
import com.zf.live.web.WebConst;
import com.zf.live.web.WebConst.EhCacheNames;
import com.zf.live.web.vo.video.CachedVideoDetailVo;
import com.zf.live.web.vo.video.CategoryRecommendVo;

@Component("webVideoService")
public class WebVideoService {

	static final Logger log = LoggerFactory.getLogger(WebVideoService.class);

	@Resource(name="localVideoService")
	private LocalVideoService localVideoService ;

	private static final EhCacheUtil ehCacheUtil = EhCacheUtil.getInstance(WebConst.WebEhcacheFileName);

	/**
	 * 根据分类查询置顶视频（先走缓存，再走DB）
	 * @param category
	 * @return
	 */
	public CategoryRecommendVo selectCategoryRecommend(String category){
		CategoryRecommendVo cateRecommendVo = null ;
		//先从缓存中读取
		cateRecommendVo = ehCacheUtil.get(EhCacheNames.categoryRecommendTopVideoCache, category, CategoryRecommendVo.class); 
		if(cateRecommendVo != null){
			return cateRecommendVo ;
		}
		cateRecommendVo = new CategoryRecommendVo();
		LocalVideoSearchCondition condition = new LocalVideoSearchCondition() ;
		condition.setCategory(category);
		condition.setPage(1);
		condition.setPageSize(7);
		condition.setOrderBy(" view_count desc , third_view_count desc ");
		PagedVo<Video> videoPageVo = localVideoService.searchVideos(condition);
		if(videoPageVo != null && videoPageVo.getData() != null && videoPageVo.getCount() > 0){
			List<Video> recommendVideos = videoPageVo.getData();
			VideoDetailVo topVideoDetailVo = selectVideoDetailVoWithCache(recommendVideos.get(0).getId(), false);
			cateRecommendVo.setTopVideoDetailVo(topVideoDetailVo);
			recommendVideos.remove(0);
			cateRecommendVo.setRecommendVideoList(recommendVideos); 
		}
		//缓存
		ehCacheUtil.put(EhCacheNames.categoryRecommendTopVideoCache, category, cateRecommendVo); 
		return cateRecommendVo ;
	}


	/**
	 * 读取视频详细信息（先走缓存，再走DB）
	 * @param videoId
	 * @return
	 */
	public CachedVideoDetailVo selectVideoDetailVoWithCache(Long videoId , boolean incrementViewCount){
		CachedVideoDetailVo cachedVideoDetailVo = null ;
		cachedVideoDetailVo = ehCacheUtil.get(EhCacheNames.videoInfoCache, videoId, CachedVideoDetailVo.class);
		if(cachedVideoDetailVo != null){
			if(incrementViewCount){
				Long viewCount = cachedVideoDetailVo.getVideo().getViewCount();
				viewCount++ ;
				cachedVideoDetailVo.setIncrementViewCount(cachedVideoDetailVo.getIncrementViewCount() + 1); 
				cachedVideoDetailVo.getVideo().setViewCount(viewCount);
			}
			return cachedVideoDetailVo ;
		}

		ServiceResult<VideoDetailVo> result = localVideoService.selectVideoWithDetailInfo(videoId,true);
		if(result == null){
			log.warn("没查询到视频{}",videoId);
			return null ;
		}
		if(result.isSuccess()){
			VideoDetailVo videoDetailVo = result.getData();
			if(videoDetailVo == null){
				return null ;
			}
			cachedVideoDetailVo = new CachedVideoDetailVo();
			BeanUtils.copyProperties(videoDetailVo, cachedVideoDetailVo);
			ehCacheUtil.put(EhCacheNames.videoInfoCache , videoId, cachedVideoDetailVo); 
			return cachedVideoDetailVo;
		}else{
			log.error(result.getErrMssage());
			return null ;
		}
	}

}
