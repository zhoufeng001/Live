package com.zf.live.client.video.local;

import com.zf.live.client.vo.ServiceResult;
import com.zf.live.client.vo.paging.PagedVo;
import com.zf.live.client.vo.video.local.LocalVideoDetailVo;
import com.zf.live.client.vo.video.local.LocalVideoSearchConditionV2;
import com.zf.live.dao.pojo.LocalVideo;
import com.zf.live.dao.pojo.VideoDetailWithBLOBs;

/**
 * 本地视频相关操作
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月5日 上午12:23:36
 */
public interface LocalVideoServiceV2 {

	/**
	 * 保存视频信息到数据库（只保存基本信息，不包括详细信息）
	 * @param video
	 * @return
	 */
	ServiceResult<String> saveVideo(LocalVideo video);
	
	/**
	 * 根据本地视频id查询本地视频
	 * @param localVideoId
	 * @param category  视频所属分类，可以为空
	 * @return
	 */
	LocalVideo selectVideoById(String localVideoId);
	
	/**
	 * 更新视频信息
	 * 分类不可为空，并且不可编辑
	 * @return
	 */
	boolean updateVideoBySelective(LocalVideo video);

	/**
	 * 保存视频详细信息
	 * 分类不可为空
	 * @param videoDetail
	 * @return
	 */
	ServiceResult<String> saveVideoDetail(VideoDetailWithBLOBs videoDetail);

	/**
	 * 保存视频基本信息以及详细信息
	 * 视频分类不可为空
	 * @param videoDetailVo
	 * @return
	 */
	ServiceResult<String> saveVideoWithDetail(LocalVideoDetailVo localVideoDetailVo);
	
	/**
	 * 判断第三方的某个视频是否存在
	 * @param videofrom
	 * @param fromId
	 * @param category 分类，可以为空
	 * @return
	 */
	Boolean existVideo(String category , Byte videofrom , String fromId);
	
	
	/**
	 * 根据第三方信息，从本地搜索视频
	 * @param videofrom
	 * @param fromId
	 * @param category 分类，可以为空
	 * @return
	 */
	LocalVideo selectVideo(String category , Byte videofrom,String fromId) ;
	
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
	 * @param category 分类，可以为空
	 * @return
	 */
	String selectVideoDetailId(String category ,Byte videofrom,String fromId) ;
	
	/**
	 * 根据视频本地ID查询视频基本信息以及详细信息
	 * @param videoId
	 * @param category 分类，可以为空
	 * @return
	 */
	ServiceResult<LocalVideoDetailVo> selectVideoWithDetailInfo(String videoId, boolean incrementViewCount);
	
	/**
	 * 根据条件搜索本地视频列表
	 * @param condition
	 * @return
	 */
	PagedVo<LocalVideo> searchVideos(LocalVideoSearchConditionV2 condition);
	
}
