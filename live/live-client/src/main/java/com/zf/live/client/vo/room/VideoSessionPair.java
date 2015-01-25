package com.zf.live.client.vo.room;

import java.io.Serializable;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月24日 下午3:17:56
 */
public class VideoSessionPair implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2252433108644320562L;

	private Long videoId ;
	
	private String sessionId ;
	
	public VideoSessionPair() {	}

	public VideoSessionPair(Long videoId, String sessionId) {
		this.videoId = videoId;
		this.sessionId = sessionId;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
