package com.zf.live.client.video.youku.response;

import java.io.Serializable;
import java.util.List;

import com.zf.live.client.vo.video.VideoSite;
import com.zf.live.dao.pojo.Video;

/**
 * 优酷接口返回的视频对象信息
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月4日 上午1:36:42
 */
public class VideoResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6065422654146156409L;

	/**
	 * 视频唯一ID
	 */
	private String id ;

	/**
	 * 视频标题
	 */
	private String title ;

	/**
	 * 视频播放链接
	 */
	private String link ;

	/**
	 * 视频截图（小）
	 */
	private String thumbnail ;

	/**
	 * 视频时长，单位：秒(有可能会有小数点)
	 */
	private String duration ;

	/**
	 * 视频分类
	 */
	private String category ;

	/**
	 * 视频标签，多个标签用逗号分隔
	 */
	private String tags ;

	/**
	 * 视频状态 normal: 正常 encoding: 转码中 encode_fail: 转码失败 auditing: 审核中 masked: 已屏蔽
	 */
	private String state ;

	/**
	 * 总播放数
	 */
	private Integer view_count ;

	/**
	 * 总收藏数
	 */
	private Integer favorite_count ;

	/**
	 * 总评论数
	 */
	private Integer comment_count ;

	/**
	 * 总顶数（暂不实现）
	 */
	private Integer up_count ;

	/**
	 * 总踩数（暂不实现）
	 */
	private Integer down_count ;

	/**
	 * 发布时间
	 */
	private String published ;

	/**
	 * 操作限制 COMMENT_DISABLED: 禁评论 DOWNLOAD_DISABLED: 禁下载
	 */
	private List<String> operation_limit ;
	
	/**
	 * 视频格式 flvhd flv 3gphd 3gp hd hd2
	 */
	private List<String> streamtypes ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getView_count() {
		return view_count;
	}

	public void setView_count(Integer view_count) {
		this.view_count = view_count;
	}

	public Integer getFavorite_count() {
		return favorite_count;
	}

	public void setFavorite_count(Integer favorite_count) {
		this.favorite_count = favorite_count;
	}

	public Integer getComment_count() {
		return comment_count;
	}

	public void setComment_count(Integer comment_count) {
		this.comment_count = comment_count;
	}

	public Integer getUp_count() {
		return up_count;
	}

	public void setUp_count(Integer up_count) {
		this.up_count = up_count;
	}

	public Integer getDown_count() {
		return down_count;
	}

	public void setDown_count(Integer down_count) {
		this.down_count = down_count;
	}

	public String getPublished() {
		return published;
	}

	public void setPublished(String published) {
		this.published = published;
	}

	public List<String> getStreamtypes() {
		return streamtypes;
	}

	public void setStreamtypes(List<String> streamtypes) {
		this.streamtypes = streamtypes;
	}

	public List<String> getOperation_limit() {
		return operation_limit;
	}

	public void setOperation_limit(List<String> operation_limit) {
		this.operation_limit = operation_limit;
	}
	
	public Video toLocalVideo(){
		Video video = new Video() ;
		video.setCategory(this.getCategory());
		video.setFromid(this.getId());
		video.setThumbnail(this.getThumbnail());
		video.setVideofrom(VideoSite.YOUKU.getValue());
		video.setVideoname(this.getTitle()); 
		return video ;
	}
	

}
