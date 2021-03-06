package com.zf.live.dao.mapper;

import com.zf.live.dao.pojo.Video;
import com.zf.live.dao.pojo.VideoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VideoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    int countByExample(VideoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    int deleteByExample(VideoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    int insert(Video record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    int insertSelective(Video record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    List<Video> selectByExample(VideoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    Video selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    int updateByExampleSelective(@Param("record") Video record, @Param("example") VideoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    int updateByExample(@Param("record") Video record, @Param("example") VideoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    int updateByPrimaryKeySelective(Video record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    int updateByPrimaryKey(Video record);
}