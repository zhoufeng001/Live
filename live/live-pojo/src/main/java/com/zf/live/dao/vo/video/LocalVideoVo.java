package com.zf.live.dao.vo.video;

import com.zf.live.dao.pojo.LocalVideo;

/**
 * 本地视频扩展
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年2月7日 下午2:10:27
 */
public class LocalVideoVo extends LocalVideo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1147916622940117715L;
	
	private String table ;
	
	private Integer audienceCount ;
	
	private Integer touristCount ;
	
	private Integer userCount ;

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public Integer getAudienceCount() {
		return audienceCount == null ? 0 : audienceCount;
	}

	public void setAudienceCount(Integer audienceCount) {
		this.audienceCount = audienceCount;
	}

	public Integer getTouristCount() {
		return touristCount == null ? 0 : touristCount;
	}

	public void setTouristCount(Integer touristCount) {
		this.touristCount = touristCount;
	}

	public Integer getUserCount() {
		return userCount == null ? 0 : userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
	
}
