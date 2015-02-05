package com.zf.live.client.vo.video.local;

import java.io.Serializable;

import com.zf.live.dao.pojo.LocalVideo;
import com.zf.live.dao.pojo.VideoDetailWithBLOBs;

/**
 * 视频详细信息，包含 Video 与 VideDetailWithBLOBs
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月5日 上午12:29:37
 */
public class LocalVideoDetailVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2422386091578635021L;

	private LocalVideo video ;
	
	private VideoDetailWithBLOBs videoDetail ;

	public LocalVideoDetailVo() {}

	public LocalVideoDetailVo(LocalVideo video, VideoDetailWithBLOBs videoDetail) {
		this.video = video;
		this.videoDetail = videoDetail;
	}

	public LocalVideo getVideo() {
		return video;
	}

	public void setVideo(LocalVideo video) {
		this.video = video;
	}

	public VideoDetailWithBLOBs getVideoDetail() {
		return videoDetail;
	}

	public void setVideoDetail(VideoDetailWithBLOBs videoDetail) {
		this.videoDetail = videoDetail;
	}
	
}
