package com.zf.live.client.video.youku.response;

/**
 * 专辑详情
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月10日 下午4:12:54
 */
public class VideoListDetailResponse extends VideoListResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5700053908548189027L;

	private String description ;
	
	private String category ;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
