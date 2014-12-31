package com.zf.live.service.impl.user;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zf.live.client.exception.LiveException;
import com.zf.live.client.user.LvuserService;
import com.zf.live.client.vo.ServiceResult;
import com.zf.live.common.Const;
import com.zf.live.common.util.FlagBitUtil;
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

	private static final Logger log = LoggerFactory.getLogger(LvuserServiceImpl.class);

	@Autowired
	private UserCacheService userCacheService;

	@Autowired
	private LvuserMapperExt lvuserMapper ;

	@Autowired
	private LvuserinfoMapperExt lvuserinfoMapper ;

	@Override
	public Lvuser selectByIdWithCache(@Notnull Long id) {
		log.info("selectByIdWithCache...");
		Lvuser lvuser = userCacheService.getCacheUserById(id);
		if(lvuser != null){
			return lvuser ;
		}
		lvuser = lvuserMapper.selectByPrimaryKey(id);
		if(lvuser == null){
			return null ;
		}
		userCacheService.putUserInfo(lvuser); 
		return lvuser ;
	}

	@Override
	public Lvuser selectByLoginname(@Notnull String loginname) {
		LvuserExample query = new LvuserExample() ;
		query.createCriteria().andLoginnameEqualTo(loginname) ;
		query.setPage(new Page(0, 1));
		List<Lvuser> results = lvuserMapper.selectByExample(query) ;
		if(results == null || results.size() == 0){
			return null ;
		}
		return results.get(0);   
	}

	@Override
	public Lvuser selectByMail(@Notnull String mail) {
		LvuserExample query = new LvuserExample() ;
		query.createCriteria().andMailEqualTo(mail) ;
		query.setPage(new Page(0, 1));
		List<Lvuser> results = lvuserMapper.selectByExample(query) ;
		if(results == null || results.size() == 0){
			return null ;
		}
		return results.get(0);   
	}

	@Override
	public Lvuser selectByPhone(@Notnull String phone) {
		LvuserExample query = new LvuserExample() ;
		query.createCriteria().andPhoneEqualTo(phone) ;
		query.setPage(new Page(0, 1));
		List<Lvuser> results = lvuserMapper.selectByExample(query) ;
		if(results == null || results.size() == 0){
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
	public boolean existMail(@Notnull String mail) {
		Integer userCount = lvuserMapper.countMail(mail) ;
		return userCount == null ? false : userCount > 0 ;
	}


	@Override
	public ServiceResult<Long> regist(
			@Notnull("loginname") @Notnull("nick") @Notnull("password") @Notnull("idxcode") @Notnull("userfrom")
			@LoginName("loginname") 
			Lvuser user
			) {
		ServiceResult<Long> result = new ServiceResult<Long>() ;

		boolean existLoginname = existLoginname(user.getLoginname());
		if(existLoginname){
			result.setErrMssage("登录名[" + user.getLoginname() + "]已存在");
			return result ;
		}

		boolean existIdxcode = existIdxcode(user.getIdxcode()) ;
		if(existIdxcode){
			result.setErrMssage("Idxcode" + user.getIdxcode() + "已存在");
			return result ;
		}

		Date currentTime = new Date();
		user.setCoin(0L);
		user.setCreatetime(currentTime);
		user.setFlag(0);
		user.setUserfrom(Const.UserConst.USER_FROM_PLATEFORM);
		user.setStatus(Const.UserConst.USER_STATUS_ENABLE);
		user.setPraise(0L);
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
				log.error("创建用户信息失败！");
				throw new LiveException("创建用户详细信息失败！");
			}
		}else{
			log.error("创建用户信息失败！");
			result.setErrMssage("创建用户失败!");
		}
		return result;
	}


	@Override
	public ServiceResult<Long> regist4Third(
			@Notnull("nick") @Notnull("idxcode") @Notnull("userfrom") @Notnull("oauthid")
			Lvuser user) {

		user.setLoginname(null);
		user.setPassword(null);
		ServiceResult<Long> result = new ServiceResult<Long>() ;
		Lvuser existUser = selectByThirdInfo(user.getUserfrom() ,user.getOauthid() );
		if(existUser != null){
			result.setErrMssage("该用户已注册！");
			return result ;
		}

		Date currentTime = new Date();
		user.setCoin(0L);
		user.setCreatetime(currentTime);
		user.setFlag(0);
		user.setPraise(0L); 
		
		//打上未设置用户名和密码标
		int userFlag = user.getFlag() ;
		userFlag = FlagBitUtil.sign(userFlag, Const.UserConst.UserFlag.notSetLoginnameAndPasswordFlag) ;
		user.setFlag(userFlag);

		user.setStatus(Const.UserConst.USER_STATUS_ENABLE); 
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
				log.error("创建用户信息失败！");
				throw new LiveException("创建用户详细信息失败！");
			}
		}else{
			log.error("创建用户信息失败！");
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
		if(results == null || results.size() == 0){
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
	public ServiceResult<String> login4Third(@Notnull Byte from, @Notnull String openid) {
		ServiceResult<String> result = new ServiceResult<String>();
		Lvuser lvuser = selectByThirdInfo(from, openid) ;
		if(lvuser == null){
			result.setErrMssage("用户不存在！");
			return result;
		}
		String token = TokenFactory.newToken() ;
		userCacheService.putLoginUserInfo(token, lvuser);
		result.setSuccess(true);
		result.setData(token);
		return result;
	}



	@Override
	public Lvuser getUserByToken(@Notnull String token) {
		Long userid = userCacheService.getUserIdByToken(token);
		if(userid == null || userid.longValue() <= 0){
			return null ;
		}
		return userCacheService.getCacheUserById(userid); 
	}

	@Override
	public void logoutByUserid(@Notnull Long userid) {
		userCacheService.removeLoginUserInfo(userid); 
	}

	@Override
	public void logoutByToken(@Notnull String token) {
		userCacheService.removeLoginUserInfo(token);
	} 

	@Override
	public boolean isLogin(@Notnull Long userid) {
		return userCacheService.getTokenByUserId(userid) != null ;
	}

	@Override
	public Lvuser selectByThirdInfo(@Notnull Byte thirdType,@Notnull String thirdId) {
		LvuserExample query = new LvuserExample() ;
		query.createCriteria().andUserfromEqualTo(thirdType).andOauthidEqualTo(thirdId);
		query.setPage(new Page(0, 1));
		List<Lvuser> user = lvuserMapper.selectByExample(query);
		if(user == null || user.size() <= 0){
			return null ;
		}
		return user.get(0); 
	}
}
