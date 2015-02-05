package com.zf.live.client.vo.room;

import java.io.Serializable;
import java.util.List;

/**
 * 房间信息
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月24日 上午12:44:22
 */
public class RoomInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278850289577016976L;

	/**
	 * 视频ID
	 */
	private String videoId ;

	/**
	 * 登录用户数量
	 */
	private int userCount ;
	
	/**
	 * 游客数量
	 */
	private int touristCount ;
	
	/**
	 * 登录用户数量 + 游客数量
	 */
	private int audienceCount ;
	
	/**
	 * （登录用户列表，根据进入房间时间排序）
	 */
	private List<Audience> users;

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public int getTouristCount() {
		return touristCount;
	}

	public void setTouristCount(int touristCount) {
		this.touristCount = touristCount;
	}

	public List<Audience> getUsers() {
		return users;
	}

	public void setUsers(List<Audience> users) {
		this.users = users;
	}

	public int getAudienceCount() {
		this.audienceCount = this.userCount + this.touristCount ;
		return audienceCount;
	}
	

}
