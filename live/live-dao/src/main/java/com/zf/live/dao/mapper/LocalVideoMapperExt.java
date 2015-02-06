package com.zf.live.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.zf.live.dao.pojo.LocalVideo;

/**
 * 该类用于扩展com.zf.live.dao.mapper.LocalVideoMapper接口
 * by is_zhoufeng@163.com 2015-02-05 22:36:26
 */
public interface LocalVideoMapperExt extends LocalVideoMapper {
	
	/**
	 * 将视频存入特定的表
	 * @param video
	 * @param table
	 */
	public int saveToSpecificTable(@Param("video")LocalVideo video , @Param("table")String table);
	
	
	/**
	 * 根据视频id从特定的表查询视频
	 * @param id
	 * @param table
	 * @return
	 */
	public LocalVideo selectFromSpecificTableById(@Param("id") String id , @Param("table") String table);
	
}