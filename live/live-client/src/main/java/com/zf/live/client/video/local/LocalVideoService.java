package com.zf.live.client.video.local;

import java.util.List;

import com.zf.live.client.vo.ServiceResult;
import com.zf.live.client.vo.video.local.LocalVideoSearchCondition;
import com.zf.live.client.vo.video.local.VideoDetailVo;
import com.zf.live.dao.pojo.Video;
import com.zf.live.dao.pojo.VideoDetailWithBLOBs;

/**
 * 本地视频相关操作
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月5日 上午12:23:36
 */
public interface LocalVideoService {

	/**
	 * 保存视频信息到数据库（只保存基本信息，不包括详细信息）
	 * @param video
	 * @return
	 */
	ServiceResult<Long> saveVideo(Video video);

	/**
	 * 保存视频详细信息
	 * @param videoDetail
	 * @return
	 */
	ServiceResult<Long> saveVideoDetail(VideoDetailWithBLOBs videoDetail);

	/**
	 * 保存视频基本信息以及详细信息
	 * @param videoDetailVo
	 * @return
	 */
	ServiceResult<Long> saveVideoWithDetail(VideoDetailVo videoDetailVo);
	
	/**
	 * 判断第三方的某个视频是否存在
	 * @param videofrom
	 * @param fromId
	 * @return
	 */
	Boolean existVideo(Byte videofrom , String fromId);
	
	/**
	 * 根据视频本地ID查询视频基本信息以及详细信息
	 * @param videoId
	 * @return
	 */
	ServiceResult<VideoDetailVo> selectVideoWithDetailInfo(Long videoId);
	
	/**
	 * 根据条件搜索本地视频列表
	 * @param condition
	 * @return
	 */
	ServiceResult<List<Video>> searchVideos(LocalVideoSearchCondition condition);
	
}
