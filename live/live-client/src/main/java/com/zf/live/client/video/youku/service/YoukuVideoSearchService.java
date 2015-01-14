package com.zf.live.client.video.youku.service;

import java.util.List;

import com.zf.live.client.video.youku.request.SearchVideoByCategoryRequest;
import com.zf.live.client.video.youku.request.SearchVideoDetailRequest;
import com.zf.live.client.video.youku.request.SearchVideoListByCategoryRequest;
import com.zf.live.client.video.youku.request.SearchVideoListDetailByIdsRequest;
import com.zf.live.client.video.youku.request.SearchVideosFromVideoListRequest;
import com.zf.live.client.video.youku.response.SearchVideoByCategoryResponse;
import com.zf.live.client.video.youku.response.SearchVideoListByCategoryResponse;
import com.zf.live.client.video.youku.response.SearchVideoListDetailByIdsResponse;
import com.zf.live.client.video.youku.response.SearchVideosFromVideoListResponse;
import com.zf.live.client.video.youku.response.VideoDetailResponse;
import com.zf.live.client.video.youku.vo.Category;
import com.zf.live.client.vo.video.local.VideoDetailVo;
import com.zf.live.dao.pojo.VideoDetailWithBLOBs;

/**
 * 搜索youku视频服务
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月4日 上午1:51:46
 */
public interface YoukuVideoSearchService {
	
	//----------------Video-----------------------------
	
	/**
	 * 根据分类搜索视频列表
	 * @param request
	 * @return
	 */
	SearchVideoByCategoryResponse searchVideoByCategory(SearchVideoByCategoryRequest request);
 	
	/**
	 * 查询视频详细信息（先从本地查找，如果本地存在，直接返回，否则从优酷查询，同时会将该视频保存到本地）
	 * @param request
	 * @return
	 */
	VideoDetailVo searchVideoDetail(SearchVideoDetailRequest request);
	
	
	/**
	 * 从优酷搜索视频详情
	 * @param request
	 * @return
	 */
	VideoDetailResponse searchYoukuVideoDetail(SearchVideoDetailRequest request);
	
	
	/**
	 * 从优酷搜索视频详情，并保存到本地
	 * @param request
	 * @return
	 */
	VideoDetailWithBLOBs searchYoukuVideoDetailAndSave(Long localVideoId , SearchVideoDetailRequest request);
	
	/**
	 * 查询所有视频分类
	 *（尽量避免多次调用该方法，因为该方法会从文件中读取分类信息，然后解析成java对象，推荐做法是，第一次调用该方法后，就缓存起来）
	 * @return
	 */
	List<Category> searchAllVideoCategories();
	
	
	
	//-------------------VideoList-------------------------
	
	/**
	 * 根据分类搜索专辑
	 * @param request
	 * @return
	 */
	SearchVideoListByCategoryResponse searchVideoListByCategory(SearchVideoListByCategoryRequest request);
	
	/**
	 * 根据专辑ids搜索专辑详细信息
	 * @param request
	 * @return
	 */
	SearchVideoListDetailByIdsResponse searchVideoListDetailByIds(SearchVideoListDetailByIdsRequest request);
	
	/**
	 * 根据专辑id搜索专辑下面的视频列表
	 * @param request
	 * @return
	 */
	SearchVideosFromVideoListResponse searchVideoListFromVideoGroup(SearchVideosFromVideoListRequest request);
	/**
	 * 查询所有专辑分类
	 * @return
	 */
	List<Category> searchAllVideoListCategories();
}
