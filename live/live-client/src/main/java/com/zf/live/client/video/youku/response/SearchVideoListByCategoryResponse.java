package com.zf.live.client.video.youku.response;

import java.util.List;

/**
 * 根据分类搜索专辑结果集
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月10日 上午11:06:50
 */
public class SearchVideoListByCategoryResponse extends ListResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 144590870789919502L;

	private List<VideoListResponse> playlists ;

	public List<VideoListResponse> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<VideoListResponse> playlists) {
		this.playlists = playlists;
	}
	
}
