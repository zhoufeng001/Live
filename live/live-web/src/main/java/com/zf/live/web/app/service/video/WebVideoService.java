package com.zf.live.web.app.service.video;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.zf.live.client.video.local.LocalVideoServiceV2;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.client.vo.paging.PagedVo;
import com.zf.live.client.vo.video.local.LocalVideoDetailVo;
import com.zf.live.client.vo.video.local.LocalVideoSearchConditionV2;
import com.zf.live.common.assertx.ZFAssert;
import com.zf.live.common.util.cache.EhCacheUtil;
import com.zf.live.dao.vo.video.LocalVideoVo;
import com.zf.live.web.WebConst;
import com.zf.live.web.WebConst.EhCacheNames;
import com.zf.live.web.vo.video.CachedVideoDetailVo;
import com.zf.live.web.vo.video.CategoryRecommendVo;

@Component("webVideoService")
public class WebVideoService {

	static final Logger log = LoggerFactory.getLogger(WebVideoService.class);

	@Resource(name="localVideoServiceV2")
	private LocalVideoServiceV2 localVideoServiceV2 ;

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
			log.info("从缓存中读取到分类列表[{}]" , category);
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
	 * 重新缓存指定分类的推荐视频
	 * @param category
	 * @return
	 */
	public void reCacheCategoryRecommend(String category){
		log.info("Recache分类[{}]" , category); 
		ZFAssert.notBlank(category, "category不能为空");
		CategoryRecommendVo cateRecommendVo = new CategoryRecommendVo();
		LocalVideoSearchConditionV2 condition = new LocalVideoSearchConditionV2() ;
		condition.setCategory(category); 
		condition.setPage(1);
		condition.setPageSize(9);
		condition.setOrderBy(" view_count desc , third_view_count desc "); 
		PagedVo<LocalVideoVo> videoPageVo = localVideoServiceV2.searchVideos(condition ,false);
		if(videoPageVo != null && videoPageVo.getData() != null && videoPageVo.getCount() > 0){
			List<LocalVideoVo> recommendVideos = videoPageVo.getData();
			LocalVideoDetailVo topVideoDetailVo = selectVideoDetailVoWithCache(recommendVideos.get(0).getId(), false);
			cateRecommendVo.setTopVideoDetailVo(topVideoDetailVo);
			recommendVideos.remove(0);
			cateRecommendVo.setRecommendVideoList(recommendVideos); 
			//缓存
			ehCacheUtil.put(EhCacheNames.categoryRecommendTopVideoCache, category, cateRecommendVo); 
		}
	}


	/**
	 * 读取视频详细信息（先走缓存，再走DB）
	 * @param videoId
	 * @return
	 */
	public CachedVideoDetailVo selectVideoDetailVoWithCache(String videoId , boolean incrementViewCount){
		CachedVideoDetailVo cachedVideoDetailVo = ehCacheUtil.get(EhCacheNames.videoInfoCache, videoId, CachedVideoDetailVo.class);
		if(cachedVideoDetailVo != null){
			log.info("从缓存中读取到视频[{}]" , videoId); 
			if(incrementViewCount){
				Long viewCount = cachedVideoDetailVo.getVideo().getViewCount();
				viewCount++ ;
				cachedVideoDetailVo.setIncrementViewCount(cachedVideoDetailVo.getIncrementViewCount() + 1); 
				cachedVideoDetailVo.getVideo().setViewCount(viewCount);
			}
			return cachedVideoDetailVo ;
		}

		ServiceResult<LocalVideoDetailVo> result = localVideoServiceV2.selectVideoWithDetailInfo(videoId,true);
		if(result == null){
			log.warn("没查询到视频{}",videoId);
			return null ;
		}
		log.info("从数据库读取到视频[{}]" , videoId); 
		if(result.isSuccess()){
			LocalVideoDetailVo videoDetailVo = result.getData();
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
	 * 给视频点赞
	 * @param videoId
	 * @return 返回点赞后该视频赞数量
	 */
	public long doPraiseVideo(String videoId){
		CachedVideoDetailVo cachedVideoDetailVo = ehCacheUtil.get(EhCacheNames.videoInfoCache, videoId, CachedVideoDetailVo.class);
		if(cachedVideoDetailVo == null){
			ServiceResult<LocalVideoDetailVo> result = localVideoServiceV2.selectVideoWithDetailInfo(videoId,false);
			if(result == null){
				log.warn("没查询到视频{}",videoId);
				return 0 ;
			}
			if(result.isSuccess()){
				LocalVideoDetailVo videoDetailVo = result.getData();
				if(videoDetailVo == null){
					return 0 ;
				}
				cachedVideoDetailVo = new CachedVideoDetailVo();
				BeanUtils.copyProperties(videoDetailVo, cachedVideoDetailVo);
				ehCacheUtil.put(EhCacheNames.videoInfoCache , videoId, cachedVideoDetailVo); 
			}else{
				log.error(result.getErrMssage());
				return 0 ;
			}
		}
		Long praiseCount = cachedVideoDetailVo.getVideo().getPraise(); 
		praiseCount++ ;
		cachedVideoDetailVo.setIncrementPraiseCount(cachedVideoDetailVo.getIncrementPraiseCount() + 1); 
		cachedVideoDetailVo.getVideo().setPraise(praiseCount); 
		return praiseCount ;

	}

	/**
	 * 根据条件分页搜索视频 
	 * 对各分类第一页进行了缓存
	 */
	@SuppressWarnings("unchecked")
	public PagedVo<LocalVideoVo> searchVideos(LocalVideoSearchConditionV2 condition){
		if(condition == null){
			return null ;
		}
		if(condition.getPage() == 1){
			StringBuilder cacheKey = new StringBuilder("vp");
			String category = condition.getCategory();
			cacheKey.append("-").append(category);
			cacheKey.append("-").append(condition.getPage().intValue());
			cacheKey.append("-").append(condition.getOrderBy());
			String cacheKeyStr = cacheKey.toString().replace(" ", ".");  
			PagedVo<LocalVideoVo> pageVo = new PagedVo<LocalVideoVo>();
			pageVo = ehCacheUtil.get(WebConst.EhCacheNames.videoPageCache, cacheKeyStr, pageVo.getClass());
			if(pageVo == null){
				log.info("从数据库中查询分类第一页缓存[{}]",condition.getCategory()); 
				pageVo = localVideoServiceV2.searchVideos(condition,true); 
				ehCacheUtil.put(WebConst.EhCacheNames.videoPageCache, cacheKeyStr, pageVo);  
			}else{
				log.info("从缓存中读取到分类第一页视频[{}]" , condition.getCategory()); 
			}
			return pageVo ;
		}else{ 
			return localVideoServiceV2.searchVideos(condition,true); 
		}
	}

}
