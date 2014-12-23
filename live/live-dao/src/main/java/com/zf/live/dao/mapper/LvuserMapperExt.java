package com.zf.live.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.zf.live.dao.pojo.Lvuser;

/**
 * 该类用于扩展com.zf.live.dao.mapper.LvuserMapper接口
 * by is_zhoufeng@163.com 2014-12-15 17:32:50
*/
public interface LvuserMapperExt extends LvuserMapper {
	

    /**
     * 随机查询
     * @return
     */
    public Lvuser selectRandom();
    
    /**
     * 根据loginname查询用户数量
     * @return
     */
    public Integer countLoginname(@Param("loginname") String loginname); 
    
    /**
     * 根据idxcode查询用户数量
     * @return
     */
    public Integer countIdxcode(@Param("idxcode") String idxcode); 
    
    /**
     * 根据idxcode查询用户数量
     * @return
     */
    public Integer countMail(@Param("mail") String mail); 

}