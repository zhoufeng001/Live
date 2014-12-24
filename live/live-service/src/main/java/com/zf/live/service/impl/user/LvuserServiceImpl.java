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
import com.zf.live.dao.vo.Page;
import com.zf.live.service.impl.cache.user.UserCacheService;
import com.zf.live.service.impl.util.TokenFactory;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月21日 下午6:45:58
 */
@Component("lvuserService")
public class LvuserServiceImpl implements LvuserService{
	
	@Autowired
	private UserCacheService userCacheService;

	@Autowired
	private LvuserMapperExt lvuserMapper ;

	@Autowired
	private LvuserinfoMapperExt lvuserinfoMapper ;
	
	@Override
	public Lvuser selectByIdWithCache(@Notnull Long id) {
		Lvuser lvuser = userCacheService.getCacheUserById(id);
		if(lvuser != null){
			return lvuser ;
		}
		lvuser = lvuserMapper.selectByPrimaryKey(id);
		userCacheService.putUserInfo(lvuser); 
		return lvuser ;
	}
	
	@Override
	public Lvuser selectByLoginname(@Notnull String loginname) {
		LvuserExample query = new LvuserExample() ;
		query.createCriteria().andLoginnameEqualTo(loginname) ;
		query.setPage(new Page(0, 1));
		List<Lvuser> results = lvuserMapper.selectByExample(query) ;
		if(results == null){
			return null ;
		}
		return results.get(0);   
	}
	
	@Override
	public Lvuser selectByMail(String mail) {
		LvuserExample query = new LvuserExample() ;
		query.createCriteria().andMailEqualTo(mail) ;
		query.setPage(new Page(0, 1));
		List<Lvuser> results = lvuserMapper.selectByExample(query) ;
		if(results == null){
			return null ;
		}
		return results.get(0);   
	}

	@Override
	public Lvuser selectByPhone(String phone) {
		LvuserExample query = new LvuserExample() ;
		query.createCriteria().andPhoneEqualTo(phone) ;
		query.setPage(new Page(0, 1));
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
	public boolean existMail(String mail) {
		Integer userCount = lvuserMapper.countMail(mail) ;
		return userCount == null ? false : userCount > 0 ;
	}


	@Override
	public ServiceResult<Long> regist(
			@Notnull("loginname") @Notnull("nick") @Notnull("password")
			@LoginName("loginname") 
			Lvuser user, 
			@Notnull 
			IdxcodeGenerator idxcodeGenerator
			) {
		ServiceResult<Long> result = new ServiceResult<Long>() ;
		
		boolean existLoginname = existLoginname(user.getLoginname());
		if(existLoginname){
			result.setErrMssage("登录名[" + user.getLoginname() + "]已存在");
			return result ;
		}
		
		String idxcode = idxcodeGenerator.generate() ;
		boolean existIdxcode = existIdxcode(idxcode) ;
		if(existIdxcode){
			result.setErrMssage("Idxcode" + user.getIdxcode() + "已存在");
			return result ;
		}
		
		Date currentTime = new Date();
		user.setCoin(0L);
		user.setCreatetime(currentTime);
		user.setFlag(0);
		user.setIdxcode(idxcode); 
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
			result.setErrMssage("创建用户失败!");
		}
		return result;
	}

	@Override
	public Lvuser selectByIdxcode(@Notnull String idxcode) {
		LvuserExample query = new LvuserExample() ;
		query.createCriteria().andIdxcodeEqualTo(idxcode) ;
		query.setPage(new Page(0, 1));
		List<Lvuser> results = lvuserMapper.selectByExample(query) ;
		if(results == null){
			return null ;
		}
		return results.get(0);  
	}

	@Override
	public ServiceResult<String> login4Platform(@Notnull String userKey, @Notnull String secret) {
		ServiceResult<String> result = new ServiceResult<String>();
		Lvuser lvuser = null ;
		if(userKey.matches("^\\d+$")){ //手机号
			lvuser = selectByPhone(userKey) ;
		}else if(userKey.matches(".+?@.+")){ //邮箱
			lvuser = selectByMail(userKey);
		}else{ //登录名
			lvuser = selectByLoginname(userKey);
		}
		if(lvuser == null){
			result.setErrMssage("用户不存在");
			return result ;
		}
		
		if(!lvuser.getPassword().equals(secret)){
			result.setErrMssage("用户名错误或密码不正确");
			return result ;
		}
		
		String token = TokenFactory.newToken() ;
		userCacheService.putLoginUserInfo(token, lvuser);
		result.setSuccess(true);
		result.setData(token);
		return result;
	}

	@Override
	public Lvuser getUserByToken(String token) {
		Long userid = userCacheService.getUserIdByToken(token);
		if(userid == null || userid.longValue() <= 0){
			return null ;
		}
		return userCacheService.getCacheUserById(userid); 
	}

	@Override
	public void logout(Long userid) {
		userCacheService.removeLoginUserInfo(userid); 
	}

	@Override
	public boolean isLogin(Long userid) {
		return userCacheService.getTokenByUserId(userid) != null ;
	} 


}
