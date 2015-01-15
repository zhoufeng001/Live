package com.zf.live.web.app.service.video;

import java.util.ArrayList;
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
import com.zf.live.common.assertx.ZFAssert;
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
		ZFAssert.notBlank(category, "category不能为空");
		CategoryRecommendVo cateRecommendVo = null ;
		//先从缓存中读取
		cateRecommendVo = ehCacheUtil.get(EhCacheNames.categoryRecommendTopVideoCache, category, CategoryRecommendVo.class); 
		if(cateRecommendVo != null){
			return cateRecommendVo ;
		}
		reCacheCategoryRecommend(category); 
		cateRecommendVo = ehCacheUtil.get(EhCacheNames.categoryRecommendTopVideoCache, category, CategoryRecommendVo.class); 
		if(cateRecommendVo == null){
			log.error("recache[{}]无效！" , category);  
		}
		return cateRecommendVo ;
	}
	
	/**
	 * 重新缓存指定分类的指定视频
	 * @param category
	 * @return
	 */
	public void reCacheCategoryRecommend(String category){
		ZFAssert.notBlank(category, "category不能为空");
		CategoryRecommendVo cateRecommendVo = new CategoryRecommendVo();
		LocalVideoSearchCondition condition = new LocalVideoSearchCondition() ;
		List<String> categoryList = new ArrayList<String>();
		categoryList.add(category);
		condition.setCategory(categoryList);
		condition.setPage(1);
		condition.setPageSize(11);
		condition.setOrderBy(" view_count desc , third_view_count desc ,praise desc , third_praise desc , publishtime desc ");
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
	
	/**
	 * 根据条件分页搜索视频 
	 * 对各分类第一页进行了缓存
	 */
	@SuppressWarnings("unchecked")
	public PagedVo<Video> searchVideos(LocalVideoSearchCondition condition){
		if(condition == null){
			return null ;
		}
		if(condition.getPage() == 1){
			StringBuilder cacheKey = new StringBuilder("vp");
			List<String> categories = condition.getCategory();
			if(categories != null && categories.size() > 0){
				for (String category : categories) {
					cacheKey.append("-").append(category);
				}
			}
			cacheKey.append("-").append(condition.getPage().intValue());
			cacheKey.append("-").append(condition.getOrderBy());
			String cacheKeyStr = cacheKey.toString().replace(" ", ".");  
			PagedVo<Video> pageVo = new PagedVo<Video>();
			pageVo = ehCacheUtil.get(WebConst.EhCacheNames.videoPageCache, cacheKeyStr, pageVo.getClass());
			if(pageVo == null){
				pageVo = localVideoService.searchVideos(condition); 
				ehCacheUtil.put(WebConst.EhCacheNames.videoPageCache, cacheKeyStr, pageVo);  
			}
			return pageVo ;
		}else{
			return localVideoService.searchVideos(condition); 
		}
	}
	
}
