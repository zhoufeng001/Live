package com.zf.live.dao.mapper;

import com.zf.live.dao.pojo.Roomsetting;
import com.zf.live.dao.pojo.RoomsettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomsettingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomsetting
     *
     * @mbggenerated Sun Dec 14 04:25:39 CST 2014
     */
    int countByExample(RoomsettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomsetting
     *
     * @mbggenerated Sun Dec 14 04:25:39 CST 2014
     */
    int deleteByExample(RoomsettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomsetting
     *
     * @mbggenerated Sun Dec 14 04:25:39 CST 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomsetting
     *
     * @mbggenerated Sun Dec 14 04:25:39 CST 2014
     */
    int insert(Roomsetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomsetting
     *
     * @mbggenerated Sun Dec 14 04:25:39 CST 2014
     */
    int insertSelective(Roomsetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomsetting
     *
     * @mbggenerated Sun Dec 14 04:25:39 CST 2014
     */
    List<Roomsetting> selectByExample(RoomsettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomsetting
     *
     * @mbggenerated Sun Dec 14 04:25:39 CST 2014
     */
    Roomsetting selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomsetting
     *
     * @mbggenerated Sun Dec 14 04:25:39 CST 2014
     */
    int updateByExampleSelective(@Param("record") Roomsetting record, @Param("example") RoomsettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomsetting
     *
     * @mbggenerated Sun Dec 14 04:25:39 CST 2014
     */
    int updateByExample(@Param("record") Roomsetting record, @Param("example") RoomsettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomsetting
     *
     * @mbggenerated Sun Dec 14 04:25:39 CST 2014
     */
    int updateByPrimaryKeySelective(Roomsetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roomsetting
     *
     * @mbggenerated Sun Dec 14 04:25:39 CST 2014
     */
    int updateByPrimaryKey(Roomsetting record);
}