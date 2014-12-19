package com.zf.live.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zf.live.client.user.LvuserService;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.common.validate.IsInteger;
import com.zf.live.common.validate.Notnull;
import com.zf.live.dao.mapper.LvuserMapperExt;
import com.zf.live.dao.pojo.Lvuser;
import com.zf.live.dao.pojo.LvuserExample;

@Component("lvuserService")
public class LvuserServiceImpl implements LvuserService{
	
    @Autowired
    private LvuserMapperExt lvuserMapper ;

	@Override
	public Lvuser selectById(Integer id) {
		return lvuserMapper.selectRandom() ;
	}
	
	@Override
	public Lvuser selectByLoginname(String loginname) {
		if(loginname == null){
			return null ;
		}
		LvuserExample query = new LvuserExample() ;
		query.createCriteria().andLoginnameEqualTo(loginname) ;
		List<Lvuser> results = lvuserMapper.selectByExample(query) ;
		if(results == null){
			return null ;
		}
		return results.get(0);   
	}
	
	@Override
	public boolean exist(String loginname) {
		Integer userCount = lvuserMapper.count(loginname) ;
		return userCount == null ? false : userCount > 0 ;
	}

	@Override
	public ServiceResult<Long> regist(
			@Notnull @Notnull("loginname") @Notnull("nick") @Notnull("password")
			@IsInteger(value = "idxcode", validateValue = true , maxValue = 100 , minValue = 30)
			Lvuser user 
			) {
		ServiceResult<Long> result = new ServiceResult<Long>() ;
		if(user == null){
			result.setErrMssage("user不能为空");
			return result ;
		}
		if(user.getLoginname() == null  ){
			
		}
		return result;
	}
	
	@Override
	public ServiceResult<String> loginNameValidate() {
		return null;
	}
    
	
}
