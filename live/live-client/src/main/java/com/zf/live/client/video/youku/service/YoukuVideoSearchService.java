package com.zf.live.client.video.youku.service;

import com.zf.live.client.video.youku.request.SearchVideoByCategoryRequest;
import com.zf.live.client.video.youku.request.SearchVideoDetailRequest;
import com.zf.live.client.video.youku.response.SearchVideoByCategoryResponse;
import com.zf.live.client.video.youku.response.VideoDetailResponse;

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
}
