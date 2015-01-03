package com.zf.live.client.video.youku.response;

import java.util.List;


/**
 * 根据视频分类搜索视频返回内容
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月4日 上午1:28:43
 */
public class SearchVideoByCategoryResponse extends ListResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1618475093775691489L;

	/**
	 * 视频列表
	 */
	private List<VideoResponse> videos ;

	public List<VideoResponse> getVideos() {
		return videos;
	}

	public void setVideos(List<VideoResponse> videos) {
		this.videos = videos;
	}
	
}
