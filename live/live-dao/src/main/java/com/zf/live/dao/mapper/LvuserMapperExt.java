package com.zf.live.dao.mapper;

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

}