package com.zf.live.client.video.youku.response;

import java.io.Serializable;

/**
 * 专辑信息
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月10日 上午11:07:33
 */
public class VideoListResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3123884012201339239L;

	private String id ;
	
	private String name ;
	
	private String link ;
	
	private String play_link ;
	
	private String thumbnail;
	
	private Integer video_count ;
	
	private Integer view_count ;
	
	private Integer duration ;
	
	private String published ;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPlay_link() {
		return play_link;
	}

	public void setPlay_link(String play_link) {
		this.play_link = play_link;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Integer getVideo_count() {
		return video_count;
	}

	public void setVideo_count(Integer video_count) {
		this.video_count = video_count;
	}

	public Integer getView_count() {
		return view_count;
	}

	public void setView_count(Integer view_count) {
		this.view_count = view_count;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getPublished() {
		return published;
	}

	public void setPublished(String published) {
		this.published = published;
	}
	
}
