package com.zf.live.client.video.youku.response;

import java.util.List;

/**
 * 根据专辑id搜索专辑下面视频返回结果
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月11日 下午9:04:02
 */
public class SearchVideosFromVideoListResponse extends ListResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1202480563536314327L;
	
	private String playlist_id;
	
	private List<ListVideoResponse> videos ;

	public String getPlaylist_id() {
		return playlist_id;
	}

	public void setPlaylist_id(String playlist_id) {
		this.playlist_id = playlist_id;
	}

	public List<ListVideoResponse> getVideos() {
		return videos;
	}

	public void setVideos(List<ListVideoResponse> videos) {
		this.videos = videos;
	}
	
}
