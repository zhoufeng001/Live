package com.zf.live.dao.mapper;

import java.util.List;

import com.zf.live.client.vo.video.local.LocalVideoCategorySearchCondition;
import com.zf.live.dao.pojo.Video;

/**
 * 该类用于扩展com.zf.live.dao.mapper.VideoMapper接口
 * by is_zhoufeng@163.com 2015-01-05 00:14:43
 */
public interface VideoMapperExt extends VideoMapper {
	
	/**
	 * 根据分类搜索本地视频
	 * @param condition
	 * @return
	 */
	List<Video> selectByCategory(LocalVideoCategorySearchCondition condition);
	
	/**
	 * 根据分类搜索视频数量
	 * @param condition
	 * @return
	 */
	Integer selectCountByCategory(LocalVideoCategorySearchCondition condition);
	
}