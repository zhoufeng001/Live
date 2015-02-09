package com.zf.live.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	public LocalVideoVo selectFromSpecificTableById(@Param("id") String id , @Param("table") String table);

	/**
	 * 根据id从特定的表修改localVideo信息
	 * @param video
	 * @param table
	 * @return
	 */
	public int updateByPrimaryKeySelectiveToSpecificTable(LocalVideoVo video) ;
	
	/**
	 * 从特定的表去搜索视频列表（不能根据在线人数排序）
	 * @param example
	 * @param table
	 * @return
	 */
	public List<LocalVideoVo> selectByExampleFromSpecificTable(LocalVideoExampleVo example) ;
	
	/**
	 * 从特定的表查询视频数量
	 * @param example
	 * @return
	 */
	public Integer countByExampleFromSpecificTable(LocalVideoExampleVo example);
	
	/**
	 * 根据分类查询视频列表（根据在线人数排序）
	 * @return
	 */
	public List<LocalVideoVo> selectByCategoryOrderByAudienceCount(@Param("table") String table ,
			@Param("category")String category , 
			@Param("begin")int begin , @Param("length")int length) ;
	
	/**
	 * 根据分类查询该分类存在观众的视频总数量
	 * @return
	 */
	public Integer countByCategoryFromAudienceCounte(@Param("category")String category ) ;

	
}