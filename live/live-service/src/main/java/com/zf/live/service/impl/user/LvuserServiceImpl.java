package com.zf.live.service.impl.user;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zf.live.client.exception.LiveException;
import com.zf.live.client.user.IdxcodeGenerator;
import com.zf.live.client.user.LvuserService;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.common.validate.LoginName;
import com.zf.live.common.validate.Notnull;
import com.zf.live.dao.mapper.LvuserMapperExt;
import com.zf.live.dao.mapper.LvuserinfoMapperExt;
import com.zf.live.dao.pojo.Lvuser;
import com.zf.live.dao.pojo.LvuserExample;
import com.zf.live.dao.pojo.Lvuserinfo;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月21日 下午6:45:58
 */
@Component("lvuserService")
public class LvuserServiceImpl implements LvuserService{

	@Autowired
	private LvuserMapperExt lvuserMapper ;

	@Autowired
	private LvuserinfoMapperExt lvuserinfoMapper ;

	@Override
	public Lvuser selectById(Integer id) {
		return lvuserMapper.selectRandom() ;
	}

	@Override
	public Lvuser selectByLoginname(@Notnull String loginname) {
		LvuserExample query = new LvuserExample() ;
		query.createCriteria().andLoginnameEqualTo(loginname) ;
		List<Lvuser> results = lvuserMapper.selectByExample(query) ;
		if(results == null){
			return null ;
		}
		return results.get(0);   
	}

	@Override
	public boolean existLoginname(@Notnull String loginname) {
		Integer userCount = lvuserMapper.countLoginname(loginname) ;
		return userCount == null ? false : userCount > 0 ;
	}

	@Override
	public boolean existIdxcode(@Notnull String idxcode) {
		Integer userCount = lvuserMapper.countIdxcode(idxcode);
		return userCount == null ? false : userCount > 0 ;
	}

	@Override
	public ServiceResult<Long> regist(
			@Notnull @LoginName("loginname") @Notnull("nick") @Notnull("password")
			Lvuser user, 
			@Notnull 
			IdxcodeGenerator idxcodeGenerator
			) {
		ServiceResult<Long> result = new ServiceResult<Long>() ;
		Date currentTime = new Date();
		user.setCoin(0L);
		user.setCreatetime(currentTime);
		user.setFlag(0);
		user.setIdxcode(idxcodeGenerator.generate()); 
		int inserResult =lvuserMapper.insertSelective(user) ;
		if(inserResult > 0){
			Lvuserinfo lvuserinfo = new Lvuserinfo() ;
			lvuserinfo.setId(user.getId());
			lvuserinfo.setCreatetime(currentTime);
			inserResult = lvuserinfoMapper.insert(lvuserinfo) ;
			if(inserResult > 0){
				result.setSuccess(true);
				result.setData(user.getId());
				return result ;
			}else{
				throw new LiveException("创建用户详细信息失败！");
			}
		}else{
			result.setSuccess(false);
			result.setErrMssage("创建用户失败!");
		}
		return result;
	}

	@Override
	public Lvuser selectByIdxcode(@Notnull String idxcode) {
		LvuserExample query = new LvuserExample() ;
		query.createCriteria().andIdxcodeEqualTo(idxcode) ;
		List<Lvuser> results = lvuserMapper.selectByExample(query) ;
		if(results == null){
			return null ;
		}
		return results.get(0);  
	}


}
