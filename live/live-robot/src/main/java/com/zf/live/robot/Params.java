package com.zf.live.robot;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年2月13日 下午2:03:11
 */
public class Params {

	private String userPrefix ;
	
	private int userFrom ;
	
	private int userTo ;
	
	private String password ;
	
	private long actionInterval ;
	
	private String videoId ;
	
	private boolean doChat ;

	public String getUserPrefix() {
		return userPrefix;
	}

	public void setUserPrefix(String userPrefix) {
		this.userPrefix = userPrefix;
	}

	public int getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(int userFrom) {
		this.userFrom = userFrom;
	}

	public int getUserTo() {
		return userTo;
	}

	public void setUserTo(int userTo) {
		this.userTo = userTo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getActionInterval() {
		return actionInterval;
	}

	public void setActionInterval(long actionInterval) {
		this.actionInterval = actionInterval;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public boolean isDoChat() {
		return doChat;
	}

	public void setDoChat(boolean doChat) {
		this.doChat = doChat;
	}
	
}
