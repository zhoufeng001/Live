package com.zf.live.dao.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * 该类用于扩展com.zf.live.dao.mapper.AudienceCountMapper接口
 * by is_zhoufeng@163.com 2015-02-08 18:55:47
 */
public interface AudienceCountMapperExt extends AudienceCountMapper {
	
	/**
	 * 修改登录用户人数
	 * @param id
	 * @param count 可为负数
	 * @return
	 */
	int incrementUserCount(@Param("id") String id , @Param("count")int count);
	
	/**
	 * 修改游客用户人数
	 * @param id
	 * @param count 可为负数
	 * 游客人数+1
	 */
	int incrementTouristCount(@Param("id") String id , @Param("count") int count);
	
	/**
	 * 根据视频id查询观众数量（登录人数+游客）
	 * @param id
	 * @return
	 */
	Integer selectAudienceCount(@Param("id")String id);
	
	
	/**
	 * 根据视频id查询登录人数
	 * @param id
	 * @return
	 */
	Integer selectUserCount(@Param("id")String id);
	
	/**
	 * 根据视频id查询游客人数
	 * @param id
	 * @return
	 */
	Integer selectTouristCount(@Param("id")String id);
	
	
	
}