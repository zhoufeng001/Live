package com.zf.live.client.video.local;

import java.util.List;

import com.zf.live.client.vo.ServiceResult;
import com.zf.live.dao.pojo.Video;
import com.zf.live.dao.pojo.VideoGroup;

/**
 * 本地视频专辑服务
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月10日 上午11:26:37
 */
public interface LocalVideoGroupService {

	/**
	 * 保存专辑到本地
	 * @param videoGroup
	 * @return
	 */
	ServiceResult<Long> saveVideoGroup(VideoGroup videoGroup);
	
	/**
	 * 判断第三方视频专辑在本地是否已经存在
	 * @param videoSite
	 * @param thirdGroupId
	 * @return
	 */
	Boolean existThirdVideoGroup(Byte videoFrom ,String thirdGroupId);
	
	
	/**
	 * 在本地查询第三方平台上的视频，如果本地存在则返回，否则返回null
	 * @param videoSite
	 * @param thirdGroupId
	 * @return
	 */ 
	ServiceResult<VideoGroup> selectThirdVideoGroup(Byte videoFrom ,String thirdGroupId);
	
	/**
	 * 根据本地视频专辑ID判断 专辑是否存在
	 * @param groupId
	 * @return
	 */
	Boolean existLocalVideoGroup(Long groupId);
	
	/**
	 * 根据本地视频专辑ID判断 获取专辑信息
	 * @param groupId
	 * @return
	 */
	VideoGroup selectLocalVideoGroup(Long groupId);
	
	/**
	 * 根据本地专辑ID该专辑的所有视频集合
	 * @param groupId
	 * @return
	 */
	List<Video> getVideosByGroupId(Long groupId);
	
	
	
}
