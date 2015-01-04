package com.zf.live.client.video.youku.response;

import java.util.List;


/**
 * 视频详细信息
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月4日 上午3:27:50
 */
public class VideoDetailResponse extends VideoResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 89418715252658644L;
	
	
	/**
	 * 视频截图（大）
	 */
	private String bigThumbnail ;
	
	/**
	 * 视频描述
	 */
	private String description ;
	
	/**
	 * 公开类型 all: 公开 friend: 仅好友观看 password: 输入密码观看
	 */
	private String public_type ;
	
	/**
	 * 多张截图（默认不返回），需要在请求中的ext参数中加入thumbnails
	 */
	private List<ThumbnailObject> thumbnails ;
	
	
	public String getBigThumbnail() {
		return bigThumbnail;
	}



	public void setBigThumbnail(String bigThumbnail) {
		this.bigThumbnail = bigThumbnail;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getPublic_type() {
		return public_type;
	}



	public void setPublic_type(String public_type) {
		this.public_type = public_type;
	}



	public List<ThumbnailObject> getThumbnails() {
		return thumbnails;
	}



	public void setThumbnails(List<ThumbnailObject> thumbnails) {
		this.thumbnails = thumbnails;
	}



	/**
	 * 视频截图列表
	 * @author is_zhoufeng@163.com , QQ:243970446
	 * 2015年1月4日 上午3:32:26
	 */
	public static class ThumbnailObject {
		/**
		 * 截图序号
		 */
		private Integer seq;
		
		/**
		 * 截图地址
		 */
		private String url ;
		
		/**
		 * 是否为封面 1.是 0.不是
		 */
		private Integer is_cover ;

		public Integer getSeq() {
			return seq;
		}

		public void setSeq(Integer seq) {
			this.seq = seq;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public Integer getIs_cover() {
			return is_cover;
		}

		public void setIs_cover(Integer is_cover) {
			this.is_cover = is_cover;
		}
		
	}
}
