package com.zf.live.client.video.youku.request;

import com.zf.live.client.video.youku.RequestField;


/**
 * 根据专辑id搜索专辑下面的视频请求参数
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月11日 下午8:59:19
 */
public class SearchVideosFromVideoListRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6628379451701025927L;
	
	@RequestField("playlist_id")
	private String playlistId ;

	public String getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(String playlistId) {
		this.playlistId = playlistId;
	}
	
}
