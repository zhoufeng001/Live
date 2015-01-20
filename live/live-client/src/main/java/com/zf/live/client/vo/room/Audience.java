package com.zf.live.client.vo.room;

import java.io.Serializable;

/**
 * 观看直播的观众
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月20日 下午10:17:34
 */
public class Audience implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8624729490001492779L;
	
	/**
	 * sessionId
	 */
	private String sessionId ;
	
	/**
	 * 是否是游客
	 */
	private boolean tourist;

	/**
	 * 用户ID
	 */
	private Long userId ;
	
	/**
	 * 登录token
	 */
	private String userToken ;
	
	/**
	 * 用户昵称
	 */
	private String userNick ;
	
	/**
	 * 用户头像
	 */
	private String userPhoto ;
	
	/**
	 * 进入房间时间
	 */
	private Long comeInTime ;

	public boolean isTourist() {
		return tourist;
	}

	public void setTourist(boolean tourist) {
		this.tourist = tourist;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public Long getComeInTime() {
		return comeInTime;
	}

	public void setComeInTime(Long comeInTime) {
		this.comeInTime = comeInTime;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	
}
