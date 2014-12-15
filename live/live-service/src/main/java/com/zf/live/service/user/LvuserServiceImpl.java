package com.zf.live.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zf.live.client.user.LvuserService;
import com.zf.live.dao.mapper.LvuserMapperExt;
import com.zf.live.dao.pojo.Lvuser;

@Component("lvuserService")
public class LvuserServiceImpl implements LvuserService{

    @Autowired
    private LvuserMapperExt lvuserMapper ;
    
    @Override
    public Lvuser selectUserById(Integer id) {
	return lvuserMapper.selectRandom() ;
    }

}
