package com.zf.live.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zf.live.dao.pojo.LocalVideo;
import com.zf.live.dao.pojo.vo.video.LocalVideoExampleVo;
import com.zf.live.dao.vo.video.LocalVideoVo;

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
	public int saveToSpecificTable(LocalVideoVo video);
	
	
	/**
	 * 根据视频id从特定的表查询视频
	 * @param id
	 * @param table
	 * @return
	 */
	public LocalVideo selectFromSpecificTableById(@Param("id") String id , @Param("table") String table);

	/**
	 * 根据id从特定的表修改localVideo信息
	 * @param video
	 * @param table
	 * @return
	 */
	public int updateByPrimaryKeySelectiveToSpecificTable(LocalVideoVo video) ;
	
	/**
	 * 从特定的表去搜索视频列表
	 * @param example
	 * @param table
	 * @return
	 */
	public List<LocalVideo> selectByExampleFromSpecificTable(LocalVideoExampleVo example) ;
	
	/**
	 * 从特定的表查询视频数量
	 * @param example
	 * @return
	 */
	public Integer countByExampleFromSpecificTable(LocalVideoExampleVo example);
	
}