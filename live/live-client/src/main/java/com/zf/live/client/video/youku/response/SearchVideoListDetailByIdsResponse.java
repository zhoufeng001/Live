package com.zf.live.client.video.youku.response;

import java.io.Serializable;
import java.util.List;

/**
 * 根据专辑ids搜索专辑详细信息返回内容
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月10日 下午4:14:52
 */
public class SearchVideoListDetailByIdsResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5685055906185684793L;
	
	private Integer total ;
	
	private List<VideoListDetailResponse> playlists ;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<VideoListDetailResponse> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<VideoListDetailResponse> playlists) {
		this.playlists = playlists;
	}
	
	
}
