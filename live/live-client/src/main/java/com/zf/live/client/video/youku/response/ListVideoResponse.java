package com.zf.live.client.video.youku.response;

/**
 * 专辑下面的视频
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月11日 下午9:07:22
 */
public class ListVideoResponse extends VideoResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6592857740930392987L;

	private Integer seq_no ;

	public Integer getSeq_no() {
		return seq_no;
	}

	public void setSeq_no(Integer seq_no) {
		this.seq_no = seq_no;
	}
	
}
