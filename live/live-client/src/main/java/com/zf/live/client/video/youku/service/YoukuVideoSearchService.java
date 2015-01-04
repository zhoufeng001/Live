package com.zf.live.client.video.youku.service;

import java.util.List;

import com.zf.live.client.video.youku.request.SearchVideoByCategoryRequest;
import com.zf.live.client.video.youku.request.SearchVideoDetailRequest;
import com.zf.live.client.video.youku.response.SearchVideoByCategoryResponse;
import com.zf.live.client.video.youku.response.VideoDetailResponse;
import com.zf.live.client.video.youku.vo.Category;

/**
 * 搜索youku视频服务
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月4日 上午1:51:46
 */
public interface YoukuVideoSearchService {
	
	/**
	 * 根据分类搜索视频列表
	 * @param request
	 * @return
	 */
	SearchVideoByCategoryResponse searchByCategory(SearchVideoByCategoryRequest request);
 	
	/**
	 * 查询视频详细信息
	 * @param request
	 * @return
	 */
	VideoDetailResponse searchVideoDetail(SearchVideoDetailRequest request);
	
	
	/**
	 * 查询所有分类
	 *（尽量避免多次调用该方法，因为该方法会从文件中读取分类信息，然后解析成java对象，推荐做法是，第一次调用该方法后，就缓存起来）
	 * @return
	 */
	List<Category> searchAllCategories();
}
