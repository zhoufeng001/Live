package com.zf.live.client.video.youku.request;

import com.zf.live.client.video.youku.RequestField;


/**
 * 查询视频详细信息请求参数
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月4日 上午3:26:36
 */
public class SearchVideoDetailRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7293758707985651931L;

	@RequestField("video_id")
	private String videoId ;
	
	/**
	 * 视频扩展信息返回， 多个用逗号分隔	
	 * thumbnails：视频截图列表
	 * show, dvd,reference
	 */
	@RequestField("ext")
	private String ext ;

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	
	public String getExt() {
		return ext;
	}

	/**
	 * 视频扩展信息返回， 多个用逗号分隔	
	 * thumbnails：视频截图列表
	 * show, dvd,reference
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}
	
}
