package com.zf.live.dao.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * 该类用于扩展com.zf.live.dao.mapper.VideoDetailMapper接口
 * by is_zhoufeng@163.com 2015-01-05 00:14:43
 */
public interface VideoDetailMapperExt extends VideoDetailMapper {
	
	
	public Integer countVideoDetail(@Param("videofrom") Byte videofrom, @Param("fromId") String fromId);
	
	public String selectVideoDetailId(@Param("videofrom") Byte videofrom, @Param("fromId") String fromId);

}