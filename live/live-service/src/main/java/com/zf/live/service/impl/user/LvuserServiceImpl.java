package com.zf.live.service.impl.user;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zf.live.client.user.LvuserService;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.common.validate.LoginName;
import com.zf.live.common.validate.Notnull;
import com.zf.live.dao.mapper.LvuserMapperExt;
import com.zf.live.dao.pojo.Lvuser;
import com.zf.live.dao.pojo.LvuserExample;

@Component("lvuserService")
public class LvuserServiceImpl implements LvuserService{

	@Autowired
	private LvuserMapperExt lvuserMapper ;
	
	@Resource(name="defaultIdxcodeGenerator")
	private DefaultIdxcodeGenerator defaultIdxcodeGenerator ;

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
	public boolean existLoginname(String loginname) {
		Integer userCount = lvuserMapper.countLoginname(loginname) ;
		return userCount == null ? false : userCount > 0 ;
	}
	
	@Override
	public boolean existIdxcode(String idxcode) {
		Integer userCount = lvuserMapper.countIdxcode(idxcode);
		return userCount == null ? false : userCount > 0 ;
	}

	@Override
	public ServiceResult<Long> regist(
			@Notnull @LoginName("loginname") @Notnull("nick") @Notnull("password")
			Lvuser user 
			) {
		ServiceResult<Long> result = new ServiceResult<Long>() ;
		user.setCoin(0L);
		user.setCreatetime(new Date());
		user.setFlag(0);
		user.setIdxcode(defaultIdxcodeGenerator.generate()); 
		int userId =lvuserMapper.insertSelective(user) ;
		if(userId > 0){
			result.setSuccess(true);
		}else{
			result.setSuccess(false);
			result.setErrMssage("创建用户失败！");
			result.setData(user.getId());
		}
		return result;
	}

	@Override
	public ServiceResult<String> loginNameValidate() {
		return null;
	}
	
	@Override
	public Lvuser selectByIdxcode(String idxcode) {
		return null;
	}


}
