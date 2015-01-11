package com.zf.live.client.video.youku.request;

import com.zf.live.client.video.youku.RequestField;

/**
 * 根据专辑ids搜索专辑详细信息
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月10日 下午4:05:42
 */
public class SearchVideoListDetailByIdsRequest extends BaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5346697066080113283L;
	@RequestField("playlist_ids")
	private String playlistIds ;

	public String getPlaylistIds() {
		return playlistIds;
	}

	public void setPlaylistIds(String playlistIds) {
		this.playlistIds = playlistIds;
	}
	
}
