package com.zf.live.dao.mapper;

import com.zf.live.dao.pojo.Buyrecored;
import com.zf.live.dao.pojo.BuyrecoredExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuyrecoredMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table buyrecored
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    int countByExample(BuyrecoredExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table buyrecored
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    int deleteByExample(BuyrecoredExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table buyrecored
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table buyrecored
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    int insert(Buyrecored record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table buyrecored
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    int insertSelective(Buyrecored record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table buyrecored
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    List<Buyrecored> selectByExample(BuyrecoredExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table buyrecored
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    Buyrecored selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table buyrecored
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    int updateByExampleSelective(@Param("record") Buyrecored record, @Param("example") BuyrecoredExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table buyrecored
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    int updateByExample(@Param("record") Buyrecored record, @Param("example") BuyrecoredExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table buyrecored
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    int updateByPrimaryKeySelective(Buyrecored record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table buyrecored
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    int updateByPrimaryKey(Buyrecored record);
}