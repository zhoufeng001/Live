package com.zf.live.client.video.local;

import com.zf.live.client.vo.ServiceResult;
import com.zf.live.client.vo.paging.PagedVo;
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
	 * 根据本地视频id查询本地视频
	 * @param localVideoId
	 * @return
	 */
	Video selectVideoById(Long localVideoId);
	
	/**
	 * 更新视频信息
	 * @return
	 */
	boolean updateVideoBySelective(Video video);

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
	 * 根据第三方信息，从本地搜索视频
	 * @param videofrom
	 * @param fromId
	 * @return
	 */
	Video selectVideo(Byte videofrom,String fromId) ;
	
	/**
	 * 判断第三方视频详情信息是否存在于本地
	 * @param videofrom
	 * @param fromId
	 * @return
	 */
	boolean existVideoDetail(Byte videofrom,String fromId) ;
	
	/**
	 * 根据第三方信息，从本地搜索视频详情，并返回其ID，如果本地不存在，则返回Null
	 * @param videofrom
	 * @param fromId
	 * @return
	 */
	Long selectVideoDetailId(Byte videofrom,String fromId) ;
	
	/**
	 * 根据视频本地ID查询视频基本信息以及详细信息
	 * @param videoId
	 * @return
	 */
	ServiceResult<VideoDetailVo> selectVideoWithDetailInfo(Long videoId, boolean incrementViewCount);
	
	/**
	 * 根据条件搜索本地视频列表
	 * @param condition
	 * @return
	 */
	PagedVo<Video> searchVideos(LocalVideoSearchCondition condition);
	
	/**
	 * 根据视频分类分页搜索视频
	 * @param condition
	 * @param searchTotalCount 是否查询总记录条数
	 * @return
	 */
	PagedVo<Video> searchVideosByCategories(LocalVideoSearchCondition condition, boolean searchTotalCount);
	
}
