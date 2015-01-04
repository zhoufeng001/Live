package com.zf.live.client.vo.video.local;

import java.io.Serializable;

import com.zf.live.dao.pojo.Video;
import com.zf.live.dao.pojo.VideoDetailWithBLOBs;

/**
 * 视频详细信息，包含 Video 与 VideDetailWithBLOBs
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月5日 上午12:29:37
 */
public class VideoDetailVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2422386091578635021L;

	private Video video ;
	
	private VideoDetailWithBLOBs videoDetail ;

	public VideoDetailVo() {}

	public VideoDetailVo(Video video, VideoDetailWithBLOBs videoDetail) {
		this.video = video;
		this.videoDetail = videoDetail;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public VideoDetailWithBLOBs getVideoDetail() {
		return videoDetail;
	}

	public void setVideoDetail(VideoDetailWithBLOBs videoDetail) {
		this.videoDetail = videoDetail;
	}
	
}
